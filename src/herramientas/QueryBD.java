package herramientas;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import clases.Bebida;
import clases.Cliente;
import clases.Comida;
import clases.Pedido;
import excepciones.ExcepcionTelefono;

/**
 * Clase QueryBD del software GPedidos
 * @author Esteban Baeza Pérez
 * @version 0.4
 * @since 27/05/2023
 */
public class QueryBD {
	//Parámetros necesarios para las querys a la base de datos
	ConexionBD conexion = new ConexionBD();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;
	TestConexionBD testconexion = new TestConexionBD();
	
	private static String insertTableSQL;
	
	/**
	 * Método cargarClientes(ArrayList<Cliente>) para cargar los clientes registrados en la base de datos
	 * @param ArrayList<Cliente> clientes donde guardará los clientes cargados de la base de datos
	 * @return el ArrayList<Cliente> clientes con los clientes cargados
	 * @throws ExcepcionTelefono
	 * @throws IOException
	 */
	public ArrayList<Cliente> cargarClientes(ArrayList<Cliente> clientes) throws ExcepcionTelefono, IOException {
		try {
			//cn = conexion.conectar();  IMPORTANTE: activar esta conexion para el funcionamiento normal del software
			cn = testconexion.conectar();  // IMPORTANTE: activar esta conexion para realizar el test JUNIT guardarCliente(Cliente)
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");
				while(rs.next()) {
					Cliente c = new Cliente();
					c.setTelefono(rs.getInt(1));
					c.setNombre(rs.getString(2));
					c.setApellidos(rs.getString(3));
					c.setFechaDeAlta(rs.getString(4));
					c.setDireccion(rs.getString(5));
					clientes.add(c);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return clientes;
	} //Cierre del Método cargarClientes(ArrayList<Cliente>)
	
	/**
	 * Método guardarCliente(Cliente) guardará el cliente pasado como parámetro en la base de datos
	 * @param cli es el cliente a guardar en la base de datos
	 * @throws Exception
	 */
	public void guardarCliente(Cliente cli) throws Exception {
		//ConexionBD conexion = new ConexionBD();
		TestConexionBD conexion = new TestConexionBD(); // IMPORTANTE: Usamos esta conexion para los test JUNIT
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO cliente (telefono,nombre,apellidos,fecha_alta,direccion) VALUES (?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			ps.setInt(1, cli.getTelefono());
			ps.setString(2, cli.getNombre());
			ps.setString(3, cli.getApellidos());
			ps.setString(4, cli.getFechaDeAlta());
			ps.setString(5, cli.getDireccion());
			ps.executeUpdate();

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Liberar recurso
			try {
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	} //Cierre de Método guardarCliente(Cliente)
	
	
	/**
	 * Método cargarBebidas(ArrayList<Bebida>) cargará las bebidas registradas en la base de datos
	 * @param bebidas será el ArrayList donde guardar las bebidas cargadas
	 * @return bebidas con las bebidas guardadas
	 * @throws ExcepcionTelefono
	 * @throws IOException
	 */
	public ArrayList<Bebida> cargarBebidas(ArrayList<Bebida> bebidas) throws ExcepcionTelefono, IOException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//cn = conexion.conectar();  // IMPORTANTE: activar esta conexion para el funcionamiento normal del software
			cn = testconexion.conectar();  // IMPORTANTE: activar esta conexion para realizar el test JUNIT guardarBebida(Bebida)
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM bebida");
				while(rs.next()) {
					Bebida b = new Bebida(rs.getInt(1),rs.getString(2),rs.getDouble(3),LocalDate.parse(rs.getString(4), formatter),rs.getString(5),0,rs.getBoolean(6),rs.getBoolean(7),rs.getString(8));
					b.llenarStock();
					bebidas.add(b);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bebidas;
	} //Cierre del Método cargarBebidas(ArrayList<Bebida>)
	
	
	/**
	 * Método guardarBebida(Bebida) para guardar la bebida pasada como parámetro en la base de datos
	 * @param b es la bebida a guardar en la base de datos
	 * @throws Exception
	 */
	public void guardarBebida(Bebida b) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//ConexionBD conexion = new ConexionBD();
		TestConexionBD conexion = new TestConexionBD(); // IMPORTANTE: Usamos esta conexion a la base de datos testgpedidos para los test JUNIT
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO bebida (codigo, nombre, precio, fecha_caducidad, estado, gaseoso, lacteo, medida) VALUES (?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			ps.setInt(1, b.getCodigo());
			ps.setString(2, b.getNombre());
			ps.setDouble(3, b.getPrecio());
			ps.setString(4, formatter.format(b.getFecha_caducidad()));
			ps.setString(5, b.getEstado());
			ps.setBoolean(6, b.isGaseoso());
			ps.setBoolean(7, b.isLacteo());
			ps.setString(8, b.getMedida());
			ps.executeUpdate();

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			try {
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	} //Cierre del Método guardarBebida(Bebida)

	
	/**
	 * Método cargarComidas(ArrayList<Comida>) cargará las comidas registradas en la base de datos
	 * @param comidas es el ArrayList donde guardar las comidas cargadas
	 * @return comidas con todas las comidas guardadas dentro de este ArrayList
	 * @throws ExcepcionTelefono
	 * @throws IOException
	 */
	public ArrayList<Comida> cargarComidas(ArrayList<Comida> comidas) throws ExcepcionTelefono, IOException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//cn = conexion.conectar();  // IMPORTANTE: activar esta conexion para el funcionamiento normal del software
			cn = testconexion.conectar();  // IMPORTANTE: activar esta conexion para realizar el test JUNIT guardarComida(Comida)
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM comida");
				while(rs.next()) {
					Comida c = new Comida(rs.getInt(1),rs.getString(2),rs.getDouble(3),LocalDate.parse(rs.getString(4), formatter),rs.getString(5),0,rs.getBoolean(6),rs.getInt(7),rs.getFloat(8),LocalDate.parse(rs.getString(9), formatter));
					c.llenarStock();
					comidas.add(c);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return comidas;
	} //Cierre del Método cargarComidas(ArrayList<Comida>)
	
	/**
	 * Método guardarComida(Comida) registrará la comida pasada como parámetro en la base de datos
	 * @param c es la comida a guardar en la base de datos
	 * @throws Exception
	 */
	public void guardarComida(Comida c) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//ConexionBD conexion = new ConexionBD();
		TestConexionBD conexion = new TestConexionBD(); // IMPORTANTE: Usamos esta conexion a la base de datos testgpedidos para los test JUNIT
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO comida (codigo, nombre, precio, fecha_caducidad, estado, perecedero, calorias, vegano, fecha_envase) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
				ps.setInt(1, c.getCodigo());
				ps.setString(2, c.getNombre());
				ps.setDouble(3, c.getPrecio());
				ps.setString(4, formatter.format(c.getFecha_caducidad()));
				ps.setString(5, c.getEstado());
				ps.setBoolean(6, c.isPerecedero());
				ps.setFloat(7, c.getCalorias());
				ps.setFloat(8, c.getVegano());
				ps.setString(9, formatter.format(c.getFecha_envase()));
				ps.executeUpdate();

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	} //Cierre del Método guardarComida(Comida)
	
	/**
	 * Método guardarPedido(Cliente, Pedido) registrará el pedido en la base de datos
	 * @param c es el cliente que realiza el pedido
	 * @param p es el pedido a guardar en la base de datos
	 * @throws Exception
	 */
	public void guardarPedido(Pedido p) throws Exception {
		//ConexionBD conexion = new ConexionBD();
		TestConexionBD conexion = new TestConexionBD(); // IMPORTANTE: Usamos esta conexion a la base de datos testgpedidos para los test JUNIT		
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO pedido (num_cliente, cod_bebida,cantidad_bebida,cod_comida,cantidad_comida,importeTotal,codigoPago,estado) VALUES (?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
				ps.setInt(1, p.getCliente().getTelefono());
				ps.setInt(2, p.getBebida().getCodigo());
				ps.setInt(3, p.getBebida().getCantidad());
				ps.setInt(4, p.getComida().getCodigo());
				ps.setInt(5, p.getComida().getCantidad());
				ps.setDouble(6, p.getImporteTotal());
				ps.setLong(7, p.getCodigoPago());
				ps.setString(8, p.getEstado());
				ps.executeUpdate();

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	} //Cierre del Método guardarPedido(Cliente, Pedido)
	
}//Cierre de la clase QueryBD
