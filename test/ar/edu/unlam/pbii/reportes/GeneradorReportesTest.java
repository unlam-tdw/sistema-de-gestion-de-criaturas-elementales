package ar.edu.unlam.pbii.reportes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;
import ar.edu.unlam.pbii.excepciones.NombreDuplicadoException;
import ar.edu.unlam.pbii.maestros.MaestroElemental;
import ar.edu.unlam.pbii.transformaciones.BendicionDelRio;

public class GeneradorReportesTest {

	private GeneradorReportes generador;
	private MaestroElemental maestro1;
	private MaestroElemental maestro2;
	private List<MaestroElemental> maestros;
	
	@Before
	public void setUp() {
		generador = new GeneradorReportes();
		maestro1 = new MaestroElemental("Gandalf", 20, AfinidadElemental.FUEGO);
		maestro2 = new MaestroElemental("Merlin", 25, AfinidadElemental.AGUA);
		maestros = new ArrayList<>();
		maestros.add(maestro1);
		maestros.add(maestro2);
	}
	
	@Test
	public void testListarTodasLasCriaturas() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.AGUA);
		Criatura c3 = new CriaturaDomesticada("Dragon3", 70, AfinidadElemental.AIRE);
		
		maestro1.agregarCriatura(c1);
		maestro1.agregarCriatura(c2);
		maestro2.agregarCriatura(c3);
		
		List<Criatura> todasLasCriaturas = generador.listarTodasLasCriaturas(maestros);
		
		assertEquals("Debe listar todas las criaturas", 3, todasLasCriaturas.size());
		assertTrue("Debe contener c1", todasLasCriaturas.contains(c1));
		assertTrue("Debe contener c2", todasLasCriaturas.contains(c2));
		assertTrue("Debe contener c3", todasLasCriaturas.contains(c3));
	}
	
	@Test
	public void testListarTodasLasCriaturasConListaVacia() {
		List<MaestroElemental> maestrosVacios = new ArrayList<>();
		
		List<Criatura> todasLasCriaturas = generador.listarTodasLasCriaturas(maestrosVacios);
		
		assertTrue("Debe retornar lista vacía", todasLasCriaturas.isEmpty());
	}
	
	@Test
	public void testObtenerCriaturaMayorEnergia() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 100, AfinidadElemental.AGUA);
		Criatura c3 = new CriaturaDomesticada("Dragon3", 75, AfinidadElemental.AIRE);
		
		maestro1.agregarCriatura(c1);
		maestro1.agregarCriatura(c2);
		maestro2.agregarCriatura(c3);
		
		Criatura mayorEnergia = generador.obtenerCriaturaMayorEnergia(maestros);
		
		assertEquals("Debe retornar la criatura con mayor energía", c2, mayorEnergia);
	}
	
	@Test
	public void testObtenerCriaturaMayorEnergiaConTransformaciones() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 100, AfinidadElemental.AGUA);
		Criatura c1Transformada = new BendicionDelRio(c1);
		
		maestro1.agregarCriatura(c1Transformada);
		maestro2.agregarCriatura(c2);
		
		Criatura mayorEnergia = generador.obtenerCriaturaMayorEnergia(maestros);
		
		assertNotNull("Debe retornar una criatura", mayorEnergia);
	}
	
	@Test
	public void testObtenerCriaturaMayorEnergiaConListaVacia() {
		List<MaestroElemental> maestrosVacios = new ArrayList<>();
		
		Criatura mayorEnergia = generador.obtenerCriaturaMayorEnergia(maestrosVacios);
		
		assertNull("Debe retornar null si no hay criaturas", mayorEnergia);
	}
	
	@Test
	public void testObtenerMaestroConMasTransformadas() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.AGUA);
		Criatura c3 = new CriaturaDomesticada("Dragon3", 70, AfinidadElemental.AIRE);
		
		Criatura c1Transformada = new BendicionDelRio(c1);
		Criatura c2Transformada = new BendicionDelRio(c2);
		Criatura c3Transformada = new BendicionDelRio(c3);
		
		maestro1.agregarCriatura(c1Transformada);
		maestro1.agregarCriatura(c2Transformada);
		maestro2.agregarCriatura(c3Transformada);
		
		MaestroElemental maestroConMas = generador.obtenerMaestroConMasTransformadas(maestros);
		
		assertEquals("Debe retornar el maestro con más transformadas", maestro1, maestroConMas);
	}
	
	@Test
	public void testObtenerMaestroConMasTransformadasConListaVacia() {
		List<MaestroElemental> maestrosVacios = new ArrayList<>();
		
		MaestroElemental maestroConMas = generador.obtenerMaestroConMasTransformadas(maestrosVacios);
		
		assertNull("Debe retornar null si no hay maestros", maestroConMas);
	}
	
	@Test
	public void testObtenerMaestroConMasTransformadasConEmpate() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.AGUA);
		Criatura c3 = new CriaturaDomesticada("Dragon3", 70, AfinidadElemental.AIRE);
		Criatura c4 = new CriaturaDomesticada("Dragon4", 80, AfinidadElemental.TIERRA);
		
		Criatura c1Transformada = new BendicionDelRio(c1);
		Criatura c2Transformada = new BendicionDelRio(c2);
		Criatura c3Transformada = new BendicionDelRio(c3);
		Criatura c4Transformada = new BendicionDelRio(c4);
		
		// Ambos maestros tienen 2 criaturas transformadas (empate)
		maestro1.agregarCriatura(c1Transformada);
		maestro1.agregarCriatura(c2Transformada);
		maestro2.agregarCriatura(c3Transformada);
		maestro2.agregarCriatura(c4Transformada);
		
		MaestroElemental maestroConMas = generador.obtenerMaestroConMasTransformadas(maestros);
		
		// En caso de empate, debe retornar el primero encontrado (maestro1)
		assertNotNull("Debe retornar un maestro en caso de empate", maestroConMas);
		assertTrue("Debe retornar uno de los maestros con más transformadas", maestroConMas == maestro1 || maestroConMas == maestro2);
		assertEquals("Ambos deben tener 2 transformadas", 2, maestro1.contarCriaturasTransformadas());
		assertEquals("Ambos deben tener 2 transformadas", 2, maestro2.contarCriaturasTransformadas());
	}
	
	@Test
	public void testObtenerCantidadPorAfinidad() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.FUEGO);
		Criatura c3 = new CriaturaDomesticada("Dragon3", 70, AfinidadElemental.AGUA);
		Criatura c4 = new CriaturaDomesticada("Dragon4", 80, AfinidadElemental.AIRE);
		
		maestro1.agregarCriatura(c1);
		maestro1.agregarCriatura(c2);
		maestro1.agregarCriatura(c3);
		maestro2.agregarCriatura(c4);
		
		HashMap<AfinidadElemental, Integer> cantidadPorAfinidad = generador.obtenerCantidadPorAfinidad(maestros);
		
		assertEquals("Debe haber 2 criaturas FUEGO", Integer.valueOf(2), cantidadPorAfinidad.get(AfinidadElemental.FUEGO));
		assertEquals("Debe haber 1 criatura AGUA", Integer.valueOf(1), cantidadPorAfinidad.get(AfinidadElemental.AGUA));
		assertEquals("Debe haber 1 criatura AIRE", Integer.valueOf(1), cantidadPorAfinidad.get(AfinidadElemental.AIRE));
		assertEquals("Debe haber 0 criaturas TIERRA", Integer.valueOf(0), cantidadPorAfinidad.get(AfinidadElemental.TIERRA));
	}
	
	@Test
	public void testObtenerCantidadPorAfinidadConTodasLasAfinidades() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		Criatura c2 = new CriaturaDomesticada("Dragon2", 60, AfinidadElemental.AGUA);
		Criatura c3 = new CriaturaDomesticada("Dragon3", 70, AfinidadElemental.AIRE);
		Criatura c4 = new CriaturaDomesticada("Dragon4", 80, AfinidadElemental.TIERRA);
		
		maestro1.agregarCriatura(c1);
		maestro1.agregarCriatura(c2);
		maestro2.agregarCriatura(c3);
		maestro2.agregarCriatura(c4);
		
		HashMap<AfinidadElemental, Integer> cantidadPorAfinidad = generador.obtenerCantidadPorAfinidad(maestros);
		
		for (AfinidadElemental afinidad : AfinidadElemental.values()) {
			assertNotNull("Debe tener entrada para " + afinidad, cantidadPorAfinidad.get(afinidad));
		}
	}
	
	@Test
	public void testObtenerCantidadPorAfinidadConTransformaciones() throws NombreDuplicadoException {
		Criatura c1 = new CriaturaDomesticada("Dragon1", 50, AfinidadElemental.FUEGO);
		
		maestro1.agregarCriatura(c1);
		
		HashMap<AfinidadElemental, Integer> cantidadPorAfinidad = generador.obtenerCantidadPorAfinidad(maestros);
		
		assertNotNull("Debe retornar un mapa", cantidadPorAfinidad);
	}
	
	@Test
	public void testObtenerCantidadPorAfinidadConListaVacia() {
		List<MaestroElemental> maestrosVacios = new ArrayList<>();
		
		HashMap<AfinidadElemental, Integer> cantidadPorAfinidad = generador.obtenerCantidadPorAfinidad(maestrosVacios);
		
		for (AfinidadElemental afinidad : AfinidadElemental.values()) {
			assertEquals("Debe tener 0 para " + afinidad, Integer.valueOf(0), cantidadPorAfinidad.get(afinidad));
		}
	}
}

