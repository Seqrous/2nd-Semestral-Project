IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'CompanyDB')
	BEGIN
		CREATE DATABASE [CompanyDB];
	END;

USE CompanyDB;

CREATE TABLE Employee (
	id int IDENTITY(1, 1) NOT NULL,
	firstName varchar(15) NOT NULL,
	lastName varchar(15) NOT NULL,
	phoneNumber varchar(12) NOT NULL,
	address varchar(25) NOT NULL,
	PRIMARY KEY (id),
)

CREATE TABLE Company (
	id int IDENTITY(1, 1) NOT NULL,
	name varchar(25) NOT NULL,
	email varchar(25) NOT NULL, 
	phoneNumber varchar(12) NOT NULL,
	PRIMARY KEY (id),
)

CREATE TABLE Orderr (
	id int IDENTITY(1, 1) NOT NULL,
	priceInTotal decimal(9, 2) NOT NULL,
	companyID int NOT NULL,
	discount int NOT NULL,
	state varchar(20) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (companyID) REFERENCES Company (id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
)

CREATE TABLE Material (
	id int IDENTITY(1, 1) NOT NULL,
	name varchar(20) NOT NULL,
	PRIMARY KEY (id),
)

CREATE TABLE Product (
	id int IDENTITY(1, 1) NOT NULL,
	materialID int NOT NULL,
	name varchar(20) NOT NULL,
	price decimal(5, 2) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (materialID) REFERENCES Material (id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
)

CREATE TABLE OrderLine (
	id int IDENTITY(1, 1) NOT NULL, 
	orderID int NOT NULL,
	productID int NOT NULL,
	amount int NOT NULL,
	historicalPrice decimal(5, 2) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (orderID) REFERENCES Orderr (id)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product (id)
)

INSERT INTO Material (name) values ('Steel')
INSERT INTO Material (name) values ('Bronze')
INSERT INTO Material (name) values ('Wood')
INSERT INTO Material (name) values ('Iron')
INSERT INTO Material (name) values ('Nickel')
INSERT INTO Material (name) values ('Copper')
INSERT INTO Material (name) values ('Titanium')