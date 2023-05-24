package clases;

import herramientas.Imprimible;

/**
 * Clase Pedido de la práctica final de Programación de 1º DAW - Curso 2022/2023
 * 
 * @author Esteban Baeza Pérez
 * @version 0.1 
 * @since 25/04/2023
 * 
 */

public class Pedido implements Imprimible{
	
	// Atributos de la clase Pedido

	private Cliente cliente;
	private Bebida bebida;
	private Comida comida;
	private double importeTotal;
	private long codigoPago;
	private String estado;
	private String rutaPedidos = "C:/Users/EstebanBP/eclipse-workspace/ProgFinal/Pedidos";
	
	// Getters and setters
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Comida getComida() {
		return comida;
	}

	public void setComida(Comida comida) {
		this.comida = comida;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public long getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(long codigoPago) {
		this.codigoPago = codigoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRutaPedidos() {
		return rutaPedidos;
	}

	public void setRutaPedidos(String rutaPedidos) {
		this.rutaPedidos = rutaPedidos;
	}


	/**  Constructor vacío de Pedido 
	 * @param cliente
	 * @param bebida
	 * @param comida
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	
	public Pedido() {
		this.cliente = null;
		this.bebida = null;
		this.comida = null;
		this.importeTotal = 0;
		this.codigoPago = 0;
		this.estado = "NO PAGADO";
	}


	/**  Constructor de Pedido con un producto
	 * @param cliente
	 * @param bebida
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente, Bebida bebida, double importeTotal, long pago, String estado) {
		this.cliente = cliente;
		this.bebida = bebida;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
	}

	/**  Constructor de Pedido con dos productos
	 * @param cliente
	 * @param bebida
	 * @param comida
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente, Bebida bebida, Comida comida, double importeTotal, long pago, String estado) {
		this.cliente = cliente;
		this.bebida = bebida;
		this.comida = comida;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
	}
	
	// Método agregarBebida para añadir la bebida pasada como parámetro al pedido

	public void agregarBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	// Método agregarComida para añadir la comida pasado como parámetro al pedido

	public void agregarComida(Comida comida) {
		this.comida = comida;
	}
	
	// Método eliminarBebida para eliminar la bebida del pedido

	public void eliminarBebida() {
		this.bebida = null;
	}
	
	// Método eliminarComida para eliminar la comida del pedido

	public void eliminarComida() {
		this.comida = null;
	}
	
	// Método imprimirPedido que crea el archivo de texto con los datos del pedido
	
	@Override
	public String imprimir() {
		if(bebida == null && comida == null) {
			return "Su pedido esta vacio. Agrege una bebida y/o una comida";
		

		} else if (comida == null) {
			
			Double impor = (bebida.cantidad * bebida.getPrecio());
			importeTotal = Math.round(impor * 100) / 100d;

			return("\nCANT.             PRODUCTO         PRECIO UD.       TOTAL\n"
					+ "=====             =========         =========        =====\n" + bebida.cantidad
					+ "                   " + bebida.getNombre() + "               " + bebida.getPrecio() + "€"
					+ "           " + bebida.cantidad * bebida.getPrecio() + "€" + "\nImporte Total = "+ importeTotal + "€");

		} else if (bebida == null) {
			
			Double impor = (comida.cantidad * comida.getPrecio());

			importeTotal = Math.round(impor * 100) / 100d;

			return ("\nCANT.             PRODUCTO         PRECIO UD.       TOTAL\n"
					+ "=====             =========         =========        =====\n" + comida.cantidad
					+ "                   " + comida.getNombre() + "               " + comida.getPrecio() + "€"
					+ "           " + comida.cantidad * comida.getPrecio() + "€" + "\nImporte Total = "+ importeTotal + "€");
		} else {
			
			Double impor = (bebida.cantidad * bebida.getPrecio()) + (comida.cantidad * comida.getPrecio());
			importeTotal = Math.round(impor * 100) / 100d;

			return("\nCANT.             PRODUCTO               PRECIO UD.       TOTAL\n"
					+ "=====             =========               =========        =====\n" + bebida.cantidad
					+ "                   " + bebida.getNombre() + "                    " + bebida.getPrecio() + "€"
					+ "           " + bebida.cantidad * bebida.getPrecio() + "€" + "\n" + comida.cantidad
					+ "                   " + comida.getNombre() + "                    " + comida.getPrecio() + "€"
					+ "           " + comida.cantidad * comida.getPrecio() + "€" + "\nImporte Total = "+ importeTotal+ "€"); 

		}
	}
}
