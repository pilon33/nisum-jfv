package com.nisum.test.dto.user;

import com.nisum.test.dto.phone.PhoneRequest;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String name;

    private String email;

    private String password;

    private Boolean isActive;

    private List<PhoneRequest> phones = new ArrayList<>();
}
