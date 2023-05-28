package menus;

import java.util.Scanner;

/**
 * Clase MenuInicial del software GPedidos
 * @author Esteban Baeza Pérez
 * @version 0.4 
 * @since 27/05/2023
 * @see Clase Menu
 */
public class MenuInicial extends Menu{
	
	/**
	 * Método elegir() para elegir la tarea a realizar
	 * @return un entero con la tarea correspondiente a realizar
	 */
	@Override
	public int elegir() {
		Scanner sc = new Scanner(System.in);
		 System.out.println("\n### MENU INICIAL ###" + "\n1. Realizar un pedido nuevo"
			 		+ "                                     \n2. Crear un cliente nuevo"
			 		+ "										\n3. Crear un producto nuevo"
			 		+ "										\n4. Terminar el programa"
	 				+ "										\n\nElija la tarea a realizar:");

		seleccion = sc.nextInt();
		return seleccion;
	} //Cierre del método elegir()
}//Cierre de la clase MenuInicial
