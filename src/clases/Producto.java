package clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Producto de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

public abstract class Producto implements Serializable{
	
	protected int codigo;
	protected String nombre;
	protected double precio;
	protected LocalDate fecha_caducidad;
	protected String estado;
	protected int cantidad;
	protected int stock[] = new int[30]; 

	

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
	
	public Producto(int codigo, String nombre, double precio, LocalDate fecha_caducidad, String estado, int cantidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha_caducidad = fecha_caducidad;
		this.estado = estado;
		this.cantidad = cantidad;
	}
	
	public abstract void obtenerCaducidad();
	
	public abstract void detalleProducto();

	public abstract LocalDate calcularCaducidad();
	
	public int mostrarStock() {
		int e = 0;
		for (int i = 0; i < stock.length; i++) {
			if (stock[i] == 0) {
				e++;
			}			
		}
		return 30-e;
	}
	
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
}