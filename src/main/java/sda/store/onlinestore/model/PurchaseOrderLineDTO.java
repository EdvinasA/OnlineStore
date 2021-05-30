package sda.store.onlinestore.model;

import lombok.Data;
import javax.validation.constraints.Min;

@Data
public class PurchaseOrderLineDTO {

    private Long purchaseOrderId;
    private Long productId;

    //@Min(value = 0, message = "Quantity cannot be < 0")
    private Double quantity;
}
