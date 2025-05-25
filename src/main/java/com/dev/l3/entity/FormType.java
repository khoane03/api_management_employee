package com.dev.l3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_form_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormType extends BaseEntity {

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "created_by")
    String createdBy;

}
