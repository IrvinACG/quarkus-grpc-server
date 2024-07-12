package org.iacg.services;

import io.smallrye.mutiny.Uni;
import org.iacg.services.dto.AddressDTO;

import java.util.List;

public interface IAddressService {

    Uni<AddressDTO> save(AddressDTO dto);

    Uni<List<AddressDTO>> findAll();

    Uni<AddressDTO> findByUser(Long userId);

    Uni<AddressDTO> update(Long id, AddressDTO dto);

    Uni<Boolean> delete(Long id);

}
