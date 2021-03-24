package edu.eci.cvds.samples.services.client;

import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class ServiceExample {
    public static void main(String args[]) {
        try { 
            ServiciosAlquiler sa = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
            System.out.println(sa);
            List<Cliente> clientes = sa.consultarClientes();
            System.out.println(clientes);
        } catch (ExcepcionServiciosAlquiler e) {
            System.out.println(e.getMessage()); 
            e.printStackTrace();
        }
    }
}
