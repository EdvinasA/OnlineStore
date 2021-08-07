package sda.store.onlinestore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
        reason = "Requested product does not exist")
public class FoundException extends RuntimeException{
    public FoundException(String message) {
        super(message);
    }
}
