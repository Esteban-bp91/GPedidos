package menus;

import java.util.Scanner;

/**
 * Clase MenuInicial de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

public class MenuInicial extends Menu{

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
	}
	
	

}
