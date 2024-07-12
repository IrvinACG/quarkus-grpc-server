package org.iacg.resources;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.RequiredArgsConstructor;
import org.iacg.services.IAddressService;
import org.iacg.services.dto.AddressDTO;

import java.net.URI;

@RequiredArgsConstructor
@ApplicationScoped
@Path("/api/addresses")
public class AddressResource {

    private final IAddressService service;

    @POST
    public Uni<Response> save(AddressDTO dto){
        return service.save(dto)
                .onItem()
                .transform(address -> {
                   return Response.created(URI.create("/api/address/" + address.getId()))
                           .entity(address)
                           .build();
                });
    }

    @GET
    public Uni<Response> findAll(){
        return service.findAll()
                .onItem()
                .transform(addresses -> {
                   return Response.ok(addresses).build();
                });
    }

    @GET
    @Path("/user/{idUser}")
    public Uni<Response> findByUserId(@PathParam("idUser") Long idUser){
        return service.findByUser(idUser)
                .onItem()
                .ifNotNull()
                .transform(address -> {
                    return Response.ok(address).build();
                }).replaceIfNullWith(Response.status(Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, AddressDTO dto){
        return service.update(id, dto)
                .onItem()
                .ifNotNull()
                .transform(address -> {
                    return Response.created(URI.create("/api/address/" + address.getId()))
                            .entity(address)
                            .build();
                }).replaceIfNullWith(Response.status(Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id){
        return service.delete(id)
                .onItem()
                .transform(deleted -> {
                    if(deleted){
                        return Response.noContent().build();
                    }else {
                        return Response.status(Status.NOT_FOUND).build();
                    }
                });
    }
}
