package com.nisum.test.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseModel {

    private String name;

    private String email;

    private String password;

    private String token;

    @Column(name = "is_active")
    private Boolean isActive = true;

    private LocalDateTime lastLogin;


    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "user")
    private List<Phone> phones = new ArrayList<>();

}
