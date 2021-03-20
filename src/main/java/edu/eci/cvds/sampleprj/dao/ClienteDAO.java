package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;


import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {
 
    public Cliente consultarCliente(int id) throws PersistenceException;

    /**
     * Registrar un nuevo item rentado asociado al cliente identificado con 'idc' y
     * relacionado con el item identificado con 'idi'
     * 
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin
     */
    public void agregarItemRentadoACliente(int id,int idit, Date fechainicio, Date fechafin) throws PersistenceException;

    /**
     * Consultar todos los clientes
     * 
     * @return
     */
    public List<Cliente> consultarClientes() throws PersistenceException;
}
