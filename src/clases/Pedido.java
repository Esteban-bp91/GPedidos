package practica4;

public class Pedido {

	private Cliente cliente;
	private Producto producto1;
	private Producto producto2;
	private double importeTotal;
	private long codigoPago;
	private String estado;

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


	/**
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

	/**
	 * @param cliente
	 * @param producto1
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente, Producto producto1, double importeTotal, long pago, String estado) {
		this.cliente = cliente;
		this.producto1 = producto1;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
	}

	/**
	 * @param cliente
	 * @param producto1
	 * @param producto2
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente, Producto producto1, Producto producto2, double importeTotal, long pago,
			String estado) {
		this.cliente = cliente;
		this.producto1 = producto1;
		this.producto2 = producto2;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
	}

	public String Pagar(String forma) {
		return forma;
	}

	public Cliente agregarCliente(Cliente c) {
		this.cliente = c;
		return cliente;
	}

	public void agregarProducto1(Producto prod) {
		this.producto1 = prod;
	}

	public void agregarProducto2(Producto prod) {
		this.producto2 = prod;
	}

	public void eliminarProducto1() {
		this.producto1 = null;
	}

	public void eliminarProducto2() {
		this.producto2 = null;
	}

	public String toString() {

		if (producto2 == null) {

			return "CANT.             PRODUCTO         PRECIO UD.       TOTAL\n"
					+ "=====             =========         =========        =====\n" + producto1.getCantidad()
					+ "                   " + producto1.getNombre() + "               " + producto1.getPrecio() + "e"
					+ "           " + producto1.getCantidad() * producto1.getPrecio() + "e" + "\nImporte Total = "
					+ (producto1.getCantidad() * producto1.getPrecio()) + "e";

		} else {

			return "CANT.             PRODUCTO               PRECIO UD.       TOTAL\n"
					+ "=====             =========               =========        =====\n" + producto1.getCantidad()
					+ "                   " + producto1.getNombre() + "               " + producto1.getPrecio() + "e"
					+ "           " + producto1.getCantidad() * producto1.getPrecio() + "e" + "\n" + producto2.getCantidad()
					+ "                   " + producto2.getNombre() + "               " + producto2.getPrecio() + "e"
					+ "           " + producto2.getCantidad() * producto2.getPrecio() + "e" + "\nImporte Total = "
					+ (producto1.getCantidad() * producto1.getPrecio()) + (producto2.getCantidad() * producto2.getPrecio()) + "e";

		}

	}

}
