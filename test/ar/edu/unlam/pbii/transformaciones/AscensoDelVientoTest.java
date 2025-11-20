package ar.edu.unlam.pbii.transformaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;

public class AscensoDelVientoTest {

	private Criatura criatura;
	private AscensoDelViento ascenso;
	
	@Before
	public void setUp() {
		criatura = new CriaturaDomesticada("Dragon", 50, AfinidadElemental.FUEGO);
		ascenso = new AscensoDelViento(criatura);
	}
	
	@Test
	public void testGetAfinidadRetornaAire() {
		assertEquals("Debe retornar AIRE", 
				AfinidadElemental.AIRE, ascenso.getAfinidad());
		
		criatura = new CriaturaDomesticada("Test", 50, AfinidadElemental.AGUA);
		ascenso = new AscensoDelViento(criatura);
		
		assertEquals("Debe retornar AIRE siempre", AfinidadElemental.AIRE, ascenso.getAfinidad());
	}
	
	@Test
	public void testGetAfinidadOriginal() {
		AfinidadElemental afinidadOriginal = criatura.getAfinidad();
		
		assertEquals("Debe retornar la afinidad original", afinidadOriginal, ascenso.getAfinidadOriginal());
	}
	
	@Test
	public void testGetEnergiaTotal() {
		int energiaBase = criatura.getEnergiaTotal();
		
		assertEquals("No debe modificar la energía", energiaBase, ascenso.getEnergiaTotal());
	}
	
	@Test
	public void testConCriaturaTransformada() {
		BendicionDelRio primera = new BendicionDelRio(criatura);
		AscensoDelViento segunda = new AscensoDelViento(primera);
		
		assertEquals("Debe retornar AIRE incluso con transformaciones apiladas", AfinidadElemental.AIRE, segunda.getAfinidad());
		
		assertEquals("Debe mantener la afinidad original", criatura.getAfinidad(), segunda.getAfinidadOriginal());
	}
}

