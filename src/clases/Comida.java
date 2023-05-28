package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import menus.CartaComidas;


/**
 * Clase Comida del software GPedidos
 * @author Esteban Baeza Pérez
 * @version 0.4
 * @since 27/05/2023
 * @see Clase Producto
 */

public class Comida extends Producto{
	// Parámetros de la subclase Comida
	private boolean perecedero; //Indica si el alimento debe consumirse con inmediatez
	private float calorias; //Indica las calorías que tiene el alimento
	private float vegano; //True o 0, si el alimento se considera vegano
	private LocalDate fecha_envase; //Fecha de envasado del alimento
	
	//Getters and setters
	public boolean isPerecedero() {
		return perecedero;
	}
	public void setPerecedero(boolean perecedero) {
		this.perecedero = perecedero;
	}
	public float getCalorias() {
		return calorias;
	}
	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}
	public float getVegano() {
		return vegano;
	}
	public void setVegano(float vegano) {
		this.vegano = vegano;
	}
	public LocalDate getFecha_envase() {
		return fecha_envase;
	}
	public void setFecha_envase(LocalDate fecha_envase) {
		this.fecha_envase = fecha_envase;
	}
	public int[] getStock() {
		return stock;
	}
	public void setStock(int[] stock) {
		this.stock = stock;
	}
	
	/**
	 * Constructor de la subclase Comida
	 * @param codigo identifica la instancia de la comida
	 * @param nombre especifica el nombre de la comida
	 * @param precio especifica el precio de la comida
	 * @param fecha_caducidad especifica la fecha de caducidad de la comida
	 * @param estado especifica el estado de la comida
	 * @param cantidad especifica la cantidad de la comida que tendrá el pedido
	 * @param perecedero especifica si la comida es perecedera o no
	 * @param calorias especifica las calorias de la comida
	 * @param vegano especifica si la comida es vegana o no
	 * @param fecha_envase especifica la fecha de envase de la comida
	 */
	public Comida(int codigo, String nombre, double precio, LocalDate fecha_caducidad, String estado,int cantidad, boolean perecedero, float calorias,
			float vegano, LocalDate fecha_envase) {
		super(codigo, nombre, precio, fecha_caducidad, estado, cantidad);
		this.perecedero = perecedero;
		this.calorias = calorias;
		this.vegano = vegano;
		this.fecha_envase = fecha_envase;
	} //Cierre del constructor Comida
	
	/**
	 * Método obtenerCaducidad() imprime por pantalla la fecha de caducidad
	 */
	public void obtenerCaducidad() {
		System.out.println(calcularCaducidad());
	} //Cierre del método obtenerCaducidad()

	/**
	 * Método calcularCaducidad() establece la fecha de caducidad de la comida según sea perecedera o no
	 * @return la fecha de caducidad de la comida
	 */
	@Override
	public LocalDate calcularCaducidad() {
		if(perecedero == true) {
			fecha_caducidad = fecha_envase.plusDays(10);
		} else {
			fecha_caducidad = LocalDate.now().plusDays(3);
		}
	return fecha_caducidad;
	} // Cierre del método calcularCaducidad()
	
	/**
	 * Método detalleProducto() imprime por pantalla los parámetros de la instancia 
	 */
	@Override
	public void detalleProducto() {
		System.out.println("Nombre: "+nombre+"\nPrecio: "+precio+"\nFecha de caducidad: "+fecha_caducidad+"\nEstado: "+estado+"\nPerecedero: "+perecedero+"\nCalorias: "+calorias+"\nVegano: "+vegano+"\nFecha de envase: "+fecha_envase);
	} //Cierre del método detalleProducto()
	
	/**
	 * Método elegirComiuda(ArrayList<Comida>) para elegir una comida del ArrayList
	 * @return la comida elegida con la cantidad establecida para el pedido
	 */
	public Comida elegirComida(ArrayList<Comida> comidas) {  // Método para elegir una comida
		Comida comida = null;
		Scanner sc = new Scanner(System.in);
		try {
			CartaComidas cartaComidas = new CartaComidas();
			cartaComidas.mostrar(comidas);
			System.out.println("\nElija su comida:");
			int prod = sc.nextInt();
			
			if(prod >= 0 && prod < comidas.size()) {		
				comida = comidas.get(prod);		
			}else {
				System.out.println("Comida incorrecta");
			}
			
			//Si el stock de la comida elegida es menor a 5, se repone el stock al completo
			
			System.out.println("Ha elegido " + comida.getNombre() + "   Precio: " + comida.getPrecio() + "      Stock: " + comida.mostrarStock());
			if (comida.mostrarStock() <= 5) {
				System.out.println("Stock de " + comida.getNombre() + " bajo. \n Reponemos el stock al completo");
				comida.llenarStock();	
				System.out.println("Stock de " + comida.getNombre() + ": " + comida.mostrarStock());	
			}
		} catch (NumberFormatException prod) {
			System.out.println("No has introducido un numero correcto");
			prod.printStackTrace();
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en tu eleccion");
			e.printStackTrace();
		}
		
		// Pedimos al usuario la cantidad de la comida elegida que tendrá el pedido. Si es mayor al stock disponible, se servirá la cantidad disponible 
		try {
			System.out.println("Indique la cantidad que quiere de su comida: "+comida.getNombre());				
			comida.cantidad = sc.nextInt();
			
			if(comida.cantidad > comida.mostrarStock()) {						
				System.out.println("No puede comprar más de "+comida.mostrarStock()+" unidades. Le serviremos el stock disponible, siendo " + comida.mostrarStock());
				comida.cantidad = comida.mostrarStock();						
			}
		} catch (NumberFormatException cant) {
			System.out.println("Cantidad elegida incorrecta");
			cant.printStackTrace();
		}
		return comida;
	} // Cierre del método elegirComiuda(ArrayList<Comida>)
	
	/**
	 * Método rellenarComida(Comida, ArrayList<Comida>) rellena los parámetros de una instancia de comida
	 */
	public void rellenarComida(Comida comida, ArrayList<Comida> comidas) {
		Scanner s = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			int codigo = (int)(Math.random()*1000+1); //Obtenemos un codigo random de 4 digitos
			for(Comida c: comidas) { //Nos aseguramos que el codigo no este repetido con otra comida
				if(c.getCodigo() == codigo) {
					codigo = (int)(Math.random()*1000+1);
				}
			}
			comida.setCodigo(codigo); // Establecemos el codigo random a la comida
			try {
				System.out.println("Nombre de la comida:");
				comida.setNombre(s.next());
				System.out.println("Precio de la comida:");
				comida.setPrecio(s.nextDouble());
				System.out.println("Estado de la comida:");
				comida.setEstado(s.next());
				String sl = s.next();
				System.out.println("Esta bebida es perecedera? Y/N");
				String r = s.next();
					if (r.equalsIgnoreCase("y")) {
						comida.setPerecedero(true);
					} else if (r.equalsIgnoreCase("n")) {
						comida.setPerecedero(false);
					} else {
						System.out.println("Respuesta incorrecta");
					}
				System.out.println("Calorias de la comida:");
				comida.setCalorias(s.nextFloat());
				System.out.println("Esta comida es vegana? Y/N");
				r = s.next();
				if (r.equalsIgnoreCase("y")) {
					comida.setVegano(0);
				} else if (r.equalsIgnoreCase("n")) {
					comida.setVegano(1);
				} else {
					System.out.println("Respuesta incorrecta");
				}
				System.out.println("Fecha de envase de la comida: dd/mm/aaaa");
				comida.setFecha_envase(LocalDate.parse(s.next(), formatter));
				comida.setFecha_caducidad(comida.calcularCaducidad());
				comida.llenarStock();
				System.out.println("Comida "+comida.getNombre()+" creada correctamente con codigo "+comida.getCodigo());
			} catch (InputMismatchException e) {
				System.out.println("La comida no ha podido ser creada");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // Cierre del método rellenarComida(Comida, ArrayList<Comida>)
} //Cierre de la subclase Comida
