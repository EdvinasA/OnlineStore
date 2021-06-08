package sda.store.onlinestore.model.responseBody;

import lombok.Data;
import sda.store.onlinestore.model.PurchaseOrder;

@Data
public class PurchaseOrderTotalCostResponse {
    private PurchaseOrder purchaseOrder;
    private double totalCost;
}
