package ar.edu.unlam.pbii.excepciones;

public class CriaturaNoEncontradaException extends Exception {
	private String nombreCriatura;
	private String nombreMaestro;
	
	public CriaturaNoEncontradaException(String nombreCriatura, String nombreMaestro) {
		super(String.format("Criatura '%s' no encontrada en la colección del maestro '%s'", nombreCriatura, nombreMaestro));
		this.nombreCriatura = nombreCriatura;
		this.nombreMaestro = nombreMaestro;
	}
	
	public String getNombreCriatura() {
		return nombreCriatura;
	}
	
	public String getNombreMaestro() {
		return nombreMaestro;
	}
}
