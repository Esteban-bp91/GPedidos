package practica4;

public class Producto {

	private String nombre;
	private float precio;
	private int cantidad;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 */
	public Producto() {
		this.nombre = null;
		this.precio = 0;
		this.cantidad = 0;
	}

	public Producto(String nombre, float precio, int cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}



}
