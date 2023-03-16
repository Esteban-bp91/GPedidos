package herramientas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ficheros {
	
	// Método para escribir en Fichero.txt
	
	public void escribirFichero() {
	
	String ruta = "C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/Pedidos/BDClientes.txt";
	FileWriter fichero = null;
	PrintWriter pw = null;
	try {
		// Añadir flag a true para no machacar contenido del fichero de escritura
		fichero = new FileWriter(ruta, true);
		pw = new PrintWriter(fichero);
		pw.println("Modelo");

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

	}
	
	
	// Método para leer una fila en Fichero.txt
	
	public String leerFila() throws FileNotFoundException, IOException{
		String ruta = "C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/BDClientes.txt";
		String cadena;
		FileReader f = new FileReader(ruta);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
		}
		b.close();
		return cadena;
	}
	
	
	// Método para leer un dato de una fila de Fichero.txt
	
	public void leerCliente() {
		String ruta = "C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/BDClientes.txt";
		File f = new File(ruta);
		System.out.println("La ruta del fichero es: " + f.getAbsolutePath());
		Scanner s;
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*,\\s*");
				System.out.println(sl.next());
				System.out.println(sl.next());
				System.out.println(sl.next());
				System.out.println(sl.next());
				System.out.println(sl.next());
				System.out.println(sl.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// PrintWriter pw = null;
			e.printStackTrace();
			// e.printStackTrace(pw);

		}
	}

}
