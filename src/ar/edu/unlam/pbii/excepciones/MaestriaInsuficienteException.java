package ar.edu.unlam.pbii.excepciones;

public class MaestriaInsuficienteException extends Exception {	
	private int nivelMaestria;
	private int nivelMinimo;
	
	public MaestriaInsuficienteException(int nivelMaestria, int nivelMinimo) {
		super(String.format("Nivel de maestría insuficiente: %d (mínimo requerido: %d)", nivelMaestria, nivelMinimo));
		this.nivelMaestria = nivelMaestria;
		this.nivelMinimo = nivelMinimo;
	}
	
	public int getNivelMaestria() {
		return nivelMaestria;
	}
	
	public int getNivelMinimo() {
		return nivelMinimo;
	}
}
