package ar.edu.unlam.pbii.interacciones;

import ar.edu.unlam.pbii.criaturas.Criatura;

public class SistemaInteracciones {
    public static void interactuar(Criatura c1, Criatura c2) {
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("Las criaturas no pueden ser null");
        }

        c1.interactuar(c2);
    }
}
