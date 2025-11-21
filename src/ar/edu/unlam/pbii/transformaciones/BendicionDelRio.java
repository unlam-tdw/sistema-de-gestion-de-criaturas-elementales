package ar.edu.unlam.pbii.transformaciones;

import ar.edu.unlam.pbii.criaturas.Criatura;

public class BendicionDelRio extends CriaturaTransformada {
	private static final int ENERGIA_MAXIMA = 180;
	
	public BendicionDelRio(Criatura criatura) {
		super(criatura);
	}
	
	@Override
	public int getEnergiaTotal() {
		int energiaBase = criatura.getEnergiaTotal();
		int energiaDuplicada = energiaBase * 2;
		
		if (energiaDuplicada > ENERGIA_MAXIMA) {
			return ENERGIA_MAXIMA;
		}
		
		return energiaDuplicada;
	}
}

