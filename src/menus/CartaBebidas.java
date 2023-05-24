package menus;

/**
 * Clase CartaBebidas de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

import java.util.ArrayList;

import clases.Bebida;

public class CartaBebidas{

	public void mostrar(ArrayList<Bebida> bebidas) {
		System.out.println("\nCarta de bebidas:");
		for (int i = 0; i < bebidas.size(); i++) {			
			System.out.println(i+") "+ bebidas.get(i).getNombre() + "		Precio: "+ bebidas.get(i).getPrecio());			
		}		
	}
}
