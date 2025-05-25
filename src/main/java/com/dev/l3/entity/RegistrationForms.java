package com.dev.l3.entity;

import com.dev.l3.utils.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_registration_forms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationForms extends BaseEntity{
    @Column(name = "code")
    String code;

    @Column(name = "content")
    String content;

    @Column(name = "note")
    String note;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusEnum status;

    @Column(name = "submission_date")
    LocalDate submissionDate;

    @Column(name = "approval_date")
    LocalDate approvalDate;

    @Column(name = "rejection_date")
    LocalDate rejectionDate;

    @Column(name = "additional_required_date")
    LocalDate additionalRequiredDate;

    @Column(name = "reason")
    String reason;

    @Column(name = "position_approval")
    String positionApproval;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    EmployeeInfo employeeInfo;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    User leader;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    User manager;

    @ManyToOne
    @JoinColumn(name = "form_type_id")
    FormType formType;


}
