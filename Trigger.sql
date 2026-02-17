DELIMITER //

CREATE TRIGGER tr_liberar_producto_express
AFTER UPDATE ON Pedidos
FOR EACH ROW
BEGIN
    IF NEW.estado = 'Cancelado' THEN
        UPDATE Productos 
        SET disponible = TRUE 
        WHERE id_producto IN (SELECT id_producto FROM Detalle_Pedidos WHERE id_pedido = NEW.id_pedido);
    END IF;
END //

DELIMITER ;