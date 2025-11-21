package ar.edu.unlam.pbii.excepciones;

public class EnergiaExcedidaException extends RuntimeException {
	private int energiaActual;
	private int energiaMaxima;
	
	public EnergiaExcedidaException(int energiaActual, int energiaMaxima) {
		super(String.format("Energía excedida: %d (máximo permitido: %d)", energiaActual, energiaMaxima));
		this.energiaActual = energiaActual;
		this.energiaMaxima = energiaMaxima;
	}
	
	public int getEnergiaActual() {
		return energiaActual;
	}
	
	public int getEnergiaMaxima() {
		return energiaMaxima;
	}
}
