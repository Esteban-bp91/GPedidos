package controlStock;

/**
 * Clase Pedido de la practica 4 de Programación
 * 
 * @author EstebanBP
 * @version 1.1  
 * @since 08/02/2023
 * 
 * 
 * Funcionalidad controlStock añadida
 * 
 */

public class Pedido {
	
	// Atributos de la clase Pedido

	private Cliente cliente;
	private Producto producto1;
	private int cantidad1;
	private Producto producto2;
	private int cantidad2;
	private double importeTotal;
	private long codigoPago;
	private String estado;
	
	// Getters and setters
	
	public int getCantidad1() {
		return cantidad1;
	}

	public void setCantidad1(int cantidad1) {
		this.cantidad1 = cantidad1;
	}

	public int getCantidad2() {
		return cantidad2;
	}

	public void setCantidad2(int cantidad2) {
		this.cantidad2 = cantidad2;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto1() {
		return producto1;
	}

	public void setProducto1(Producto producto1) {
		this.producto1 = producto1;
	}

	public Producto getProducto2() {
		return producto2;
	}

	public void setProducto2(Producto producto2) {
		this.producto2 = producto2;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public long getPago() {
		return codigoPago;
	}

	public void setPago(long pago) {
		this.codigoPago = pago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**  Constructor vacío de Pedido 
	 * @param cliente
	 * @param producto1
	 * @param producto2
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido() {
		this.cliente = null;
		this.producto1 = null;
		this.producto2 = null;
		this.importeTotal = 0;
		this.codigoPago = 0;
		this.estado = "NO PAGADO";
	}

	/**  Constructor de Pedido con un producto
	 * @param cliente
	 * @param producto1
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente, Producto producto1, int cantidad1, double importeTotal, long pago, String estado) {
		this.cliente = cliente;
		this.producto1 = producto1;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
		this.cantidad1 = cantidad1;
	}

	/**  Constructor de Pedido con dos productos
	 * @param cliente
	 * @param producto1
	 * @param producto2
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente, Producto producto1, int cantidad1, Producto producto2, int cantidad2, double importeTotal, long pago,
			String estado) {
		this.cliente = cliente;
		this.producto1 = producto1;
		this.producto2 = producto2;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
		this.cantidad1 = cantidad1;
		this.cantidad2 = cantidad2;

	}
	
	// Método agregarProducto1 para añadir el producto pasado como parámetro al pedido

	public void agregarProducto1(Producto prod) {
		this.producto1 = prod;
	}

	// Método agregarProducto2 para añadir el producto pasado como parámetro al pedido

	public void agregarProducto2(Producto prod) {
		this.producto2 = prod;
	}
	
	// Método eliminarProducto1 para eliminar el producto1 del pedido

	public void eliminarProducto1() {
		this.producto1 = null;
	}
	
	// Método eliminarProducto2 para eliminar el producto2 del pedido

	public void eliminarProducto2() {
		this.producto2 = null;
	}
	
	// Método toString redefinido para que nos devuelva el ticket del pedido según tenga un producto o dos

	public String toString() {

		if (producto2 == null) {

			return "CANT.             PRODUCTO         PRECIO UD.       TOTAL\n"
					+ "=====             =========         =========        =====\n" + cantidad1
					+ "                   " + producto1.getNombre() + "               " + producto1.getPrecio() + "€"
					+ "           " + cantidad1 * producto1.getPrecio() + "€" + "\nImporte Total = "
					+ (cantidad1 * producto1.getPrecio()) + "€";

		} else {

			return "CANT.             PRODUCTO               PRECIO UD.       TOTAL\n"
					+ "=====             =========               =========        =====\n" + cantidad1
					+ "                   " + producto1.getNombre() + "                    " + producto1.getPrecio() + "€"
					+ "           " + cantidad1 * producto1.getPrecio() + "€" + "\n" + cantidad2
					+ "                   " + producto2.getNombre() + "                    " + producto2.getPrecio() + "€"
					+ "           " + cantidad2 * producto2.getPrecio() + "€" + "\nImporte Total = "
					+ (double)((double)(cantidad1 * producto1.getPrecio()) + (double)(cantidad2 * producto2.getPrecio())) + "€";

		}

	}

}
