package menus;

/**
 * Clase Menu del software GPedidos
 * @author Esteban Baeza Pérez
 * @version 0.4 
 * @since 27/05/2023
 */
public abstract class Menu {
	//Parámetro necesario
	protected int seleccion;
	//Getters and setters
	public int getSeleccion() {
		return seleccion;
	}
	public void setSeleccion(int seleccion) {
		this.seleccion = seleccion;
	}
	
	/**
	 * Método abstracto elecgir()
	 * @return un número entero con la tarea a realizar
	 */
	public abstract int elegir();
} //Cierre de la clase Menu
