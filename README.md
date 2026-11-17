# Projeto34 – JDBC and the DAO Pattern

A collection of Maven projects that walk through **JDBC with PostgreSQL**, from the first connection to a complete DAO-based application.

## Modules
| Folder | Topic |
|---|---|
| `Projeto-jdbc` | Connecting to the database and running a query |
| `Projeto-jdbc2` … `Projeto-jdbc4` | Inserting, updating and querying data with `PreparedStatement` |
| `Projeto-jdbc5`, `Projeto-jdbc6` | Deleting records and handling integrity violations (`DBIntegrityException`) |
| `Projeto-dao-jdbc` | Full **DAO pattern** implementation for `Seller` and `Department` |

## DAO module layout
```
application   Program, Program2 – manual test scenarios (findById, findByDepartment, findAll, insert, update, delete)
db            DB (connection helper), DbException, DBIntegrityException
model.entities  Seller, Department
model.dao       SellerDao, DepartmentDao (interfaces), DaoFactory
model.impl      SellerDaoJDBC, DepartmentDaoJDBC
```

## Requirements
- JDK and Maven
- PostgreSQL with a `coursejdbc` database/schema containing the `department` and `seller` tables

## Configuration
Each module reads a `db.properties` file from its own folder. The real file is **git-ignored**; copy the template and fill in your own values:
```bash
cp db.properties.example db.properties   # inside each module folder
```
```properties
user=YOUR_USER
password=YOUR_PASSWORD
dburl=jdbc:postgresql://localhost:5432/coursejdbc?currentSchema=coursejdbc
useSSL=false
```

## Running
Open a module in IntelliJ IDEA (or use Maven) and run `application.Program`.
