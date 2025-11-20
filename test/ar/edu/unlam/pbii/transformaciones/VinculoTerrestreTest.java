package ar.edu.unlam.pbii.transformaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;

public class VinculoTerrestreTest {

	private Criatura criatura;
	private VinculoTerrestre vinculo;
	private static final int ENERGIA_MINIMA = 50;
	
	@Before
	public void setUp() {
		criatura = new CriaturaDomesticada("Dragon", 50, AfinidadElemental.TIERRA);
		vinculo = new VinculoTerrestre(criatura);
	}
	
	@Test
	public void testGetEnergiaTotalConEnergiaMayorA50() {
		criatura = new CriaturaDomesticada("Test", 100, AfinidadElemental.AGUA);
		vinculo = new VinculoTerrestre(criatura);
		
		assertEquals("No debe cambiar si la energía es mayor a 50", 100, vinculo.getEnergiaTotal());
	}
	
	@Test
	public void testGetEnergiaTotalConEnergiaMenorA50() {
		criatura = new CriaturaDomesticada("Test", 30, AfinidadElemental.FUEGO);
		vinculo = new VinculoTerrestre(criatura);
		
		assertEquals("Debe garantizar mínimo 50", ENERGIA_MINIMA, vinculo.getEnergiaTotal());
	}
	
	@Test
	public void testGetEnergiaTotalConEnergia0() {
		criatura = new CriaturaDomesticada("Test", 0, AfinidadElemental.AIRE);
		vinculo = new VinculoTerrestre(criatura);
		
		assertEquals("Con energía 0, debe retornar mínimo 50", ENERGIA_MINIMA, vinculo.getEnergiaTotal());
	}
	
	@Test
	public void testGetEnergiaTotalConEnergia49() {
		criatura = new CriaturaDomesticada("Test", 49, AfinidadElemental.TIERRA);
		vinculo = new VinculoTerrestre(criatura);
		
		assertEquals("Con energía 49, debe retornar mínimo 50", ENERGIA_MINIMA, vinculo.getEnergiaTotal());
	}
	
	@Test
	public void testGetAfinidad() {
		AfinidadElemental afinidadBase = criatura.getAfinidad();
		
		assertEquals("Debe mantener la afinidad de la criatura base", afinidadBase, vinculo.getAfinidad());
	}
}

