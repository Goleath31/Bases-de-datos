INSERT INTO Clientes (nombre, apellido_paterno, apellido_materno, domicilio_entrega, fecha_nacimiento, contrasena) 
VALUES 
('Juan', 'Perez', 'Garcia', 'Calle Siempre Viva 123', '1990-05-15', '1523487'),
('Ana', 'Lopez', 'Martinez', NULL, '1985-10-20', '1523488'), 
('Carlos', 'Sánchez', 'Ruiz', 'Av. Tecnológico 456', '2000-01-01', '1523489');

INSERT INTO Telefonos (numero, etiqueta, id_cliente) 
VALUES 
('6441234567', 'Casa', 1), 
('6449876543', 'Trabajo', 1), 
('6445554433', 'Celular Personal', 2),
('6441112233', 'Casa', 3);

INSERT INTO Productos (nombre, lista_ingredientes, descripcion, precio, disponible, tipo) 
VALUES 
('Concha de Vainilla', 'Harina, azúcar, mantequilla, levadura', 'Pan dulce tradicional con cobertura blanca', 18.00, TRUE, 'dulce'),
('Bolillo', 'Harina de trigo, agua, sal', 'Pan salado crujiente', 6.50, TRUE, 'salado'),
('Muffin Integral', 'Harina integral, avena, miel', 'Muffin saludable con alto contenido de fibra', 25.00, TRUE, 'integral'),
('Dona de Chocolate', 'Harina, chocolate amargo, chispas', 'Dona frita cubierta de chocolate', 20.00, FALSE, 'dulce');

INSERT INTO Cupones (codigo, descuento, usos_maximos, usos_actuales, fecha_inicio, fecha_final) 
VALUES 
('BIENVENIDA10', 10.00, 100, 5, '2024-01-01', '2026-12-31'),
('PROMO_VENCIDA', 15.00, 50, 50, '2023-01-01', '2023-12-31'),
('CUPON20', 20.00, 10, 2, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY));


INSERT INTO Empleados (nombre, apellido_paterno, apellido_materno, usuario, contrasena) 
VALUES 
('Admin', 'Panaderia', 'Central', 'admin_sucursal', '1523482'),
('Pedro', 'Gómez', 'Díaz', 'pedro_vendedor', '1523481');

INSERT INTO Pedidos (fecha_creacion, pin_seguridad, total, estado, tipo_pedido, id_cliente, id_cupon) 
VALUES 
(NOW(), NULL, 36.00, 'Pendiente', 'orden', 1, NULL),
(DATE_SUB(NOW(), INTERVAL 10 MINUTE), '87654321', 18.00, 'Listo', 'expres', NULL, NULL),
(DATE_SUB(NOW(), INTERVAL 1 HOUR), NULL, 120.00, 'Entregado', 'orden', 2, 1);

INSERT INTO Detalle_Pedidos (cantidad, precio_unitario, notas_personalizadas, id_pedido, id_producto) 
VALUES 
(2, 18.00, 'Muy tostadas por favor', 1, 1),
(1, 18.00, NULL, 2, 1),
(10, 6.50, NULL, 3, 2);

INSERT INTO Pagos (fecha_pago, monto_pagado, metodo_pago, id_pedido, id_empleado) 
VALUES 
(NOW(), 120.00, 'efectivo', 3, 2);

INSERT INTO Historial (estado_anterior, estado_nuevo, fecha_cambio, id_pedido) 
VALUES 
('Pendiente', 'En Preparación', DATE_SUB(NOW(), INTERVAL 50 MINUTE), 3),
('En Preparación', 'Listo', DATE_SUB(NOW(), INTERVAL 30 MINUTE), 3),
('Listo', 'Entregado', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 3);