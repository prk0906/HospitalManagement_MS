package com.hospital.patient.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateDTO {
    private String houseNo;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

}
