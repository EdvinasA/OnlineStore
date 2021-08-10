package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
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

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderLineRepository purchaseOrderLineRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductQuantityRepository productQuantityRepository;
    private final UserRepository userRepository;

    public PurchaseOrderLine addProductToPurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO) {
        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();

        Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchaseOrderLineDTO.getPurchaseOrderId());
        PurchaseOrder purchaseOrder = purchaseOrderOpt.orElseThrow(() -> new RuntimeException("Purchase order was not found"));
        Optional<Product> productOpt = productRepository.findById(purchaseOrderLineDTO.getProductId());
        Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));

        purchaseOrderLine.setPurchaseOrder(purchaseOrder);
        purchaseOrderLine.setProduct(product);
        purchaseOrderLine.setQuantity(purchaseOrderLineDTO.getQuantity());
        purchaseOrderLineRepository.save(purchaseOrderLine);
        return purchaseOrderLine;
    }

    public void performOrderLineCreationActions(Long purchase_order_id, Long userId){
        createOrderLinesFromCartByUserId(purchase_order_id, userId);

        Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchase_order_id);
        PurchaseOrder purchaseOrder = purchaseOrderOpt.orElseThrow(
                () -> new RuntimeException("Purchase order was not found ID="+purchase_order_id));

        reduceProductQuantitiesOnDate(purchaseOrder.getOrderDate());
        clearCart();
    }

    public void createOrderLinesFromCartByUserId(Long purchase_order_id, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow(() -> new RuntimeException("User was not found"));
        List<Cart> carts = cartRepository.findAllByUser(user);
//        List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();
        for (Cart cart: carts) {
            PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();

            Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchase_order_id);
            PurchaseOrder purchaseOrder = purchaseOrderOpt.orElseThrow(() -> new RuntimeException("Purchase order was not found."));

            purchaseOrderLine.setPurchaseOrder(purchaseOrder);
            purchaseOrderLine.setProduct(cart.getProduct());
            purchaseOrderLine.setQuantity(cart.getQuantity());

            purchaseOrderLineRepository.save(purchaseOrderLine);
//            purchaseOrderLines.add(purchaseOrderLine);
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

    public List<PurchaseOrderLine> getAllPurchaseOrderLineByOrderId(Long id) {
        return purchaseOrderLineRepository.findPurchaseOrderLineByPurchaseOrderId(id);
    }

    public List<PurchaseOrderTotalCostResponse> getAllPurchaseOrdersCostByUserId(String userId) {
        List<PurchaseOrderTotalCostResponse> purchaseOrderTotalCostResponses = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAllByUserId(Long.parseLong(userId));
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
