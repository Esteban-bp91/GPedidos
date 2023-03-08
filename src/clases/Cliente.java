package controlStock;

import java.time.LocalDate;
import java.util.Scanner;


/**
 * Clase Cliente de la practica 4 de Programación
 * 
 * @author EstebanBP
 * @version 1.1  
 * @since 08/02/2023
 * 
 * 
 * Funcionalidad controlStock añadida
 * 
 */

public class Cliente {
	
	// Atributos de la clase Cliente

	private String nombre;
	private String apellidos;
	private LocalDate FechaDeAlta;
	private int telefono;
	private String direccion;
	private String historial;
	
	
	// Getters and setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaDeAlta() {
		return FechaDeAlta;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
		FechaDeAlta = fechaDeAlta;
	}

	public void setFechaDeAlta() {
		FechaDeAlta = LocalDate.now();
	}

	public int getTelefono() {
		return telefono;
	}
	
	// El setter de telefono comprueba que sea un número entre 600000000 y 999999999 para que sea un número válido

	public void setTelefono(int telefono) {
		if (600000000 <= telefono && telefono <= 999999999) {
			this.telefono = telefono;
		} else {
			System.out.println("Teléfono incorrecto");
		}
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	/** Constructor de Cliente
	 * @param nombre
	 * @param apellidos
	 * @param fechaDeAlta
	 * @param telefono
	 * @param direccion
	 * @param historial
	 */
	public Cliente(String nombre, String apellidos, LocalDate fechaDeAlta, int telefono, String direccion,
			String historial) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.FechaDeAlta = fechaDeAlta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.historial = historial;
	}
	
	/** Constructor vacío de Cliente
	 * @param nombre
	 * @param apellidos
	 * @param fechaDeAlta
	 * @param telefono
	 * @param direccion
	 * @param historial
	 */

	public Cliente() {
		this.nombre = null;
		this.apellidos = null;
		this.FechaDeAlta = null;
		this.telefono = 0;
		this.direccion = null;
		this.historial = null;
	}
	
	// Método agregarPedido añade el código de pago introducido como parámetro de un pedido pagado al historial del cliente
	
	
	public void agregarPedido(long p) {

		historial = historial + "\n" + p;

	}
	
	/** Método realizarPedido crea y devuelve un pedido con los parámetros pasados al método. El pedido creado será de un o dos productos
	 *  
	 * @return El pedido creado segun el caso
	 * 
	*/
	
	public Pedido realizarPedido(Cliente c, Producto p1, int cantidad1, Producto p2, int cantidad2, double total, long codigodePago, String estado) {

		if (p2 == null) {  // Pedido de un solo producto

			Pedido pedido = new Pedido(c, p1, cantidad1, total, codigodePago, estado); // Pedido inicializado

			return pedido;  

		} else {  // Pedido de dos productos

			Pedido pedido = new Pedido(c, p1, cantidad1, p2, cantidad2, total, codigodePago, estado); // Pedido inicializado

			return pedido;

		}

	}
	
	// Método rellenarCliente sirve para rellenar los atributos del cliente
	
	public void rellenarCliente(Cliente cliente) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del cliente:");
		String nom = sc.nextLine();
		cliente.setNombre(nom.toLowerCase());

		System.out.println("Apellidos del cliente:");
		String ape = sc.nextLine();
		cliente.setApellidos(ape.toUpperCase());

		cliente.setFechaDeAlta(LocalDate.now());

		 System.out.println("Telefono del cliente:");
		 cliente.setTelefono(sc.nextInt());
		 String saltodelinea;
		 saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores

		 System.out.println("Direccion del cliente:");
		 cliente.setDireccion(sc.nextLine());
		 cliente.setHistorial("0");
		 
		 System.out.println("Cliente: \nNombre: " + cliente.getNombre() +
		 " Apellidos: " + cliente.getApellidos() + " Fecha de Alta: " +
		 cliente.getFechaDeAlta() + " Telefono: " + cliente.getTelefono() +
		 " Direccion: " + cliente.getDireccion() + " Historial: " +
		 cliente.getHistorial());
		
	}


}
