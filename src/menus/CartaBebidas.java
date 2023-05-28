package menus;

/**
 * Clase CartaBebidas del software GPedidos
 * 
 * @author Esteban Baeza Pérez
 * @version 0.4 
 * @since 27/05/2023
 * 
 */

import java.util.ArrayList;

import clases.Bebida;

public class CartaBebidas{
	
	/**
	 * Método mostrar(ArrayList<Bebida>) imprime por pantalla la carta de bebidas
	 * @param bebidas es el ArrayList con las bebidas a imprimir
	 */
	public void mostrar(ArrayList<Bebida> bebidas) {
		System.out.println("\nCarta de bebidas:");
		for (int i = 0; i < bebidas.size(); i++) {			
			System.out.println(i+") "+ bebidas.get(i).getNombre() + "		Precio: "+ bebidas.get(i).getPrecio());			
		}		
	} //Cierre del Método mostrar(ArrayList<Bebida>)
} //Cierre de la clase CartaBebidas
