package ar.edu.unlam.pbii.criaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.transformaciones.AscensoDelViento;
import ar.edu.unlam.pbii.transformaciones.BendicionDelRio;
import ar.edu.unlam.pbii.transformaciones.LlamaInterna;

public class CriaturaInteraccionesTest {

	private Criatura criatura1;
	private Criatura criatura2;
	
	@Before
	public void setUp() {
		criatura1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.AGUA);
	}
	
	@Test
	public void testInteractuarMismaAfinidadAmbasGanan10() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.FUEGO);
		
		int energiaInicial1 = criatura1.getNivelEnergia();
		int energiaInicial2 = criatura2.getNivelEnergia();
		
		criatura1.interactuar(criatura2);
		
		assertEquals("Ambas deben ganar 10 energía", energiaInicial1 + 10, criatura1.getNivelEnergia());
		assertEquals("Ambas deben ganar 10 energía", energiaInicial2 + 10, criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarMismaAfinidadNoSupera200() {
		criatura1 = new CriaturaDomesticada("Test1", 195, AfinidadElemental.AGUA);
		criatura2 = new CriaturaDomesticada("Test2", 195, AfinidadElemental.AGUA);
		
		criatura1.interactuar(criatura2);
		
		assertEquals("No debe superar 200", 200, criatura1.getNivelEnergia());
		assertEquals("No debe superar 200", 200, criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarAguaFuegoAmbasInestables() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.AGUA);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.FUEGO);
		
		criatura1.interactuar(criatura2);
		
		assertTrue("Ambas deben volverse inestables", criatura1.estaInestable());
		assertTrue("Ambas deben volverse inestables", criatura2.estaInestable());
	}
	
	@Test
	public void testInteractuarFuegoAguaAmbasInestables() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.AGUA);
		
		criatura1.interactuar(criatura2);
		
		assertTrue("Ambas deben volverse inestables", criatura1.estaInestable());
		assertTrue("Ambas deben volverse inestables", criatura2.estaInestable());
	}
	
	@Test
	public void testInteractuarAireTierraAmbasInestables() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.AIRE);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.TIERRA);
		
		criatura1.interactuar(criatura2);
		
		assertTrue("Ambas deben volverse inestables", criatura1.estaInestable());
		assertTrue("Ambas deben volverse inestables", criatura2.estaInestable());
	}
	
	@Test
	public void testInteractuarTierraAireAmbasInestables() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.TIERRA);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.AIRE);
		
		criatura1.interactuar(criatura2);
		
		assertTrue("Ambas deben volverse inestables", criatura1.estaInestable());
		assertTrue("Ambas deben volverse inestables", criatura2.estaInestable());
	}
	
	@Test
	public void testInteractuarAncestralGana20OtraPierde15() {
		criatura1 = new CriaturaAncestral("Ancestral", 150, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Normal", 50, AfinidadElemental.AGUA);
		
		int energiaInicial1 = criatura1.getNivelEnergia();
		int energiaInicial2 = criatura2.getNivelEnergia();
		
		criatura1.interactuar(criatura2);
		
		assertEquals("Ancestral debe ganar 20", energiaInicial1 + 20, criatura1.getNivelEnergia());
		assertEquals("Otra debe perder 15 (mínimo 0)", Math.max(0, energiaInicial2 - 15), criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarAncestralOtraNoPuedeQuedarNegativa() {
		criatura1 = new CriaturaAncestral("Ancestral", 150, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Normal", 10, AfinidadElemental.AGUA);
		
		criatura1.interactuar(criatura2);
		
		assertEquals("No debe quedar con energía negativa", 0, criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarAmbasAncestralesAmbasGanan20() {
		criatura1 = new CriaturaAncestral("Ancestral1", 150, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaAncestral("Ancestral2", 120, AfinidadElemental.AGUA);
		
		int energiaInicial1 = criatura1.getNivelEnergia();
		int energiaInicial2 = criatura2.getNivelEnergia();
		
		criatura1.interactuar(criatura2);
		
		assertEquals("Ambas deben ganar 20", energiaInicial1 + 20, criatura1.getNivelEnergia());
		assertEquals("Ambas deben ganar 20", energiaInicial2 + 20, criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarConTransformacionBendicionDelRio() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.FUEGO);
		
		BendicionDelRio transformada = new BendicionDelRio(criatura1);
		int energiaInicial1 = transformada.getEnergiaTotal();
		int energiaInicial2 = criatura2.getNivelEnergia();
		
		// La interacción debe funcionar con la criatura transformada
		// Nota: La interacción modifica la energía base, no la transformada
		transformada.getCriaturaBase().interactuar(criatura2);
		
		// Verificar que la interacción afectó a ambas criaturas
		assertTrue("La energía debe haber cambiado", transformada.getCriaturaBase().getNivelEnergia() != energiaInicial1 / 2);
		assertTrue("La energía debe haber cambiado", criatura2.getNivelEnergia() != energiaInicial2);
	}

	
	@Test
	public void testInteractuarConTransformacionLlamaInternaFuego() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.FUEGO);
		
		LlamaInterna transformada = new LlamaInterna(criatura1);
		int energiaInicial1 = transformada.getCriaturaBase().getNivelEnergia();
		int energiaInicial2 = criatura2.getNivelEnergia();
		
		// La interacción debe funcionar normalmente con la transformación
		transformada.interactuar(criatura2);
		
		// Verificar que ambas ganaron energía (misma afinidad = ambas +10)
		assertEquals("Ambas deben ganar 10 energía", energiaInicial1 + 10, transformada.getCriaturaBase().getNivelEnergia());
		assertEquals("Ambas deben ganar 10 energía", energiaInicial2 + 10, criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarAmbasConTransformaciones() {
		criatura1 = new CriaturaDomesticada("Test1", 50, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaDomesticada("Test2", 60, AfinidadElemental.FUEGO);
		
		BendicionDelRio transformada1 = new BendicionDelRio(criatura1);
		LlamaInterna transformada2 = new LlamaInterna(criatura2);
		
		int energiaInicial1 = transformada1.getCriaturaBase().getNivelEnergia();
		
		// La interacción debe funcionar con ambas transformadas
		transformada1.interactuar(transformada2);
		
		// Verificar que ambas ganaron energía (misma afinidad = ambas +10)
		assertEquals("Ambas deben ganar 10 energía", energiaInicial1 + 10, transformada1.getCriaturaBase().getNivelEnergia());
	}
	
	@Test
	public void testInteractuarAncestralMantieneEnergiaMinima100() {
		// Caso: Ancestral con energía mínima (100) interactúa con otra ancestral
		// Ambas ganan 20, pero la ancestral debe mantener mínimo 100
		criatura1 = new CriaturaAncestral("Ancestral1", 100, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaAncestral("Ancestral2", 100, AfinidadElemental.AGUA);
		
		criatura1.interactuar(criatura2);
		
		// Ambas deben tener al menos 100 de energía
		assertTrue("Ancestral debe mantener mínimo 100 de energía", criatura1.getNivelEnergia() >= 100);
		assertTrue("Ancestral debe mantener mínimo 100 de energía", criatura2.getNivelEnergia() >= 100);
		// Ambas ganaron 20, así que deben tener 120
		assertEquals("Ancestral debe tener 120 después de ganar 20", 120, criatura1.getNivelEnergia());
		assertEquals("Ancestral debe tener 120 después de ganar 20", 120, criatura2.getNivelEnergia());
	}
	
	@Test
	public void testInteractuarAncestralConEnergiaBajaMantieneMinimo100() {
		// Caso: Ancestral con energía mínima (100) que interactúa
		// Verificar que nunca baja de 100
		criatura1 = new CriaturaAncestral("Ancestral", 100, AfinidadElemental.FUEGO);
		criatura2 = new CriaturaAncestral("Ancestral2", 150, AfinidadElemental.AGUA);
		
		int energiaInicial1 = criatura1.getNivelEnergia();
		
		criatura1.interactuar(criatura2);
		
		// La ancestral debe mantener mínimo 100
		assertTrue("Ancestral debe mantener mínimo 100 de energía", criatura1.getNivelEnergia() >= 100);
		// Debe haber ganado 20, así que debe tener 120
		assertEquals("Ancestral debe tener 120 después de ganar 20", energiaInicial1 + 20, criatura1.getNivelEnergia());
	}
}

