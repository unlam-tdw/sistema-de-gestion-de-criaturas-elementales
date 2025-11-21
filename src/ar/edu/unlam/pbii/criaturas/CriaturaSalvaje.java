package ar.edu.unlam.pbii.criaturas;
import ar.edu.unlam.pbii.excepciones.*;

public class CriaturaSalvaje extends Criatura {

    public CriaturaSalvaje(String nombre, int nivelEnergia, AfinidadElemental afinidad) {
        super(nombre, nivelEnergia, afinidad);
    }

    public void entrenar(int nivelMaestria) {
        int energiaObtenida = (int)(Math.random() * 200) + 1; // Obtiene al menos 1 de energía
        int nuevoNivelEnergia = this.nivelEnergia + energiaObtenida;
        if (nuevoNivelEnergia > 200) {
            throw new EnergiaExcedidaException(nuevoNivelEnergia, 200);
        } else {
            this.nivelEnergia = nuevoNivelEnergia;
        };
    };

    public void pacificar() {
        this.estaInestable = false;
    };
}
