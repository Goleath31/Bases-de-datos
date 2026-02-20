CREATE DATABASE IF NOT EXISTS panaderia_db;
USE panaderia_db;


CREATE TABLE Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL 
);


CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    domicilio TEXT, 
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL 
);


CREATE TABLE Telefono (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(15) NOT NULL,
    etiqueta VARCHAR(30) NOT NULL, 
    id_cliente INT NOT NULL,
    CONSTRAINT fk_telefono_cliente FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);


CREATE TABLE Producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo ENUM('Dulce', 'Salado', 'Integral') NOT NULL, 
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    estado ENUM('Disponible', 'No disponible') DEFAULT 'Disponible' 
);


CREATE TABLE Cupon (
    id_cupon INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    descuento DECIMAL(5,2) NOT NULL,
    vigencia DATE NOT NULL,
    limite_usos INT NOT NULL,
    usos_actuales INT DEFAULT 0
);


CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    estado ENUM('Pendiente', 'Listo', 'Entregado', 'Cancelado', 'No Entregado') DEFAULT 'Pendiente', 
    id_empleado INT,
    CONSTRAINT fk_pedido_empleado FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);


CREATE TABLE Pedido_Programado (
    id_pedido INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_cupon INT, 
    CONSTRAINT fk_prog_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    CONSTRAINT fk_prog_cliente FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    CONSTRAINT fk_prog_cupon FOREIGN KEY (id_cupon) REFERENCES Cupon(id_cupon)
);


CREATE TABLE Pedido_Express (
    id_pedido INT PRIMARY KEY,
    folio VARCHAR(20) UNIQUE NOT NULL,
    pin_seguridad VARCHAR(255) NOT NULL, 
    CONSTRAINT fk_exp_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);


CREATE TABLE Detalle_Pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    notas_adicionales TEXT, 
    precio_unitario DECIMAL(10,2) NOT NULL, 
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Historial_Estado (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    estado_anterior VARCHAR(20),
    estado_nuevo VARCHAR(20) NOT NULL,
    fecha_hora_cambio DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);