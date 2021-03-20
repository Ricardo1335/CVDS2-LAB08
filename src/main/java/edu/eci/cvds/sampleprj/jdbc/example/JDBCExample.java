
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1: "+ valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2162405;
            registrarNuevoProducto(con, suCodigoECI, "JP AMAYA", 99999999);            
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        //Asignar parámetros
        //usar 'execute'
        String insertString =
        "INSERT into ORD_PRODUCTOS values (?,?,?);";

  
      try (PreparedStatement insertProduct = con.prepareStatement(insertString))
      
      {
        con.setAutoCommit(false);
            insertProduct.setInt(1,codigo);
            insertProduct.setString(2,nombre);
            insertProduct.setInt(3,precio);
            insertProduct.execute();
            con.commit();
        
      } catch (SQLException e) {
        System.err.print(e);
        if (con != null) {
          try {
            System.err.print("Transaction is being rolled back");
            con.rollback();
          } catch (SQLException excep) {
            System.err.print(excep);
          }
        }
        
        con.commit();
        
    }
}
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultados del ResultSet
        //Llenar la lista y retornarla
        String readString ="SELECT ORD_PRODUCTOS.nombre FROM ORD_DETALLE_PEDIDO LEFT JOIN ORD_PEDIDOS ON ORD_PEDIDOS.codigo = ORD_DETALLE_PEDIDO.pedido_fk LEFT JOIN ORD_PRODUCTOS ON ORD_PRODUCTOS.codigo = ORD_DETALLE_PEDIDO.producto_fk WHERE ORD_PEDIDOS.codigo = ?;";
        try (PreparedStatement readOrder = con.prepareStatement(readString))
      
        {
          con.setAutoCommit(false);
              readOrder.setInt(1,codigoPedido);
              ResultSet rs = readOrder.executeQuery();
              while (rs.next()){
                  np.add(rs.getString("nombre"));
              }
              con.commit();
          
        } catch (SQLException e) {
          System.err.print(e);
          if (con != null) {
            try {
              System.err.print("Transaction is being rolled back");
              con.rollback();
            } catch (SQLException excep) {
              System.err.print(excep);
            }
          }
        }
        return np;
    }
    
    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultado del ResultSet
        int resultado=0;
        String readString ="SELECT SUM(ORD_PRODUCTOS.precio*cantidad) AS suma FROM ORD_DETALLE_PEDIDO LEFT JOIN ORD_PEDIDOS ON ORD_PEDIDOS.codigo = ORD_DETALLE_PEDIDO.pedido_fk LEFT JOIN ORD_PRODUCTOS ON ORD_PRODUCTOS.codigo = ORD_DETALLE_PEDIDO.producto_fk WHERE ORD_PEDIDOS.codigo = ?;";
        try (PreparedStatement readOrder1 = con.prepareStatement(readString))
      
        {
          con.setAutoCommit(false);
              readOrder1.setInt(1,codigoPedido);
              ResultSet rs = readOrder1.executeQuery();
              while (rs.next()){
                resultado = rs.getInt("suma");
              }
              con.commit();
          
        } catch (SQLException e) {
          System.err.print(e);
          if (con != null) {
            try {
              System.err.print("Transaction is being rolled back");
              con.rollback();
            } catch (SQLException excep) {
              System.err.print(excep);
            }
          }
        }
        return resultado;
    }
    

    
    
    
}