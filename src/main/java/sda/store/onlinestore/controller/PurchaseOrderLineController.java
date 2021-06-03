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
@RequestMapping(value = "/purchase/order")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PurchaseOrderLineController {
    @Autowired
    private PurchaseOrderLineService purchaseOrderLineService;

    @PostMapping(value = "/line")
    public PurchaseOrderLine postPurchaseOrderLine(@RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return purchaseOrderLineService.addProductToPurchaseOrderLine(purchaseOrderLineDTO);
    }

    @GetMapping(value = "/lines")
    public List<PurchaseOrderLine> getAllPurchaseOrderLine(){
        return purchaseOrderLineService.getAllPurchaseOrderLine();
    }

    @GetMapping(value = "/line/{id}")
    public PurchaseOrderLine getPurchaseOrderLineById(@PathVariable Long id){
        return purchaseOrderLineService.getPurchaseOrderLineById(id);
    }

    @GetMapping(value = "{id}/lines")
    public List<PurchaseOrderLine> getPurchaseOrderLineByOrderId(@PathVariable Long id){
        return purchaseOrderLineService.getPurchaseOrderLineByOrderId(id);
    }
}
