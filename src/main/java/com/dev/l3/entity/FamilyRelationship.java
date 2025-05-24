package com.dev.l3.entity;

import com.dev.l3.utils.enums.GenderEnum;
import com.dev.l3.utils.enums.RelationEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_family_relationship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FamilyRelationship extends BaseEntity {
    @Column(name = "full_name")
    String fullName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "id_card")
    String idCard;

    @Column(name = "relation")
    @Enumerated(EnumType.STRING)
    RelationEnum relation;

    @Column(name = "address")
    String address;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    EmployeeInfo employeeInfo;

}
