package edu.eci.cvds.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception{

    

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public ExcepcionServiciosAlquiler(String string, PersistenceException ex) {
        super(string,ex);
    }

    public ExcepcionServiciosAlquiler(String string) {
        super(string);
    }

    public ExcepcionServiciosAlquiler(String string, edu.eci.cvds.sampleprj.dao.PersistenceException ex) {
        super(string,ex);
    }
}
