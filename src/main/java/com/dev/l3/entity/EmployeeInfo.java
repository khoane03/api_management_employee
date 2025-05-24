package com.dev.l3.entity;

import com.dev.l3.utils.enums.GenderEnum;
import com.dev.l3.utils.enums.StatusEnum;
import com.dev.l3.utils.enums.TeamEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Table(name = "tbl_employee_info")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeInfo extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "address")
    String address;

    @Column(name = "team")
    @Enumerated(EnumType.STRING)
    TeamEnum team;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "id_card")
    String idCard;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusEnum status;

    @Column(name = "note")
    String note;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<FamilyRelationship> familyRelationships;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Certificate> certificates;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = StatusEnum.NEW_SAVE;
        }
    }


}
