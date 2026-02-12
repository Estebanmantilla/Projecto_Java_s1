create database tecnostore_db;
use tecnostore_db;

 CREATE TABLE cliente (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL,
  identificacion varchar(50) NOT NULL,
  telefono varchar(50) NOT NULL,
  correo varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_identificacion (identificacion)
 );


  CREATE TABLE marca (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL,
  PRIMARY KEY (id)
  );


  CREATE TABLE celular (
  id int NOT NULL AUTO_INCREMENT,
  marca int NOT NULL,
  sistema_operativo enum('ios','android','colorOS') DEFAULT NULL,
  gama enum('baja','media','alta') DEFAULT NULL,
  precio int NOT NULL,
  stock int NOT NULL,
  modelo varchar(50) NOT NULL,
  PRIMARY KEY (id),
  KEY marca (marca),
  CONSTRAINT celular_ibfk_1 FOREIGN KEY (marca) REFERENCES marca (id)
  );


  CREATE TABLE ventas (
  id int NOT NULL AUTO_INCREMENT,
  id_cliente int NOT NULL,
  fecha datetime DEFAULT CURRENT_TIMESTAMP,
  total double DEFAULT NULL,
  PRIMARY KEY (id),
  KEY id_cliente (id_cliente),
  CONSTRAINT ventas_ibfk_1 FOREIGN KEY (id_cliente) REFERENCES cliente (id)
  );

  CREATE TABLE detalle_ventas (
  id int NOT NULL AUTO_INCREMENT,
  id_venta int DEFAULT NULL,
  id_celular int DEFAULT NULL,
  cantidad int NOT NULL,
  subtotal double DEFAULT NULL,
  PRIMARY KEY (id),
  KEY id_venta (id_venta),
  KEY id_celular (id_celular),
  CONSTRAINT detalle_ventas_ibfk_1 FOREIGN KEY (id_venta) REFERENCES ventas (id),
  CONSTRAINT detalle_ventas_ibfk_2 FOREIGN KEY (id_celular) REFERENCES celular (id)
  );