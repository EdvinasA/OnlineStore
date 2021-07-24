package sda.store.onlinestore.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PurchaseOrderDTO {

    private String userName;

    private String userSurname;

    private String deliveryAddress;

    private LocalDate orderDate;
}
