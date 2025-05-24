package com.dev.l3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name = "tbl_roles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseEntity {
    @Column(name = "role_name")
    String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
