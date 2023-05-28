package test;

import java.io.IOException;
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
import excepciones.ExcepcionTelefono;
import herramientas.QueryBD;

class TestQueryBD {
	
	private QueryBD bd;
	
	@BeforeEach
	public void testInicio() {
		bd = new QueryBD();
	}
	
	@Test
	void testCargarClientes() throws ExcepcionTelefono, IOException {  // IMPORTANTE: activar la conexion ConexionBD conexion en el método cargarClientes(ArrayList<Cliente>) en la clase QueryBD
		ArrayList<Cliente> clientesEsperados = new ArrayList<Cliente>();
		ArrayList<Cliente> clientesDevueltos = new ArrayList<Cliente>();
		clientesEsperados.add(new Cliente("Andrea","Manresa Carreño","03/05/2019",656565656,"Calle Constitucion, 14, 04400",""));
		clientesEsperados.add(new Cliente("Alberto","AMOROS BASTIAS","14/05/2023",666666666,"Av. Purisima, 45, 03360",""));
		clientesEsperados.add(new Cliente("Carlos","BANON CUIN","14/05/2023",666666777,"Av. Atzavares, 5, 03360",""));
		clientesEsperados.add(new Cliente("Daniel","CACERES DAMIAN","14/05/2023",666666888,"C. Virgen, 6, 03360",""));
		clientesEsperados.add(new Cliente("antonio","SEVA VALDES","25/05/2023",675675675,"Calle San Juan, 21, 03360",""));
		clientesEsperados.add(new Cliente("Ana","BAEZA PEREZ","14/05/2023",777777777,"C. Los Rosales, 4, 03360",""));
		clientesEsperados.add(new Cliente("Eli","GARCIA BERNA","14/05/2023",888888888,"Barrio San Jose, SN, 03360",""));

		clientesDevueltos = bd.cargarClientes(clientesDevueltos);
		for(int i = 0;i<clientesDevueltos.size();i++) {
			Assertions.assertEquals(clientesEsperados.get(i).getNombre(), clientesDevueltos.get(i).getNombre());
			Assertions.assertEquals(clientesEsperados.get(i).getApellidos(), clientesDevueltos.get(i).getApellidos());
			Assertions.assertEquals(clientesEsperados.get(i).getFechaDeAlta(), clientesDevueltos.get(i).getFechaDeAlta());
			Assertions.assertEquals(clientesEsperados.get(i).getTelefono(), clientesDevueltos.get(i).getTelefono());
			Assertions.assertEquals(clientesEsperados.get(i).getDireccion(), clientesDevueltos.get(i).getDireccion());
		}

	}
	
	@Test
	void testGuardarCliente() throws Exception {  // IMPORTANTE: activar la conexion TestConexionBD en el método guardarCliente(Cliente) 
		// y en el método cargarClientes(ArrayList<Cliente>) de la clase QueryB
		// Eliminar el registro Cliente para sucesivos test JUNIT
													// 
		Cliente c = new Cliente("Alejandro","Manresa Carrasco","03/05/2019",656565656,"Calle Constitucion, 14, 04400","");
		bd.guardarCliente(c);
		ArrayList<Cliente> clientesDevueltos = new ArrayList<Cliente>();
		clientesDevueltos = bd.cargarClientes(clientesDevueltos);
			Assertions.assertEquals(c.getNombre(), clientesDevueltos.get(0).getNombre());
			Assertions.assertEquals(c.getApellidos(), clientesDevueltos.get(0).getApellidos());
			Assertions.assertEquals(c.getFechaDeAlta(), clientesDevueltos.get(0).getFechaDeAlta());
			Assertions.assertEquals(c.getTelefono(), clientesDevueltos.get(0).getTelefono());
			Assertions.assertEquals(c.getDireccion(), clientesDevueltos.get(0).getDireccion());
		
	}

	
	@Test
	void testCargarBebidas() throws ExcepcionTelefono, IOException {  // IMPORTANTE: activar la conexion ConexionBD en el método cargarBebidas(ArrayList<Bebida>) de la clase QueryBD para este test JUNIT
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		ArrayList<Bebida> bebidasEsperadas = new ArrayList<Bebida>();
		ArrayList<Bebida> bebidasDevueltas = new ArrayList<Bebida>();
		bebidasEsperadas.add(new Bebida(205,"Batido",4.95,LocalDate.parse("2023-06-11", formatter),"Buen estado",0,false,true,"33cc"));
		bebidasEsperadas.add(new Bebida(1000,"Coca-cola",2.55,LocalDate.parse("2023-06-09", formatter),"Buen estado",0,true,false,"33cc"));
		bebidasEsperadas.add(new Bebida(3382,"Cerveza",3.25,LocalDate.parse("2023-06-09", formatter),"Buen estado",0,true,false,"33cc"));
		bebidasEsperadas.add(new Bebida(4624,"Agua",1.95,LocalDate.parse("2023-06-09", formatter),"Buen estado",0,false,false,"50cc"));
		bebidasEsperadas.add(new Bebida(7427,"Fanta limon",2.55,LocalDate.parse("2023-06-09", formatter),"Buen estado",0,true,false,"33cc"));
		bebidasEsperadas.add(new Bebida(7428,"Fanta naranja",2.55,LocalDate.parse("2023-06-09", formatter),"Buen estado",0,true,false,"33cc"));

		bebidasDevueltas = bd.cargarBebidas(bebidasDevueltas);
		for(int i = 0;i<bebidasDevueltas.size();i++) {
			Assertions.assertEquals(bebidasEsperadas.get(i).getCodigo(), bebidasDevueltas.get(i).getCodigo());
			Assertions.assertEquals(bebidasEsperadas.get(i).getNombre(), bebidasDevueltas.get(i).getNombre());
			Assertions.assertEquals(bebidasEsperadas.get(i).getPrecio(), bebidasDevueltas.get(i).getPrecio());
			Assertions.assertEquals(bebidasEsperadas.get(i).getFecha_caducidad(), bebidasDevueltas.get(i).getFecha_caducidad());
			Assertions.assertEquals(bebidasEsperadas.get(i).getEstado(), bebidasDevueltas.get(i).getEstado());
			Assertions.assertEquals(bebidasEsperadas.get(i).isGaseoso(), bebidasDevueltas.get(i).isGaseoso());
			Assertions.assertEquals(bebidasEsperadas.get(i).isLacteo(), bebidasDevueltas.get(i).isLacteo());
			Assertions.assertEquals(bebidasEsperadas.get(i).getMedida(), bebidasDevueltas.get(i).getMedida());
		}
	}
	
	@Test
	void testGuardarBebida() throws Exception {  // IMPORTANTE: activar la conexion TestConexionBD en el método guardarBebida(Bebida) 
		// y en el método cargarBebidas(ArrayList<Bebida>) de la clase QueryB
		// Eliminar el registro Bebida de la base de datos para sucesivos test JUNIT
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
	void testCargarComidas() throws ExcepcionTelefono, IOException {  // IMPORTANTE: activar la conexion ConexionBD en el método cargarComidas(ArrayList<Comida>) de la clase QueryBD para este test JUNIT
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		ArrayList<Comida> comidasEsperadas = new ArrayList<Comida>();
		ArrayList<Comida> comidasDevueltas = new ArrayList<Comida>();
		comidasEsperadas.add(new Comida(3224,"Ensalada",4.95,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,300,0,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(3225,"Ensaladilla",2.45,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,400,0,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(4148,"Huevos rotos",4.55,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,500,1,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(6873,"Chuletas",15.95,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,500,1,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(8312,"Gambas",12.95,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,300,1,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(8438,"Hamburguesa",8.85,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,600,1,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(8474,"Pat. bravas",3.95,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,400,0,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(9122,"Pizza",7.95,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,600,1,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(9279,"Pulpo",9.99,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,300,1,LocalDate.parse("2023-05-24", formatter)));
		comidasEsperadas.add(new Comida(9477,"Pat. fritas",2.45,LocalDate.parse("2023-06-03", formatter),"Buen estado",0,true,300,0,LocalDate.parse("2023-05-24", formatter)));

		comidasDevueltas = bd.cargarComidas(comidasDevueltas);
		for(int i = 0;i<comidasDevueltas.size();i++) {
			Assertions.assertEquals(comidasEsperadas.get(i).getCodigo(), comidasDevueltas.get(i).getCodigo());
			Assertions.assertEquals(comidasEsperadas.get(i).getNombre(), comidasDevueltas.get(i).getNombre());
			Assertions.assertEquals(comidasEsperadas.get(i).getPrecio(), comidasDevueltas.get(i).getPrecio());
			Assertions.assertEquals(comidasEsperadas.get(i).getFecha_caducidad(), comidasDevueltas.get(i).getFecha_caducidad());
			Assertions.assertEquals(comidasEsperadas.get(i).getEstado(), comidasDevueltas.get(i).getEstado());
			Assertions.assertEquals(comidasEsperadas.get(i).isPerecedero(), comidasDevueltas.get(i).isPerecedero());
			Assertions.assertEquals(comidasEsperadas.get(i).getCalorias(), comidasDevueltas.get(i).getCalorias());
			Assertions.assertEquals(comidasEsperadas.get(i).getVegano(), comidasDevueltas.get(i).getVegano());
			Assertions.assertEquals(comidasEsperadas.get(i).getFecha_envase(), comidasDevueltas.get(i).getFecha_envase());
		}
	}
	
	@Test
	void testGuardarComida() throws Exception {  // IMPORTANTE: activar la conexion TestConexionBD en el método guardarComida(Comida) 
		// y en el método cargarComidas(ArrayList<Comida>) de la clase QueryB
		// Eliminar el registro Comida de la base de datos para sucesivos test JUNIT
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
	    Assertions.assertEquals(cli.getTelefono(), rs.getInt(1));
	    Assertions.assertEquals(b.getCodigo(), rs.getInt(2));
	    Assertions.assertEquals(b.getCantidad(), rs.getInt(3));
	    Assertions.assertEquals(co.getCodigo(), rs.getInt(4));
	    Assertions.assertEquals(co.getCantidad(), rs.getInt(5));
	    Assertions.assertEquals(p.getImporteTotal(), rs.getInt(6));
	    Assertions.assertEquals(p.getCodigoPago(), rs.getInt(7));
	    Assertions.assertEquals(p.getEstado(), rs.getString(8));

	    // Cierre de recursos
	    rs.close();
	    stm.close();
	    cn.close();
	}
}
