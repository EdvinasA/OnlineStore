package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;


    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserName(purchaseOrderDTO.getUserName());
        purchaseOrder.setUserSurname(purchaseOrderDTO.getUserSurname());
        purchaseOrder.setDeliveryAddress(purchaseOrderDTO.getDeliveryAddress());
        purchaseOrder.setOrderDate(purchaseOrderDTO.getOrderDate());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }

}
