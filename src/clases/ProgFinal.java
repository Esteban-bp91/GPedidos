package clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import excepciones.ExcepcionImporte;
import herramientas.Fichero;
import menus.Menu;
import menus.MenuInicial;

/**
 * Clase ProgFinal (main) de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

public class ProgFinal {
	
	public static void main (String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int tarea; // Variable donde guardaremos la tarea a realizar al ejecutar un menú
		Cliente cli = new Cliente("clienteNulo","N. N.","01/01/0001", 600000000, "nula0", "0" ); //Cliente nulo para iniciar el programa antes de asignar un cliente al pedido que se va a crear
		Fichero fichero = new Fichero();
		Bebida bebida = new Bebida(0,null, 0, null, null, 0, false, false, null);
		Comida comida = new Comida(0,null,0,null,null,0,false,0,0,null);
		Double impor;
		Double importeTotal;
		Pedido pedido;
		
		//ArrayList para guardar los clientes 
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(); 
		
		//ArrayList para guardar las bebidas ya registradas
		ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
		
		//ArrayList para guardar las comidass ya registradas
		ArrayList<Comida> comidas = new ArrayList<Comida>();
		
		 //Clientes para añadir al array en caso de que el metodo cargarClientes de Fichero no funcione
		/*clientes.add(new Cliente("Alberto","AMOROS BASTIAS","14/05/2023",666666666,"Av. Purisima, 45, 03360","0"));
		clientes.add(new Cliente("Ana","BAEZA PEREZ","14/05/2023",777777777,"C. Los Rosales, 4, 03360","0"));
		clientes.add(new Cliente("Eli","GARCIA BERNA","14/05/2023",888888888,"Barrio San Jose, SN, 03360","0"));
		clientes.add(new Cliente("Carlos","BANON CUIN","14/05/2023",666666777,"Av. Atzavares, 5, 03360","0"));
		clientes.add(new Cliente("Daniel","CACERES DAMIAN","14/05/2023",666666888,"C. Virgen, 6, 03360","0"));
		*/
		
		try {
			// Cargamos los clientes de la carpeta Clientes
			clientes = fichero.cargarClientes(clientes);	
			
			//fichero.guardarClientes(clientes);  // Para guardar el arrayList de los clientes en el Fichero Clientes.dat
			
			// Cargamos las bebidas de la carpeta Bebidas
			bebidas = fichero.cargarBebidas(bebidas);
			
			//fichero.guardarBebidas(bebidas); // Para guardar el arrayList de las bebidas en el Fichero Bebidas.dat
					
			// Cargamos las comidas de la carpeta Comidas
			comidas = fichero.cargarComidas(comidas);
			
			//fichero.guardarComidas(comidas); // Para guardar el arrayList de las comidas en el Fichero Comidas.dat
			
		} catch (FileNotFoundException i) {
			System.out.println("Error. No se encontró el archivo al cargar algún dato de entrada");
		} catch(IOException e) {
			System.out.println("Error. No se pudo leer correctamente algún dato de entrada");
		} catch (ParseException f) {
			System.out.println("Error. No se pudo cargar correctamente algún dato de entrada");
		} catch (ClassNotFoundException c) {
			System.out.println("Error. No se pudo cargar correctamente algun objeto de entrada");
		}
		
		// Bucle Do que se realizará hasta que el usuario decida terminar el programa con la opción 4 del menú inicial
		do {
			Menu menuInicial = new MenuInicial();  // Creamos el menú inicial
			tarea = menuInicial.elegir();  // Se elige la tarea a realizar

			switch (tarea) {  // Switch para realizar la tarea elegida
			case 1: {  // Crear un pedido nuevo
				cli.mostrarClientes(clientes); 	// Listamos los clientes registrados		
				cli = cli.asignarCliente(clientes);  // Se elige el cliente que va a realizar el pedido
				if(cli.getTelefono() == 0) {  // Comprobamos que el teléfono no sea nulo, es decir, que el cliente se haya asignado correctamente
					break;
				} else {
					System.out.println("Ha elegido a: " + cli.getNombre() + " " + cli.getApellidos());
				}
				bebida = bebida.elegirBebida(bebidas); //Elegimos la bebida del pedido
				
				// El pedido puede estar compuesto por 1 o 2 productos. Preguntamos al usuario si quiere una comida en el pedido.
				System.out.println("Quiere agregar una comida al pedido? Y/N");
				String answer = sc.next();

					if (answer.equalsIgnoreCase("y")) {
						comida = comida.elegirComida(comidas); //Elegimos la comida
						
						// Calculamos el importe total del pedido en caso de tener dos productos
						impor = (bebida.cantidad * bebida.getPrecio()) + (comida.cantidad * comida.getPrecio());
						importeTotal = Math.round(impor * 100) / 100d;
						if ((Object)importeTotal instanceof Double) {
						}else {
							throw new ExcepcionImporte("Error en el importe del pedido");
						}
					} else {
						comida = null;

						// Calculamos el importe total del pedido en el caso de tener solo bebida
						impor = (bebida.cantidad * bebida.getPrecio());
						importeTotal = Math.round(impor * 100) / 100d;	
						if ((Object)importeTotal instanceof Double) {
						}else {
							throw new ExcepcionImporte("Error en el importe del pedido");
						}
					}
					
					// Con el método realizarPedido de la clase Cliente e introduciendo los parámetros necesarios, creamos y guardamos el pedido en la variable pedido
					pedido = cli.realizarPedido(cli, bebida, comida, importeTotal, 0, "NO PAGADO"); // Crea un pedido con esos atributos(cliente, bebida, comida si hay,
																														//importe total y un codigodePago a 0 por estar sin pagar)
					// Mostramos el ticket del pedido con el método imprimir
					System.out.println(pedido.imprimir());
					
					// Una vez creado el pedido con todos sus atributos necesarios, mostramos al usuario las 5 posibles acciones que puede realizar.
					// 1. Pagar  2. Eliminar Producto1  3. Eliminar Producto2  4. Agregar Producto1  5. Agregar Producto2
					int elec = 0;
					do {
						System.out.println("\n\nQue desea hacer? Seleccione el numero\n1.Pagar\n2.Eliminar bebida\n3.Eliminar comida\n4.Agregar bebida\n5.Agregar comida");
						elec = sc.nextInt();
						
						// 1. Pagar
						if (elec == 1) {  
								cli.pagarPedido(cli,pedido,importeTotal,bebida,comida);
								fichero.imprimirPedido(pedido);
						}
						
						// 2. Eliminar Bebida
						if (elec == 2) {									
							if (pedido.getBebida() != null) {	
								pedido.eliminarBebida();
								System.out.println(pedido.imprimir());
							} else {										
								System.out.println("Esta bebida no existe. Agregue una bebida al pedido");
							}									
						}
						
						// 3. Eliminar Comida
						if (elec == 3) {								
							if(pedido.getComida() != null) {									
								pedido.eliminarComida();
								System.out.println(pedido.imprimir());
							} else {									
								System.out.println("Esta comida no existe. Agregue una comida al pedido");
							}								
						}
						
						// 4. Agregar Bebida
						if (elec == 4) {								
							if(pedido.getBebida() == null) {									
								bebida = new Bebida(0,null, 0, null, null, 0, false, false, null);
								bebida = bebida.elegirBebida(bebidas);
							} else {							
								System.out.println("La bebida ya esta elegida. Elimine su bebida para elegir otra.");							
							}
							pedido.setBebida(bebida);
							System.out.println(pedido.imprimir());
						}
						
						// 5. Agregar Comida
						if (elec == 5) {			
							if (pedido.getComida() == null) {										
								comida = new Comida(0,null,0,null,null,0,false,0,0,null);
								comida = comida.elegirComida(comidas);
								System.out.println(pedido.imprimir());
							} else {					
								System.out.println("La comida ya esta elegida. Elimine su comida para elegir otra.");					
							}
							pedido.setComida(comida);
							System.out.println(pedido.imprimir());
						}		
					} while (elec != 1);
				break;
			}
			case 2: {
				cli = new Cliente("clienteNulo","N. N.","01/01/0001", 600000000, "nula0", "0" ); //Establecemos el cliente cli con estos valores para no machacar el cliente elegido con anterioridad si ya se ha realizado un pedido
				cli.rellenarCliente(cli);
				if(cli.getTelefono() != 0) {
					Fichero f = new Fichero();
					f.guardarCliente(cli, clientes); //Guardamos el nuevo cliente en el fichero Clientes
				}
				break;
			}
			case 3: {
				System.out.println("Que tipo de producto desea crear?"
						+"\n1. Una bebida nueva"
						+"\n2. Una comida nueva");
				try {
					int crear = sc.nextInt();
					switch (crear) {
					case 1: {
						Bebida nuevaBebida = new Bebida(0,null, 0, null, null, 0, false, false, null); //Establecemos la bebida con estos valores para no machacar la bebida elegida con anterioridad si ya se ha realizado un pedido
						nuevaBebida.rellenarBebida(nuevaBebida, bebidas);
						Fichero f = new Fichero();
						f.guardarBebida(nuevaBebida, bebidas); //Guardamos la nueva bebida en el fichero Bebidas
						break;
					}
					case 2: {
						Comida nuevaComida = new Comida(0,null,0,null,null,0,false,0,0,null); //Establecemos la bebida con estos valores para no machacar la bebida elegida con anterioridad si ya se ha realizado un pedido
						nuevaComida.rellenarComida(nuevaComida, comidas);
						Fichero f = new Fichero();
						f.guardarComida(nuevaComida, comidas); //Guardamos la nueva bebida en el fichero Bebidas
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + crear);
					}
				} catch (ArithmeticException e) {
					System.out.println("No ha elegido una opcion valida");
				}
				break;
			}
			case 4: {
				break;
			}
			default:
				throw new IllegalArgumentException("Respuesta incorrecta: " + tarea +". Eliga una opcion pulsando el numero, del 1 al 4.)");
			}
		} while (tarea != 4); 
		 System.out.println("\nPrograma terminado"); 
	}
}
