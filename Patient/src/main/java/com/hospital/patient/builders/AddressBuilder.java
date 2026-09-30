package com.hospital.patient.builders;

import com.hospital.patient.dto.AddressCreateDTO;
import com.hospital.patient.dto.AddressUpdateDTO;
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

    public static Address createAddressFromAddressUpdateDTO(Address address, AddressUpdateDTO req){
        address.setHouseNo(req.getHouseNo());
        address.setStreet(req.getStreet());
        address.setCity(req.getCity());
        address.setState(req.getState());
        address.setCountry(req.getCountry());
        address.setZipCode(req.getZipCode());
        address.setActive(req.getActive());

        return address;
    }
}
