package clases;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import excepciones.ExcepcionCuenta;
import excepciones.ExcepcionTarjeta;
import excepciones.ExcepcionTelefono;
import herramientas.QueryBD;


/**
 * Clase Cliente del software GPedidos
 * Superclase Producto
 * @author Esteban Baeza Pérez
 * @version 0.4
 * @since 27/05/2023
 * 
 */

public class Cliente implements Serializable{
	
	// Parámetros de la clase Cliente
	private String nombre;  
	private String apellidos;
	private String FechaDeAlta;
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
	public String getFechaDeAlta() {    
		return FechaDeAlta;
	}
	public void setFechaDeAlta(String date) {  
		FechaDeAlta = date;
	}
	public int getTelefono() {
		return telefono;
	}
	// El setter de telefono comprueba que sea un número entre 600000000 y 999999999 para que sea un número válido
	public void setTelefono(int telefono) throws ExcepcionTelefono{
		if (telefono < 600000000 || telefono > 999999999) {
			throw new ExcepcionTelefono("El telefono debe estar comprendido entre 600000000 y 999999999");
		} else {
			this.telefono = telefono;
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
	
	/** Constructor de Cliente para recoger datos del cliente
	 * @param String nombre especifica el nombre del cliente
	 * @param String apellidos especifica los apellidos del cliente
	 * @param String fechaDeAlta especifica la fecha de creación del cliente
	 * @param int telefono identifica al cliente
	 * @param String direccion especifica la dirección del cliente
	 * @param String historial especifica los pedidos hechos por el cliente durante la ejecución del programa
	 */
	 public Cliente(String nombre, String apellidos, String fechaDeAlta, int telefono, String direccion,
			String historial) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.FechaDeAlta = fechaDeAlta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.historial = historial;
	} //Cierre del constructor Cliente
	
	/** Constructor vacío de Cliente
	 * @param String nombre especifica el nombre del cliente
	 * @param String apellidos especifica los apellidos del cliente
	 * @param String fechaDeAlta especifica la fecha de creación del cliente
	 * @param int telefono identifica al cliente
	 * @param String direccion especifica la dirección del cliente
	 * @param String historial especifica los pedidos hechos por el cliente durante la ejecución del programa
	 */
	public Cliente() {
		this.nombre = null;
		this.apellidos = null;
		this.FechaDeAlta = null;
		this.telefono = 0;
		this.direccion = null;
		this.historial = null;
	} //Cierre del constructor vacío de Cliente
	
	// Método agregarPedido(long) añade el código de pago introducido como parámetro de un pedido pagado al historial del cliente
	public void agregarPedido(long p) {
		historial = historial + "\n" + p;
	} //Cierre del Método agregarPedido(long)
	
	/** Método realizarPedido(Cliente, Bebida, Comida, double, long, String) crea y devuelve un pedido con los parámetros pasados al método. El pedido creado será de un o dos productos
	 *  @return El pedido creado segun el caso
	 * 
	*/
	public Pedido realizarPedido(Cliente c, Bebida bebida, Comida comida, double total, long codigodePago, String estado) {
		if (comida == null) {  // Pedido de un solo producto
			Pedido pedido = new Pedido(c, bebida, total, codigodePago, estado); // Pedido inicializado
			return pedido;  
		} else {  // Pedido de dos productos
			Pedido pedido = new Pedido(c, bebida, comida, total, codigodePago, estado); // Pedido inicializado
			return pedido;
		}
	} //Cierre del Método realizarPedido(Cliente, Bebida, Comida, double, long, String)
	
	/**
	 *  Método rellenarCliente(Cliente) donde recogemos los datos del nuevo cliente por consola
	 * @param cliente
	 * @throws ParseException
	 * @throws ExcepcionTelefono
	 */
	public void rellenarCliente(Cliente cliente) throws ParseException, ExcepcionTelefono{		

		Scanner sc = new Scanner(System.in);
		String saltodelinea;
		System.out.println("Nombre del cliente:");
		String nom = sc.nextLine();
		cliente.setNombre(nom.toLowerCase());
		System.out.println("Apellidos del cliente:");
		String ape = sc.nextLine();
		cliente.setApellidos(ape.toUpperCase());
		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  // Recogemos la fecha en este String para poder parsear a LocalDate			  
		cliente.setFechaDeAlta(fecha);
		System.out.println("Telefono del cliente:");
			try {
				cliente.setTelefono(sc.nextInt());
			} catch (ExcepcionTelefono et) {
				System.out.println("Error. "+et.getMessage());
			}
		 saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores

		 System.out.println("Direccion del cliente:");
		 cliente.setDireccion(sc.nextLine());
		 cliente.setHistorial("0");
	} //Cierre del Método rellenarCliente(Cliente)
	
	/**
	 * Método rellenarCliente para el caso de que el teléfono introducido no esté ya registrado 
	 * @param cliente
	 * @param int telf será el teléfono a asignar al nuevo cliente
	 * @throws ParseException
	 * @throws ExcepcionTelefono
	 */
	public void rellenarCliente(Cliente cliente, int telf) throws ParseException, ExcepcionTelefono{		
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
		
		try {
			cliente.setTelefono(telf);
		} catch (ExcepcionTelefono et) {
			System.out.println("Error. "+et.getMessage());
		}

		System.out.println("Direccion del cliente:");
		cliente.setDireccion(sc.nextLine());
		cliente.setHistorial("0");		
	} //Cierre del Método rellenarCliente(Cliente, int)
	
	/**
	 * Método mostrarClientes(ArrayList<Cliente>) imprime por pantalla todos los clientes y sus parámetros nombre, apellidos y teléfono del ArrayList de Cliente
	 * @param clientes
	 */
	public void mostrarClientes(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {  // Bucle for para mostrar los clientes y sus telefonos
		 	System.out.println("Cliente "+i+": " + clientes.get(i).getNombre() +" "+clientes.get(i).getApellidos()+" Telf: " + clientes.get(i).getTelefono());
		}
	} //Cierre del Método mostrarClientes(ArrayList<Cliente>)
	
	/**
	 * Método asignarCliente(ArrayList<Cliente>) selecciona a un cliente del ArrayList según su teléfono y si el teléfono no se encuentra en el ArrayList permite crear un cliente nuevo
	 * @param clientes
	 * @return el cliente elegido
	 * @throws Exception
	 */
	public Cliente asignarCliente(ArrayList<Cliente> clientes) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEscriba el telefono del cliente:");
		int telf = sc.nextInt();
		Cliente cli = new Cliente();
		QueryBD bd = new QueryBD();
		
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
					cli.rellenarCliente(cli, telf);
					if(cli.getTelefono() != 0) {
						bd.guardarCliente(cli); //Guardamos el nuevo cliente en el fichero Clientes
					}
				} else {
					cli = new Cliente("clienteNulo","N. N.","01/01/0001",0, "nula0", "0" );
				}
			}	
		return cli;
	} //Cierre del Método asignarCliente(ArrayList<Cliente>)
	
	/**
	 * Método pagarPedido() permite al cliente pagar el pedido, creando una instancia de PasarelaDePago
	 * @param cli
	 * @param p
	 * @param importeTotal
	 * @param bebida
	 * @param comida
	 * @throws ExcepcionCuenta
	 * @throws ExcepcionTarjeta
	 */
	public void pagarPedido(Cliente cli, Pedido p, Double importeTotal, Bebida bebida, Comida comida) throws ExcepcionCuenta, ExcepcionTarjeta {
		PasarelaDePago pago = new PasarelaDePago(importeTotal); // Accedemos a la PasareladePago introduciendo el importe total del pedido
		pago.pagar(); // Accedemos al método pagar para pagar el pedido
		cli.agregarPedido(pago.getCodigoPago()); // Añadimos al historial el pedido pagado con el método agregarPedido de la clase Cliente
		
		// En caso de que no se haya pagado el pedido, se añadirá un 0 al historial
		if (pago.getCodigoPago() != 0) {  // Una vez pagado el pedido y con codigoPago diferente a 0, procedemos a servir el pedido y actualizar el stock de cada producto
			p.setCodigoPago(pago.getCodigoPago());								
			p.setEstado("PAGADO");
			System.out.println(p.getEstado());
			p.setEstado("PREPARANDO");
			System.out.println(p.getEstado());
			p.setEstado("LISTO");
			System.out.println(p.getEstado());
			p.setEstado("SERVIDO");
			System.out.println(p.getEstado());								
			bebida.actualizarStock(bebida.cantidad);								
			bebida.mostrarStock();
			
			if (comida != null) {
				comida.actualizarStock(comida.cantidad);
				comida.mostrarStock();
			}	
			
			System.out.println("Historial:" + cli.getHistorial());	
		}	
	} //Cierre del Método pagarPedido()
} //Cierre de la clase Cliente
