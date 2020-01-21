package com.sia.tacocloud.essences.jpa;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;

    private String password;

    private String fullname;

    private String userStreet;

    private String userCity;

    private String userState;

    private String userZipCode;

    private String userPhoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username,
                passwordEncoder.encode(password),
                fullname,
                userStreet,
                userCity,
                userState,
                userZipCode,
                userPhoneNumber);
    }

}
