package com.hospital.doctor.dtos;

import com.hospital.doctor.entities.enums.DoctorStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorUpdateDTO {

    private String name;

    private String specialization;

    private String phone;

    private String email;

    private DoctorStatus status;

    private Boolean active;
}
