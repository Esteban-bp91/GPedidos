package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Bebida;
import clases.Cliente;
import clases.Comida;
import clases.Pedido;

class TestQueryBD {
	
	private QueryBDtest bd;
	
	@BeforeEach
	public void testInicio() {
		bd = new QueryBDtest();
	}
	
	@Test
	void testGuardarCargarClientes() throws Exception {  
		ArrayList<Cliente> clientesEsperados = new ArrayList<Cliente>();
		ArrayList<Cliente> clientesDevueltos = new ArrayList<Cliente>();
		Cliente x = new Cliente("Antonio","Gutierrez Velez","03/05/2019",636363636,"Calle Morera, 33, 04400","");
		bd.guardarCliente(x);
		clientesEsperados.add(x);

		clientesDevueltos = bd.cargarClientes(clientesDevueltos);
		int i = 0;
			Assertions.assertEquals(clientesEsperados.get(i).getNombre(), clientesDevueltos.get(i).getNombre());
			Assertions.assertEquals(clientesEsperados.get(i).getApellidos(), clientesDevueltos.get(i).getApellidos());
			Assertions.assertEquals(clientesEsperados.get(i).getFechaDeAlta(), clientesDevueltos.get(i).getFechaDeAlta());
			Assertions.assertEquals(clientesEsperados.get(i).getTelefono(), clientesDevueltos.get(i).getTelefono());
			Assertions.assertEquals(clientesEsperados.get(i).getDireccion(), clientesDevueltos.get(i).getDireccion());
	}
	
	@Test
	void testGuardarCargarBebidas() throws Exception {  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");				
		Bebida b = new Bebida(1000,"Coca-cola",2.55,LocalDate.parse("2023-06-09", formatter),"Buen estado",0,true,false,"33cc");
		bd.guardarBebida(b);
		ArrayList<Bebida> bebidasDevueltas = new ArrayList<Bebida>();
		bebidasDevueltas = bd.cargarBebidas(bebidasDevueltas);
			Assertions.assertEquals(b.getCodigo(), bebidasDevueltas.get(0).getCodigo());
			Assertions.assertEquals(b.getNombre(), bebidasDevueltas.get(0).getNombre());
			Assertions.assertEquals(b.getPrecio(), bebidasDevueltas.get(0).getPrecio());
			Assertions.assertEquals(b.getFecha_caducidad(), bebidasDevueltas.get(0).getFecha_caducidad());
			Assertions.assertEquals(b.getEstado(), bebidasDevueltas.get(0).getEstado());
			Assertions.assertEquals(b.isGaseoso(), bebidasDevueltas.get(0).isGaseoso());
			Assertions.assertEquals(b.isLacteo(), bebidasDevueltas.get(0).isLacteo());
			Assertions.assertEquals(b.getMedida(), bebidasDevueltas.get(0).getMedida());
	}
	
	@Test
	void testGuardarComida() throws Exception {  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");									
		Comida c = new Comida(3225,"Ensaladilla",2.45,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,400,0,LocalDate.parse("2023-05-24", formatter));
		bd.guardarComida(c);
		ArrayList<Comida> comidasDevueltas = new ArrayList<Comida>();
		comidasDevueltas = bd.cargarComidas(comidasDevueltas);
			Assertions.assertEquals(c.getCodigo(), comidasDevueltas.get(0).getCodigo());
			Assertions.assertEquals(c.getNombre(), comidasDevueltas.get(0).getNombre());
			Assertions.assertEquals(c.getPrecio(), comidasDevueltas.get(0).getPrecio());
			Assertions.assertEquals(c.getFecha_caducidad(), comidasDevueltas.get(0).getFecha_caducidad());
			Assertions.assertEquals(c.getEstado(), comidasDevueltas.get(0).getEstado());
			Assertions.assertEquals(c.isPerecedero(), comidasDevueltas.get(0).isPerecedero());
			Assertions.assertEquals(c.getCalorias(), comidasDevueltas.get(0).getCalorias());
			Assertions.assertEquals(c.getVegano(), comidasDevueltas.get(0).getVegano());
			Assertions.assertEquals(c.getFecha_envase(), comidasDevueltas.get(0).getFecha_envase());
	}
	
	@Test
	void testGuardarPedido() throws Exception { // IMPORTANTE: activar la conexion TestConexionBD en el método guardarPedido(Pedido) de la clase QueryBD 
		// Eliminar el registro Pedido de la base de datos para sucesivos test JUNIT
		// NOTA: Para realizar este test JUNIT debe corregir los datos usuario y contraseña del parámetro pasado a DriverManager.getConnection
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Cliente cli = new Cliente("Eli","GARCIA BERNA","14/05/2023",888888888,"Barrio San Jose, SN, 03360","");
		Comida co = new Comida(3225,"Ensaladilla",2.45,LocalDate.parse("2023-06-03", formatter),"Buen estado",1,true,400,0,LocalDate.parse("2023-05-24", formatter));
		Bebida b = new Bebida(1000,"Coca-cola",2.55,LocalDate.parse("2023-06-09", formatter),"Buen estado",1,true,false,"33cc");
		Pedido p = new Pedido(cli,b,co,5.00,121843502,"SERVIDO");
		
		bd.guardarPedido(p);
		
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3310/testgpedidos"+
				"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
				"serverTimezone=UTC&useSSL=false", "root", "1234");
	    Statement stm = cn.createStatement();
	    ResultSet rs = stm.executeQuery("SELECT * FROM pedido where num_cliente = 888888888");
	    Assertions.assertTrue(rs.next()); // Verifica que al menos un registro fue devuelto
	    Assertions.assertEquals(p.getCodigoPago(), rs.getInt(1));
	    Assertions.assertEquals(cli.getTelefono(), rs.getInt(2));
	    Assertions.assertEquals(b.getCodigo(), rs.getInt(3));
	    Assertions.assertEquals(b.getCantidad(), rs.getInt(4));
	    Assertions.assertEquals(co.getCodigo(), rs.getInt(5));
	    Assertions.assertEquals(co.getCantidad(), rs.getInt(6));
	    Assertions.assertEquals(p.getImporteTotal(), rs.getInt(7));
	    Assertions.assertEquals(p.getEstado(), rs.getString(8));

	    // Cierre de recursos
	    rs.close();
	    stm.close();
	    cn.close();
	}
}
