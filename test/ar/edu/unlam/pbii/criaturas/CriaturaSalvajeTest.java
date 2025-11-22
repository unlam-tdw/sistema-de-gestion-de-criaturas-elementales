package ar.edu.unlam.pbii.criaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.excepciones.EnergiaExcedidaException;

public class CriaturaSalvajeTest {

	private CriaturaSalvaje criatura;

	@Before
	public void setUp() {
		criatura = new CriaturaSalvaje("Dragon Salvaje", 50, AfinidadElemental.FUEGO);
	}

	@Test
	public void testEntrenarAumentaEnergia() throws EnergiaExcedidaException {
		int energiaInicial = criatura.getNivelEnergia();
		int nivelMaestria = 5;

		criatura.entrenar(nivelMaestria);

		assertTrue("La energía debe aumentar después de entrenar", criatura.getNivelEnergia() >= energiaInicial);
	}

	@Test
	public void testEntrenarAumentoAleatorio() throws EnergiaExcedidaException {
		int nivelMaestria = 5;
		boolean vario = false;
		int energiaAnterior = 0;

		for (int i = 0; i < 10; i++) {
			criatura = new CriaturaSalvaje("Test", 50, AfinidadElemental.AGUA);

			try {
				criatura.entrenar(nivelMaestria);
			} catch (EnergiaExcedidaException e) {
				fail("No debe lanzar EnergiaExcedidaException");
			}

			if (i > 0 && criatura.getNivelEnergia() != energiaAnterior) {
				vario = true;
				break;
			}
			energiaAnterior = criatura.getNivelEnergia();
		}

		assertTrue("El aumento debe ser aleatorio", vario);
	}

	@Test
	public void testEntrenarPuedeSuperar200() {
		criatura = new CriaturaSalvaje("Test", 190, AfinidadElemental.FUEGO);
		int nivelMaestria = 20;

		try {
			criatura.entrenar(nivelMaestria);
			if (criatura.getNivelEnergia() > 200) {
				fail("Debe lanzar EnergiaExcedidaException si supera 200");
			}
		} catch (EnergiaExcedidaException e) {
			assertEquals("Debe lanzar excepción con energía máxima 200", 200, e.getEnergiaMaxima());
		}
	}

	@Test(expected = EnergiaExcedidaException.class)
	public void testEntrenarLanzaExcepcionSiSupera200() throws EnergiaExcedidaException {
		criatura = new CriaturaSalvaje("Test", 199, AfinidadElemental.FUEGO);
		int nivelMaestria = 50;

		criatura.entrenar(nivelMaestria);
	}

	@Test
	public void testEntrenarPuedeVolverseInestable() throws EnergiaExcedidaException {
		boolean seVolvioInestable = false;

		for (int i = 0; i < 20; i++) {
			criatura = new CriaturaSalvaje("Test", 50, AfinidadElemental.AGUA);
			try {
				criatura.entrenar(5);
			} catch (EnergiaExcedidaException eee) {

			} finally {
				if (criatura.estaInestable()) {
					seVolvioInestable = true;
					break;
				}
			}

		}

		assertTrue("Debe poder volverse inestable durante entrenamiento", seVolvioInestable);
	}

	@Test
	public void testPacificarEstableceInestableFalse() throws EnergiaExcedidaException {
		criatura = new CriaturaSalvaje("Test", 50, AfinidadElemental.FUEGO);

		for (int i = 0; i < 50; i++) {
			try {
				criatura.entrenar(5);
			} catch (EnergiaExcedidaException eee) {

			} finally {

				if (criatura.estaInestable()) {
					break;
				}
			}
		}

		if (criatura.estaInestable()) {
			criatura.pacificar();
			assertFalse("Pacificar debe establecer estaInestable en false", criatura.estaInestable());
		}
	}

	@Test
	public void testEntrenarConEnergia199() {
		criatura = new CriaturaSalvaje("Test", 199, AfinidadElemental.AIRE);
		int nivelMaestria = 10;

		try {
			criatura.entrenar(nivelMaestria);
			fail("Debe lanzar excepción si supera 200");
		} catch (EnergiaExcedidaException e) {
			assertEquals("La energía debe ser mayor a 200", 200, e.getEnergiaMaxima());
		}
	}
}
