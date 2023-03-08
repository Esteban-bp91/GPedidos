package practica4;

import java.time.LocalDate;
import java.util.Scanner;

public class GestionPedidos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Producto prod1 = null;
		Producto prod2 = null;
		Cliente cli;
		Pedido pedido;
		double impor = 0;
		double importeT;
		
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
		

		// Creamos 3 clientes diferentes (nombre, apellidos, fecha, telefono, direccion,
			// historial)

	
		 // Cliente 1
		 
		 System.out.println("Nombre del cliente 1");
			String nom = sc.nextLine();
			cliente1.setNombre(nom.toLowerCase());

		 System.out.println("Apellidos del cliente 1");
			String ape = sc.nextLine();
			cliente1.setApellidos(ape.toUpperCase());

		 cliente1.setFechaDeAlta(LocalDate.now());

		 System.out.println("Telefono del cliente 1");
			cliente1.setTelefono(sc.nextInt());
			saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores

		 System.out.println("Direccion del cliente 1");
			cliente1.setDireccion(sc.nextLine());
			cliente1.setHistorial("0");
		 
		 System.out.println("Cliente 1: \nNombre: " + cliente1.getNombre() +
		 "\nApellidos: " + cliente1.getApellidos() + "\nFecha de Alta: " +
		 cliente1.getFechaDeAlta() + "\nTelefono: " + cliente1.getTelefono() +
		 "\nDireccion: " + cliente1.getDireccion() + "\nHistorial: " +
		 cliente1.getHistorial());
		 
		 // Cliente 2
		 
		 System.out.println("Nombre del cliente 2");
			nom = sc.nextLine();
			cliente2.setNombre(nom.toLowerCase());

		 System.out.println("Apellidos del cliente 2");
			ape = sc.nextLine();
			cliente2.setApellidos(ape.toUpperCase());

		 cliente2.setFechaDeAlta(LocalDate.now());

		 System.out.println("Telefono del cliente 2");
			cliente2.setTelefono(sc.nextInt());
			saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores

			while (cliente2.getTelefono() == cliente1.getTelefono()) {
				System.out.println(
						"El telefono de cliente 2 debe ser diferente al telefono de cliente 1. \nEscriba de nuevo el telefono de cliente 2: ");
				cliente2.setTelefono(sc.nextInt());
			}

		 System.out.println("Direccion del cliente 2");
			cliente2.setDireccion(sc.nextLine());
			cliente2.setHistorial("0");
		 
		 System.out.println("Cliente 2: \nNombre: " + cliente2.getNombre() +
		 "\nApellidos: " + cliente2.getApellidos() + "\nFecha de Alta: " +
		 cliente2.getFechaDeAlta() + "\nTelefono: " + cliente2.getTelefono() +
		 "\nDireccion: " + cliente2.getDireccion() + "\nHistorial: " +
		 cliente2.getHistorial());
		 
		 // Cliente 3
		 
		 System.out.println("Nombre del cliente 3");
			nom = sc.nextLine();
			cliente3.setNombre(nom.toLowerCase());

		 System.out.println("Apellidos del cliente 3");
			ape = sc.nextLine();
			cliente3.setApellidos(ape.toUpperCase());

		 cliente3.setFechaDeAlta(LocalDate.now());

		 System.out.println("Telefono del cliente 3");
			cliente3.setTelefono(sc.nextInt());
			saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores

			while (cliente3.getTelefono() == cliente1.getTelefono() || cliente3.getTelefono() == cliente2.getTelefono()) {
				System.out.println(
						"El telefono de cliente 3 debe ser diferente al telefono de cliente 1 y 2. \nEscriba de nuevo el telefono de cliente 3: ");
				cliente3.setTelefono(sc.nextInt());
			}

		 System.out.println("Direccion del cliente 3");
			cliente3.setDireccion(sc.nextLine());
			cliente3.setHistorial("0");
		 
		 System.out.println("Cliente 3: \nNombre: " + cliente3.getNombre() +
		 "\nApellidos: " + cliente3.getApellidos() + "\nFecha de Alta: " +
		 cliente3.getFechaDeAlta() + "\nTelefono: " + cliente3.getTelefono() +
		 "\nDireccion: " + cliente3.getDireccion() + "\nHistorial: " +
		 cliente3.getHistorial());



			// Creamos 5 productos diferentes (nombre, precio, cantidad)
			
			 // Producto 1
			 
			 System.out.println("Nombre del producto 1:");
			 producto1.setNombre(sc.nextLine());
			 System.out.println("Precio del producto 1:");
			 producto1.setPrecio(sc.nextFloat());
			 System.out.println("Cantidad del producto 1:");
			 producto1.setCantidad(sc.nextInt());
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
			 
			 System.out.println("Producto 1: \nNombre: " + producto1.getNombre() +
			 "\nPrecio: " + producto1.getPrecio() + "\nCantidad: " +
			 producto1.getCantidad());
			 
			 // Producto 2
			 
			 System.out.println("Nombre del producto 2:");
			 producto2.setNombre(sc.nextLine());
			 System.out.println("Precio del producto 2:");
			 producto2.setPrecio(sc.nextFloat());
			 System.out.println("Cantidad del producto 2:");
			 producto2.setCantidad(sc.nextInt());
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
			 
			 System.out.println("Producto 2: \nNombre: " + producto2.getNombre() +
			 "\nPrecio: " + producto2.getPrecio() + "\nCantidad: " +
			 producto2.getCantidad());
			 
			 // Producto 3
			 
			 System.out.println("Nombre del producto 3:");
			 producto3.setNombre(sc.nextLine());
			 System.out.println("Precio del producto 3:");
			 producto3.setPrecio(sc.nextFloat());
			 System.out.println("Cantidad del producto 3:");
			 producto3.setCantidad(sc.nextInt());
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
			 
			 System.out.println("Producto 3: \nNombre: " + producto3.getNombre() +
			 "\nPrecio: " + producto3.getPrecio() + "\nCantidad: " +
			 producto3.getCantidad());
			 
			 // Producto 4
			 
			 System.out.println("Nombre del producto 4:");
			 producto4.setNombre(sc.nextLine());
			 System.out.println("Precio del producto 4:");
			 producto4.setPrecio(sc.nextFloat());
			 System.out.println("Cantidad del producto 4:");
			 producto4.setCantidad(sc.nextInt());
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
			 
			 System.out.println("Producto 4: \nNombre: " + producto4.getNombre() +
			 "\nPrecio: " + producto4.getPrecio() + "\nCantidad: " +
			 producto4.getCantidad());
			 
			 // Producto 5
			 
			 System.out.println("Nombre del producto 5:");
			 producto5.setNombre(sc.nextLine());
			 System.out.println("Precio del producto 5:");
			 producto5.setPrecio(sc.nextFloat());
			 System.out.println("Cantidad del producto 5:");
			 producto5.setCantidad(sc.nextInt());
				saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
		
			 System.out.println("Producto 5: \nNombre: " + producto5.getNombre() +
			 "\nPrecio: " + producto5.getPrecio() + "\nCantidad: " +
			 producto5.getCantidad());

		
				// Una vez creados clientes y productos entramos al MENU PEDIDOS

			System.out.println("###MENU PEDIDOS###" + "\n1. Realizar pedidos");

			System.out.println("Escriba el telefono del cliente");

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

				int prod;

				System.out.println("Elija el producto 1: " + "\n1." + producto1.getNombre() + "\n2." + producto2.getNombre()
						+ "\n3." + producto3.getNombre() + "\n4." + producto4.getNombre() + "\n5." + producto5.getNombre());

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

				System.out.println("Quiere anyadir un segundo producto al pedido? Y/N");

				String respuesta = sc.next();

				if (respuesta.equalsIgnoreCase("y")) {

					System.out.println("Elija el producto 2: " + "\n1." + producto1.getNombre() + "\n2." + producto2.getNombre()
							+ "\n3." + producto3.getNombre() + "\n4." + producto4.getNombre() + "\n5." + producto5.getNombre());

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

					// Calculamos el importe total del pedido

					impor = (prod1.getCantidad() * prod1.getPrecio()) + (prod2.getCantidad() * prod2.getPrecio());

					importeT = Math.round(impor * 100) / 100d;

					System.out.println(importeT);

				} else {

					prod2 = null;

					// Calculamos el importe total del pedido

					impor = (prod1.getCantidad() * prod1.getPrecio());

					importeT = Math.round(impor * 100) / 100d;

					System.out.println(importeT);

				}

				pedido = cli.realizarPedido(cli, prod1, prod2, importeT, 0, "NO PAGADO"); // Crea un pedido con esos atributos
																																								// (cliente, producto 1, producto 2 si
																																								// hay, importe total y un codigodePago
																																								// a 0 por estar sin pagar)

				System.out.println(pedido.toString());


				int elec = 0;

				do {

					System.out.println("Que desea hacer? Seleccione el numero" + "\n1.Pagar" + "\n2.Eliminar Producto 1"
							+ "\n3.Eliminar Producto 2" + "\n4.Agregar Producto 1" + "\n5.Agregar Producto 2");

					elec = sc.nextInt();

					if (elec == 1) {

						PasarelaDePago pago = new PasarelaDePago(importeT); // Accedemos a la PasareladePago

						cli.agregarPedido(pago.getCodigoPago()); // Añadimos al historial el pedido pagado

						// En caso de que no se haya pagado el pedido, se añadirá un 0 al historial

						if (pago.getCodigoPago() != 0) {

							pedido.setEstado("PAGADO");

							System.out.println(pedido.getEstado());

							pedido.setEstado("PREPARANDO");

							System.out.println(pedido.getEstado());

							pedido.setEstado("LISTO");

							System.out.println(pedido.getEstado());

							pedido.setEstado("SERVIDO");

							System.out.println(pedido.getEstado());

							System.out.println("Historial:" + cli.getHistorial());

						} else {

							System.out.println("Pedido no pagado");

						}

					}

					if (elec == 2) {

						pedido.eliminarProducto1();

					}

					if (elec == 3) {

						pedido.eliminarProducto2();

					}

					if (elec == 4) {

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

					}

					if (elec == 5) {

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

					}

				} while (elec != 1);



			} else {

				System.out.println("Telefono incorrecto");

			}

			sc.close();

		}


		
}
