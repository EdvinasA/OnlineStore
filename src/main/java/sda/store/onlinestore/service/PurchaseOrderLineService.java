package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.*;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.PurchaseOrderLineRepository;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderLineService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Autowired
    private ProductRepository productRepository;

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
