package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Clase Producto GPedidos v0.3 
 * 
 * @author EstebanBP
 * @version 0.3  
 * @since 16/03/2023
 * 
 * 
 * Funcionalidad lectura/escritura de archivos .txt para Clientes, Productos y Pedidos
 * 
 */

public class Producto {
	
	// Atributos de la clase Producto
	
	private int codigo;
	private String nombre;
	private double precio;
	private int stock[] = new int[30]; 
	
	// Getters and setters
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int[] getStock() {
		return stock;
	}

	public void setStock(int[] cantidad) {
		this.stock = cantidad;
	}

	/** Constructor de Producto vacío 
	 * @param nombre
	 * @param precio
	 * @param stock
	 */
	public Producto() {
		this.nombre = null;
		this.precio = 0;
	}
	
	/** Constructor de Producto 
	 * @param nombre
	 * @param precio
	 * @param stock
	 */
	public Producto(int codigo, String nombre, double precio, int[] cantidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = cantidad;
	}
	
	// Método rellenarProducto para rellenar los atributos necesarios de cada producto
	
	public void rellenarProducto(File f, Producto producto) {
		
		try {
			Scanner s = new Scanner(f);
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s*;\\s*");
			producto.setCodigo(sl.nextInt());
			producto.setNombre(sl.next());
			producto.setPrecio(sl.nextDouble());
			producto.llenarStock();
			
		
		} catch (FileNotFoundException e) {
			// PrintWriter pw = null;
			e.printStackTrace();
			// e.printStackTrace(pw);

		}
		
		/**
		 * Codigo anterior para recoger los datos por consola
		 * 
		 * 		
		System.out.println("Nombre del producto:");
		producto.setNombre(sc.nextLine());
		System.out.println("Precio del producto :");
		producto.setPrecio(Math.round((sc.nextDouble()) * 100) / 100d);
		 			 
		producto.llenarStock();                        //Llenamos el stock del producto
			 
		String saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
			 
		System.out.println("Producto: \nNombre: " + producto.getNombre() +
		"\nPrecio: " + producto.getPrecio());
		
		*/
		
	}
	
	// Método llenarStock para reponer el stock del producto al completo
	
	public void llenarStock() {
		for (int i = 0; i < stock.length; i++) {
			stock[i] = 1;
		}
	}
	
	// Método actualizarStock modifica el array stock eliminando tantos 1 como unidades del producto se hayan comprado
	
	public void actualizarStock(int cantidad) {
		
		int e = 0;
		
		// Primer bucle for sirve para detectar cuantos 1 tenemos en el array
		
		for (int i = 0; i < stock.length; i++) {
			if (stock[i] == 0 || i == (stock.length-1)) {
				
				e = i;
				
				i = stock.length;
			}
		}
		
		// Segundo bucle for sirve para eliminar tantos 1 como unidades del producto se hayan comprado
		
		for (int z = 0; z < cantidad; z++) {
			
			stock[e] = 0;
			e--;
			
		}
		
	}
	
	/** Método mostrarStock sirve para detectar cuantos 0 tenemos en el array stock
	 *  
	 * @return Devuelve la cantidad de 1 (o la cantidad de unidades disponibles)
	 * 
	*/
	
	
	public int mostrarStock() {
		
		int e = 0;
				
		for (int i = 0; i < stock.length; i++) {
			if (stock[i] == 0) {
				
				e++;
			}
							
		}
				
		return 30-e;
		
	}
	
	public void rellenarProducto(Producto producto, ArrayList<Producto> productos) throws ParseException{		

		Scanner sc = new Scanner(System.in);

		// Generamos un int random entre 1000 y 9999 para el codigo del producto
		Random numAleatorio = new Random();
		int cod = numAleatorio.nextInt(9999-1000+1)+1000;
		for(int i = 0; i < productos.size(); i++) {
			if(cod != productos.get(i).getCodigo()) {
				producto.setCodigo(cod);
			} else {
				cod = numAleatorio.nextInt(9999-1000+1)+1000;
			}
		}
		System.out.println("Nuevo producto con codigo: "+producto.getCodigo());

		System.out.println("Nombre del producto:");
		String nom = sc.nextLine();
		producto.setNombre(nom.toLowerCase());

		System.out.println("Precio del producto:");
		String pvp = sc.nextLine();
		
		if(pvp.contains(",")) {
			pvp = pvp.replace(",",".");
		}
		
		//System.out.println(pvp);
		Double precio = Double.parseDouble(pvp);
		producto.setPrecio(precio);
		
	}
	
	public Producto elegirProducto(ArrayList<Producto> productos) {
		
		Producto prod1 = null;
		int cantidad1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nCARTA:");
		for (int i = 0; i < productos.size(); i++) {			
			System.out.println(i+") "+ productos.get(i).getNombre() + "		Precio: "+ productos.get(i).getPrecio());			
		}
		
		System.out.println("\nElija el producto:");
		int prod = sc.nextInt();
		
		if(prod >= 0 && prod < productos.size()) {		
			prod1 = productos.get(prod);		
		}else {
			System.out.println("Producto 1 incorrecto");
		}
		
		
		//Si el stock del producto 1 elegido es menor a 5, se repone el stock al completo

		
		System.out.println("Ha elegido como Producto 1: " + prod1.getNombre() + "   Precio: " + prod1.getPrecio() + "      Stock: " + prod1.mostrarStock());
		if (prod1.mostrarStock() <= 5) {
			System.out.println("Stock de " + prod1.getNombre() + " bajo. \n Reponemos el stock al completo");
			prod1.llenarStock();	
			System.out.println("Stock de " + prod1.getNombre() + ": " + prod1.mostrarStock());	
		}
		
		return prod1;
	}

}
