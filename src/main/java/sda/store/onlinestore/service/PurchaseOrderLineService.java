package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.*;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.PurchaseOrderLineRepository;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

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

    public List<PurchaseOrderLine> createOrderLinesFromCart(Long purchase_order_id) {
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

        cartRepository.deleteAll();
        return purchaseOrderLines;
    }

    public List<PurchaseOrderLine> getAllPurchaseOrderLine() {
        return purchaseOrderLineRepository.findAll();
    }



    public PurchaseOrderLine getPurchaseOrderLineById(Long purchase_order_line_id) {
        Optional<PurchaseOrderLine> purchaseOrderLineOpt = purchaseOrderLineRepository.findById(purchase_order_line_id);
        return purchaseOrderLineOpt.orElseThrow(() -> new RuntimeException("Purchase order line was not found"));
    }

    public List<PurchaseOrderLine> getPurchaseOrderLineByOrderId(Long id) {
        return purchaseOrderLineRepository.findPurchaseOrderLineByPurchaseOrderId(id);
    }

}
