package clases;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase Pedido GPedidos v0.3 
 * 
 * @author EstebanBP
 * @version 0.3  
 * @since 16/03/2023
 * 
 * 
 * Funcionalidad lectura/escritura de archivos .txt para Clientes, Productos y Pedidos
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
	private String rutaPedidos = "C:/Users/EstebanBP/eclipse-workspace/GPedidos/src/Pedidos";
	
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
		
		if (producto1 == null) {
			
			Double impor = (cantidad2 * producto2.getPrecio());

			importeTotal = Math.round(impor * 100) / 100d;

			return "\nCANT.             PRODUCTO         PRECIO UD.       TOTAL\n"
					+ "=====             =========         =========        =====\n" + cantidad2
					+ "                   " + producto2.getNombre() + "               " + producto2.getPrecio() + "€"
					+ "           " + cantidad2 * producto2.getPrecio() + "€" + "\nImporte Total = "+ importeTotal + "€";

		} else if (producto2 == null) {
			
			Double impor = (cantidad1 * producto1.getPrecio());

			importeTotal = Math.round(impor * 100) / 100d;

			return "\nCANT.             PRODUCTO         PRECIO UD.       TOTAL\n"
					+ "=====             =========         =========        =====\n" + cantidad1
					+ "                   " + producto1.getNombre() + "               " + producto1.getPrecio() + "€"
					+ "           " + cantidad1 * producto1.getPrecio() + "€" + "\nImporte Total = "+ importeTotal + "€";

		} else {
			
			Double impor = (cantidad1 * producto1.getPrecio()) + (cantidad2 * producto2.getPrecio());

			importeTotal = Math.round(impor * 100) / 100d;

			return "\nCANT.             PRODUCTO               PRECIO UD.       TOTAL\n"
					+ "=====             =========               =========        =====\n" + cantidad1
					+ "                   " + producto1.getNombre() + "                    " + producto1.getPrecio() + "€"
					+ "           " + cantidad1 * producto1.getPrecio() + "€" + "\n" + cantidad2
					+ "                   " + producto2.getNombre() + "                    " + producto2.getPrecio() + "€"
					+ "           " + cantidad2 * producto2.getPrecio() + "€" + "\nImporte Total = "+ importeTotal+ "€";

		}

	}
	
	public void imprimirPedido(Pedido pedido) {
		
		String ruta = rutaPedidos+"/" +pedido.getPago()+ ".txt";
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			// Añadir flag a true para no machacar contenido del fichero de escritura
			fichero = new FileWriter(ruta, true);
			pw = new PrintWriter(fichero);
			pw.println(pedido.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para asegurarnos que se cierra el fichero.
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
