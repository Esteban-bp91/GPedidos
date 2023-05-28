package menus;

import java.util.ArrayList;

import clases.Comida;

/**
 * Clase CartaComidas de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.4 
 * @since 27/05/2023
 * 
 */
public class CartaComidas{
	
	/**
	 * Método mostrar(ArrayList<Comida>) imprime por pantalla la carta de comidas
	 * @param comidas es el ArrayList con las comidas a imprimir
	 */
	public void mostrar(ArrayList<Comida> comidas) {
		System.out.println("\nCarta de comidas:");
		for (int i = 0; i < comidas.size(); i++) {			
			System.out.println(i+") "+ comidas.get(i).getNombre() + "		Precio: "+ comidas.get(i).getPrecio());			
		}		
	} //Cierre del Método mostrar(ArrayList<Comida>)
} //Cierre de la clase CartaComidas

