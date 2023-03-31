package com.example.webrtc.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String password;

    @Column
    private boolean isDeleted;

    @Column
    private LocalDate createdAt;

//    @Builder
//    public User(String email,String password,boolean isDeleted,LocalDate createdAt){
//        this.email = email;
//        this.password = password;
//        this.isDeleted = isDeleted;
//        this.createdAt = createdAt;
//    }

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.isDeleted = false;
        this.createdAt = LocalDate.now();
    }
}
