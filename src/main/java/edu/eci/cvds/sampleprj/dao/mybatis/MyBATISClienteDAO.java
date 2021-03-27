package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;
import com.google.inject.*;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;

public class MyBATISClienteDAO implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;    
    @Override
    public Cliente consultarCliente(int id) throws PersistenceException{
        // TODO Auto-generated method stub
        try{
           return clienteMapper.consultarCliente(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+id,e);
        } 
    }

    @Override
    public void agregarItemRentadoACliente(int id, int idit, Date fechainicio, Date fechafin)throws PersistenceException {
        // TODO Auto-generated method stub
        try{
            clienteMapper.agregarItemRentadoACliente(id,idit,fechafin,fechafin);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agregar el item "+id,e);
         } 
    }

    @Override
    public List<Cliente> consultarClientes()throws PersistenceException {
        // TODO Auto-generated method stub
        try{
            return clienteMapper.consultarClientes();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar clientes",e);
         } 
    }
    @Override
    public List<ItemRentado> getItemsCliente(int idCliente) throws PersistenceException {
        try {
            return (List<ItemRentado>) clienteMapper.consultarCliente(idCliente);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar los items de los clientes", e);
        }
    }
    @Override
    public void save(Cliente c) throws PersistenceException {
        try {
            clienteMapper.agregarCliente(c);
        } catch (Exception e) {
            throw new PersistenceException(e.toString(), e);
        }

    }

    @Override
    public void vetarCliente(long idCliente, int estado) throws PersistenceException {
        try{
            clienteMapper.vetarCliente(idCliente,estado);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException(PersistenceException.V_CLIENTE);
        }

    }
}
