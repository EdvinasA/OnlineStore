package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.model.PurchaseOrderLine;
import sda.store.onlinestore.model.PurchaseOrderLineDTO;
import sda.store.onlinestore.service.CartService;
import sda.store.onlinestore.service.PurchaseOrderLineService;

@RestController
@RequestMapping(value = "/purchase/order/line")
public class PurchaseOrderLineController {
    @Autowired
    private PurchaseOrderLineService purchaseOrderLineService;

    @PostMapping(value = "/add")
    public PurchaseOrderLine addProductToPurchaseOrderLine(@RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return purchaseOrderLineService.addProductToPurchaseOrderLine(purchaseOrderLineDTO);
    }
}
