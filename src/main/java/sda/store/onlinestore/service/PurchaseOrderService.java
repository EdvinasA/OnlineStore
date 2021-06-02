package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserName(purchaseOrderDTO.getUserName());
        purchaseOrder.setUserSurname(purchaseOrderDTO.getUserSurname());
        purchaseOrder.setDeliveryAddress(purchaseOrderDTO.getDeliveryAddress());
        purchaseOrder.setOrderDate(purchaseOrderDTO.getOrderDate());
        purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrder;
    }

    public List<PurchaseOrder> getAllPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long purchase_order_id) {
        Optional<PurchaseOrder> purchaseOrderOpt = purchaseOrderRepository.findById(purchase_order_id);
        return purchaseOrderOpt.orElseThrow(() -> new RuntimeException("Purchase order was nor found"));
    }
}
