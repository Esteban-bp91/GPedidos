package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import herramientas.Fichero;


/**
 * Clase Cliente GPedidos v0.31 
 * 
 * @author EstebanBP
 * @version 0.31  
 * @since 25/03/2023
 * 
 * 
 * Funcionalidad lectura/escritura de archivos .txt para Clientes, Productos y Pedidos
 * 
 */

public class Cliente {
	
	// Atributos de la clase Cliente

	private String nombre;
	private String apellidos;
	private String FechaDeAlta;   // LocalDate para recoger datos del cliente por consola. Date para recoger datos del cliente por fichero
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

	/**
	 * Método setFechaDeAlta para recoger datos del cliente por consola
	 * 
	 * public LocalDate getFechaDeAlta() {
		return FechaDeAlta;
	}
	*/
	
	public String getFechaDeAlta() {    
		return FechaDeAlta;
	}

	public void setFechaDeAlta(String date) {  // Método setFechaDeAlta para recoger datos del cliente por fichero
		FechaDeAlta = date;
	}

	/**
	 * 
	 * Método setFechaDeAlta para recoger la fecha del cliente por consola
	 * 
	 * public void setFechaDeAlta() {
		FechaDeAlta = LocalDate.now();
	}
	*/

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

	/** Constructor de Cliente para recoger datos del cliente por consola
	 * @param nombre
	 * @param apellidos
	 * @param fechaDeAlta
	 * @param telefono
	 * @param direccion
	 * @param historial
	 */
	/**
	 * public Cliente(String nombre, String apellidos, LocalDate fechaDeAlta, int telefono, String direccion,
	 
			String historial) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.FechaDeAlta = fechaDeAlta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.historial = historial;
	}
	*/
	
	/** Constructor de Cliente para recoger datos del cliente por fichero
	 * @param nombre
	 * @param apellidos
	 * @param fechaDeAlta
	 * @param telefono
	 * @param direccion
	 * @param historial
	 */
	 public Cliente(String nombre, String apellidos, String fechaDeAlta, int telefono, String direccion,
	 
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
	
	// Método rellenarCliente(File, Cliente) sirve para rellenar los atributos del cliente con los ficheros de la carpeta Clientes
	
	public void rellenarCliente(File f, Cliente cliente) throws FileNotFoundException, IOException, ParseException {
				
		try {
			Scanner s = new Scanner(f);
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s*;\\s*");
			cliente.setNombre(sl.next().toLowerCase());
			cliente.setApellidos(sl.next().toUpperCase());
				
			String fecha = sl.next();  // Recogemos la fecha en este String para poder parsear a Date			  			    
			cliente.setFechaDeAlta(fecha);
										
			String telf = sl.next(); // Recogemos el teléfono en este String para poder parsear a int
			int telefono = Integer.parseInt(telf);
			cliente.setTelefono(telefono);
				
			cliente.setDireccion(sl.next());
							
			cliente.setHistorial("0");
			
		} catch (FileNotFoundException e) {
			// PrintWriter pw = null;
			e.printStackTrace();
			// e.printStackTrace(pw);

		}
		
	}
	
	
	
	// Método rellenarCliente(Cliente) donde recogemos los datos del nuevo cliente por consola

	public void rellenarCliente(Cliente cliente) throws ParseException{		

		Scanner sc = new Scanner(System.in);
		String saltodelinea;
		
		System.out.println("Nombre del cliente:");
		String nom = sc.nextLine();
		cliente.setNombre(nom.toLowerCase());

		System.out.println("Apellidos del cliente:");
		String ape = sc.nextLine();
		cliente.setApellidos(ape.toUpperCase());

		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  // Recogemos la fecha en este String para poder parsear a Date			
		  			    
		cliente.setFechaDeAlta(fecha);
		
		 System.out.println("Telefono del cliente:");
		 cliente.setTelefono(sc.nextInt());
		 saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores

		 System.out.println("Direccion del cliente:");
		 cliente.setDireccion(sc.nextLine());
		 cliente.setHistorial("0");
		
	}
	
	public void mostrarClientes(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {  // Bucle for para mostrar los clientes y sus telefonos
		 	System.out.println("\nCliente "+i+": " + clientes.get(i).getNombre() + " Telf: " + clientes.get(i).getTelefono());
		}
	}
	
	public Cliente asignarCliente(ArrayList<Cliente> clientes) throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEscriba el telefono del cliente:");
		int telf = sc.nextInt();
		Cliente cli = new Cliente();
		
		// Buscamos el telefono del cliente que coincida con el introducido por el usuario						
			for (int j = 0; j < clientes.size(); j++) {							
				if( telf == clientes.get(j).getTelefono()) {	
					cli = clientes.get(j);	
					j = clientes.size();
				}
			}
			// Bucle if para crear un cliente nuevo si el telefono introducido no tiene coincidencias en el arrayList Clientes
			if(cli.getTelefono() == 0) {
				Scanner entrada = new Scanner(System.in);
				System.out.println("No se ha encontrado ningun cliente con ese telefono. \nDesea crear el nuevo cliente? Y/N");
				String ans = entrada.next();
				if(ans.equalsIgnoreCase("y")) {
					cli.rellenarCliente(cli);				 
					clientes.add(cli);  // Guardamos el nuevo cliente en el array de clientes				
					Fichero nuevoCliente = new Fichero();	// Creamos el archivo .txt con los datos del cliente			
					nuevoCliente.crearCliente(cli);
				}
			}	
		return cli;
	}
}
