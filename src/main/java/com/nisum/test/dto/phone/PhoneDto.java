package com.nisum.test.dto.phone;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    private UUID uuid;

    private String number;

    private String cityCode;

    private String countryCode;
}
