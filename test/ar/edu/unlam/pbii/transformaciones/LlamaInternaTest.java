package ar.edu.unlam.pbii.transformaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;

public class LlamaInternaTest {

	private Criatura criaturaFuego;
	private Criatura criaturaNoFuego;
	private LlamaInterna llamaFuego;
	private LlamaInterna llamaNoFuego;
	
	@Before
	public void setUp() {
		criaturaFuego = new CriaturaDomesticada("Dragon Fuego", 50, AfinidadElemental.FUEGO);
		criaturaNoFuego = new CriaturaDomesticada("Dragon Agua", 50, AfinidadElemental.AGUA);
		llamaFuego = new LlamaInterna(criaturaFuego);
		llamaNoFuego = new LlamaInterna(criaturaNoFuego);
	}
	
	@Test
	public void testGetEnergiaTotalConAfinidadFuego() {
		int energiaBase = criaturaFuego.getEnergiaTotal();
		
		int energiaTransformada = llamaFuego.getEnergiaTotal();
		
		assertEquals("Debe sumar 30 si la afinidad es FUEGO", energiaBase + 30, energiaTransformada);
	}
	
	@Test
	public void testGetEnergiaTotalConAfinidadNoFuego() {
		int energiaBase = criaturaNoFuego.getEnergiaTotal();
		
		int energiaTransformada = llamaNoFuego.getEnergiaTotal();
		
		assertEquals("No debe aumentar si no es FUEGO", energiaBase, energiaTransformada);
	}
	
	@Test
	public void testEstaInestableConAfinidadFuego() {
		assertFalse("No debe estar inestable si la afinidad es FUEGO", llamaFuego.estaInestable());
	}
	
	@Test
	public void testEstaInestableConAfinidadNoFuego() {
		assertTrue("Debe estar inestable si la afinidad NO es FUEGO", llamaNoFuego.estaInestable());
	}
	
	@Test
	public void testGetAfinidad() {
		AfinidadElemental afinidadBase = criaturaFuego.getAfinidad();
		
		assertEquals("Debe mantener la afinidad de la criatura base", afinidadBase, llamaFuego.getAfinidad());
	}
	
	@Test
	public void testConCriaturaTransformada() {
		BendicionDelRio primera = new BendicionDelRio(criaturaFuego);
		LlamaInterna segunda = new LlamaInterna(primera);
		
		int energia = segunda.getEnergiaTotal();
		
		assertTrue("Debe aplicar ambas transformaciones", energia > 0);
	}
}

