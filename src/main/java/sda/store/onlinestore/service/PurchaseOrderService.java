package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

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
}
