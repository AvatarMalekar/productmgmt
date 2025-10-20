package com.apibanking.productmgmt.resource;

import java.util.List;

import com.apibanking.productmgmt.entity.Product;
import com.apibanking.productmgmt.service.ProductService;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@NonBlocking
public class ProductResource {

    @Inject
    ProductService service;

    @POST
    public Uni<PanacheEntityBase> create(Product product) {
        return service.create(product);
    }

    @GET
    public Uni<List<Product>> getAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @WithSession
    public Uni<Product> getById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Uni<Product> update(@PathParam("id") Long id, Product updated) {
    	return service.update(id,updated);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> delete(@PathParam("id") Long id) {
        return service.delete(id);
    }

    @GET
    @Path("/{id}/stock")
    public Uni<Boolean> isStockAvailable(@PathParam("id") Long id, @QueryParam("count") Integer count) {
        return service.isStockAvailable(id, count);
    }

    @GET
    @Path("/sorted/price")
    public Uni<List<Product>> getProductsSortedByPrice() {
        return service.sortByPriceAsc();
    }
}
