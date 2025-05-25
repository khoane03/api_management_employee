package com.dev.l3.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name = "tbl_users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "position")
    String position;

    @Column(name = "code")
    String code;

    @Column(name = "username", unique = true)
    String username;

    @Column(name = "password")
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles;

    @OneToOne(mappedBy = "user")
    Token token;

}
