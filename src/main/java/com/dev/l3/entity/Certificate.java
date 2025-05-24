package com.dev.l3.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_certificate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certificate extends BaseEntity{

    @Column(name = "name")
    String name;

    @Column(name = "issued_date")
    LocalDate issuedDate;

    @Column(name = "content")
    String content;

    @Column(name = "field")
    String field;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    EmployeeInfo employeeInfo;

}
