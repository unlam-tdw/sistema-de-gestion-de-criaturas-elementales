package ar.edu.unlam.pbii.transformaciones;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;

public class AscensoDelViento extends CriaturaTransformada {
	private AfinidadElemental afinidadOriginal;
	
	public AscensoDelViento(Criatura criatura) {
		super(criatura);
		if (criatura instanceof Transformacion) {
			this.afinidadOriginal = ((Transformacion) criatura).getCriaturaBase().getAfinidad();
		} else {
			this.afinidadOriginal = criatura.getAfinidad();
		}
	}
	
	@Override
	public AfinidadElemental getAfinidad() {
		return AfinidadElemental.AIRE;
	}
	
	public AfinidadElemental getAfinidadOriginal() {
		return afinidadOriginal;
	}
}

