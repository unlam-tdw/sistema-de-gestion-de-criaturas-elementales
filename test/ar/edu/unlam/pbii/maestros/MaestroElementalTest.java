package ar.edu.unlam.pbii.maestros;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;
import ar.edu.unlam.pbii.criaturas.CriaturaSalvaje;
import ar.edu.unlam.pbii.excepciones.CriaturaNoEncontradaException;
import ar.edu.unlam.pbii.excepciones.MaestriaInsuficienteException;
import ar.edu.unlam.pbii.excepciones.NombreDuplicadoException;
import ar.edu.unlam.pbii.transformaciones.BendicionDelRio;

public class MaestroElementalTest {

	private MaestroElemental maestro;
	private Criatura criatura;
	
	@Before
	public void setUp() {
		maestro = new MaestroElemental("Gandalf", 15, AfinidadElemental.FUEGO);
		criatura = new CriaturaDomesticada("Dragon", 50, AfinidadElemental.FUEGO);
	}
	
	@Test
	public void testConstructor() {
		String nombre = "Merlin";
		int nivelMaestria = 25;
		AfinidadElemental afinidad = AfinidadElemental.AGUA;
		
		MaestroElemental nuevoMaestro = new MaestroElemental(nombre, nivelMaestria, afinidad);
		
		assertEquals("El nombre debe ser correcto", nombre, nuevoMaestro.getNombre());
		assertEquals("El nivel de maestría debe ser correcto", nivelMaestria, nuevoMaestro.getNivelMaestria());
		assertEquals("La afinidad principal debe ser correcta", afinidad, nuevoMaestro.getAfinidadPrincipal());
		assertNotNull("Las criaturas no deben ser null", nuevoMaestro.getCriaturas());
		assertTrue("Las criaturas deben estar vacías inicialmente", nuevoMaestro.getCriaturas().isEmpty());
	}
	
	@Test
	public void testConstructorValidaNivelMaestria() {
		MaestroElemental maestroMin = new MaestroElemental("Test", 0, AfinidadElemental.AIRE);
		assertEquals("Nivel menor a 1 debe ajustarse a 1", 1, maestroMin.getNivelMaestria());
		
		MaestroElemental maestroMax = new MaestroElemental("Test", 100, AfinidadElemental.TIERRA);
		assertEquals("Nivel mayor a 50 debe ajustarse a 50", 50, maestroMax.getNivelMaestria());
	}
	
	@Test
	public void testAgregarCriatura() throws NombreDuplicadoException {
		maestro.agregarCriatura(criatura);
		
		assertTrue("Debe contener la criatura agregada", maestro.getCriaturas().containsKey(criatura.getNombre()));
		assertEquals("Debe retornar la criatura correcta", criatura, maestro.getCriaturas().get(criatura.getNombre()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarCriaturaNullLanzaExcepcion() throws NombreDuplicadoException {
		maestro.agregarCriatura(null);
	}
	
	@Test
	public void testEntrenarCriaturaExitoso() throws MaestriaInsuficienteException, CriaturaNoEncontradaException, NombreDuplicadoException {
		maestro.agregarCriatura(criatura);
		int energiaInicial = criatura.getNivelEnergia();
		
		maestro.entrenarCriatura(criatura.getNombre());
		
		assertTrue("La energía debe aumentar después de entrenar", criatura.getNivelEnergia() > energiaInicial);
	}
	
	@Test
	public void testEntrenarCriaturaConMaestria10() throws MaestriaInsuficienteException, CriaturaNoEncontradaException, NombreDuplicadoException {
		MaestroElemental maestroLimite = new MaestroElemental("Test", 10, AfinidadElemental.AGUA);
		maestroLimite.agregarCriatura(criatura);
		
		maestroLimite.entrenarCriatura(criatura.getNombre());
	}
	
	@Test(expected = MaestriaInsuficienteException.class)
	public void testEntrenarCriaturaConMaestria9LanzaExcepcion() throws MaestriaInsuficienteException, CriaturaNoEncontradaException, NombreDuplicadoException {
		MaestroElemental maestroInsuficiente = new MaestroElemental("Test", 9, AfinidadElemental.FUEGO);
		maestroInsuficiente.agregarCriatura(criatura);
		
		maestroInsuficiente.entrenarCriatura(criatura.getNombre());
	}
	
	@Test(expected = CriaturaNoEncontradaException.class)
	public void testEntrenarCriaturaNoEncontradaLanzaExcepcion() throws MaestriaInsuficienteException, CriaturaNoEncontradaException {
		maestro.entrenarCriatura("CriaturaInexistente");
	}
	
	@Test
	public void testEntrenarCriaturaSalvajePuedeLanzarExcepcion() throws NombreDuplicadoException {
		CriaturaSalvaje criaturaSalvaje = new CriaturaSalvaje("Dragon Salvaje", 199, AfinidadElemental.FUEGO);
		maestro.agregarCriatura(criaturaSalvaje);
		
		try {
			maestro.entrenarCriatura(criaturaSalvaje.getNombre());
		} catch (MaestriaInsuficienteException e) {
			fail("No debe lanzar MaestriaInsuficienteException");
		} catch (CriaturaNoEncontradaException e) {
			fail("No debe lanzar CriaturaNoEncontradaException");
		} catch (RuntimeException e) {
			assertTrue("Puede lanzar EnergiaExcedidaException (unchecked)", e instanceof RuntimeException);
		}
	}
	
	@Test
	public void testPacificarCriatura() throws CriaturaNoEncontradaException, NombreDuplicadoException {
		maestro.agregarCriatura(criatura);
		
		maestro.pacificarCriatura(criatura.getNombre());
		
		assertNotNull("No debe lanzar excepción", criatura);
	}
	
	@Test(expected = CriaturaNoEncontradaException.class)
	public void testPacificarCriaturaNoEncontradaLanzaExcepcion() throws CriaturaNoEncontradaException {
		maestro.pacificarCriatura("CriaturaInexistente");
	}
	
	@Test
	public void testContarCriaturasTransformadas() throws NombreDuplicadoException {
		assertEquals("No debe haber criaturas transformadas inicialmente", 0, maestro.contarCriaturasTransformadas());
		
		maestro.agregarCriatura(criatura);
		assertEquals("Criatura normal no cuenta como transformada", 0, maestro.contarCriaturasTransformadas());
	}
	
	@Test(expected = NombreDuplicadoException.class)
	public void testAgregarCriaturaConNombreDuplicadoLanzaExcepcion() throws NombreDuplicadoException {
		maestro.agregarCriatura(criatura);
		Criatura criaturaDuplicada = new CriaturaDomesticada(criatura.getNombre(), 60, AfinidadElemental.AGUA);
		
		maestro.agregarCriatura(criaturaDuplicada);
	}
	
	@Test
	public void testTransformarCriatura() throws CriaturaNoEncontradaException, NombreDuplicadoException {
		maestro.agregarCriatura(criatura);
		int energiaInicial = criatura.getEnergiaTotal();
		
		BendicionDelRio transformacion = new BendicionDelRio(criatura);
		maestro.transformarCriatura(criatura.getNombre(), transformacion);
		
		Criatura criaturaTransformada = maestro.getCriaturas().get(criatura.getNombre());
		assertNotNull("Debe existir la criatura transformada", criaturaTransformada);
		assertTrue("La energía debe aumentar con la transformación", criaturaTransformada.getEnergiaTotal() > energiaInicial);
	}
	
	@Test(expected = CriaturaNoEncontradaException.class)
	public void testTransformarCriaturaNoEncontradaLanzaExcepcion() throws CriaturaNoEncontradaException {
		BendicionDelRio transformacion = new BendicionDelRio(criatura);
		maestro.transformarCriatura("CriaturaInexistente", transformacion);
	}
}

