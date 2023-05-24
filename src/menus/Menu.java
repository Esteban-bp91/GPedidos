package menus;

public abstract class Menu {
	protected int seleccion;

	public int getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(int seleccion) {
		this.seleccion = seleccion;
	}
	
	public abstract int elegir();
}
