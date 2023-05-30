package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase TestConexionBD para realizar la conexion a la base de datos testgpedidos para realizar los test a los métodos de guardar registros
 * @author EstebanBP
 *
 */

class TestConexionBD {
		//Parámetros de la clase TestConexionBD
		private static String nombre_bd = null;
		private static String ubicacion = null;
		private static String puerto = null;
		private static String Usuario = null;
		private static String Clave = null;
		private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
		private static String URL = null;
	
	/**
	* Este static comprueba que el controlador es correcto y ya no será necesario volver a comprobarlo al realizar sucesivas conexiones
	*/
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			// * TODO Auto-generated catch block
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	/**
	 * Método conectar() crea una nueva conexion, lee los datos del fichero de configuración y establece la conexion con la base de datos
	 * @return  una conexion de la clase Connection con la base de datos MySQL
	 * @throws IOException
	 */
	public Connection conectar() throws IOException {
		Connection conexion = null;
		nombre_bd = "testgpedidos";
		ubicacion = "localhost";
		puerto = "3310";
		Usuario = "root";
		Clave = "1234";
		URL = "jdbc:mysql://"+ubicacion+":"+puerto+"/" + nombre_bd +
				"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
				"serverTimezone=UTC&useSSL=false";
		try {
			// Establecemos la conexión para eso java nos proporciona conexion =
			conexion = DriverManager.getConnection(URL, Usuario, Clave);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	} //Cierre del método conectar()
} //Cierre de la clase TestConexionBD


