package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.PurchaseOrder;
import sda.store.onlinestore.model.PurchaseOrderDTO;
import sda.store.onlinestore.service.PurchaseOrderService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/purchase/order")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public PurchaseOrder postPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
    }
    @GetMapping(value = "/{userId}")
    public List<PurchaseOrder> getAllPurchaseOrderByUserId(@PathVariable String userId){
        return purchaseOrderService.getAllPurchaseOrderByUserId(userId);
    }

}
