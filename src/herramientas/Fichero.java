package herramientas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import clases.Cliente;
import clases.Pedido;
import clases.Producto;

public class Fichero {
	
	private String rutaClientes = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/src/Clientes";
	private String rutaProductos = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/src/Productos";
	private String rutaPedidos = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/src/Pedidos";
	
	public void crearCliente(Cliente cliente) {
		
		   try {
		      File nuevoCliente = new File(rutaClientes+"/CL"+cliente.getTelefono()+".txt");		      
		      if (nuevoCliente.createNewFile()) {		    	  
		        System.out.println("Archivo creado: " + nuevoCliente.getName());		        
		      } else {		    	  
		        System.out.println("Este archivo ya existe.");	        
		      }	      
		    } catch (IOException e) {		    	
		      System.out.println("An error occurred.");		      
		      e.printStackTrace();		      
		    }
		   
			FileWriter fichero = null;
			PrintWriter pw = null;
			try {
				// Añadir flag a true para no machacar contenido del fichero de escritura
				fichero = new FileWriter(rutaClientes+"/CL"+cliente.getTelefono()+".txt", true);
				pw = new PrintWriter(fichero);
				pw.println(cliente.getNombre()+"; "+cliente.getApellidos()+"; "+cliente.getFechaDeAlta()+"; "+cliente.getTelefono()+"; "+cliente.getDireccion());

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
	
	public void crearProducto(Producto producto) {
		
		   try {
		      File nuevoProducto = new File(rutaProductos+"/PR"+producto.getCodigo()+".txt");      
		      if (nuevoProducto.createNewFile()) {	    	  
		        System.out.println("Archivo creado: " + nuevoProducto.getName());	        
		      } else {	    	  
		        System.out.println("Este archivo/producto ya existe.");	        
		      }	      
		    } catch (IOException e) {	    	
		      System.out.println("An error occurred.");	      
		      e.printStackTrace();	      
		    }
		   
			FileWriter fichero = null;
			PrintWriter pw = null;
			try {
				// Añadir flag a true para no machacar contenido del fichero de escritura
				fichero = new FileWriter(rutaProductos+"/PR"+producto.getCodigo()+".txt", true);
				
				String precio = producto.getPrecio()+"";
				if(precio.contains(".")) {			
					precio = precio.replace(".",",");
				}
				pw = new PrintWriter(fichero);
				pw.println(producto.getCodigo()+"; "+producto.getNombre()+"; "+precio);

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

	
	// Método para leer un dato de una fila de Fichero.txt
	
	public ArrayList<Cliente> cargarClientes(ArrayList<Cliente> clientes) throws FileNotFoundException, IOException, ParseException {
		
		//Creamos un array con los archivos txt con los datos de los clientes y así saber la cantidad de clientes que hay 
		
		File carpetaClientes = new File(rutaClientes); 
		File[] listaClientes = carpetaClientes.listFiles(); 

		// Rellenamos los clientes con el método rellenarCliente (nombre, apellidos, fecha, telefono, direccion, historial)

		for (int i = 0; i < listaClientes.length; i++) {   // Bucle for para crear tantos clientes como archivos tenga la carpeta Clientes y asignar la ruta de su respectivo archivo
			
		// Cliente i
			
			Scanner scan = new Scanner(System.in);
			clientes.add(new Cliente());					 
			clientes.get(i).rellenarCliente(listaClientes[i], clientes.get(i));
			
		    for (int j = 0; j < clientes.size(); j++) {  // Bucle for para comprobar que los telefonos no se repitan
				while (clientes.get(i).getTelefono() == clientes.get(j).getTelefono() && i != j) {
					System.out.println(
								"El telefono de cliente "+i+" debe ser diferente al telefono de cliente "+j+". \nEscriba de nuevo el telefono de cliente "+i+": ");
					clientes.get(i).setTelefono(scan.nextInt());
				 }
		     }	    			 		
		}
		
		return clientes;
	}
	
	public ArrayList<Producto> cargarProductos(ArrayList<Producto> productos) throws FileNotFoundException, IOException, ParseException {
		
		//Creamos un array con los archivos txt con los datos de los productos y así saber la cantidad de productos que hay 
		
		File carpetaProductos = new File(rutaProductos); 
		File[] listaProductos = carpetaProductos.listFiles();  

			// Rellenamos los datos de los productos con el método rellenarProducto (nombre, precio, stock)
			
			 // Producto i
		
		for (int i = 0; i < listaProductos.length; i++) {   // Bucle for para crear tantos productos como archivos tenga la carpeta Productos y asignar la ruta de su respectivo archivo		
				productos.add(new Producto());										 
				productos.get(i).rellenarProducto(listaProductos[i], productos.get(i));		 		
			}
		
		return productos;
	}
	
	public void imprimirPedido(Pedido pedido) {
		
		String ruta = rutaPedidos+"/" +pedido.getPago()+ ".txt";
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			// Añadir flag a true para no machacar contenido del fichero de escritura
			fichero = new FileWriter(ruta, true);
			pw = new PrintWriter(fichero);
			pw.println(pedido.toString());

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

}
