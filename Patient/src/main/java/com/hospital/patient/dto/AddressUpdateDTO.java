package com.hospital.patient.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressUpdateDTO {
    private String houseNo;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private Boolean active;
}
