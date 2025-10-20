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


### 1. Clone the repository

```open bash terminal or command promt and execute below commads
git clone https://github.com/AvatarMalekar/productmgmt.git
cd productmgmt
git checkout master

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



