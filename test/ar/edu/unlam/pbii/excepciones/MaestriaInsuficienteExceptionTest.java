package ar.edu.unlam.pbii.excepciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaestriaInsuficienteExceptionTest {

	@Test
	public void testCrearExcepcionConMensaje() {
		int nivelMaestria = 5;
		int nivelMinimo = 10;
		
		MaestriaInsuficienteException excepcion = new MaestriaInsuficienteException(nivelMaestria, nivelMinimo);
		
		assertNotNull("La excepción no debe ser null", excepcion);
		assertTrue("El mensaje debe contener el nivel de maestría", excepcion.getMessage().contains("5"));
		assertTrue("El mensaje debe contener el nivel mínimo", excepcion.getMessage().contains("10"));
	}
	
	@Test
	public void testGetters() {
		int nivelMaestria = 8;
		int nivelMinimo = 10;
		
		MaestriaInsuficienteException excepcion = new MaestriaInsuficienteException(nivelMaestria, nivelMinimo);
		
		assertEquals("Debe retornar el nivel de maestría correcto", nivelMaestria, excepcion.getNivelMaestria());
		assertEquals("Debe retornar el nivel mínimo correcto", nivelMinimo, excepcion.getNivelMinimo());
	}
	
	@Test
	public void testEsCheckedException() {
		MaestriaInsuficienteException excepcion = new MaestriaInsuficienteException(5, 10);
		
		assertTrue("Debe ser instancia de Exception", excepcion instanceof Exception);
		// Verificar que es checked exception (extiende Exception, no RuntimeException)
		// Verificamos que la clase padre es Exception, no RuntimeException
		Class<?> clasePadre = excepcion.getClass().getSuperclass();
		assertEquals("Debe extender Exception directamente", Exception.class, clasePadre);
	}
}

