package ar.edu.unlam.pbii.transformaciones;

import ar.edu.unlam.pbii.criaturas.Criatura;

public class VinculoTerrestre extends CriaturaTransformada {
	private static final int ENERGIA_MINIMA = 50;
	
	public VinculoTerrestre(Criatura criatura) {
		super(criatura);
	}
	
	@Override
	public int getEnergiaTotal() {
		int energiaBase = criatura.getEnergiaTotal();
		
		if (energiaBase < ENERGIA_MINIMA) {
			return ENERGIA_MINIMA;
		}
		
		return energiaBase;
	}
}

