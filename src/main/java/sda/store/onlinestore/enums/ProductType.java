package sda.store.onlinestore.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum ProductType {
    COMPUTER("COMPUTER"),
    MONITOR("MONITOR"),
    OTHERS("OTHERS");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

   /* @JsonCreator
    public ProductType convertStringToEnum1 (String type) {
        //   return ProductType.convert(type.toUpperCase());
        try{
            return ProductType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }*/

    @JsonCreator
    public static ProductType convert(String type) {
        return Stream.of(ProductType.values()).filter(targetEnum ->
                targetEnum.type.toUpperCase().equals(type)).findFirst().orElse(null);
    }

    @JsonValue
    public String getType() {
        return type;
    }
 }
