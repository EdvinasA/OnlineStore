package sda.store.onlinestore.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderServiceTest {
    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @InjectMocks
    PurchaseOrderService purchaseOrderService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }

    @Test
    void when_createPurchaseOrder_return_purchase_order() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setOrderDate(LocalDate.of(2021, 06, 30));
        purchaseOrderDTO.setUserName("Jonas");
        purchaseOrderDTO.setUserSurname("Jonaitis");
        purchaseOrderDTO.setDeliveryAddress("Kaunas");

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderDate(LocalDate.of(2021, 06, 30));
        purchaseOrder.setUserName("Jonas");
        purchaseOrder.setUserSurname("Jonaitis");
        purchaseOrder.setDeliveryAddress("Kaunas");

        PurchaseOrder purchaseOrderExpected = new PurchaseOrder();
        purchaseOrderExpected.setId(1L);
        purchaseOrderExpected.setOrderDate(LocalDate.of(2021, 06, 30));
        purchaseOrderExpected.setUserName("Jonas");
        purchaseOrderExpected.setUserSurname("Jonaitis");
        purchaseOrderExpected.setDeliveryAddress("Kaunas");

        when(purchaseOrderRepository.save(any(PurchaseOrder.class))).thenReturn(purchaseOrderExpected);

        PurchaseOrder result = purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);

        verify(purchaseOrderRepository, times(1)).save(purchaseOrder);
        assertThat(result).isSameAs(purchaseOrderExpected);
    }

    @Test
    void when_getAllPurchaseOrder_return_purchase_order_list() {
        PurchaseOrder purchaseOrder1 = new PurchaseOrder();
        purchaseOrder1.setId(1L);
        purchaseOrder1.setOrderDate(LocalDate.of(2021, 06, 30));
        purchaseOrder1.setUserName("Jonas");
        purchaseOrder1.setUserSurname("Jonaitis");
        purchaseOrder1.setDeliveryAddress("Kaunas");

        PurchaseOrder purchaseOrder2 = new PurchaseOrder();
        purchaseOrder2.setId(2L);
        purchaseOrder2.setOrderDate(LocalDate.of(2021, 06, 29));
        purchaseOrder2.setUserName("Petras");
        purchaseOrder2.setUserSurname("Petraitis");
        purchaseOrder2.setDeliveryAddress("Kaunas");

        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.add(purchaseOrder1);
        purchaseOrders.add(purchaseOrder2);

        when(purchaseOrderRepository.findAll()).thenReturn(purchaseOrders);

        List<PurchaseOrder> result = purchaseOrderService.getAllPurchaseOrder();

        assertThat(result).isEqualTo(purchaseOrders);
    }
}
