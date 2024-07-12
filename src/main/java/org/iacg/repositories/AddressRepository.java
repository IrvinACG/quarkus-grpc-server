package org.iacg.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.iacg.repositories.entities.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
}
