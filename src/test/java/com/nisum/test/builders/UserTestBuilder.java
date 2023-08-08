package com.nisum.test.builders;

import com.nisum.test.model.Phone;
import com.nisum.test.model.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserTestBuilder {
    @Builder
    public static User user(UUID uuid, String name, String email, String password, String token, Boolean isActive, LocalDateTime lastLogin, List<Phone> phones) {
        User user = new User();
        user.setUuid(uuid);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setToken(token);
        user.setIsActive(isActive);
        user.setLastLogin(lastLogin);
        if (phones != null) {
            user.setPhones(phones);
        }
        return user;
    }

}
