package ar.edu.unlam.pbii.criaturas;

public enum AfinidadElemental {
	AGUA,
	FUEGO,
	AIRE,
	TIERRA;
	
	public boolean esOpuesta(AfinidadElemental otra) {
		if (otra == null) {
			return false;
		}
		
		return (this == AGUA && otra == FUEGO) ||
			   (this == FUEGO && otra == AGUA) ||
			   (this == AIRE && otra == TIERRA) ||
			   (this == TIERRA && otra == AIRE);
	}
}
