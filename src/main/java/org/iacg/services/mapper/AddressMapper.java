package org.iacg.services.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.iacg.repositories.entities.Address;
import org.iacg.services.dto.AddressDTO;

@ApplicationScoped
public class AddressMapper {

    public Address toEntity(AddressDTO dto){
        Address entity = new Address();
        entity.setNumber(dto.getNumber());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setUserId(dto.getUserId());
        return entity;
    }

    public AddressDTO toDto(Address entity){
        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setUserId(entity.getUserId());
        return dto;
    }

    public Address updateEntity(Address entity, AddressDTO dto){
        entity.setNumber(dto.getNumber());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setUserId(dto.getUserId());
        return entity;
    }
}
