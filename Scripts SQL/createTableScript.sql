CREATE TYPE role AS ENUM ('CUSTOMER', 'EMPLOYEE');

CREATE TABLE customer(
	id BIGSERIAL PRIMARY KEY,
	username VARCHAR(100),
	name VARCHAR(100),
	email VARCHAR(100),
	cpf VARCHAR(11),
	phone VARCHAR(13),
	password VARCHAR(40),
	role ROLE
);

CREATE TABLE address (
	id BIGSERIAL PRIMARY KEY,
	street VARCHAR(90),
	district VARCHAR(50),
	city VARCHAR(50),
	state VARCHAR(30),
	customer_id BIGINT REFERENCES customer(id)
);

CREATE TABLE employee (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(100),
	email VARCHAR(100),
	cpf VARCHAR(11),
	phone VARCHAR(13),
	password VARCHAR(40),
	role ROLE
);

CREATE TABLE product (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(100),
	description TEXT,
	price DECIMAL,
	imageURL TEXT
);

CREATE TABLE stock (
	product_id BIGINT REFERENCES product(id),
	quantity INTEGER
);

CREATE TABLE shoppingCart (
	id BIGSERIAL PRIMARY KEY,
	shippingPrice DECIMAL,
	customer_id BIGINT REFERENCES customer(id),
	address_id BIGINT REFERENCES address(id)
);

CREATE TABLE customerOrder(
	id BIGSERIAL PRIMARY KEY,
	total DECIMAL,
	moment TIMESTAMPTZ,
	shoppingCart_ID BIGINT REFERENCES shoppingCart(id),
	customer_ID BIGINT REFERENCES customer(id)
);

CREATE TABLE productItem(
	shoppingCart_ID BIGINT REFERENCES shoppingCart(id),
	product_ID BIGINT REFERENCES product(id),
	quantity INTEGER,
	price DECIMAL
);


