package controlStock;

import java.time.LocalDate;
import java.util.Scanner;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Atributos necesarios del main
		
		Scanner sc = new Scanner(System.in);
		Producto prod1 = null;
		int cantidad1 = 0;
		Producto prod2 = null;
		int cantidad2 = 0;
		Cliente cli;
		Pedido pedido;
		double impor = 0;
		double importeTotal;
		
		
		System.out.println("###MENU INICIAL###"+
				"\n1. Crear 3 clientes y 5 productos");
		
		String saltodelinea; // Guardamos aquí el salto de carro para evitar errores
		
		// Creamos los 3 clientes y 5 productos sin atributos
		
		Cliente cliente1 = new Cliente(); 
		Cliente cliente2 = new Cliente(); 
		Cliente cliente3 = new Cliente(); 
		
		Producto producto1 = new Producto(); 
		Producto producto2 = new Producto(); 
		Producto producto3 = new Producto(); 
		Producto producto4 = new Producto(); 
		Producto producto5 = new Producto(); 
		

		// Rellenamos los 3 clientes diferentes con el método rellenarCliente (nombre, apellidos, fecha, telefono, direccion,
			// historial)

	
		 // Cliente 1
		
		System.out.println("\nRellena los datos del Cliente 1");
		 
		cliente1.rellenarCliente(cliente1);
		 
		 // Cliente 2
		 
		System.out.println("\nRellena los datos del Cliente 2");
		 
		cliente2.rellenarCliente(cliente2);
		
		 
		while (cliente2.getTelefono() == cliente1.getTelefono()) {
			System.out.println(
						"El telefono de cliente 2 debe ser diferente al telefono de cliente 1. \nEscriba de nuevo el telefono de cliente 2: ");
			cliente2.setTelefono(sc.nextInt());
		 }
		 
		 // Cliente 3
		
		System.out.println("\nRellena los datos del Cliente 3");
		 
		 cliente3.rellenarCliente(cliente3);
		 
			//Comprobamos que el telefono sea diferente al resto de clientes
		 
		 while (cliente3.getTelefono() == cliente1.getTelefono() || cliente3.getTelefono() == cliente2.getTelefono()) {
			 System.out.println(
						"El telefono de cliente 3 debe ser diferente al telefono de cliente 1 y 2. \nEscriba de nuevo el telefono de cliente 3: ");
			 cliente3.setTelefono(sc.nextInt());
		 }



			// Rellenamos los datos de los 5 productos diferentes con el método rellenarProducto (nombre, precio, stock)
			
			 // Producto 1
		 
		System.out.println("\nRellena los datos del Producto 1");
			 
		 producto1.rellenarProducto(producto1);
			 
			 // Producto 2
		 
		System.out.println("\nRellena los datos del Producto 2");
			 
		 producto2.rellenarProducto(producto2);
			 
			 // Producto 3
		 
		System.out.println("\nRellena los datos del Producto 3");
			 
		 producto3.rellenarProducto(producto3);
			 
			 // Producto 4
		 
		System.out.println("\nRellena los datos del Producto 4");
			 
		 producto4.rellenarProducto(producto4);
			 
			 // Producto 5
		 
		System.out.println("\nRellena los datos del Producto 5");
			 
		 producto5.rellenarProducto(producto5);
		
				// Una vez creados clientes y productos entramos al MENU PEDIDOS
		 
		 String respuesta;
		 
		 do {
			 
			 System.out.println("\n### MENU PEDIDOS ###" + "\n1. ¿Desea realizar un pedido nuevo? Y/N");
			 
			 respuesta = sc.next();

			 if (respuesta.equalsIgnoreCase("y")) {  // Bucle para crear un pedido pidiendo el telefono del cliente
				 
				 	System.out.println("\nCliente 1: " + cliente1.getNombre() + " Telf: " + cliente1.getTelefono() + "\nCliente 2: " + cliente2.getNombre() + " Telf: " + cliente2.getTelefono() + "\nCliente 3: " + cliente3.getNombre() + " Telf: " + cliente3.getTelefono());
				 
					System.out.println("\nEscriba el telefono del cliente:");

					int telf = sc.nextInt();

					if (telf == cliente1.getTelefono() || telf == cliente2.getTelefono() || telf == cliente3.getTelefono()) {

						if (telf == cliente1.getTelefono()) {
							
							cli = cliente1;

							System.out.println("Ha elegido a: " + cli.getNombre() + " " + cli.getApellidos());


						} else {

							if (telf == cliente2.getTelefono()) {

								cli = cliente2;

								System.out.println("Ha elegido a: " + cli.getNombre() + " " + cli.getApellidos());


							} else { // Telf = cliente 3

								cli = cliente3;

								System.out.println("Ha elegido a: " + cli.getNombre() + " " + cli.getApellidos());

							}

						}
						
						// Pedimos al usuario que elija el producto con un switch para asignarlo al producto 1 

						int prod;

						System.out.println("\nElija el producto 1: " + "\n1." + producto1.getNombre() + "		Precio: "+ producto1.getPrecio() + " Stock: " + producto1.mostrarStock() + "\n2." + producto2.getNombre()
						+ "		Precio: "+ producto2.getPrecio() + " Stock: " + producto2.mostrarStock() + "\n3." + producto3.getNombre() + "		Precio: "+ producto3.getPrecio() + " Stock: " + producto4.mostrarStock() + "\n4." + producto4.getNombre() + "		Precio: "+ producto4.getPrecio() + " Stock: " + producto4.mostrarStock() + "\n5." + producto5.getNombre()+ "		Precio: "+ producto5.getPrecio() + " Stock: " + producto5.mostrarStock());

						prod = sc.nextInt();

						switch (prod) {

						case 1:
							prod1 = producto1;

							break;

						case 2:
							prod1 = producto2;

							break;

						case 3:
							prod1 = producto3;

							break;

						case 4:
							prod1 = producto4;

							break;

						case 5:
							prod1 = producto5;

							break;

						default:
							System.out.println("Producto no valido");

							break;

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
						
						// El pedido puede estar compuesto por 1 o 2 productos. Preguntamos al usuario si quiere un segundo producto en el pedido. Si la respuesta es Yes, se elige el producto que será el producto 2 del pedido.

						System.out.println("Quiere anyadir un segundo producto al pedido? Y/N");

						respuesta = sc.next();

						if (respuesta.equalsIgnoreCase("y")) {

							System.out.println("Elija el producto 2: " + "\n1." + producto1.getNombre() + "		Precio: "+ producto1.getPrecio() + " Stock: " + producto1.mostrarStock() + "\n2." + producto2.getNombre()
							+ "		Precio: "+ producto2.getPrecio() + " Stock: " + producto2.mostrarStock() + "\n3." + producto3.getNombre() + "		Precio: "+ producto3.getPrecio() + " Stock: " + producto4.mostrarStock() + "\n4." + producto4.getNombre() + "		Precio: "+ producto4.getPrecio() + " Stock: " + producto4.mostrarStock() + "\n5." + producto5.getNombre()+ "		Precio: "+ producto5.getPrecio() + " Stock: " + producto5.mostrarStock());

							prod = sc.nextInt();

							switch (prod) {

							case 1:
								prod2 = producto1;

								break;

							case 2:
								prod2 = producto2;

								break;

							case 3:
								prod2 = producto3;

								break;

							case 4:
								prod2 = producto4;

								break;

							case 5:
								prod2 = producto5;

								break;

							default:
								prod2 = null;

								break;

							}
							
							//Si el stock del producto 2 elegido es menor a 5, se repone el stock al completo
							
							System.out.println("Ha elegido como Producto 2: " + prod2.getNombre() + "   Precio: " + prod2.getPrecio() + " Stock: " + prod2.mostrarStock());
							
							if (prod2.mostrarStock() <= 5) {
								
								System.out.println("Stock de Producto 2 bajo. \n Reponemos el stock al completo");
								
								prod2.llenarStock();
								
								System.out.println("Stock de Producto 2: " + prod2.mostrarStock());
								
							}
							
							// Pedimos al usuario la cantidad de producto 2 que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 2

							
							System.out.println("Indique la cantidad que quiere de " + prod1.getNombre() + " (int menor de 30)");
							
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
									
								} else {

									System.out.println("Pedido no pagado");

								}

							}
							
							// 2. Eliminar Producto1

							if (elec == 2) {
								
								if (prod1 != null) {
									
									pedido.eliminarProducto1();
									
								} else {
									
									System.out.println("El producto 1 no existe. Agregue un producto 1 al pedido");

								}
								
							}
							
							// Eliminar Producto2

							if (elec == 3) {
								
								if(prod2 != null) {
									
									pedido.eliminarProducto2();
									
								} else {
									
									System.out.println("El producto 2 no existe. Agregue un producto 2 al pedido");

								}
								
							}
							
							// 4. Agregar Producto 1

							if (elec == 4) {
								
								if(prod1 == null) {
									
									System.out
									.println("Elija el nuevo producto 1: " + "\n1." + producto1.getNombre() + "\n2." + producto2.getNombre()
											+ "\n3." + producto3.getNombre() + "\n4." + producto4.getNombre() + "\n5." + producto5.getNombre());

									int selec = sc.nextInt();

									switch (selec) {
									case 1:

										pedido.agregarProducto1(producto1);

										break;

									case 2:

										pedido.agregarProducto1(producto2);

										break;

									case 3:

										pedido.agregarProducto1(producto3);

										break;

									case 4:

										pedido.agregarProducto1(producto4);

										break;

									case 5:

										pedido.agregarProducto1(producto5);

										break;

									default:

										System.out.println("Producto 1 no se ha cambiado");

										break;
									}

									System.out.println(pedido.toString());
									
								} else {
									
									System.out.println("El producto 1 ya esta elegido. Elimine el producto 1 para agregar otro.");
									
								}

							}
							
							// 5. Agregar Producto2

							if (elec == 5) {
								
								if (prod2 == null) {
									
									System.out
									.println("Elija el nuevo producto 2: " + "\n1." + producto1.getNombre() + "\n2." + producto2.getNombre()
											+ "\n3." + producto3.getNombre() + "\n4." + producto4.getNombre() + "\n5." + producto5.getNombre());

									int selec = sc.nextInt();

									switch (selec) {
									case 1:

										pedido.agregarProducto2(producto1);

										break;

									case 2:

										pedido.agregarProducto2(producto2);

										break;

									case 3:

										pedido.agregarProducto2(producto3);

										break;

									case 4:

										pedido.agregarProducto2(producto4);

										break;

									case 5:

										pedido.agregarProducto2(producto5);

										break;

									default:

										System.out.println("Producto 2 no se ha cambiado");

										break;
									}

									System.out.println(pedido.toString());
									
								} else {
									
									System.out.println("El producto 2 ya esta elegido. Elimine el producto 2 para agregar otro.");
									
								}

							}

						} while (elec != 1);


					} else {

						System.out.println("Telefono incorrecto");

					}
					
			 } else {
				 
				 System.out.println("Programa terminado");
				 
			 }
			 
		 } while (respuesta.equalsIgnoreCase("y"));
		 
		 sc.close();  // Cerramos el scanner

	}

		
}
