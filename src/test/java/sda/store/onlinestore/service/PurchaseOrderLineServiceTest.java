package sda.store.onlinestore.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import sda.store.onlinestore.OnlineStoreApplicationTests;
import sda.store.onlinestore.model.*;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.PurchaseOrderLineRepository;
import sda.store.onlinestore.repository.PurchaseOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class PurchaseOrderLineServiceTest extends OnlineStoreApplicationTests {
    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private PurchaseOrderLineService purchaseOrderLineService;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void when_addProductToPurchaseOrderLine_return_purchase_order_line() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(50.99);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);
        purchaseOrder.setOrderDate(LocalDate.of(2021, 06,15));
        purchaseOrder.setUserName("Jonas");
        purchaseOrder.setUserSurname("Jonaitis");
        purchaseOrder.setDeliveryAddress("Kaunas");

        PurchaseOrderLineDTO purchaseOrderLineDTO = new PurchaseOrderLineDTO();
        purchaseOrderLineDTO.setPurchaseOrderId(1L);
        purchaseOrderLineDTO.setProductId(1L);
        purchaseOrderLineDTO.setQuantity(3.0);

        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
        purchaseOrderLine.setId(1L);
        purchaseOrderLine.setPurchaseOrder(purchaseOrder);
        purchaseOrderLine.setProduct(product);
        purchaseOrderLine.setQuantity(3.0);

        when(purchaseOrderRepository.findById(anyLong())).thenReturn(Optional.of(purchaseOrder));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(purchaseOrderLineRepository.save(any(PurchaseOrderLine.class))).thenReturn(purchaseOrderLine);

        PurchaseOrderLine result = purchaseOrderLineService.addProductToPurchaseOrderLine(purchaseOrderLineDTO);

        assertThat(result).isEqualTo(purchaseOrderLine);
    }

    @Test
    void when_performOrderLineCreationActions_run_three_functions() {
        PurchaseOrderLineService purchaseOrderLineServiceMock = Mockito.mock(PurchaseOrderLineService.class);
        doNothing().when(purchaseOrderLineServiceMock).createOrderLinesFromCart(anyLong());
        when(cartRepository.findAll()).thenReturn(new ArrayList<>());
        when(purchaseOrderRepository.findById(anyLong())).thenReturn(Optional.of(new PurchaseOrder()));
        when(purchaseOrderLineRepository.save(any(PurchaseOrderLine.class))).thenReturn(new PurchaseOrderLine());

        purchaseOrderLineService.performOrderLineCreationActions(1L);

        verify(purchaseOrderLineServiceMock).createOrderLinesFromCart(anyLong());
    }

    @Test
    void createOrderLinesFromCart() {
    }

    @Test
    void reduceProductQuantitiesOnDate() {
    }

    @Test
    void clearCart() {
    }

    @Test
    void getAllPurchaseOrderLineByOrderId() {
    }

    @Test
    void getAllPurchaseOrdersCost() {
    }

    @Test
    void getPurchaseOrderCostByOrderId() {
    }
}
