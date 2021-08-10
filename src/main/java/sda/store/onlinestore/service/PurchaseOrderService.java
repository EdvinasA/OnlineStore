package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.repository.PurchaseOrderRepository;
import sda.store.onlinestore.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final UserRepository userRepository;

    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, String userId) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
        User user = userOpt.orElseThrow(() -> new RuntimeException("User was not found"));
        purchaseOrder.setUserName(purchaseOrderDTO.getUserName());
        purchaseOrder.setUserSurname(purchaseOrderDTO.getUserSurname());
        purchaseOrder.setDeliveryAddress(purchaseOrderDTO.getDeliveryAddress());
        purchaseOrder.setOrderDate(LocalDate.now());
        purchaseOrder.setUser(user);
        purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrder;
    }

    public List<PurchaseOrder> getAllPurchaseOrderByUserId(String userId) {
        return purchaseOrderRepository.findAllByUserId(Long.parseLong(userId));
    }

}
