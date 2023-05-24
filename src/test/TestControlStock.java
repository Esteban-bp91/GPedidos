package test;

import org.junit.jupiter.api.Test;
import clases.Comida;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class TestControlStock {

	    private Comida comida;

	    @BeforeEach
	    public void testInicio() {
	    	comida = new Comida(0,"comidaTest",2,null,null,5,false,0,0,null);
	    	comida.llenarStock();
	    }

	    @Test
	    public void testMostrarStock() {
	        int esperado = 30;
	        int resultado = comida.mostrarStock();
	        Assertions.assertEquals(esperado, resultado);
	    }

	    @Test
	    public void testLlenarStock() {
	        int esperado = 30;
	        comida.llenarStock();
	        int resultado = comida.mostrarStock();
	        Assertions.assertEquals(esperado, resultado);
	    }

	    @Test
	    public void testActualizarStock() {
	        int esperado = 27; // Suponiendo que inicialmente hay 30 unidades en stock
	        comida.actualizarStock(3); // Suponiendo que se compran 3 unidades
	        int resultado = comida.mostrarStock();
	        Assertions.assertEquals(esperado, resultado);
	    }
}
