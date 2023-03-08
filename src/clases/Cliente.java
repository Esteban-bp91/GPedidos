package practica4;

import java.time.LocalDate;

public class Cliente {

	private String nombre;
	private String apellidos;
	private LocalDate FechaDeAlta;
	private int telefono;
	private String direccion;
	private String historial;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaDeAlta() {
		return FechaDeAlta;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
		FechaDeAlta = fechaDeAlta;
	}

	public void setFechaDeAlta() {
		FechaDeAlta = LocalDate.now();
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		if (600000000 <= telefono && telefono <= 999999999) {
			this.telefono = telefono;
		} else {
			System.out.println("Teléfono incorrecto");
		}
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	/**
	 * @param nombre
	 * @param apellidos
	 * @param fechaDeAlta
	 * @param telefono
	 * @param direccion
	 * @param historial
	 */
	public Cliente(String nombre, String apellidos, LocalDate fechaDeAlta, int telefono, String direccion,
			String historial) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.FechaDeAlta = fechaDeAlta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.historial = historial;
	}

	public Cliente() {
		this.nombre = null;
		this.apellidos = null;
		this.FechaDeAlta = null;
		this.telefono = 0;
		this.direccion = null;
		this.historial = null;
	}

	public void agregarPedido(long p) {

		historial = historial + "\n" + p;

	}

	public Pedido realizarPedido(Cliente c, Producto p1, Producto p2, double total, long codigodePago, String estado) {

		if (p2 == null) {

			Pedido pedido = new Pedido(c, p1, total, codigodePago, estado); // Pedido inicializado

			return pedido;

		} else {

			Pedido pedido = new Pedido(c, p1, p2, total, codigodePago, estado); // Pedido inicializado

			return pedido;

		}

	}


}
