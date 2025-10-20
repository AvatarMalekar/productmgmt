 package com.apibanking.productmgmt.repository;


import com.apibanking.productmgmt.entity.Product;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
	
}