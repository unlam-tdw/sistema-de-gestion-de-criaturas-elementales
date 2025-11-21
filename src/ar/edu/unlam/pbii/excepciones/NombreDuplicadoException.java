package ar.edu.unlam.pbii.excepciones;

public class NombreDuplicadoException extends Exception {	
	private String nombreCriatura;
	
	public NombreDuplicadoException(String nombreCriatura) {
		super(String.format("Ya existe una criatura con el nombre '%s'", nombreCriatura));
		this.nombreCriatura = nombreCriatura;
	}
	
	public String getNombreCriatura() {
		return nombreCriatura;
	}
}
