package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import herramientas.Ficheros;

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
		
		Scanner sc = new Scanner(System.in);
		Producto prod1 = null;
		int cantidad1 = 0;
		Producto prod2 = null;
		int cantidad2 = 0;
		Cliente cli = null;
		Pedido pedido;
		double impor = 0;
		double importeTotal;
		
		
		System.out.println("###MENU INICIAL###"+
				"\n1. Crear 3 clientes y 5 productos");
		
		String saltodelinea; // Guardamos aquí el salto de carro para evitar errores
		
		// Creamos los 3 clientes y 5 productos sin atributos
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(); //ArrayList para guardar los clientes 
		
		
		//Creamos un array con los archivos txt con los datos de los clientes y así saber la cantidad de clientes que hay 
		
		File carpetaClientes = new File("C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/Clientes"); 
		File[] listaClientes = carpetaClientes.listFiles(); 
		

		// Rellenamos los clientes con el método rellenarCliente (nombre, apellidos, fecha, telefono, direccion,
			// historial)
		

		for (int i = 0; i < listaClientes.length; i++) {   // Bucle for para crear tantos clientes como archivos tenga la carpeta Clientes y asignar la ruta de su respectivo archivo
			
		// Cliente i
			
			clientes.add(new Cliente());
				
			System.out.println("\nRellena los datos del Cliente "+i);
			
			String ruta = "C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/Clientes/Cliente"+i+".txt";
			 
			clientes.get(i).rellenarCliente(ruta, clientes.get(i));
			
		    for (int j = 0; j < clientes.size(); j++) {  // Bucle for para comprobar que los telefonos no se repitan
		    	
				while (clientes.get(i).getTelefono() == clientes.get(j).getTelefono() && i != j) {
					System.out.println(
								"El telefono de cliente "+i+" debe ser diferente al telefono de cliente "+j+". \nEscriba de nuevo el telefono de cliente "+i+": ");
					clientes.get(i).setTelefono(sc.nextInt());
				 }
		        
		      }
			 		
		}
		
		//ArrayList para guardar los productos
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		//Creamos un array con los archivos txt con los datos de los productos y así saber la cantidad de productos que hay 
		
		File carpetaProductos = new File("C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/Productos"); 
		File[] listaProductos = carpetaProductos.listFiles();  


			// Rellenamos los datos de los productos con el método rellenarProducto (nombre, precio, stock)
			
			 // Producto i
		
		for (int i = 0; i < listaProductos.length; i++) {   // Bucle for para crear tantos productos como archivos tenga la carpeta Productos y asignar la ruta de su respectivo archivo
			
			// Cliente i
				
				productos.add(new Producto());
					
				System.out.println("\nRellena los datos del Producto "+i);
				
				String ruta = "C:/Users/EstebanBP/Desktop/DAW/ENTORNOS DE DESARROLLO/GestionPedidos/Productos/Producto"+i+".txt";
				 
				productos.get(i).rellenarProducto(ruta, productos.get(i));
				
				 		
			}

		
				// Una vez creados clientes y productos entramos al MENU PEDIDOS
		 
		 String respuesta;
		 
		 do {
			 
			 System.out.println("\n### MENU PEDIDOS ###" + "\n1. ¿Desea realizar un pedido nuevo? Y/N");
			 
			 respuesta = sc.next();

			 if (respuesta.equalsIgnoreCase("y")) {  // Bucle para crear un pedido pidiendo el telefono del cliente
				 	
				 
				 //TODO Bucle para asignar el telefono con la cantidad de clientes que sea
				 
					for (int i = 0; i < listaClientes.length; i++) {
						
					 	System.out.println("\nCliente "+i+": " + clientes.get(i).getNombre() + " Telf: " + clientes.get(i).getTelefono());
						
					}
										
					System.out.println("\nEscriba el telefono del cliente:");

					int telf = sc.nextInt();
					
					do {
																
						for (int j = 0; j < listaClientes.length; j++) {
						
							if( telf == clientes.get(j).getTelefono()) {
							
								cli = clientes.get(j);
							
							}
						
						}
						
					
					} while (cli.getTelefono() != telf);

					
					System.out.println("Ha elegido a: " + cli.getNombre() + " " + cli.getApellidos());

					
						
						// Pedimos al usuario que elija el producto para asignarlo al producto 1 

						int prod;
						
						System.out.println("\nCARTA:");
						
						for (int i = 0; i < listaProductos.length; i++) {
							
							System.out.println(i+") "+ productos.get(i).getNombre() + "		Precio: "+ productos.get(i).getPrecio());
							
						}

						System.out.println("\nElija el producto 1:");

						prod = sc.nextInt();
						
						if(prod >= 0 && prod < listaProductos.length) {
							
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
						
						// Pedimos al usuario la cantidad de producto 1 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 1

						
						System.out.println("Indique la cantidad que quiere de su producto 1: "+prod1.getNombre());
						
						cantidad1 = sc.nextInt();
						
						if(cantidad1 > prod1.mostrarStock()) {
							
							System.out.println("No puede comprar más de "+prod1.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + prod1.mostrarStock());
							cantidad1 = prod1.mostrarStock();
							
						}
						
						// El pedido puede estar compuesto por 1 o 2 productos. Preguntamos al usuario si quiere un segundo producto en el pedido. Si la respuesta es Yes, se elige el producto que será el producto 2 del pedido.

						System.out.println("Quiere anyadir un segundo producto al pedido? Y/N");

						respuesta = sc.next();

						if (respuesta.equalsIgnoreCase("y")) {

							System.out.println("\nElija el producto 2:");
							
							for (int i = 0; i < listaProductos.length; i++) {
								
								System.out.println(i+") "+ productos.get(i).getNombre() + "		Precio: "+ productos.get(i).getPrecio());
								
							}
							
							prod = sc.nextInt();
							
							if(prod >= 0 && prod < listaProductos.length) {
								
								prod2 = productos.get(prod);
								
							}else {
								System.out.println("Producto 2 incorrecto");
							}
							
							//Si el stock del producto 2 elegido es menor a 5, se repone el stock al completo
							
							System.out.println("Ha elegido como Producto 2: " + prod2.getNombre() + "   Precio: " + prod2.getPrecio() + " Stock: " + prod2.mostrarStock());
							
							if (prod2.mostrarStock() <= 5) {
								
								System.out.println("Stock de Producto 2 bajo. \n Reponemos el stock al completo");
								
								prod2.llenarStock();
								
								System.out.println("Stock de Producto 2: " + prod2.mostrarStock());
								
							}
							
							// Pedimos al usuario la cantidad de producto 2 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 2

							
							System.out.println("Indique la cantidad que quiere de " + prod2.getNombre() + " (int menor de 30)");
							
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

						pedido = cli.realizarPedido(cli, prod1, cantidad1, prod2, cantidad2, importeTotal, 0, "NO PAGADO"); // Crea un pedido con esos atributos
																														// (cliente, producto 1, producto 2 si
																														// hay, importe total y un codigodePago a 0 por estar sin pagar)
						
						// Mostramos el ticket del pedido con el método toString
						
						System.out.println(pedido.toString());

						// Una vez creado el pedido con todos sus atributos necesarios, mostramos al usuario las 5 posibles acciones que puede realizar.
						// 1. Pagar  2. Eliminar Producto1  3. Eliminar Producto2  4. Agregar Producto1  5. Agregar Producto2
						
						int elec = 0;

						do {

							System.out.println("Que desea hacer? Seleccione el numero" + "\n1.Pagar" + "\n2.Eliminar Producto 1"
									+ "\n3.Eliminar Producto 2" + "\n4.Agregar Producto 1" + "\n5.Agregar Producto 2");

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
									
									pedido.imprimirPedido(pedido);
									
								} else {

									System.out.println("Pedido no pagado");

								}

							}
							
							// 2. Eliminar Producto1

							if (elec == 2) {
								
								System.out.println(pedido.getProducto1());
								
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
									
									for (int i = 0; i < listaProductos.length; i++) {
										
										System.out.println("\n"+i+") "+ productos.get(i).getNombre() + "		Precio: "+ productos.get(i).getPrecio() + " Stock: " + productos.get(i).mostrarStock());
										
									}

									
									prod = sc.nextInt();
									
									if(prod >= 0 && prod < listaProductos.length) {
										
										prod1 = productos.get(prod);
										
									}else {
										System.out.println("Producto 1 incorrecto");
									}
									
									
									//Si el stock del producto 1 elegido es menor a 5, se repone el stock al completo

									
									System.out.println("Ha elegido como Producto 1: " + prod1.getNombre() + "   Precio: " + prod1.getPrecio() + " Stock: " + prod1.mostrarStock());
									
									if (prod1.mostrarStock() <= 5) {
										
										System.out.println("Stock de " + prod1.getNombre() + " bajo. \n Reponemos el stock al completo");
										
										prod1.llenarStock();
										
										System.out.println("Stock de " + prod1.getNombre() + ": " + prod1.mostrarStock());
										
									}
									
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
									
									for (int i = 0; i < listaProductos.length; i++) {
										
										System.out.println("\n"+i+") "+ productos.get(i).getNombre() + "		Precio: "+ productos.get(i).getPrecio() + " Stock: " + productos.get(i).mostrarStock());
										
									}
									
									prod = sc.nextInt();
									
									if(prod >= 0 && prod < listaProductos.length) {
										
										prod2 = productos.get(prod);
										
									}else {
										System.out.println("Producto 2 incorrecto");
									}
									
									//Si el stock del producto 2 elegido es menor a 5, se repone el stock al completo
									
									System.out.println("Ha elegido como Producto 2: " + prod2.getNombre() + "   Precio: " + prod2.getPrecio() + " Stock: " + prod2.mostrarStock());
									
									if (prod2.mostrarStock() <= 5) {
										
										System.out.println("Stock de Producto 2 bajo. \n Reponemos el stock al completo");
										
										prod2.llenarStock();
										
										System.out.println("Stock de Producto 2: " + prod2.mostrarStock());
										
									}
									
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

					
			 } else {
				 
				 System.out.println("Programa terminado");
				 
			 }
			 
		 } while (respuesta.equalsIgnoreCase("y"));
		 
		 System.out.println("Programa terminado");
		 
		 sc.close();  // Cerramos el scanner

	}

		
}
