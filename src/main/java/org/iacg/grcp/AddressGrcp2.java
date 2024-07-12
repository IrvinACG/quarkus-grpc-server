package org.iacg.grcp;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iacg.grcp.domain.address.*;
import org.iacg.services.IAddressService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class AddressGrcp2 implements AddressService {

    private final IAddressService service;

    @Override
    public Uni<ReplyAddress> findByUserId(Id request) {
        log.info("AQUIIIIIIIIIII");
        return service.findByUser(request.getId())
                .onItem()
                .transform(address -> {
                   return ReplyAddress.newBuilder()
                           .setId(address.getId())
                           .setNumber(address.getNumber())
                           .setStreet(address.getStreet())
                           .setCity(address.getCity())
                           .setUserId(address.getUserId())
                           .build();
                });
    }

    @Override
    public Uni<ReplyListAddress> findAll(Empty request) {
        return null;
    }
}
