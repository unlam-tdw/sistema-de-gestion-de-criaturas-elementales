package ar.edu.unlam.pbii.interacciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;

public class SistemaInteraccionesTest {

	private Criatura criatura1;
	private Criatura criatura2;
	
	@Before
	public void setUp() {
		criatura1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.AGUA);
	}
	
	@Test
	public void testInteractuarDelegaACriatura() {
		SistemaInteracciones.interactuar(criatura1, criatura2);
		
		assertNotNull("No debe lanzar excepción", criatura1);
		assertNotNull("No debe lanzar excepción", criatura2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInteractuarConCriaturasNullLanzaExcepcion() {
		SistemaInteracciones.interactuar(null, criatura2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInteractuarConCriatura2NullLanzaExcepcion() {
		SistemaInteracciones.interactuar(criatura1, null);
	}
}

