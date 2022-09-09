-- Database
CREATE DATABASE myproducts;
\c myproducts;

CREATE TABLE products (
	id int NOT NULL,
	name character varying NOT NULL,
	brand character varying NOT NULL,
	brandProducer character varying NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO products (id, name, brand, brandProducer) VALUES
    ('1', 'Nutella', 'Ferrero', 'Ferrero');
INSERT INTO products (id, name, brand, brandProducer) VALUES
    ('2', 'San Benedetto', 'Fonte essenziale', 'Nestle');
INSERT INTO products (id, name, brand, brandProducer) VALUES
    ('3', 'Patate Lays', 'Patate azienda', 'Patate Holding');
COMMIT;
