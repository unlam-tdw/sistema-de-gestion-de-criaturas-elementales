package ar.edu.unlam.pbii.criaturas;

public class CriaturaDomesticada extends Criatura {

    public CriaturaDomesticada(String nombre, int nivelEnergia, AfinidadElemental afinidad) {
        super(nombre, nivelEnergia, afinidad);
    }

    public void entrenar(int nivelMaestria) {
        int energiaObtenida = nivelMaestria * 2;
        int nuevoNivelEnergia = this.nivelEnergia + energiaObtenida;
        if (nuevoNivelEnergia > 200) {
            this.nivelEnergia = 200;
        } else {
            this.nivelEnergia = nuevoNivelEnergia;
        }
        ;
    };

    public void pacificar() {
    };
}
