package com.nisum.test.builders;

import com.nisum.test.model.Phone;
import com.nisum.test.model.User;
import lombok.Builder;

import java.util.UUID;

public class PhoneTestBuilder {

    @Builder
    public static Phone phone(UUID uuid, String number, String countryCode,
                              String cityCode, User user) {
        Phone phone = new Phone();
        phone.setUuid(uuid);
        phone.setNumber(number);
        phone.setCityCode(cityCode);
        phone.setCountryCode(countryCode);
        phone.setUser(user);
        return phone;
    }

}
