package practica4;

import java.util.Date;
import java.util.Scanner;

public class PasarelaDePago {

	private double importe;
	private long codigoPago;

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public long getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(long codigoPago) {
		this.codigoPago = codigoPago;
	}

	/**
	 * @param importe
	 * @param codigoPago
	 */
	public PasarelaDePago(double importe) {
		this.importe = importe;

		int metodo;
		String numero;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca el metodo de pago: \n1. Efectivo \n2. Tarjeta \n3. Cuenta bancaria");

		metodo = sc.nextInt();
		String saltodelinea = sc.nextLine(); // Guardamos aquí el salto de carro para evitar errores
		
		if (metodo == 1 || metodo == 2 || metodo == 3) {
			if(metodo == 1) {
				codigoPago = Efectivo(importe);
			}
			if (metodo == 2) {
				System.out.println("Ha elegido pagar con tarjeta \nIntroduzca el numero de tarjeta");
				numero = sc.nextLine();
				codigoPago = Tarjeta(numero);
			}
			if (metodo == 3) {
				System.out.println("Ha elegido Cuenta bancaria \nIntroduzca su numero IBAN:");
				numero = sc.nextLine();
				codigoPago = Cuenta(numero);
			}

		} else {
			System.out.println("Metodo de pago incorrecto");
		}
	}

	public long Efectivo(double importe) { // REVISAR
		this.importe = importe;
		
		int B500 = 0, B200 = 0, B100 = 0, B50 = 0, B20 = 0, B10 = 0, B5 = 0, M2 = 0, M1 = 0, C50 = 0, C20 = 0, C10 = 0,
				C5 = 0;


		System.out.println("Ha elegido pago en efectivo");

		if (importe >= 500) {

			double b500 = importe / 500;
			B500 = (int) b500;
			importe = importe - (B500 * 500);
			System.out.println("Paga con " + B500 + " billetes de 500e");

		}

		if (importe >= 200) {

			double b200 = importe / 200;
			B200 = (int) b200;
			importe = importe - (B200 * 200);
			System.out.println("Paga con " + B200 + " billetes de 200e");

		}

		if (importe >= 100) {

			double b100 = importe / 100;
			B100 = (int) b100;
			importe = importe - (B100 * 100);
			System.out.println("Paga con " + B100 + " billetes de 100e");

		}

		if (importe >= 50) {

			double b50 = importe / 50;
			B50 = (int) b50;
			importe = importe - (B50 * 50);
			System.out.println("Paga con " + B50 + " billetes de 50e");

		}

		if (importe >= 20) {

			double b20 = importe / 20;
			B20 = (int) b20;
			importe = importe - (B20 * 20);
			System.out.println("Paga con " + B20 + " billetes de 20e");

		}

		if (importe >= 10) {

			double b10 = importe / 10;
			B10 = (int) b10;
			importe = importe - (B10 * 10);
			System.out.println("Paga con " + B10 + " billetes de 10e");

		}

		if (importe >= 5) {

			double b5 = importe / 5;
			B5 = (int) b5;
			importe = importe - (B5 * 5);
			System.out.println("Paga con " + B5 + " billetes de 5e");

		}

		if (importe >= 2) {

			double m2 = importe / 2;
			M2 = (int) m2;
			importe = importe - (M2 * 2);
			System.out.println("Paga con " + M2 + " monedas de 2e");

		}

		if (importe >= 1) {

			double m1 = importe;
			M1 = (int) m1;
			importe = importe - (M1);
			System.out.println("Paga con " + M1 + " monedas de 1e");

		}

		importe = importe * 100;

		if (importe >= 50) {

			double c50 = importe / 50;
			C50 = (int) c50;
			importe = importe - (C50 * 50);
			System.out.println("Paga con " + C50 + " monedas de 50 centimos");

		}

		if (importe >= 20) {

			double c20 = importe / 20;
			C20 = (int) c20;
			importe = importe - (C20 * 20);
			System.out.println("Paga con " + C20 + " monedas de 20 centimos");

		}

		if (importe >= 10) {

			double c10 = importe / 10;
			C10 = (int) c10;
			importe = importe - (C10 * 10);
			System.out.println("Paga con " + C10 + " monedas de 10 centimos");

		}

		importe = Math.round(importe);

		if (importe >= 5) {

			double c5 = importe / 5;
			C5 = (int) c5;
			System.out.println("Paga con " + C5 + " monedas de 5 centimos");

		}
		
		System.out.println("Deja de propina " + importe + " centimos");
		
		
		Date fecha = new Date();
		
		codigoPago = fecha.getTime();

		System.out.println("Codigo de pago: " + codigoPago);
		
		return codigoPago;
	}

	public long Tarjeta(String tarjeta) {

		boolean pagado = false;

		setCodigoPago(0); // Establecemos el codigo de pago a 0


		int longitud = tarjeta.length();

		if (longitud == 17 || longitud == 19) {

			int espacio1 = tarjeta.indexOf(' ');

			if (espacio1 == 4) { // Detectamos si los 4 primeros digitos se separan del resto por el primer
														// espacio

				for (int i = 0; i <= 3; i++) { // Este bucle obliga a introducir solo números. Si se introduce otro caracter,
																				// el programa no funciona.

					String a = tarjeta.substring(i, ++i);

					int A = Integer.parseInt(a);

				}

				String d1 = tarjeta.substring(0, 1);

				int D1 = Integer.parseInt(d1);

				if (D1 == 3 || D1 == 4 || D1 == 5) { // Leemos el primer digito para saber que tipo de tarjeta es, sino la
																							// tarjeta es desconocida.

					if ((D1 == 4 || D1 == 5) && longitud == 19) {

						System.out.println("Tarjeta Visa o Mastercard");

						int espacio2;

						String cadena2 = tarjeta.substring(5, 10);

						for (int i = 0; i <= 3; i++) { // Este bucle obliga a introducir solo números. Si se introduce otro
																						// caracter el programa no funciona.

							String a = cadena2.substring(i, ++i);

							int A = Integer.parseInt(a);

						}

						espacio2 = cadena2.indexOf(' ');

						if (espacio2 == 4) {

							int espacio3;

							String cadena3 = tarjeta.substring(10, 15);

							for (int i = 0; i <= 3; i++) { // Este bucle obliga a introducir solo números. Si se introduce otro
								// caracter el programa no funciona.

								String a = cadena3.substring(i, ++i);

								int A = Integer.parseInt(a);

							}

							espacio3 = cadena3.indexOf(' ');

							if (espacio3 == 4) {

								String cadena4 = tarjeta.substring(15, 19);

								for (int i = 0; i <= 3; i++) { // Este bucle obliga a introducir solo números. Si se introduce otro
									// caracter el programa no funciona.

									String a = cadena4.substring(i, ++i);

									int A = Integer.parseInt(a);

								}

								System.out.println("Tarjeta correcta");

								pagado = true;

							} else {
								System.out.println("No ha introducido un tercer espacio correcto");
							}

						} else {
							System.out.println("No ha introducido un segundo espacio correcto");
						}

					} else {

						if (longitud == 17 && D1 == 3) {

							System.out.println("Tarjeta American Express"); // 3123 123456 12345

							int espacio2;

							String cadena32 = tarjeta.substring(5, 12);

							for (int i = 0; i <= 5; i++) { // Este bucle obliga a introducir solo números. Si se introduce otro
								// caracter el programa no funciona.

								String a = cadena32.substring(i, ++i);

								int A = Integer.parseInt(a);

							}

							espacio2 = cadena32.indexOf(' ');

							if (espacio2 == 6) {

								String cadena3 = tarjeta.substring(12, 17);

								for (int i = 0; i <= 4; i++) { // Este bucle obliga a introducir solo números. Si se introduce otro
									// caracter el programa no funciona.

									String a = cadena3.substring(i, ++i);

									int A = Integer.parseInt(a);

								}

								System.out.println("Tarjeta correcta");

								pagado = true;

							} else {
								System.out.println("No ha introducido un segundo espacio correcto");
							}

						} else {
							System.out.println("Tarjeta incorrecta");
						}

					}

				} else {

					System.out.println("Tarjeta desconocida");
				}

			}

		} else {
			System.out.println("Longitud incorrecta");
		}

		if (pagado == true) { // Confirmamos que el pedido se ha pagado para generar el codigo de pago

			Date fecha = new Date();

			codigoPago = fecha.getTime();

			System.out.println(codigoPago);

		}

		return codigoPago; // Devolvemos el codigo de pago, si no esta pagado devolverá 0
		
	}

	public long Cuenta(String c) { // REVISAR

		boolean pagado = false;
		
		setCodigoPago(0); // Establecemos el codigo de pago a 0

		if (c.length() == 24) {
				
				pagado = true;

		}

		if (pagado == true) { // Confirmamos que el pedido se ha pagado para generar el codigo de pago

			Date fecha = new Date();

			codigoPago = fecha.getTime();

			System.out.println(codigoPago);

		}

		return codigoPago;
	}

}
