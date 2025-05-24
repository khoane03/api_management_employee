package com.dev.l3.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "tbl_token")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token extends BaseEntity {

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "is_valid")
    boolean isValid;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "access_token")
    String accessToken;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;

}
