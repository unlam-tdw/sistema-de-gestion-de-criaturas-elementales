package ar.edu.unlam.pbii.criaturas;

public abstract class Criatura {
	protected String nombre;
	protected int nivelEnergia;
	protected AfinidadElemental afinidad;
	protected boolean estaInestable;

	public Criatura(String nombre, int nivelEnergia, AfinidadElemental afinidad) {
		this.nombre = nombre;
		this.nivelEnergia = nivelEnergia < 0 ? 0 : nivelEnergia;
		this.afinidad = afinidad;
		this.estaInestable = false;
	}

	public abstract void entrenar(int nivelMaestria);

	public abstract void pacificar();

	public int getEnergiaTotal() {
		return nivelEnergia;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivelEnergia() {
		return nivelEnergia;
	}

	public void setNivelEnergia(int nivelEnergia) {
		this.nivelEnergia = nivelEnergia;
	}

	public AfinidadElemental getAfinidad() {
		return afinidad;
	}

	public boolean estaInestable() {
		return estaInestable;
	}

	protected void setEstaInestable(boolean estaInestable) {
		this.estaInestable = estaInestable;
	}

	public boolean esAncestral() {
		return false;
	}

	public void interactuar(Criatura otra) {
		if (this.afinidad.equals(otra.getAfinidad())) {
			int nuevoNivelEnergia = Math.max(0, Math.min(200, this.getNivelEnergia() + 10));	
			int nuevoNivelEnergiaOtra = Math.max(0, Math.min(200, otra.getNivelEnergia() + 10));
			this.setNivelEnergia(nuevoNivelEnergia);
			otra.setNivelEnergia(nuevoNivelEnergiaOtra);
		} else if (this.getAfinidad().esOpuesta(otra.getAfinidad())) {
			this.setEstaInestable(true);
			otra.setEstaInestable(true);
		} else {
			return; // No hacer nada
		}
	}
}
