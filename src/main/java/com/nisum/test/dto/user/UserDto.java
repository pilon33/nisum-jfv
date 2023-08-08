package com.nisum.test.dto.user;

import com.nisum.test.dto.phone.PhoneDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID uuid;

    private String name;

    private String email;

    private String token;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    private LocalDateTime lastLogin;

    private List<PhoneDto> phones = new ArrayList<>();
}
