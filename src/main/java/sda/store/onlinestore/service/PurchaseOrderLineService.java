package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.*;
import sda.store.onlinestore.model.responseBody.PurchaseOrderTotalCostResponse;
import sda.store.onlinestore.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseOrderLineService {
    private PurchaseOrderRepository purchaseOrderRepository;
    private PurchaseOrderLineRepository purchaseOrderLineRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private ProductQuantityRepository productQuantityRepository;

    public PurchaseOrderLine addProductToPurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO) {
        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();

        Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchaseOrderLineDTO.getPurchaseOrderId());
        PurchaseOrder purchaseOrder = purchaseOrderOpt.orElseThrow(() -> new RuntimeException("Purchase order was not found"));
        purchaseOrderLine.setPurchaseOrder(purchaseOrder);

        Optional<Product> productOpt = productRepository.findById(purchaseOrderLineDTO.getProductId());
        Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));
        purchaseOrderLine.setProduct(product);

        purchaseOrderLine.setQuantity(purchaseOrderLineDTO.getQuantity());
        purchaseOrderLineRepository.save(purchaseOrderLine);
        return purchaseOrderLine;
    }

    public void performOrderLineCreationActions(Long purchase_order_id){
        createOrderLinesFromCart(purchase_order_id);

        Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchase_order_id);
        PurchaseOrder purchaseOrder = purchaseOrderOpt.orElseThrow(() ->
                new RuntimeException("Purchase order was not found ID="+purchase_order_id));

        reduceProductQuantitiesOnDate(purchaseOrder.getOrderDate());
        clearCart();
    }

    public void createOrderLinesFromCart(Long purchase_order_id) {
        List<Cart> carts = cartRepository.findAll();
        List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();
        for (Cart cart: carts) {
            PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();

            Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchase_order_id);
            PurchaseOrder purchaseOrder = purchaseOrderOpt.orElseThrow(() -> new RuntimeException("Purchase order was not found."));

            purchaseOrderLine.setPurchaseOrder(purchaseOrder);
            purchaseOrderLine.setProduct(cart.getProduct());
            purchaseOrderLine.setQuantity(cart.getQuantity());

            purchaseOrderLineRepository.save(purchaseOrderLine);
            purchaseOrderLines.add(purchaseOrderLine);
        }
    }

    public void reduceProductQuantitiesOnDate(LocalDate date){
        List<Cart> carts = cartRepository.findAll();
        for (Cart cart: carts) {
            ProductQuantity productQuantity = new ProductQuantity();
            productQuantity.setProduct(cart.getProduct());
            productQuantity.setQuantity(-cart.getQuantity());
            productQuantity.setDate(date);

            productQuantityRepository.save(productQuantity);
        }
    }

    public void clearCart(){
        cartRepository.deleteAll();
    }


    public List<PurchaseOrderLine> getAllPurchaseOrderLine() {
        return purchaseOrderLineRepository.findAll();
    }


    public PurchaseOrderLine getPurchaseOrderLineById(Long purchase_order_line_id) {
        Optional<PurchaseOrderLine> purchaseOrderLineOpt = purchaseOrderLineRepository.findById(purchase_order_line_id);
        return purchaseOrderLineOpt.orElseThrow(() -> new RuntimeException("Purchase order line was not found"));
    }

    public List<PurchaseOrderLine> getAllPurchaseOrderLineByOrderId(Long id) {
        return purchaseOrderLineRepository.findPurchaseOrderLineByPurchaseOrderId(id);
    }

    public List<PurchaseOrderTotalCostResponse> getAllPurchaseOrdersCost() {
        List<PurchaseOrderTotalCostResponse> purchaseOrderTotalCostResponses = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        for (PurchaseOrder purchaseOrder: purchaseOrders) {
            PurchaseOrderTotalCostResponse purchaseOrderTotalCostResponse = new PurchaseOrderTotalCostResponse();
            purchaseOrderTotalCostResponse.setPurchaseOrder(purchaseOrder);
            purchaseOrderTotalCostResponse.setTotalCost(getPurchaseOrderCostByOrderId(purchaseOrder.getId()));
            purchaseOrderTotalCostResponses.add(purchaseOrderTotalCostResponse);
        }
        return purchaseOrderTotalCostResponses;
    }

    public double getPurchaseOrderCostByOrderId(Long purchase_order_id){
        double totalCost = 0;
        List<PurchaseOrderLine> purchaseOrderLines = purchaseOrderLineRepository.findPurchaseOrderLineByPurchaseOrderId(purchase_order_id);
        for (PurchaseOrderLine purchaseOrderLine: purchaseOrderLines) {
            totalCost += purchaseOrderLine.getQuantity() * purchaseOrderLine.getProduct().getPrice();
        }
        return totalCost;
    }

}
