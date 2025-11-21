package ar.edu.unlam.pbii.transformaciones;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;

public abstract class CriaturaTransformada extends Criatura implements Transformacion {
	protected Criatura criatura;
	
	public CriaturaTransformada(Criatura criatura) {
		super(criatura != null ? criatura.getNombre() : "", 
			  criatura != null ? criatura.getNivelEnergia() : 0, 
			  criatura != null ? criatura.getAfinidad() : null);
		if (criatura == null) {
			throw new IllegalArgumentException("La criatura no puede ser null");
		}
		this.criatura = criatura;
	}
	
	@Override
	public Criatura getCriaturaBase() {
		if (criatura instanceof Transformacion) {
			return ((Transformacion) criatura).getCriaturaBase();
		}
		return criatura;
	}
	
	@Override
	public String getNombre() {
		return criatura.getNombre();
	}
	
	@Override
	public int getNivelEnergia() {
		return criatura.getNivelEnergia();
	}
	
	@Override
	public AfinidadElemental getAfinidad() {
		return criatura.getAfinidad();
	}
	
	@Override
	public boolean estaInestable() {
		return criatura.estaInestable();
	}
	
	@Override
	public boolean esAncestral() {
		return criatura.esAncestral();
	}
	
	@Override
	public void entrenar(int nivelMaestria) {
		criatura.entrenar(nivelMaestria);
	}
	
	@Override
	public void pacificar() {
		criatura.pacificar();
	}
	
	@Override
	public void interactuar(Criatura otra) {
		criatura.interactuar(otra);
	}
}

