package org.iacg.services.impl;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.iacg.repositories.AddressRepository;
import org.iacg.services.IAddressService;
import org.iacg.services.dto.AddressDTO;
import org.iacg.services.mapper.AddressMapper;

import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
@WithTransaction
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository repository;

    private final AddressMapper mapper;

    @Override
    public Uni<AddressDTO> save(AddressDTO dto) {
        return repository.persist(mapper.toEntity(dto))
                .onItem()
                .transform(mapper::toDto);
    }

    @Override
    public Uni<List<AddressDTO>> findAll() {
        return repository.listAll()
                .onItem()
                .transform(addresses -> {
                   return addresses.stream()
                           .map(mapper::toDto)
                           .toList();
                });
    }

    @Override
    public Uni<AddressDTO> findByUser(Long userId) {
        return repository.findById(userId)
                .onItem()
                .ifNotNull()
                .transform(mapper::toDto);
    }

    @Override
    public Uni<AddressDTO> update(Long id, AddressDTO dto) {
        return repository.findById(id)
                .onItem()
                .ifNotNull()
                .transformToUni(address -> {
                   return repository.persist(mapper.updateEntity(address, dto));
                }).map(mapper::toDto);
    }

    @Override
    public Uni<Boolean> delete(Long id) {
        return repository.deleteById(id);
    }
}
