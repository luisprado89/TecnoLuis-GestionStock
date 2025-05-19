
DROP DATABASE IF EXISTS tecnoluisdb;
CREATE DATABASE tecnoluisdb CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE tecnoluisdb;

CREATE TABLE usuarios (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(200) COLLATE utf8_spanish_ci NOT NULL,
  correo VARCHAR(200) COLLATE utf8_spanish_ci NOT NULL,
  pass VARCHAR(50) COLLATE utf8_spanish_ci NOT NULL,
  rol VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO usuarios (nombre, correo, pass, rol) VALUES
('admin', 'admin', 'admin', 'Administrador'),
('Luis Fernández', 'luis@admin.com', 'admin123', 'Administrador'),
('Carmen Ruiz', 'carmen@admin.com', 'admin456', 'Administrador'),
('Pedro Martínez', 'pedro@admin.com', 'admin789', 'Vendedor'),
('Lucía Gómez', 'lucia@admin.com', 'admin321', 'Vendedor'),
('Javier Ortega', 'javier@admin.com', 'admin654', 'Administrador');

CREATE TABLE clientes (
  id INT(11) NOT NULL AUTO_INCREMENT,
  dni VARCHAR(9) COLLATE utf8_spanish_ci NOT NULL,
  nombre VARCHAR(180) COLLATE utf8_spanish_ci NOT NULL,
  telefono VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  direccion VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  razon_social VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO clientes (dni, nombre, telefono, direccion, razon_social) VALUES
('12345678', 'Juan Pérez', '600123456', 'Madrid', 'Consultores Pérez S.L.'),
('23456789', 'Ana Torres', '600234567', 'Barcelona', 'Torres Exportaciones S.A.'),
('34567890', 'Luis García', '600345678', 'Valencia', 'García y Asociados'),
('45678901', 'Marta Díaz', '600456789', 'Sevilla', 'Servicios Díaz'),
('56789012', 'Carlos López', '600567890', 'Bilbao', 'Distribuciones López S.L.');

CREATE TABLE proveedor (
  id INT(11) NOT NULL AUTO_INCREMENT,
  dni VARCHAR(9) COLLATE utf8_spanish_ci NOT NULL,
  nombre VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  telefono VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  direccion VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  razon_social VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO proveedor (dni, nombre, telefono, direccion, razon_social) VALUES
('10010010', 'Distribuciones ABC', '912345678', 'Madrid', 'Distribuciones ABC S.L.'),
('20020020', 'Componentes XYZ', '922345678', 'Valencia', 'XYZ Tech Supplies'),
('30030030', 'Proveedores Uno', '932345678', 'Zaragoza', 'Proveedores Uno S.A.'),
('40040040', 'LogiTechPro', '942345678', 'Sevilla', 'LogiTechPro S.L.'),
('50050050', 'Suministros Informáticos', '952345678', 'Barcelona', 'Suministros y Equipos Informáticos');

CREATE TABLE productos (
  id INT(11) NOT NULL AUTO_INCREMENT,
  codigo VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  nombre VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  descripcion VARCHAR(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  proveedor_id INT(11) NOT NULL,
  stock INT(11) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


INSERT INTO productos (codigo, nombre, descripcion, proveedor_id, stock, precio) VALUES
('A001', 'Monitor Samsung', 'Monitor de 24 pulgadas Full HD', 1, 10, 120.00),
('A002', 'Teclado Logitech', 'Teclado inalámbrico con teclas multimedia', 2, 25, 35.00),
('A003', 'Ratón inalámbrico', 'Ratón óptico inalámbrico con USB', 3, 50, 15.99),
('A004', 'Portátil HP', 'Portátil HP con Intel i5 y 8GB RAM', 4, 7, 599.99),
('A005', 'Disco SSD 1TB', 'Unidad SSD de 1TB para alto rendimiento', 5, 12, 89.90);


CREATE TABLE ventas (
  id INT(11) NOT NULL AUTO_INCREMENT,
  id_cliente INT(11) NOT NULL,
  vendedor VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO ventas (id_cliente, vendedor, total, fecha) VALUES
(1, 'Luis Fernández', 150.00, '2023-01-01 10:00:00'),
(2, 'Carmen Ruiz',     250.00, '2023-02-01 11:30:00'),
(3, 'Pedro Martínez',  350.00, '2023-03-01 15:15:00'),
(4, 'Lucía Gómez',     450.00, '2023-04-01 09:45:00'),
(5, 'Javier Ortega',   550.00, '2023-05-01 13:00:00');

CREATE TABLE detalle (
  id INT(11) NOT NULL AUTO_INCREMENT,
  id_producto INT(11) NOT NULL,
  cantidad INT(11) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  id_venta INT(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO detalle (id_producto, cantidad, precio, id_venta) VALUES
(1, 2, 120.00, 1),
(2, 1, 35.00, 2),
(3, 3, 15.99, 3),
(4, 1, 599.99, 4),
(5, 2, 89.90, 5);


-- almacena todos los datos de la empresa
CREATE TABLE config (
  id INT(11) NOT NULL AUTO_INCREMENT,
  dni VARCHAR(9) COLLATE utf8_spanish_ci NOT NULL,
  nombre VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL, -- nombre de la empresa
  telefono VARCHAR(20) NOT NULL,
  direccion VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL,
  razon_social VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL, -- razon social de la empresa
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO config (dni, nombre, telefono, direccion, razon_social) VALUES
('11111111', 'TecnoLuis S.L.', '911111111', 'Madrid', 'TecnoLuis S.L.'),
('22222222', 'Soluciones Red', '922222222', 'Sevilla', 'Soluciones Red S.A.'),
('33333333', 'SoftTech', '933333333', 'Valencia', 'SoftTech Group'),
('44444444', 'Grupo IT', '944444444', 'Bilbao', 'Grupo IT S.L.'),
('55555555', 'DesarrolloPlus', '955555555', 'Barcelona', 'DesarrolloPlus S.A.');

-- RELACIONES CON ALTER TABLE

ALTER TABLE productos
  ADD CONSTRAINT fk_productos_proveedor FOREIGN KEY (proveedor_id) REFERENCES proveedor(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ventas
  ADD CONSTRAINT fk_ventas_clientes FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE detalle
  ADD CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE ON UPDATE CASCADE;



-- Creamos la tabla empresa

CREATE TABLE empresa (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dni_nif VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  nombre VARCHAR(100) COLLATE utf8_spanish_ci NOT NULL,
  telefono VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  razon_social VARCHAR(100) COLLATE utf8_spanish_ci NOT NULL,
  direccion VARCHAR(255) COLLATE utf8_spanish_ci NOT NULL
);
INSERT INTO empresa (dni_nif, nombre, telefono, razon_social, direccion)
VALUES ('B12345678', 'TecnoLuis S.A.', '987654321', 'TecnoLuis S.A.', 'Calle Ficticia 123');
