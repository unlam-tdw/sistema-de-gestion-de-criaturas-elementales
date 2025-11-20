package ar.edu.unlam.pbii.transformaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;

public class CriaturaTransformadaTest {

	private Criatura criaturaBase;
	
	@Before
	public void setUp() {
		criaturaBase = new CriaturaDomesticada("Test", 50, AfinidadElemental.FUEGO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorConCriaturaNullLanzaExcepcion() {
		new BendicionDelRio(null);
	}
	
	@Test
	public void testGetCriaturaBase() {
		BendicionDelRio transformada = new BendicionDelRio(criaturaBase);
		
		Criatura base = transformada.getCriaturaBase();
		
		assertEquals("Debe retornar la criatura base", criaturaBase, base);
	}
	
	@Test
	public void testGetCriaturaBaseConTransformacionAnidada() {
		BendicionDelRio primera = new BendicionDelRio(criaturaBase);
		LlamaInterna segunda = new LlamaInterna(primera);
		
		Criatura base = segunda.getCriaturaBase();
		
		assertEquals("Debe retornar la criatura base original", criaturaBase, base);
	}
	
	@Test
	public void testGetNombre() {
		BendicionDelRio transformada = new BendicionDelRio(criaturaBase);
		
		assertEquals("Debe retornar el nombre de la criatura base", criaturaBase.getNombre(), transformada.getNombre());
	}
}

