package ar.edu.unlam.pbii.criaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CriaturaAncestralTest {

	private CriaturaAncestral criatura;
	private static final int ENERGIA_MINIMA = 100;

	@Before
	public void setUp() {
		criatura = new CriaturaAncestral("Dragon Ancestral", 150, AfinidadElemental.FUEGO);
	}

	@Test
	public void testEntrenarAumentaEnergia() {
		int energiaInicial = criatura.getNivelEnergia();
		int nivelMaestria = 10;

		criatura.entrenar(nivelMaestria);

		int energiaEsperada = energiaInicial + (nivelMaestria * 3);
		assertEquals("La energía debe aumentar (factor 3)", energiaEsperada, criatura.getNivelEnergia());
	}

	@Test
	public void testCriaturaCreadaTieneEnergiaMinima100() {
		criatura = new CriaturaAncestral("Test", 50, AfinidadElemental.AGUA);

		assertTrue("La energía debe ser minimo 100", criatura.getNivelEnergia() >= ENERGIA_MINIMA);
	}

	@Test
	public void testEntrenarNuncaBajaDe100() {
		criatura = new CriaturaAncestral("Test", ENERGIA_MINIMA, AfinidadElemental.AGUA);

		criatura.entrenar(0);

		assertTrue("La energía nunca debe bajar de 100", criatura.getNivelEnergia() >= ENERGIA_MINIMA);
	}

	@Test
	public void testEntrenarConEnergia100() {
		criatura = new CriaturaAncestral("Test", ENERGIA_MINIMA, AfinidadElemental.AIRE);
		int nivelMaestria = 5;

		criatura.entrenar(nivelMaestria);

		assertEquals("La energía debe aumentar desde 100", 115, criatura.getNivelEnergia());
	}

	@Test
	public void testEntrenarExtremoConMaestria41() {
		criatura = new CriaturaAncestral("Test", 150, AfinidadElemental.TIERRA);
		int nivelMaestria = 41;

		criatura.entrenar(nivelMaestria);

		assertTrue("Debe volverse inestable con maestría > 40", criatura.estaInestable());
	}

	@Test
	public void testEntrenarExtremoConMaestria40SiComienzaEn100() {
		criatura = new CriaturaAncestral("Test", 100, AfinidadElemental.FUEGO);
		int nivelMaestria = 40;

		criatura.entrenar(nivelMaestria);

		assertFalse("NO debe volverse inestable con maestría = 40", criatura.estaInestable());
	}

	@Test
	public void testPacificarEstableceInestableFalse() {
		criatura.entrenar(41);
		assertTrue("Debe estar inestable", criatura.estaInestable());

		criatura.pacificar();

		assertFalse("Pacificar debe establecer estaInestable en false", criatura.estaInestable());
	}

	@Test
	public void testPacificarMantieneEnergiaMinima100() {
		criatura = new CriaturaAncestral("Test", ENERGIA_MINIMA, AfinidadElemental.FUEGO);

		criatura.pacificar();

		assertTrue("Pacificar debe mantener energía mínima de 100", criatura.getNivelEnergia() >= ENERGIA_MINIMA);
	}

	@Test
	public void testConstructorConEnergiaMenorA100() {
		criatura = new CriaturaAncestral("Test", 50, AfinidadElemental.AIRE);

		assertEquals("El constructor debe corregir energía a mínimo 100", ENERGIA_MINIMA, criatura.getNivelEnergia());
	}

	@Test
	public void testEsAncestral() {
		assertTrue("esAncestral debe retornar true", criatura.esAncestral());
	}
	
	@Test
	public void testEnergiaNuncaBajaDe100EnInteracciones() {
		// Verificar que una ancestral con energía mínima mantiene 100 en interacciones
		criatura = new CriaturaAncestral("Ancestral", ENERGIA_MINIMA, AfinidadElemental.FUEGO);
		Criatura otraAncestral = new CriaturaAncestral("Ancestral2", 150, AfinidadElemental.AGUA);
		
		int energiaInicial = criatura.getNivelEnergia();
		assertEquals("Debe comenzar con energía mínima 100", ENERGIA_MINIMA, energiaInicial);
		
		criatura.interactuar(otraAncestral);
		
		// Después de la interacción, debe tener al menos 100
		assertTrue("La energía nunca debe bajar de 100", criatura.getNivelEnergia() >= ENERGIA_MINIMA);
		// Debe haber ganado 20, así que debe tener 120
		assertEquals("Debe tener 120 después de ganar 20", ENERGIA_MINIMA + 20, criatura.getNivelEnergia());
	}
}
