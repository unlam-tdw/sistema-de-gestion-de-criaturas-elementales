package ar.edu.unlam.pbii.excepciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnergiaExcedidaExceptionTest {

	@Test
	public void testCrearExcepcionConMensaje() {
		int energiaActual = 250;
		int energiaMaxima = 200;
		
		EnergiaExcedidaException excepcion = new EnergiaExcedidaException(energiaActual, energiaMaxima);	
		
		assertNotNull("La excepción no debe ser null", excepcion);
		assertTrue("El mensaje debe contener la energía actual", excepcion.getMessage().contains("250"));
		assertTrue("El mensaje debe contener la energía máxima", excepcion.getMessage().contains("200"));
	}
	
	@Test
	public void testGetters() {
		int energiaActual = 220;
		int energiaMaxima = 200;
		
		EnergiaExcedidaException excepcion = new EnergiaExcedidaException(energiaActual, energiaMaxima);
		
		assertEquals("Debe retornar la energía actual correcta", energiaActual, excepcion.getEnergiaActual());
		assertEquals("Debe retornar la energía máxima correcta", energiaMaxima, excepcion.getEnergiaMaxima());
	}
	
	@Test
	public void testEsUncheckedException() {
		EnergiaExcedidaException excepcion = new EnergiaExcedidaException(250, 200);
		
		assertTrue("Debe ser instancia de RuntimeException", excepcion instanceof RuntimeException);
		assertTrue("Debe ser instancia de Exception", excepcion instanceof Exception);
	}
}

