package herramientas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConexionBD {
	
		private static String nombre_bd = null;
		private static String ubicacion = null;
		private static String puerto = null;
		private static String Usuario = null;
		private static String Clave = null;

		private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
		private static String URL = null;
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			// * TODO Auto-generated catch block
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	public Connection conectar() throws IOException {
		Connection conexion = null;
		Fichero config = new Fichero();
		String[] datos =  new String[5];
		datos = config.obtenerDatos();
		nombre_bd = datos[0];
		ubicacion = datos[1];
		puerto = datos[2];
		Usuario = datos[3];
		Clave = datos[4];
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
	}
}
