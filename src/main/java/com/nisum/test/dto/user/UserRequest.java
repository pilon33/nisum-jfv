package com.nisum.test.dto.user;

import com.nisum.test.dto.phone.PhoneRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "User name can't be null.")
    private String name;

    @NotNull(message = "User email can't be null.")
    private String email;

    @NotNull(message = "User pass can't be null.")
    private String password;

    private Boolean isActive;

    private List<PhoneRequest> phones = new ArrayList<>();
}
