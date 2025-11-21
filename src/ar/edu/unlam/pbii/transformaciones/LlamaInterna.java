package ar.edu.unlam.pbii.transformaciones;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;

public class LlamaInterna extends CriaturaTransformada {
	private static final int BONUS_ENERGIA = 30;
	
	public LlamaInterna(Criatura criatura) {
		super(criatura);
	}
	
	@Override
	public int getEnergiaTotal() {
		int energiaBase = criatura.getEnergiaTotal();
		
		if (criatura.getAfinidad() == AfinidadElemental.FUEGO) {
			return energiaBase + BONUS_ENERGIA;
		}
		
		return energiaBase;
	}
	
	@Override
	public boolean estaInestable() {
		if (criatura.getAfinidad() == AfinidadElemental.FUEGO) {
			return criatura.estaInestable();
		}
		
		return true;
	}
}

