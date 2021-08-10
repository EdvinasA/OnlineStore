package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.PurchaseOrderLine;
import sda.store.onlinestore.model.PurchaseOrderLineDTO;
import sda.store.onlinestore.model.responseBody.PurchaseOrderTotalCostResponse;
import sda.store.onlinestore.service.PurchaseOrderLineService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/purchase/order")
@CrossOrigin(origins = {"http://localhost:4200", "https://digitalists.herokuapp.com"}, allowedHeaders = "*")
@AllArgsConstructor
public class PurchaseOrderLineController {

    private final PurchaseOrderLineService purchaseOrderLineService;

    @PostMapping(value = "/line")
    public PurchaseOrderLine postPurchaseOrderLine(@Valid @RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return purchaseOrderLineService.addProductToPurchaseOrderLine(purchaseOrderLineDTO);
    }

    @PostMapping(value = "/lines/{userId}")
    public List<PurchaseOrderLine> createOrderLinesFromCart(@RequestBody String purchase_order_id, @PathVariable String userId){
        purchaseOrderLineService.performOrderLineCreationActions(Long.parseLong(purchase_order_id), Long.parseLong(userId));
        return purchaseOrderLineService.getAllPurchaseOrderLineByOrderId(Long.parseLong(purchase_order_id));
    }

    @GetMapping(value = "{id}/lines")
    public List<PurchaseOrderLine> getPurchaseOrderLineByOrderId(@PathVariable (required = false) Long id){
        return purchaseOrderLineService.getAllPurchaseOrderLineByOrderId(id);
    }

    @GetMapping(value = "/totals/{userId}")
    public List<PurchaseOrderTotalCostResponse> getAllPurchaseOrdersCost(@PathVariable String userId){
        return purchaseOrderLineService.getAllPurchaseOrdersCostByUserId(userId);
    }

    @GetMapping(value = "{id}/total")
    public double getPurchaseOrderCostByOrderId(@PathVariable (required = false) Long id){
        return purchaseOrderLineService.getPurchaseOrderCostByOrderId(id);
    }
}
