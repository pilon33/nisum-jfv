package com.nisum.test.dto.phone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {
    private UUID uuid;

    private String number;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty( "countryCode")
    private String countryCode;
}
