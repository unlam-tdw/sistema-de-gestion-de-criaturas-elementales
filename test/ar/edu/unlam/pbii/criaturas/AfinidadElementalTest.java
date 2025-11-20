package ar.edu.unlam.pbii.criaturas;

import static org.junit.Assert.*;

import org.junit.Test;

public class AfinidadElementalTest {

	@Test
	public void testValoresEnum() {
		AfinidadElemental[] valores = AfinidadElemental.values();
		
		assertEquals("Debe tener 4 valores", 4, valores.length);
		assertEquals("Debe tener AGUA", AfinidadElemental.AGUA, valores[0]);
		assertEquals("Debe tener FUEGO", AfinidadElemental.FUEGO, valores[1]);
		assertEquals("Debe tener AIRE", AfinidadElemental.AIRE, valores[2]);
		assertEquals("Debe tener TIERRA", AfinidadElemental.TIERRA, valores[3]);
	}
	
	@Test
	public void testEsOpuesta() {
		assertTrue("AGUA debe ser opuesta a FUEGO", AfinidadElemental.AGUA.esOpuesta(AfinidadElemental.FUEGO));
		assertTrue("FUEGO debe ser opuesta a AGUA", AfinidadElemental.FUEGO.esOpuesta(AfinidadElemental.AGUA));
		
		assertTrue("AIRE debe ser opuesta a TIERRA", AfinidadElemental.AIRE.esOpuesta(AfinidadElemental.TIERRA));
		assertTrue("TIERRA debe ser opuesta a AIRE", AfinidadElemental.TIERRA.esOpuesta(AfinidadElemental.AIRE));
		
		assertFalse("AGUA no debe ser opuesta a AIRE", AfinidadElemental.AGUA.esOpuesta(AfinidadElemental.AIRE));
		assertFalse("AGUA no debe ser opuesta a TIERRA", AfinidadElemental.AGUA.esOpuesta(AfinidadElemental.TIERRA));
		assertFalse("AGUA no debe ser opuesta a sí misma", AfinidadElemental.AGUA.esOpuesta(AfinidadElemental.AGUA));
	}
}

