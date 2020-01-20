package com.sia.tacocloud.essences.jdbc;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;

    private Date placedAt;

    @NotBlank(message="Name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip Code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^\\d{2}\\/\\d{2}$", message = "Must be formatted as MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CCV")
    private String ccCVV;

    private List<Taco> tacoList = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacoList.add(taco);
    }
}
