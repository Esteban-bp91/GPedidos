package clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Producto del software GPedidos
 * @author Esteban Baeza Pérez
 * @version 0.4
 * @since 27/05/2023
 * 
 */

public abstract class Producto implements Serializable{
	//Parámetros de la clase Producto
	protected int codigo;
	protected String nombre;
	protected double precio;
	protected LocalDate fecha_caducidad;
	protected String estado;
	protected int cantidad;
	protected int stock[] = new int[30]; 

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
	public LocalDate getFecha_caducidad() {
		return fecha_caducidad;
	}
	public void setFecha_caducidad(LocalDate fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
	public int[] getStock() {
		return stock;
	}
	public void setStock(int[] stock) {
		this.stock = stock;
	}
	
	/**
	 * Constructor de la superclase Producto
	 * @param codigo identifica la instancia de la comida
	 * @param nombre especifica el nombre de la comida
	 * @param precio especifica el precio de la comida
	 * @param fecha_caducidad especifica la fecha de caducidad de la comida
	 * @param estado especifica el estado de la comida
	 * @param cantidad especifica la cantidad de la comida que tendrá el pedido
	 */
	public Producto(int codigo, String nombre, double precio, LocalDate fecha_caducidad, String estado, int cantidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha_caducidad = fecha_caducidad;
		this.estado = estado;
		this.cantidad = cantidad;
	} //Cierre del constructor Producto
	
	/**
	 * Método abstracto obtenerCaducidad()
	 */
	public abstract void obtenerCaducidad();
	
	/**
	 * Método abstracto detalleProducto()
	 */
	public abstract void detalleProducto();
	
	/**
	 * Método abstracto calcularCaducidad()
	 */
	public abstract LocalDate calcularCaducidad();
	
	/**
	 * Método mostrarStock() para saber cuantas unidades hay disponibles de un producto
	 * @return el valor entero de la cantidad que hay disponible de un producto
	 */
	public int mostrarStock() {
		int e = 0;
		for (int i = 0; i < stock.length; i++) {
			if (stock[i] == 0) {
				e++;
			}			
		}
		return 30-e;
	} //Cierre del Método mostrarStock()
	
	/**
	 * Método llenarStock() llena el array Stock al completo con unos
	 */
	public void llenarStock() {
		for (int i = 0; i < stock.length; i++) {
			stock[i] = 1;
		}
	} //Cierre del Método llenarStock()
	
	/**
	 *  Método actualizarStock(int) modifica el array stock eliminando tantos 1 como unidades del producto se hayan comprado
	 * @param int cantidad es la cantidad a restar al stock
	 */
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
	} //Cierre del Método actualizarStock(int)
}