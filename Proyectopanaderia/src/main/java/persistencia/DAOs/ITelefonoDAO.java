/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import java.util.List;
import persistencia.dominio.Telefono;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public interface ITelefonoDAO {
    
    public List<Telefono> consultarTelefonos(int id) throws PersistenciaException;
    
    public Telefono editarTelefono(int id, Telefono telefonoNuevo) throws PersistenciaException;
    
    public boolean eliminarTelefono(int id) throws PersistenciaException;
    
    public Telefono crearTelefono(Telefono telefono) throws PersistenciaException;
}
