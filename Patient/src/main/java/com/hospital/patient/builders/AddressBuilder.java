package com.hospital.patient.builders;

import com.hospital.patient.dto.AddressCreateDTO;
import com.hospital.patient.models.Address;

public class AddressBuilder {
    public static Address createAddressFromAddressCreateDto(AddressCreateDTO req){
        return Address.builder()
                .houseNo(req.getHouseNo())
                .street(req.getStreet())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getCountry())
                .zipCode(req.getZipCode())
                .active(true)
                .deleted(false)
                .build();
    }
}
