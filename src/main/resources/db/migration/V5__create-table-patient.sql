CREATE TABLE patients(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    dni VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    district VARCHAR(100) NOT NULL,
    number VARCHAR(20) NOT NULL,
    city VARCHAR(100) NOT NULL,
    complement VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
)