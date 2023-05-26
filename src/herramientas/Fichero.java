package herramientas;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
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
import java.util.HashMap;

import clases.Bebida;
import clases.Cliente;
import clases.Comida;
import clases.Pedido;

/**
 * Clase Fichero de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

public class Fichero {
	
	private String rutaFicheros = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/Ficheros";
	private String rutaPedidos = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/Pedidos";
	private String rutaConfig = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/Ficheros/configuracion.txt";
	
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
	}
	
	public void guardarBebidas(ArrayList<Bebida> bebidas) throws Exception {
		FileOutputStream fc = null;
		ObjectOutputStream oc = null;
		try {
			fc = new FileOutputStream(rutaFicheros+"/Bebidas.dat");
			oc = new ObjectOutputStream(fc);
			oc.writeObject(bebidas);
			oc.flush();
		} catch (EOFException eof) {
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Clientes no encontrados");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
	}
	
	public void guardarBebida(Bebida bebida, ArrayList<Bebida> bebidas) throws Exception {
		bebidas.add(bebida);
		FileOutputStream fc = null;
		ObjectOutputStream oc = null;
		try {
			fc = new FileOutputStream(rutaFicheros+"/Bebidas.dat");
			oc = new ObjectOutputStream(fc);
			oc.writeObject(bebidas);
			oc.flush();
		} catch (EOFException eof) {
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Clientes no encontrados");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Bebida> cargarBebidas(ArrayList<Bebida> bebidas) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException {
		FileInputStream fc = null;
		ObjectInputStream oc = null;
		try {
			fc = new FileInputStream(rutaFicheros+"/Bebidas.dat");
			oc = new ObjectInputStream(fc);
			bebidas = (ArrayList<Bebida>)oc.readObject(); 
		} catch (EOFException eof) {
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			System.out.println("Error en la carga de las bebidas");
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Bebidas no encontradas");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
		return bebidas;
	}
	
	public void guardarComidas(ArrayList<Comida> comidas) throws Exception {
		FileOutputStream fc = null;
		ObjectOutputStream oc = null;
		try {
			fc = new FileOutputStream(rutaFicheros+"/Comidas.dat");
			oc = new ObjectOutputStream(fc);
			oc.writeObject(comidas);
			oc.flush();
		} catch (EOFException eof) {
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Comidas no encontrados");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
	}
	
	public void guardarComida(Comida comida, ArrayList<Comida> comidas) throws Exception {
		comidas.add(comida);
		FileOutputStream fc = null;
		ObjectOutputStream oc = null;
		try {
			fc = new FileOutputStream(rutaFicheros+"/Comidas.dat");
			oc = new ObjectOutputStream(fc);
			oc.writeObject(comidas);
			oc.flush();
		} catch (EOFException eof) {
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Comidas no encontrados");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Comida> cargarComidas(ArrayList<Comida> comidas) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException {
		FileInputStream fc = null;
		ObjectInputStream oc = null;
		try {
			fc = new FileInputStream(rutaFicheros+"/Comidas.dat");
			oc = new ObjectInputStream(fc);
			comidas = (ArrayList<Comida>)oc.readObject(); 
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			System.out.println("Error en la carga de las comidas");
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Comidas no encontradas");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
		return comidas;
	}
	
	public void imprimirPedido(Pedido pedido) {
		
		String ruta = rutaPedidos+"/" +pedido.getCodigoPago()+ ".txt";
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
	}
	
	public void guardarClientes(ArrayList<Cliente> clientes) throws Exception {
		FileOutputStream fc = null;
		ObjectOutputStream oc = null;
		try {
			fc = new FileOutputStream(rutaFicheros+"/Clientes.dat");
			oc = new ObjectOutputStream(fc);
			oc.writeObject(clientes);
			oc.flush();
		} catch (EOFException eof) {
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Clientes no encontrados");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
	}
	
	public void guardarCliente(Cliente cli,ArrayList<Cliente> clientes) throws Exception {
		clientes.add(cli);
		FileOutputStream fc = null;
		ObjectOutputStream oc = null;
		try {
			fc = new FileOutputStream(rutaFicheros+"/Clientes.dat");
			oc = new ObjectOutputStream(fc);
			oc.writeObject(clientes);
			oc.flush();
		} catch (EOFException eof) {
		} catch (FileNotFoundException f){
			f.printStackTrace();
			System.out.println("Clientes no encontrados");
		} finally {
			if (oc != null) {
		        oc.close();
		    }
		    if (fc != null) {
		        fc.close();
		    }
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> cargarClientes(ArrayList<Cliente> clientes) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException {
			FileInputStream fc = null;
			ObjectInputStream oc = null;
			try {
				fc = new FileInputStream(rutaFicheros+"/Clientes.dat");
				oc = new ObjectInputStream(fc);
				clientes = (ArrayList<Cliente>)oc.readObject(); 
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				System.out.println("Error en la carga de los clientes");
			} catch (FileNotFoundException f){
				f.printStackTrace();
				System.out.println("Clientes no encontrados");
			} finally {
				if (oc != null) {
			        oc.close();
			    }
			    if (fc != null) {
			        fc.close();
			    }
			}
		return clientes;
	}

}
