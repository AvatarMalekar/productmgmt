package com.apibanking.productmgmt;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.apibanking.productmgmt.entity.Product;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductResourceTest {

	static Long createdProductId;

	@Test
	@Order(1)
	void testCreateProduct() {
		Product product = new Product();
		product.name = "Test Product";
		product.description = "Test Description";
		product.price = 100.0;
		product.quantity = 50;

		createdProductId = given().contentType(ContentType.JSON).body(product).when().post("/products").then()
				.statusCode(Response.Status.OK.getStatusCode()).body("name", equalTo("Test Product")).extract()
				.<Number>path("id").longValue();

		Assertions.assertNotNull(createdProductId);
	}

	@Test
	@Order(2)
	void testGetAllProducts() {
		given().when().get("/products").then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
				greaterThan(0));
	}

	@Test
	@Order(3)
	void testGetProductById() {
		given().pathParam("id", createdProductId).when().get("/products/{id}").then().statusCode(200);
	}

	@Test
	@Order(4)
	void testUpdateProduct() {
		Product updated = new Product();
		updated.name = "Updated Product";
		updated.description = "Updated Description";
		updated.price = 200.0;
		updated.quantity = 99;

		given().contentType(ContentType.JSON).body(updated).when().put("/products/" + createdProductId).then()
				.statusCode(Response.Status.OK.getStatusCode()).body("name", equalTo("Updated Product"))
				.body("price", equalTo(200.0f)); 
	}

	@Test
	@Order(5)
	void testIsStockAvailableTrue() {
		given().queryParam("count", 10).when().get("/products/" + createdProductId + "/stock").then()
				.statusCode(Response.Status.OK.getStatusCode()).body(equalTo("true"));
	}

	@Test
	@Order(6)
	void testIsStockAvailableFalse() {
		given().queryParam("count", 1000).when().get("/products/" + createdProductId + "/stock").then()
				.statusCode(Response.Status.OK.getStatusCode()).body(equalTo("false"));
	}

	@Test
	@Order(7)
	void testSortedByPrice() {
		given().when().get("/products/sorted/price").then().statusCode(Response.Status.OK.getStatusCode())
				.body("size()", greaterThan(0)).body("[0].price", lessThanOrEqualTo(200.0f)); // assuming 200 is highest
	}

	@Test
	@Order(8)
	void testDeleteProduct() {
		given().when().delete("/products/" + createdProductId).then().statusCode(Response.Status.OK.getStatusCode())
				.body(equalTo("true"));
	}

	@Test
	@Order(9)
	void testGetDeletedProduct() {
		given().when().get("/products/" + createdProductId).then()
				.statusCode(Response.Status.NO_CONTENT.getStatusCode());
	}
}
