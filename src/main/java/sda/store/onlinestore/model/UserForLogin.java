package sda.store.onlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForLogin {

    private String userName;

    private String password;
}
