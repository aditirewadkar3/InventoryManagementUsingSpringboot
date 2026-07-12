# Inventory & Warehouse Management System

> A Spring Boot based **Inventory & Warehouse Management System** that helps manage products, categories, warehouses, and inventory with advanced features like **SKU Generation, Barcode & QR Code Generation, Excel Import/Export, Inventory Transfer, and Optimistic Locking**.

---

# 📌 Project Overview

The **Inventory & Warehouse Management System** is designed to simplify inventory operations for businesses by providing an efficient way to manage products across multiple warehouses.

The application supports product categorization, warehouse management, stock tracking, inventory transfers, Excel-based bulk product management, barcode/QR code generation, and concurrency control using optimistic locking.

---

# 🚀 Features

## Category Management

* Create Category
* Update Category
* Delete Category
* Get Category by ID
* Get All Categories

---

## Product Management

* Create Product
* Update Product
* Delete Product
* Get Product by ID
* Get All Products
* Search Product by Name

### Advanced Features

* Automatic SKU Generation
* Barcode Generation
* QR Code Generation
* Excel Import
* Excel Export

---

## Warehouse Management

* Create Warehouse
* Update Warehouse
* Delete Warehouse
* Get Warehouse by ID
* Get All Warehouses

---

## Inventory Management

* Create Inventory
* Stock In
* Stock Out
* Transfer Stock Between Warehouses
* View Inventory
* Low Stock API
* Optimistic Locking using `@Version`

---

## Dashboard

Displays

* Total Categories
* Total Products
* Total Warehouses
* Total Inventory Records
* Low Stock Products

---

# 🏗️ Project Architecture

```
Client
   │
   ▼
REST Controller
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

---

# 📂 Project Structure

```
src
│
├── controller
│
├── service
│      ├── interface
│      └── implementation
│
├── repository
│
├── entity
│
├── dto
│      ├── request
│      └── response
│
├── mapper
│
├── util
│      ├── BarcodeGenerator
│      ├── QRCodeGenerator
│      ├── SKUGenerator
│      └── ExcelHelper
│
├── exception
│
├── common
│
└── config
```

---

# 🗄️ Database Design

## Category

| Field       | Type   |
| ----------- | ------ |
| id          | Long   |
| name        | String |
| description | String |

---

## Product

| Field        | Type      |
| ------------ | --------- |
| id           | Long      |
| name         | String    |
| description  | String    |
| sellingPrice | Double    |
| costPrice    | Double    |
| sku          | String    |
| barcode      | String    |
| qrCode       | String    |
| category     | ManyToOne |

---

## Warehouse

| Field       | Type   |
| ----------- | ------ |
| id          | Long   |
| name        | String |
| location    | String |
| managerName | String |

---

## Inventory

| Field     | Type      |
| --------- | --------- |
| id        | Long      |
| quantity  | Integer   |
| version   | Long      |
| product   | ManyToOne |
| warehouse | ManyToOne |

---

# 🔄 Application Workflow

```
Category
      │
      ▼
Product
      │
      ▼
Warehouse
      │
      ▼
Inventory
      │
      ├───────────────┐
      │               │
      ▼               ▼
 Stock In         Stock Out
      │
      ▼
Transfer Stock
```

---

# ⚙️ Technologies Used

### Backend

* Java 25 LTS
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Database

* MySQL

### Utilities

* Apache POI (Excel Import/Export)
* ZXing (QR Code)
* Barcode4J (Barcode Generation)

### API Testing

* Postman
* Swagger UI

---

# 🔐 Advanced Features

## SKU Generation

Each product receives a unique SKU automatically.

Example

```
LAP-0001
MOU-0002
KEY-0003
```

---

## Barcode Generation

A barcode image is generated automatically for every product.

Example

```
SKU

↓

Barcode

↓

Stored in Project
```

---

## QR Code Generation

Each product receives a QR Code containing

* Product Name
* SKU
* Selling Price

---

## Excel Import

Bulk product upload using Excel.

```
Excel File

↓

Apache POI

↓

Read Rows

↓

Generate SKU

↓

Generate Barcode

↓

Generate QR

↓

Save into Database
```

---

## Excel Export

Exports all products into an Excel file.

```
Database

↓

Apache POI

↓

products.xlsx
```

---

## Optimistic Locking

Implemented using

```java
@Version
private Long version;
```

This prevents multiple users from updating the same inventory record simultaneously.

---

## Stock Transfer

Transfer inventory between warehouses.

```
Warehouse A

100 Units

↓

Transfer 30

↓

Warehouse B

20 Units

↓

Result

Warehouse A = 70

Warehouse B = 50
```

---

# 📡 REST APIs

## Category APIs

| Method | Endpoint               |
| ------ | ---------------------- |
| POST   | `/api/categories`      |
| GET    | `/api/categories`      |
| GET    | `/api/categories/{id}` |
| PUT    | `/api/categories/{id}` |
| DELETE | `/api/categories/{id}` |

---

## Product APIs

| Method | Endpoint                        |
| ------ | ------------------------------- |
| POST   | `/api/products`                 |
| GET    | `/api/products`                 |
| GET    | `/api/products/{id}`            |
| PUT    | `/api/products/{id}`            |
| DELETE | `/api/products/{id}`            |
| GET    | `/api/products/search?keyword=` |
| GET    | `/api/products/export`          |
| POST   | `/api/products/import`          |

---

## Warehouse APIs

| Method | Endpoint               |
| ------ | ---------------------- |
| POST   | `/api/warehouses`      |
| GET    | `/api/warehouses`      |
| GET    | `/api/warehouses/{id}` |
| PUT    | `/api/warehouses/{id}` |
| DELETE | `/api/warehouses/{id}` |

---

## Inventory APIs

| Method | Endpoint                   |
| ------ | -------------------------- |
| POST   | `/api/inventory/create`    |
| POST   | `/api/inventory/stock-in`  |
| POST   | `/api/inventory/stock-out` |
| POST   | `/api/inventory/transfer`  |
| GET    | `/api/inventory`           |
| GET    | `/api/inventory/{id}`      |
| GET    | `/api/inventory/low-stock` |

---

## Dashboard API

| Method | Endpoint         |
| ------ | ---------------- |
| GET    | `/api/dashboard` |

---

# ▶️ How to Run the Project

## Clone Repository

```bash
git clone https://github.com/your-username/inventory-management-system.git
```

---

## Configure Database

Create a MySQL database:

```sql
CREATE DATABASE inventory_management;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Install Dependencies

```bash
mvn clean install
```

---

## Run the Application

```bash
mvn spring-boot:run
```

---

## Open Swagger

```
http://localhost:8080/swagger-ui/index.html
```


# 👩‍💻 Author

**Aditi Rewadkar**


**Tech Stack:** Java • Spring Boot • MySQL • Hibernate • Maven • REST APIs

