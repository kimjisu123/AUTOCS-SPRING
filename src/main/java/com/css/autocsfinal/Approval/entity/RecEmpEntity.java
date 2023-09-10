package com.css.autocsfinal.Approval.entity;

import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import com.css.autocsfinal.member.entity.EmpPosiDeptEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RECEIVER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecEmpEntity {

    @EmbeddedId
    private ReceiverDocumentEntityKey key;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_NO", insertable = false, updatable = false)
    private EmpPosiDeptEntity employee;

    @Column(name = "STATUS")
    private String status;
}
