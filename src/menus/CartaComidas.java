package menus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import clases.Bebida;
import clases.Comida;

/**
 * Clase CartaComidas de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

public class CartaComidas{

	public void mostrar(ArrayList<Comida> comidas) {
		System.out.println("\nCarta de comidas:");
		for (int i = 0; i < comidas.size(); i++) {			
			System.out.println(i+") "+ comidas.get(i).getNombre() + "		Precio: "+ comidas.get(i).getPrecio());			
		}		
	}
}
