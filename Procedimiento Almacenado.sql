DELIMITER //

CREATE PROCEDURE sp_crear_pedido(
    IN p_id_cliente INT,
    IN p_tipo_pedido ENUM('orden', 'expres'),
    IN p_total DECIMAL(10,2),
    IN p_pin VARCHAR(255) 
)
BEGIN
    DECLARE v_pedidos_activos INT;

    SELECT COUNT(*) INTO v_pedidos_activos 
    FROM Pedidos 
    WHERE id_cliente = p_id_cliente 
    AND estado NOT IN ('Entregado', 'Cancelado', 'No Reclamado');

    IF v_pedidos_activos >= 3 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El cliente ya tiene 3 pedidos activos. Debe esperar a que se entreguen o cancelen.';
    ELSE
        INSERT INTO Pedidos (fecha_creacion, pin_seguridad, total, estado, tipo_pedido, id_cliente)
        VALUES (NOW(), p_pin, p_total, 'Pendiente', p_tipo_pedido, p_id_cliente);
        
        SELECT LAST_INSERT_ID() AS nuevo_folio; 
    END IF;
END //

DELIMITER ;