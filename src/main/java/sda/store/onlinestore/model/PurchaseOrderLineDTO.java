package sda.store.onlinestore.model;

import lombok.Data;

@Data
public class PurchaseOrderLineDTO {

    private Long purchaseOrderId;
    private Long productId;
    private Double quantity;
}
