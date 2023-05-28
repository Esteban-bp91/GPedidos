package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import menus.CartaBebidas;

/**
 * Subclase Bebida del software GPedidos
 * Superclase Producto
 * @author Esteban Baeza Pérez
 * @version 0.4
 * @since 27/05/2023
 * @see Clase Producto
 */

public class Bebida extends Producto {
	// Parámetros de la subclase Bebida
	private boolean gaseoso; //Indica si es un producto con gas o sin gas
	private boolean lacteo;  //Indica si el producto tiene algún tipo de lácteo
	private String medida;   //Medida en cc de la bebida
	
	//Getters and setters
	public boolean isGaseoso() {
		return gaseoso;
	}
	public void setGaseoso(boolean gaseoso) {
		this.gaseoso = gaseoso;
	}
	public boolean isLacteo() {
		return lacteo;
	}
	public void setLacteo(boolean lacteo) {
		this.lacteo = lacteo;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public int[] getStock() {
		return stock;
	}
	public void setStock(int[] stock) {
		this.stock = stock;
	}
	
	/**
	 * Constructor de la subclase Bebida
	 * @param int codigo identifica la instancia de la bebida
	 * @param String nombre identifica el nombre de la bebida
	 * @param double precio especifica el precio de la bebida
	 * @param LocalDate fecha_caducidad especifica la fecha de la bebida
	 * @param String estado especifica el estado de la bebida
	 * @param int cantidad especifica la cantidad de la bebida que tendrá el pedido
	 * @param boolean gaseoso especifica si la bebida es gaseosa
	 * @param boolean lacteo especifica si la bebida contiene leche
	 * @param String medida especifica el volumen de la bebida
	 */
	public Bebida(int codigo, String nombre, double precio, LocalDate fecha_caducidad, String estado, int cantidad, boolean gaseoso,
			boolean lacteo, String medida) {
		super(codigo, nombre, precio, fecha_caducidad, estado, cantidad);
		this.gaseoso = gaseoso;
		this.lacteo = lacteo;
		this.medida = medida;
	} //Cierre del constructor Bebida
	
	/**
	 * Método obtenerCaducidad() imprime por pantalla la fecha de caducidad
	 */
	@Override
	public void obtenerCaducidad() {
		System.out.println(calcularCaducidad());
	} //Cierre del Método obtenerCaducidad()
	
	/**
	 * Método calcularCaducidad() establece la fecha de caducidad de la bebida según contenga leche o no 
	 */
	@Override
	public LocalDate calcularCaducidad() {
		if(lacteo == true) {
			setFecha_caducidad(LocalDate.now().plusDays(10));
		} else {
			setFecha_caducidad(LocalDate.now().plusDays(20));
		}
		return fecha_caducidad;
	} //Cierre del Método calcularCaducidad()
	
	/**
	 * Método detalleProducto() imprime por pantalla los parámetros de la instancia 
	 */
	@Override
	public void detalleProducto() {
		System.out.println("Nombre: "+nombre+"\nPrecio: "+precio+"\nFecha de caducidad: "+fecha_caducidad+"\nEstado: "+estado+"\nGaseoso: "+gaseoso+"\nLacteo: "+lacteo+"\nMedida: "+medida);
	} //Cierre del Método detalleProducto()
	
	/**
	 * Método rellenarBebida(Bebida, ArrayList<Bebida>) rellena los parámetros de una instancia de bebida
	 */
	public void rellenarBebida(Bebida bebida, ArrayList<Bebida> bebidas) {
		Scanner s = new Scanner(System.in);
		try {
			int codigo = (int)(Math.random()*1000+1); //Obtenemos un codigo random de 4 digitos
			for(Bebida b: bebidas) { //Nos aseguramos que el codigo no este repetido con otra bebida
				if(b.getCodigo() == codigo) {
					codigo = (int)(Math.random()*1000+1);
				}
			}
			bebida.setCodigo(codigo); // Establecemos el codigo random a la bebida
			try {
				System.out.println("Nombre de la bebida:");
				bebida.setNombre(s.next());
				System.out.println("Precio de la bebida:");
				bebida.setPrecio(s.nextDouble());
				System.out.println("Estado de la bebida:");
				bebida.setEstado(s.next());
				System.out.println("Esta bebida es gaseosa? Y/N");
				String r = s.next();
					if (r.equalsIgnoreCase("y")) {
						bebida.setGaseoso(true);
					} else if (r.equalsIgnoreCase("n")) {
						bebida.setGaseoso(false);
					} else {
						System.out.println("Respuesta incorrecta");
					}
				System.out.println("Esta bebida es lactea? Y/N");
				r = s.next();
					if (r.equalsIgnoreCase("y")) {
						bebida.setGaseoso(true);
					} else if (r.equalsIgnoreCase("n")) {
						bebida.setGaseoso(false);
					} else {
						System.out.println("Respuesta incorrecta");
					}
				System.out.println("Volumen de la bebida:");
				bebida.setMedida(s.next());
				bebida.setFecha_caducidad(bebida.calcularCaducidad());
				bebida.llenarStock();
			} catch (InputMismatchException e) {
				System.out.println("La bebida no ha podido ser creada");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //Cierre del Método rellenarBebida(Bebida, ArrayList<Bebida>)
	
	/**
	 * Método elegirBebida(ArrayList<Bebida>) para elegir una bebida del ArrayList
	 * @return la bebida elegida con la cantidad establecida para el pedido
	 */
	public Bebida elegirBebida(ArrayList<Bebida> bebidas) {
		Bebida bebida = null;
		Scanner sc = new Scanner(System.in);

		try {
			CartaBebidas cartaBebidas = new CartaBebidas();
			cartaBebidas.mostrar(bebidas);
			System.out.println("\nElija su bebida:");
			int prod = sc.nextInt();			
			if(prod >= 0 && prod < bebidas.size()) {		
				bebida = bebidas.get(prod);		
			}else {
				System.out.println("Bebida incorrecta");
			}
			
			//Si el stock de la bebida elegida es menor a 5, se repone el stock al completo
			System.out.println("Ha elegido " + bebida.getNombre() + "   Precio: " + bebida.getPrecio() + "      Stock: " + bebida.mostrarStock());
			if (bebida.mostrarStock() <= 5) {
				System.out.println("Stock de " + bebida.getNombre() + " bajo. \n Reponemos el stock al completo");
				bebida.llenarStock();	
				System.out.println("Stock de " + bebida.getNombre() + ": " + bebida.mostrarStock());	
			}
		} catch (NumberFormatException prod) {
			System.out.println("No has introducido un numero correcto");
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en tu eleccion");
		}
		
		// Pedimos al usuario la cantidad de la bebida elegida que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible de producto 1
		try {
			System.out.println("Indique la cantidad que quiere de su bebida: "+bebida.getNombre());				
			bebida.cantidad = sc.nextInt();
			
			if(bebida.cantidad > bebida.mostrarStock()) {						
				System.out.println("No puede comprar más de "+bebida.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + bebida.mostrarStock());
				bebida.cantidad = bebida.mostrarStock();						
			}
		} catch (NumberFormatException cant) {
			System.out.println("Cantidad elegida incorrecta");
		}
		
		return bebida;
	} //Cierre del Método elegirBebida(ArrayList<Bebida>)
} //Cierre de la subclase Bebida
