package edu.eci.cvds.test;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.ognl.ParseException;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(2159581);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        };
    }


    @Test
    public void ConsultarCostoDeUnItemAdicionado() throws ExcepcionServiciosAlquiler{
        try {
            Item item = new Item(new TipoItem(44, "prueba" ),6,
                    "bicicleta", "con firma de cr7", new SimpleDateFormat("yyyy/MM/dd").parse("2077/12/26"),
                    1000,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            Assert.assertEquals(100*5, serviciosAlquiler.consultarCostoAlquiler(6, 5));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }



    @Test
    public void ConsultarCliente(){
        try {
            ArrayList<ItemRentado> itemRentados = new ArrayList<ItemRentado>();
            Cliente cliente = new Cliente("Juanito",1193555822,"334565037","cra 105 #42-56 a","boldman@moonlight.com",false,itemRentados);
            serviciosAlquiler.registrarCliente(cliente);
            Assert.assertEquals("Juanito", serviciosAlquiler.consultarCliente(1193555822).getNombre());
        } catch (Exception e) {
            Assert.fail("fail");
        }
    }

    @Test
    public void ConsultarTarifaxDia() throws ParseException, ExcepcionServiciosAlquiler {
        try {
            /* Cliente cliente = new Cliente("Nombre",111,"telefono","direccion","email",false,null); */
            Item item = new Item(new TipoItem(44, "prueba" ),45,
                    "bicicleta", "con firma de cr7", new SimpleDateFormat("yyyy/MM/dd").parse("2077/12/26"),
                    1000,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            Assert.assertEquals(100, serviciosAlquiler.valorMultaRetrasoxDia(45));
        } catch (Exception e) {
            Assert.assertTrue(false);
        } 
    }

    @Test
    public void ConsultarItem(){
        try {
            Item item = new Item(new TipoItem(100, "prueba" ),142,
                    "prueba", "un test", new SimpleDateFormat("yyyy/MM/dd").parse("2022/7/4"),
                    1000,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            Assert.assertEquals(150, serviciosAlquiler.consultarItem(142).getId());
        } catch (Exception e) {
            Assert.fail("fail");
        }
    }

    @Test
    public void ActualizarTarifaItem(){
        try {
            Item item = new Item(new TipoItem(150, "prueba" ),150,
                    "prueba", "prueba Pai", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/4"),
                    1000,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            serviciosAlquiler.actualizarTarifaItem(150, 200);
            Assert.assertEquals(200, serviciosAlquiler.consultarItem(2).getTarifaxDia());
        } catch (Exception e) {
            Assert.fail("fail");
        }
    }

    @Test
    public void RegistrarItemaCliente() throws ExcepcionServiciosAlquiler {
        try {
            Item item = new Item(new TipoItem(14, "xd" ),1010,
                    "telefono", "telefono", new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/24"),
                    1000,"prueba","prueba");
            Cliente cliente = new Cliente("prueba", 207742, "prueba", "prueba","prueba");
            serviciosAlquiler.registrarCliente(cliente);
            serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(LocalDate.parse("2022/01/24")) , serviciosAlquiler.consultarCliente(207742).getDocumento() , item , 4 );
            Assert.assertEquals(Date.valueOf(LocalDate.parse("2022/11/04")), serviciosAlquiler.consultarCliente(207427).getRentados().get(0).getFechainiciorenta());
        } catch (Exception e) {
            Assert.fail("fail");
        }
    }


}