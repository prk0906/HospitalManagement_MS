package com.hospital.doctor.dtos;

import com.hospital.doctor.entities.enums.DoctorStatus;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDoctorDTO {
    private String name;

    private String specialization;

    private String phone;

    private String email;

    private DoctorStatus status;

    private Boolean active = true;
}
