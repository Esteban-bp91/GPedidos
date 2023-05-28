package clases;

import herramientas.Imprimible;

/**
 * Clase Pedido del software GPedidos
 * @author Esteban Baeza Pérez
 * @version 0.4
 * @since 27/05/2023
 * 
 */

public class Pedido implements Imprimible{
	
	// Parámetros de la clase Pedido
	private Cliente cliente; //Indica el cliente que realiza el pedido
	private Bebida bebida; // Indica la bebida que tendrá el pedido
	private Comida comida; // Indica la comuda que tendrá el pedido
	private double importeTotal; //Indica el importe total del pedido
	private long codigoPago; // Indica el codigo de pago si el pedido a sido pagado
	private String estado; // Indica el estado del pedido
	
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

	/**  Constructor vacío de Pedido 
	 * @param cliente especifica el cliente que realiza el pedido
	 * @param bebida especifica la bebida que incluye el pedido
	 * @param comida especifica la comuda que incluye el pedido
	 * @param importeTotal especifica el importe total del pedido
	 * @param pago especifica el codigo de pago del pedido
	 * @param estado especifica el estado del pedido
	 */
	public Pedido() {
		this.cliente = null;
		this.bebida = null;
		this.comida = null;
		this.importeTotal = 0;
		this.codigoPago = 0;
		this.estado = "NO PAGADO";
	} //Cierre del constructor vacío Pedido

	/**  Constructor de Pedido con una bebida
	 * @param cliente especifica el cliente que realiza el pedido
	 * @param bebida especifica la bebida que incluye el pedido
	 * @param importeTotal especifica el importe total del pedido
	 * @param pago especifica el codigo de pago del pedido
	 * @param estado especifica el estado del pedido
	 */
	public Pedido(Cliente cliente, Bebida bebida, double importeTotal, long pago, String estado) {
		this.cliente = cliente;
		this.bebida = bebida;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
	} //Cierre del constructor Pedido con una bebida

	/**  Constructor de Pedido con una bebida y una comida
	 * @param cliente especifica el cliente que realiza el pedido
	 * @param bebida especifica la bebida que incluye el pedido
	 * @param comida especifica la comuda que incluye el pedido
	 * @param importeTotal especifica el importe total del pedido
	 * @param pago especifica el codigo de pago del pedido
	 * @param estado especifica el estado del pedido
	 */
	public Pedido(Cliente cliente, Bebida bebida, Comida comida, double importeTotal, long pago, String estado) {
		this.cliente = cliente;
		this.bebida = bebida;
		this.comida = comida;
		this.importeTotal = importeTotal;
		this.codigoPago = pago;
		this.estado = estado;
	} //Cierre del constructor Pedido con una bebida y una comida
	
	/**
	 *  Método agregarBebida(Bebida) para añadir la bebida pasada como parámetro al pedido
	 * @param bebida
	 */
	public void agregarBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	/**
	 *  Método agregarComida(Comida) para añadir la comida pasada como parámetro al pedido
	 * @param comida
	 */
	public void agregarComida(Comida comida) {
		this.comida = comida;
	}
	
	/**
	 *  Método eliminarBebida() para eliminar la bebida del pedido
	 */
	public void eliminarBebida() {
		this.bebida = null;
	}
	
	/**
	 *  Método eliminarComida() para eliminar la comida del pedido
	 */
	public void eliminarComida() {
		this.comida = null;
	}
	
	/**
	 *  Método imprimir() que crea un string con los datos del pedido
	 *  @return el string con los datos del pedido
	 */
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
	} //Cierre del Método imprimir()
} //Cierre de la clase Pedido
