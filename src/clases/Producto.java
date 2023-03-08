package controlStock;

import java.util.Scanner;


/**
 * Clase Producto de la practica 4 de Programación
 * 
 * @author EstebanBP
 * @version 1.1  
 * @since 08/02/2023
 * 
 * 
 * Funcionalidad controlStock añadida
 * 
 */

public class Producto {
	
	// Atributos de la clase Producto

	private String nombre;
	private double precio;
	private int stock[] = new int[30]; 
	
	// Getters and setters

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
	public Producto(String nombre, double precio, int[] cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = cantidad;
	}
	
	// Método rellenarProducto para rellenar los atributos necesarios de cada producto
	
	public void rellenarProducto(Producto producto) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del producto:");
		producto.setNombre(sc.nextLine());
		System.out.println("Precio del producto :");
		producto.setPrecio(Math.round((sc.nextDouble()) * 100) / 100d);
		 			 
		producto.llenarStock();                        //Llenamos el stock del producto
			 
		String saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
			 
		System.out.println("Producto: \nNombre: " + producto.getNombre() +
		"\nPrecio: " + producto.getPrecio());
		
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

}
