package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.model.PurchaseOrderLine;
import sda.store.onlinestore.model.PurchaseOrderLineDTO;
import sda.store.onlinestore.service.CartService;
import sda.store.onlinestore.service.PurchaseOrderLineService;

import java.util.List;

@RestController
@RequestMapping(value = "/purchase/order/line")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PurchaseOrderLineController {
    @Autowired
    private PurchaseOrderLineService purchaseOrderLineService;

    @PostMapping
    public PurchaseOrderLine postPurchaseOrderLine(@RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return purchaseOrderLineService.addProductToPurchaseOrderLine(purchaseOrderLineDTO);
    }

    @GetMapping
    public List<PurchaseOrderLine> getAllPurchaseOrderLine(){
        return purchaseOrderLineService.getAllPurchaseOrderLine();
    }

    @GetMapping(value = "/{id}")
    public PurchaseOrderLine getAllPurchaseOrderLineById(@PathVariable Long id){
        return purchaseOrderLineService.getAllPurchaseOrderLineById(id);
    }
}
