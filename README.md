# Product Management API (Quarkus + Reactive Panache)

This is a RESTful application for managing products, built using **Quarkus** and **Reactive Panache**.

---

## 🚀 Features

- Create, retrieve, update, and delete products.
- Check stock availability.
- Get products sorted by price.
- Get stock by name
- Doesn't allow duplicate entry with same product name
- Built using Quarkus and Hibernate Reactive.

---

## 🛠 Technologies

- Java 17+
- Quarkus
- Hibernate Reactive with Panache
- JUnit 5
- REST Assured
- Mutiny
- Postman

---

🛠️  **Project Prerequisites**
==============================


The following tools are required or recommended to build and run the project:

-------------------------------------------------------------------------
| Tool             | Minimum Version | Description                      |
|------------------|------------------|---------------------------------|
| Java (JDK)       | 17               | Required to run Quarkus         |
| Maven            | 3.8+             | For building and running the app|
| Git              | Latest           | To clone the repository         |
| Postman          | –                | To manually test API endpoints  |
| IDE              | –                | IntelliJ IDEA, sts, etc.        |
-------------------------------------------------------------------------

## ⚙️ Setup Instructions
==============================


1. in Mysql create db/schema named 'productsystem'
make sure sql is up and running application will create table automatically

2. open project in any ide and execute below command
  mvn compile
  mvn quarkus:dev
  mvn test

-- compile will copile the project
-- quarkus:dev will start the project or run project
-- test will execute the tests



### Clone the repository

open bash terminal or command promt and execute below commads

git clone https://github.com/AvatarMalekar/productmgmt.git
cd productmgmt
git checkout master

---

📡 API Endpoints – cURLs
-------------------------------------------------
Base URL:

http://localhost:8081/products


Replace 8081 with the actual port if you've configured a different one in application.properties.

📦 1. Create a Product – POST /products
--------------------------------------------------
curl -X POST http://localhost:8081/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Samsung",
    "description": "Samsung Mobiles",
    "price": 100.0,
    "quantity": 1000
}'

📋 2. Get All Products – GET /products
------------------------------------------------
curl http://localhost:8081/products

🔍 3. Get Product by ID – GET /products/{id}
-----------------------------------------------------
curl http://localhost:8081/products/1
Replace 1 with the desired product ID.



🔁 4. Update a Product – PUT /products/{id}
----------------------------------------------------
curl -X PUT http://localhost:8081/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "LG",
    "description": "LG Electronics",
    "price": 150.0,
    "quantity": 2000
}'

Replace 1 with the product ID you want to update.

❌ 5. Delete a Product – DELETE /products/{id}
-----------------------------------------------------------
curl -X DELETE http://localhost:8081/products/1

📦 6. Check Stock Availability – GET /products/{id}/stock?count={count}
--------------------------------------------------------------------------
curl "http://localhost:8081/products/1/stock?count=5"


This returns true or false depending on availability.

💰 7. Get Products Sorted by Price – GET /products/sorted/price
----------------------------------------------------------------------
curl http://localhost:8081/products/sorted/price


Returns products sorted in ascending order by price.

📝 Tips
Content-Type: application/json is required for requests that send a body (POST, PUT).





