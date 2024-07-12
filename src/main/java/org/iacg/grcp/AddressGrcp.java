package org.iacg.grcp;


import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iacg.grcp.domain.address.*;
import org.iacg.services.IAddressService;
import org.iacg.services.dto.AddressDTO;

@Slf4j
@RequiredArgsConstructor
public class AddressGrcp extends AddressServiceGrpc.AddressServiceImplBase {

    private final IAddressService service;


    @Override
    public void findAll(Empty request, StreamObserver<ReplyListAddress> responseObserver) {
    }

    @Override
    public void findByUserId(Id request, StreamObserver<ReplyAddress> responseObserver) {
    /**
        service.findByUser(request.getId())
                .subscribe()
                .with(address -> {
                            ReplyAddress replyAddress = ReplyAddress.newBuilder()
                                    .setId(address.getId())
                                    .setNumber(address.getNumber())
                                    .setStreet(address.getStreet())
                                    .setCity(address.getCity())
                                    .setUserId(address.getUserId())
                                    .build();
                            responseObserver.onNext(replyAddress);
                            responseObserver.onCompleted();
                        }, responseObserver::onError
                );
    */
    log.info("Entra aqui 1");
        AddressDTO address = new AddressDTO(1L, "01", "Calle Uno", "Ciudad Uno", 51L);
        ReplyAddress replyAddress = ReplyAddress.newBuilder()
                .setId(address.getId())
                .setNumber(address.getNumber())
                .setStreet(address.getStreet())
                .setCity(address.getCity())
                .setUserId(address.getUserId())
                .build();
        responseObserver.onNext(replyAddress);
        responseObserver.onCompleted();
    }
}
