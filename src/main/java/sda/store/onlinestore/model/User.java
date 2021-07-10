package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "USERS")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    @NotEmpty
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    private String role;

    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<PurchaseOrder> purchaseOrder;

    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<Cart> cart;






}
