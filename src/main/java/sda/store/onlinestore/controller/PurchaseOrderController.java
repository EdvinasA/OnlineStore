package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.service.PurchaseOrderService;

@RestController
@RequestMapping(value = "/purchase/order")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public PurchaseOrder postPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
    }

}
