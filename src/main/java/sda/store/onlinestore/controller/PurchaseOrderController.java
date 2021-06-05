package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.service.PurchaseOrderService;

import java.util.List;


@RestController
@RequestMapping(value = "/purchase/order")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public PurchaseOrder postPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
    }
    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrder(){
        return purchaseOrderService.getAllPurchaseOrder();
    }

    @GetMapping (value = "/{id}")
    public PurchaseOrder getPurchaseOrderById(@PathVariable Long id){
        return purchaseOrderService.getPurchaseOrderById(id);
    }
}
