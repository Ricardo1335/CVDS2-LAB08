package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class ServiceExample {
    public static void main(String args[]) {
        try { 
            System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarClientes());
        } catch (ExcepcionServiciosAlquiler e) {
        System.out.println(e.getMessage()); 
                e.printStackTrace();
        }
    }
}
