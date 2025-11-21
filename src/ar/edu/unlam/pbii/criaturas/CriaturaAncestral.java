package ar.edu.unlam.pbii.criaturas;

public class CriaturaAncestral extends Criatura {
    public CriaturaAncestral(String nombre, int nivelEnergia, AfinidadElemental afinidad) {
        super(nombre, nivelEnergia < 100 ? 100 : nivelEnergia, afinidad);
    }

    public void entrenar(int nivelMaestria) {

        int energiaObtenida = nivelMaestria * 3; // Factor mayor ya que son más sensibles al entrenamiento
        int nuevoNivelEnergia = this.nivelEnergia + energiaObtenida;
        if (nuevoNivelEnergia > 200) {
            this.estaInestable = true;
            this.nivelEnergia = 200;
        } else {
            this.nivelEnergia = nuevoNivelEnergia;
        }
        ;
    }

    public void pacificar() {
        this.estaInestable = false;
    }

    @Override
    public boolean esAncestral() {
        return true;
    }
}
