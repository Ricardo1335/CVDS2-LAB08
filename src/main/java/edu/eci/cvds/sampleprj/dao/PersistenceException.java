package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PersistenceException(String string, org.apache.ibatis.exceptions.PersistenceException e) {
    }
    
}
