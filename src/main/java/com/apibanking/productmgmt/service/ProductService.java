package com.apibanking.productmgmt.service;

import java.util.List;

import com.apibanking.productmgmt.entity.Product;
import com.apibanking.productmgmt.repository.ProductRepository;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;;

@ApplicationScoped
public class ProductService {

	@Inject
	ProductRepository repository;

	@WithTransaction
	public Uni<PanacheEntityBase> create(Product product) {
		 // to check if a product with the same name already exists
		    return Product.find("name", product.name).firstResult()
		        .onItem().ifNotNull().failWith(() -> 
		            new RuntimeException("Product with name '" + product.name + "' already exists."))
		        .onItem().ifNull().switchTo(() -> product.persist().replaceWith(product));
	}
	 
	 @WithTransaction
	 public Uni<Product> update(Long id, Product product) {
		    return Product.<Product>findById(id)
		        .onItem().ifNotNull().transformToUni(existing -> {
		            existing.name = product.name;
		            existing.description = product.description;
		            existing.price = product.price;
		            existing.quantity = product.quantity;
		            return Uni.createFrom().item(existing); 
		        })
		        .onItem().ifNull().failWith(() -> new NotFoundException("Product not found"));
		}
	 
	@WithSession
	public Uni<List<Product>> findAll() {
		return repository.listAll();
	}

	@WithSession
	public Uni<Product> findById(Long id) {
		return repository.findById(id);
	}

	@WithTransaction
	public Uni<Boolean> delete(Long id) {
		return repository.deleteById(id);
	}

	@WithSession
	public Uni<List<Product>> sortByPriceAsc() {
		return repository.list("ORDER BY price ASC");
	}

	@WithSession
	public Uni<Boolean> isStockAvailable(Long id, Integer count) {
		return repository.findById(id).onItem().ifNotNull().transform(p -> p.quantity >= count).onItem().ifNull()
				.continueWith(Boolean.FALSE);
	}
}
