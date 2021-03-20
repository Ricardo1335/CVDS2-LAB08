package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;
import com.google.inject.*;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{
    @Inject
    private TipoItemMapper tipoItemMapper;    
    @Override
    public List<TipoItem> getTiposItems()throws PersistenceException {
        // TODO Auto-generated method stub
        try{
            return tipoItemMapper.getTiposItems();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar tipos ",e);
         } 
    }

    @Override
    public TipoItem getTipoItem(int id)throws PersistenceException {
        // TODO Auto-generated method stub
        try{
            return tipoItemMapper.getTipoItem(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar tipo "+id,e);
         } 
    }

    @Override
    public void addTipoItem(String des)throws PersistenceException {
        // TODO Auto-generated method stub
        try{
             tipoItemMapper.addTipoItem(des);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al añadir el tipo item "+des,e);
         } 
    }
    
}
