package com.barbas.www.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String socialId;
    private String socialPhoto;
    private String socialName;
    private String photo;
    private String tel;
    private LocalDate birth;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Lob
    private String metadata;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ON;

    public enum Role {
        USER, OPERATOR, EMPLOYE, ANALIST, ADMIN
    }

    public enum Status {
        ON, OFF, DEL
    }
}
