package ar.edu.unlam.pbii.criaturas;

import static org.junit.Assert.*;

import org.junit.Test;

public class CriaturaTest {

	private class CriaturaTestImpl extends Criatura {
		public CriaturaTestImpl(String nombre, int nivelEnergia, AfinidadElemental afinidad) {
			super(nombre, nivelEnergia, afinidad);
		}
		
		@Override
		public void entrenar(int nivelMaestria) {
		}
		
		@Override
		public void pacificar() {
		}
	}
	
	@Test
	public void testConstructor() {
		String nombre = "TestCreature";
		int nivelEnergia = 50;
		AfinidadElemental afinidad = AfinidadElemental.FUEGO;
		
		Criatura criatura = new CriaturaTestImpl(nombre, nivelEnergia, afinidad);
		
		assertEquals("El nombre debe ser correcto", nombre, criatura.getNombre());
		assertEquals("El nivel de energía debe ser correcto", nivelEnergia, criatura.getNivelEnergia());
		assertEquals("La afinidad debe ser correcta", afinidad, criatura.getAfinidad());
		assertFalse("No debe estar inestable por defecto", criatura.estaInestable());
	}
	
	@Test
	public void testGetEnergiaTotal() {
		int nivelEnergia = 75;
		Criatura criatura = new CriaturaTestImpl("Test", nivelEnergia, AfinidadElemental.AGUA);
		
		assertEquals("getEnergiaTotal debe retornar nivelEnergia", nivelEnergia, criatura.getEnergiaTotal());
	}
	
	@Test
	public void testGetters() {
		String nombre = "Dragon";
		int nivelEnergia = 100;
		AfinidadElemental afinidad = AfinidadElemental.FUEGO;
		
		Criatura criatura = new CriaturaTestImpl(nombre, nivelEnergia, afinidad);
		
		assertEquals("getNombre debe retornar el nombre", nombre, criatura.getNombre());
		assertEquals("getNivelEnergia debe retornar la energía", nivelEnergia, criatura.getNivelEnergia());
		assertEquals("getAfinidad debe retornar la afinidad", afinidad, criatura.getAfinidad());
		assertFalse("estaInestable debe retornar false por defecto", criatura.estaInestable());
	}
	
	@Test
	public void testValidarEnergiaInicialNegativa() {
		Criatura criatura = new CriaturaTestImpl("Test", -10, AfinidadElemental.AIRE);
		
		assertEquals("La energía negativa debe corregirse a 0", 0, criatura.getNivelEnergia());
	}
	
	@Test
	public void testEsAncestral() {
		Criatura criatura = new CriaturaTestImpl("Test", 50, AfinidadElemental.TIERRA);
		
		assertFalse("Una criatura normal no debe ser ancestral", criatura.esAncestral());
	}
}

