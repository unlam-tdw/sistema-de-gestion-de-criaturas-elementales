package ar.edu.unlam.pbii.transformaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;

public class BendicionDelRioTest {

	private Criatura criaturaBase;
	private BendicionDelRio bendicion;

	@Before
	public void setUp() {
		criaturaBase = new CriaturaDomesticada("Dragon", 50, AfinidadElemental.AGUA);
		bendicion = new BendicionDelRio(criaturaBase);
	}

	@Test
	public void testGetEnergiaTotalDuplica() {
		int energiaBase = criaturaBase.getEnergiaTotal();

		int energiaTransformada = bendicion.getEnergiaTotal();

		assertEquals("Debe duplicar la energía", energiaBase * 2, energiaTransformada);
	}

	@Test
	public void testGetEnergiaTotalNoSupera180() {
		criaturaBase = new CriaturaDomesticada("Test", 100, AfinidadElemental.FUEGO);
		bendicion = new BendicionDelRio(criaturaBase);

		assertEquals("No debe superar 180", 180, bendicion.getEnergiaTotal());
	}

	@Test
	public void testGetEnergiaTotalConEnergia90() {
		criaturaBase = new CriaturaDomesticada("Test", 90, AfinidadElemental.AIRE);
		bendicion = new BendicionDelRio(criaturaBase);

		assertEquals("Con energía 90, debe retornar 180", 180, bendicion.getEnergiaTotal());
	}

	@Test
	public void testGetEnergiaTotalConEnergia100() {
		criaturaBase = new CriaturaDomesticada("Test", 100, AfinidadElemental.TIERRA);
		bendicion = new BendicionDelRio(criaturaBase);

		assertEquals("Con energía 100, debe limitarse a 180", 180, bendicion.getEnergiaTotal());
	}

	@Test
	public void testGetAfinidad() {
		AfinidadElemental afinidadBase = criaturaBase.getAfinidad();

		assertEquals("Debe mantener la afinidad de la criatura base", afinidadBase, bendicion.getAfinidad());
	}

	@Test
	public void testConCriaturaTransformada() {
		LlamaInterna primera = new LlamaInterna(criaturaBase);
		BendicionDelRio segunda = new BendicionDelRio(primera);

		int energiaSegunda = segunda.getEnergiaTotal();

		assertTrue("Debe aplicar ambas transformaciones", energiaSegunda > 0);
	}
}
