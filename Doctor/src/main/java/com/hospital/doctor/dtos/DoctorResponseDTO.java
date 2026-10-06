package com.hospital.doctor.dtos;

import com.hospital.doctor.entities.enums.DoctorStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDTO {
    private Long doctorId;

    private String name;

    private String specialization;

    private String phone;

    private String email;

    private DoctorStatus status;

    private Boolean active;

    private Boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
