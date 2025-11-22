package ar.edu.unlam.pbii.excepciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.criaturas.CriaturaDomesticada;
import ar.edu.unlam.pbii.maestros.MaestroElemental;

public class CriaturaNoEncontradaExceptionTest {

    private MaestroElemental maestro;
    private Criatura criatura;
    private CriaturaNoEncontradaException exception;

    @Before
    public void setUp() {
        maestro = new MaestroElemental("Gandalf", 15, AfinidadElemental.FUEGO);
        criatura = new CriaturaDomesticada("Dragon", 50, AfinidadElemental.FUEGO);
        exception = new CriaturaNoEncontradaException(criatura.getNombre(), maestro.getNombre());
    }

    @Test
    public void testTraerNombreCriaturaDeLaException() {
        assertEquals(exception.getNombreCriatura(), criatura.getNombre());
    }

    @Test
    public void testTraerNombreMaestroDeLaException() {
        assertEquals(exception.getNombreMaestro(), maestro.getNombre());
    }
}
