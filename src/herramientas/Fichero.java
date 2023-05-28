package herramientas;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import clases.Bebida;
import clases.Cliente;
import clases.Comida;
import clases.Pedido;

/**
 * Clase Fichero del software GPedidos
 * 
 * @author Esteban Baeza Pérez
 * @version 0.4 
 * @since 27/05/2023
 * 
 */
public class Fichero {
	//Parámetros de la clase Fichero con las rutas a los diferentes directorios necesarios
	private String rutaPedidos = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/Pedidos";
	private String rutaConfig = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/Ficheros/configuracion.txt";
	
	/**
	 * Método obtenerDatos() para la lectura del archivo de configuracion de la base de datos
	 * @return un array de 5 valores String con los datos necesarios para la conexion a la base de datos MySQL
	 * @throws IOException
	 */
	public String[] obtenerDatos() throws IOException {
		String[] datos= new String[5];
		FileReader fl = null;
		BufferedReader br = null;
		try {
			fl = new FileReader(rutaConfig);
			br = new BufferedReader(fl);
			for(int i = 0; i<5; i++) {
				datos[i]=br.readLine();
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			System.out.println("Fichero configuracion.txt no encontrado");
		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("Error al leer el archivo de configuracion");
		} finally {
			if (br != null) {
		        br.close();
		    }
		    if (fl != null) {
		        fl.close();
		    }
		}
		return datos;
	} //Cierre del Método obtenerDatos()

	/**
	 * Método imprimirPedido(Pedido) guarda en un fichero .txt los datos del pedido
	 * @param el pedido a guardar
	 */
	public void imprimirPedido(Pedido pedido) {
		
		String ruta = rutaPedidos+"/" +pedido.getCodigoPago()+ ".txt"; //El nombre del fichero .txt será el código de pago del pedido
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(ruta);
			pw = new PrintWriter(fichero);
			pw.println(pedido.imprimir());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para asegurarnos que se cierra el fichero.
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	} //Cierre del Método imprimirPedido(Pedido)
} //Cierre de la clase Fichero
