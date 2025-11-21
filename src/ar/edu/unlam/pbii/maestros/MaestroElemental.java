package ar.edu.unlam.pbii.maestros;

import java.util.HashMap;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.excepciones.CriaturaNoEncontradaException;
import ar.edu.unlam.pbii.excepciones.MaestriaInsuficienteException;
import ar.edu.unlam.pbii.excepciones.NombreDuplicadoException;
import ar.edu.unlam.pbii.transformaciones.Transformacion;

public class MaestroElemental {
	private static final int NIVEL_MAESTRIA_MINIMO = 10;
	private static final int NIVEL_MAESTRIA_MIN = 1;
	private static final int NIVEL_MAESTRIA_MAX = 50;
	
	private String nombre;
	private int nivelMaestria;
	private AfinidadElemental afinidadPrincipal;
	private HashMap<String, Criatura> criaturas;
	
	public MaestroElemental(String nombre, int nivelMaestria, AfinidadElemental afinidadPrincipal) {
		this.nombre = nombre;
		this.nivelMaestria = Math.max(NIVEL_MAESTRIA_MIN, Math.min(NIVEL_MAESTRIA_MAX, nivelMaestria));
		this.afinidadPrincipal = afinidadPrincipal;
		this.criaturas = new HashMap<>();
	}
	
	public void agregarCriatura(Criatura criatura) throws NombreDuplicadoException {
		if (criatura == null) {
			throw new IllegalArgumentException("La criatura no puede ser null");
		}
		
		if (criaturas.containsKey(criatura.getNombre())) {
			throw new NombreDuplicadoException(criatura.getNombre());
		}
		
		criaturas.put(criatura.getNombre(), criatura);
	}
	
	public void entrenarCriatura(String nombreCriatura) throws MaestriaInsuficienteException, CriaturaNoEncontradaException {
		if (nivelMaestria < NIVEL_MAESTRIA_MINIMO) {
			throw new MaestriaInsuficienteException(nivelMaestria, NIVEL_MAESTRIA_MINIMO);
		}
		
		Criatura criatura = criaturas.get(nombreCriatura);
		if (criatura == null) {
			throw new CriaturaNoEncontradaException(nombreCriatura, nombre);
		}
		
		criatura.entrenar(nivelMaestria);
	}
	
	public void pacificarCriatura(String nombreCriatura) throws CriaturaNoEncontradaException {
		Criatura criatura = criaturas.get(nombreCriatura);
		if (criatura == null) {
			throw new CriaturaNoEncontradaException(nombreCriatura, nombre);
		}
		
		criatura.pacificar();
	}
	
	public void transformarCriatura(String nombreCriatura, Criatura transformacion) throws CriaturaNoEncontradaException {
		if (transformacion == null) {
			throw new IllegalArgumentException("La transformación no puede ser null");
		}
		
		Criatura criatura = criaturas.get(nombreCriatura);
		if (criatura == null) {
			throw new CriaturaNoEncontradaException(nombreCriatura, nombre);
		}
		
		criaturas.put(nombreCriatura, transformacion);
	}
	
	public int contarCriaturasTransformadas() {
		int contador = 0;
		for (Criatura criatura : criaturas.values()) {
			if (criatura instanceof Transformacion) {
				contador++;
			}
		}
		return contador;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getNivelMaestria() {
		return nivelMaestria;
	}
	
	public AfinidadElemental getAfinidadPrincipal() {
		return afinidadPrincipal;
	}
	
	public HashMap<String, Criatura> getCriaturas() {
		return criaturas;
	}
}

