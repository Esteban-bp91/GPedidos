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

public class QueryBD {
	
	ConexionBD conexion = new ConexionBD();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;
	
	private static String insertTableSQL;
	private static String updateTableSQL;
	
	public ArrayList<Cliente> cargarClientes(ArrayList<Cliente> clientes) throws ExcepcionTelefono, IOException {
		try {
			cn = conexion.conectar();
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
	}
	
	public void guardarCliente(Cliente cli) throws Exception {
		ConexionBD conexion = new ConexionBD();
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

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
	
	public ArrayList<Bebida> cargarBebidas(ArrayList<Bebida> bebidas) throws ExcepcionTelefono, IOException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			cn = conexion.conectar();
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
	}
	
	public void guardarBebida(Bebida b) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO bebida (codigo, nombre, precio, fecha_caducidad, estado, cantidad, gaseoso, lacteo, medida) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			ps.setInt(1, b.getCodigo());
			ps.setString(2, b.getNombre());
			ps.setDouble(3, b.getPrecio());
			ps.setString(4, formatter.format(b.getFecha_caducidad()));
			ps.setString(5, b.getEstado());
			ps.setInt(6, 0);
			ps.setBoolean(7, b.isGaseoso());
			ps.setBoolean(8, b.isLacteo());
			ps.setString(9, b.getMedida());
			ps.executeUpdate();

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
	
	public void guardarBebidas(ArrayList<Bebida> bebidas) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO bebida (codigo, nombre, precio, fecha_caducidad, estado, gaseoso, lacteo, medida) VALUES (?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			for(int i = 0; i < bebidas.size();i++) {
				ps.setInt(1, bebidas.get(i).getCodigo());
				ps.setString(2, bebidas.get(i).getNombre());
				ps.setDouble(3, bebidas.get(i).getPrecio());
				ps.setString(4, formatter.format(bebidas.get(i).getFecha_caducidad()));
				ps.setString(5, bebidas.get(i).getEstado());
				ps.setBoolean(6, bebidas.get(i).isGaseoso());
				ps.setBoolean(7, bebidas.get(i).isLacteo());
				ps.setString(8, bebidas.get(i).getMedida());
				ps.executeUpdate();
			}

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
	
	public void guardarClientes(ArrayList<Cliente> clientes) throws Exception {
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO cliente (telefono,nombre,apellidos,fecha_alta,direccion) VALUES (?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			for(int i = 0; i < clientes.size();i++) {
				ps.setInt(1, clientes.get(i).getTelefono());
				ps.setString(2, clientes.get(i).getNombre());
				ps.setString(3, clientes.get(i).getApellidos());
				ps.setString(4, clientes.get(i).getFechaDeAlta());
				ps.setString(5, clientes.get(i).getDireccion());
				ps.executeUpdate();
			}
			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
	
	public void guardarComidas(ArrayList<Comida> comidas) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO comida (codigo, nombre, precio, fecha_caducidad, estado, perecedero, calorias, vegano, fecha_envase) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			for(int i = 0; i < comidas.size();i++) {
				ps.setInt(1, comidas.get(i).getCodigo());
				ps.setString(2, comidas.get(i).getNombre());
				ps.setDouble(3, comidas.get(i).getPrecio());
				ps.setString(4, formatter.format(comidas.get(i).getFecha_caducidad()));
				ps.setString(5, comidas.get(i).getEstado());
				ps.setBoolean(6, comidas.get(i).isPerecedero());
				ps.setFloat(7, comidas.get(i).getCalorias());
				ps.setFloat(8, comidas.get(i).getVegano());
				ps.setString(9, formatter.format(comidas.get(i).getFecha_envase()));
				ps.executeUpdate();
			}

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
	
	public ArrayList<Comida> cargarComidas(ArrayList<Comida> comidas) throws ExcepcionTelefono, IOException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			cn = conexion.conectar();
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
	}
	
	public void guardarComida(Comida c) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConexionBD conexion = new ConexionBD();
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

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
	
	public void guardarPedido(Cliente c, Pedido p) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO pedido (num_cliente, cod_bebida,cantidad_bebida,cod_comida,cantidad_comida,importeTotal,codigoPago,estado) VALUES (?,?,?,?,?,?,?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
				ps.setInt(1, c.getTelefono());
				ps.setInt(2, p.getBebida().getCodigo());
				ps.setInt(3, p.getBebida().getCantidad());
				ps.setInt(4, p.getComida().getCodigo());
				ps.setInt(5, p.getComida().getCantidad());
				ps.setDouble(6, p.getImporteTotal());
				ps.setLong(7, p.getCodigoPago());
				ps.setString(8, p.getEstado());
				ps.executeUpdate();

			System.out.println("El registro ha sido insertado con exito en la base de datos");

		} catch (SQLException e) { // TODO: handle exception
			e.printStackTrace();
		} finally { // Liberar recursos revisar el orden en el que se cierran
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
	}
}
