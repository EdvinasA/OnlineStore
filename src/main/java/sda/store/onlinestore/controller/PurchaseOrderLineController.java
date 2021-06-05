package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.PurchaseOrderLine;
import sda.store.onlinestore.model.PurchaseOrderLineDTO;
import sda.store.onlinestore.service.PurchaseOrderLineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/purchase/order/line")
public class PurchaseOrderLineController {
    @Autowired
    private PurchaseOrderLineService purchaseOrderLineService;

    @PostMapping(value = "/add")
    public PurchaseOrderLine addProductToPurchaseOrderLine(@RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return purchaseOrderLineService.addProductToPurchaseOrderLine(purchaseOrderLineDTO);
    }
}
