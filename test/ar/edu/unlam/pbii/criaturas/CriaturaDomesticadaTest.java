package ar.edu.unlam.pbii.criaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CriaturaDomesticadaTest {

	private CriaturaDomesticada criatura;
	
	@Before
	public void setUp() {
		criatura = new CriaturaDomesticada("Perro Fiel", 50, AfinidadElemental.TIERRA);
	}
	
	@Test
	public void testEntrenarAumentaEnergiaEstable() {
		int energiaInicial = criatura.getNivelEnergia();
		int nivelMaestria = 10;
		
		criatura.entrenar(nivelMaestria);
		
		int energiaEsperada = energiaInicial + (nivelMaestria * 2);
		assertEquals("La energía debe aumentar de forma estable (factor 2)", energiaEsperada, criatura.getNivelEnergia());
	}
	
	@Test
	public void testEntrenarNoSupera200() {
		criatura = new CriaturaDomesticada("Test", 195, AfinidadElemental.AGUA);
		int nivelMaestria = 10;
		
		criatura.entrenar(nivelMaestria);
		
		assertEquals("La energía no debe superar 200", 200, criatura.getNivelEnergia());
	}
	
	@Test
	public void testEntrenarConEnergia199() {
		criatura = new CriaturaDomesticada("Test", 199, AfinidadElemental.FUEGO);
		int nivelMaestria = 5;
		
		criatura.entrenar(nivelMaestria);
		
		assertEquals("La energía debe limitarse a 200", 200, criatura.getNivelEnergia());
	}
	
	@Test
	public void testEntrenarNuncaInestable() {
		for (int i = 0; i < 10; i++) {
			criatura.entrenar(10);
			assertFalse("Nunca debe volverse inestable", criatura.estaInestable());
		}
	}
	
	@Test
	public void testPacificarNoHaceNada() {
		boolean estadoInicial = criatura.estaInestable();
		
		criatura.pacificar();
		
		assertEquals("Pacificar no debe cambiar el estado", estadoInicial, criatura.estaInestable());
	}
	
	@Test
	public void testEntrenarConEnergia0() {
		criatura = new CriaturaDomesticada("Test", 0, AfinidadElemental.AIRE);
		int nivelMaestria = 5;
		
		criatura.entrenar(nivelMaestria);
		
		assertEquals("La energía debe aumentar desde 0", 10, criatura.getNivelEnergia());
	}
	
	@Test
	public void testEntrenarConEnergia200() {
		criatura = new CriaturaDomesticada("Test", 200, AfinidadElemental.TIERRA);
		int nivelMaestria = 10;
		
		criatura.entrenar(nivelMaestria);
		
		assertEquals("La energía debe mantenerse en 200", 200, criatura.getNivelEnergia());
	}
}

