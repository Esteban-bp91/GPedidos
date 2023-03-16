package clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import herramientas.Fichero;

/**
 * Clase main GestionPedidos de la practica 4 de Programación
 * 
 * @author EstebanBP
 * @version 1.1  
 * @since 08/02/2023
 * 
 * 
 * Funcionalidad controlStock añadida
 * 
 */

public class GestionPedidos {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
		
		// Atributos necesarios del main
		Producto prod1 = null;
		int cantidad1 = 0;
		Producto prod2 = null;
		int cantidad2 = 0;
		Cliente cli = null;
		Pedido pedido;
		double impor = 0;
		double importeTotal;
		int respuesta;
		String saltodelinea;
		Fichero f = new Fichero();
		Scanner sc = new Scanner(System.in);
		int stockNulo[] = new int[30];
		Producto nulo = new Producto("Nulo", 0.00, stockNulo);

		//ArrayList para guardar los clientes 
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(); 
		
		// Cargamos los clientes ya guardados en la carpeta Clientes
		clientes = f.cargarClientes(clientes);
		
		//ArrayList para guardar los productos
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		// Cargamois los productos ya guardados en la carpeta Productos
		productos = f.cargarProductos(productos);
		
		// Una vez creados clientes y productos entramos al MENU PEDIDOS
		 do {
			 System.out.println("\n### MENU PEDIDOS ###" + "\n1. Realizar un pedido nuevo"
			 		+ "                                     \n2. Crear un cliente nuevo"
			 		+ "										\n3. Crear un producto nuevo"
			 		+ "										\n4. Terminar el programa");
			 
			 respuesta = sc.nextInt();
			 
			 switch (respuesta) {
			 
			 case 1: { 					 // Realizar un pedido nuevo

						for (int i = 0; i < clientes.size(); i++) {  // Bucle for para mostrar los clientes y sus telefonos
						 	System.out.println("\nCliente "+i+": " + clientes.get(i).getNombre() + " Telf: " + clientes.get(i).getTelefono());
						}
											
						System.out.println("\nEscriba el telefono del cliente:");
						int telf = sc.nextInt();
						do { // Buscamos el telefono del cliente que coincida con el introducido por el usuario						
							for (int j = 0; j < clientes.size(); j++) {							
								if( telf == clientes.get(j).getTelefono()) {								
									cli = clientes.get(j);								
								}							
							}						
						} while (cli.getTelefono() != telf);

						System.out.println("Ha elegido a: " + cli.getNombre() + " " + cli.getApellidos());
					
						// Pedimos al usuario que elija el producto para asignarlo al producto 1 
						System.out.println("\nElija el primer producto:");
						prod1 = nulo.elegirProducto(productos);
						
						// Pedimos al usuario la cantidad de producto 1 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 1
						System.out.println("Indique la cantidad que quiere de su producto 1: "+prod1.getNombre());				
						cantidad1 = sc.nextInt();
						
						if(cantidad1 > prod1.mostrarStock()) {						
							System.out.println("No puede comprar más de "+prod1.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + prod1.mostrarStock());
							cantidad1 = prod1.mostrarStock();						
						}
							
						// El pedido puede estar compuesto por 1 o 2 productos. Preguntamos al usuario si quiere un segundo producto en el pedido. Si la respuesta es Yes, se elige el producto que será el producto 2 del pedido.

						System.out.println("Quiere anyadir un segundo producto al pedido? Y/N");
						String answer = sc.next();

							if (answer.equalsIgnoreCase("y")) {
								prod2 = nulo.elegirProducto(productos);
								
								// Pedimos al usuario la cantidad de producto 1 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 1
								System.out.println("Indique la cantidad que quiere de su producto 2: "+prod2.getNombre());				
								cantidad2 = sc.nextInt();
								
								if(cantidad2 > prod2.mostrarStock()) {						
									System.out.println("No puede comprar más de "+prod2.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + prod2.mostrarStock());
									cantidad2 = prod2.mostrarStock();						
								}

								// Calculamos el importe total del pedido en caso de tener dos productos
								impor = (cantidad1 * prod1.getPrecio()) + (cantidad2 * prod2.getPrecio());
								importeTotal = Math.round(impor * 100) / 100d;

							} else {
								prod2 = null;

								// Calculamos el importe total del pedido en el caso de tener un solo producto
								impor = (cantidad1 * prod1.getPrecio());
								importeTotal = Math.round(impor * 100) / 100d;		
							}
							
							// Con el método realizarPedido de la clase Cliente e introduciendo los parámetros necesarios, creamos y guardamos el pedido en la variable pedido
							pedido = cli.realizarPedido(cli, prod1, cantidad1, prod2, cantidad2, importeTotal, 0, "NO PAGADO"); // Crea un pedido con esos atributos(cliente, producto 1, producto 2 si hay,
																																//importe total y un codigodePago a 0 por estar sin pagar)
							
							// Mostramos el ticket del pedido con el método toString
							System.out.println(pedido.toString());

							// Una vez creado el pedido con todos sus atributos necesarios, mostramos al usuario las 5 posibles acciones que puede realizar.
							// 1. Pagar  2. Eliminar Producto1  3. Eliminar Producto2  4. Agregar Producto1  5. Agregar Producto2
							
							int elec = 0;
							do {
								System.out.println("\n\nQue desea hacer? Seleccione el numero\n1.Pagar\n2.Eliminar Producto 1\n3.Eliminar Producto 2\n4.Agregar Producto 1\n5.Agregar Producto 2");
								elec = sc.nextInt();
								
								// 1. Pagar
								if (elec == 1) {  
									PasarelaDePago pago = new PasarelaDePago(importeTotal); // Accedemos a la PasareladePago
									cli.agregarPedido(pago.getCodigoPago()); // Añadimos al historial el pedido pagado con el método agregarPedido de la clase Cliente
									
									// En caso de que no se haya pagado el pedido, se añadirá un 0 al historial
									if (pago.getCodigoPago() != 0) {  // Una vez pagado el pedido y con codigoPago diferente a 0, procedemos a servir el pedido y actualizar el stock de cada producto
										pedido.setPago(pago.getCodigoPago());								
										pedido.setEstado("PAGADO");
										System.out.println(pedido.getEstado());
										pedido.setEstado("PREPARANDO");
										System.out.println(pedido.getEstado());
										pedido.setEstado("LISTO");
										System.out.println(pedido.getEstado());
										pedido.setEstado("SERVIDO");
										System.out.println(pedido.getEstado());								
										prod1.actualizarStock(cantidad1);								
										prod1.mostrarStock();
										
										if (prod2 != null) {
											prod2.actualizarStock(cantidad2);
											prod2.mostrarStock();
										}	
										
										System.out.println("Historial:" + cli.getHistorial());							
										f.imprimirPedido(pedido);
										
									} else {
										System.out.println("Pedido no pagado");
									}
								}
								
								// 2. Eliminar Producto1
								if (elec == 2) {									
									if (pedido.getProducto1() != null) {	
										pedido.eliminarProducto1();											
									} else {										
										System.out.println("El producto 1 no existe. Agregue un producto 1 al pedido");
									}									
								}
								
								// Eliminar Producto2
								if (elec == 3) {								
									if(pedido.getProducto2() != null) {									
										pedido.eliminarProducto2();									
									} else {									
										System.out.println("El producto 2 no existe. Agregue un producto 2 al pedido");
									}								
								}
								
								// 4. Agregar Producto 1
								if (elec == 4) {								
									if(pedido.getProducto1() == null) {									
										System.out.println("\nElija el producto 1:");									
										prod1 = nulo.elegirProducto(productos);
										
										// Pedimos al usuario la cantidad de producto 1 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 1									
										System.out.println("Indique la cantidad que quiere de su producto 1: "+prod1.getNombre());										
										cantidad1 = sc.nextInt();
										
										if(cantidad1 > prod1.mostrarStock()) {										
											System.out.println("No puede comprar más de "+prod1.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + prod1.mostrarStock());
											cantidad1 = prod1.mostrarStock();										
										}
										
									} else {							
										System.out.println("El producto 1 ya esta elegido. Elimine el producto 1 para agregar otro.");							
									}
									
									pedido.setProducto1(prod1);
									pedido.setCantidad1(cantidad1);
									System.out.println(pedido.toString());

								}
								
								// 5. Agregar Producto2

								if (elec == 5) {			
									if (pedido.getProducto2() == null) {										
										System.out.println("\nElija el producto 2:");										
										prod2 = nulo.elegirProducto(productos);
										
										// Pedimos al usuario la cantidad de producto 2 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 2										
										System.out.println("Indique la cantidad que quiere de " + prod2.getNombre() + " (int menor de 30)");									
										cantidad2 = sc.nextInt();
								
										if(cantidad2 > prod2.mostrarStock()) {								
											System.out.println("No puede comprar más de "+prod2.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + prod2.mostrarStock());
											cantidad2 = prod2.mostrarStock();								
										}							
									} else {					
										System.out.println("El producto 2 ya esta elegido. Elimine el producto 2 para agregar otro.");					
									}
									
									pedido.setProducto2(prod2);
									pedido.setCantidad2(cantidad2);
									System.out.println(pedido.toString());
								}		
							} while (elec != 1);
				break;
			}
			case 2: {   // Crear un cliente nuevo
				
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
				// Recogemos los datos del nuevo cliente por consola
				Cliente nuevo = new Cliente();				
				nuevo.rellenarCliente(nuevo);				 
				clientes.add(nuevo);  // Guardamos el nuevo cliente en el array de clientes				
				Fichero nuevoCliente = new Fichero();				
				nuevoCliente.crearCliente(nuevo);
				
				break;
			}
			
			case 3: {  // Crear un producto nuevo
				
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
				// Recogemos los datos del nuevo cliente por consola				
				Producto nuevo = new Producto();								
				nuevo.rellenarProducto(nuevo);				 
				productos.add(nuevo);  // Guardamos el nuevo cliente en el array de clientes				
				Fichero nuevoProducto = new Fichero();				
				nuevoProducto.crearProducto(nuevo);				
				break;
			}
			
			case 4:{  // Terminar el programa
				break;
			}	
			default:	
				System.out.println("\nNo ha escogido una opcion valida");
			}
		 } while (respuesta != 4); 
		 System.out.println("\nPrograma terminado"); 		 		 
	}
}
