DELIMITER //

CREATE PROCEDURE BuscarProductoCompleto(IN busqueda VARCHAR(100))
BEGIN
    SELECT 
        id_producto, 
        nombre, 
        tipo,          
        descripcion,   
        precio,         
        estado         
    FROM Producto
    WHERE id_producto = busqueda 
       OR nombre LIKE CONCAT('%', busqueda, '%');
END //

DELIMITER ;