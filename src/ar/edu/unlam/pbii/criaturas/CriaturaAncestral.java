package ar.edu.unlam.pbii.criaturas;

public class CriaturaAncestral extends Criatura {
    public CriaturaAncestral(String nombre, int nivelEnergia, AfinidadElemental afinidad) {
        super(nombre, nivelEnergia < 100 ? 100 : nivelEnergia, afinidad);
    }

    public void entrenar(int nivelMaestria) {
        if (nivelMaestria > 40) {
            this.estaInestable = true;
        }

        int energiaObtenida = nivelMaestria * 3;
        int nuevoNivelEnergia = this.nivelEnergia + energiaObtenida;
        if (nuevoNivelEnergia > 200) {
            this.estaInestable = true;
            this.setNivelEnergia(200);
        } else {
            this.setNivelEnergia(nuevoNivelEnergia);
        }
    }

    public void pacificar() {
        this.estaInestable = false;
    }

    @Override
    public boolean esAncestral() {
        return true;
    }

    @Override
    protected void setNivelEnergia(int nivelEnergia) {
        this.nivelEnergia = Math.max(100, nivelEnergia);
    }

    @Override
    public void interactuar(Criatura otra) {
        boolean thisAncestral = this.esAncestral();
        boolean otraAncestral = otra.esAncestral();

        if (thisAncestral && otraAncestral) {
            this.setNivelEnergia(this.getNivelEnergia() + 20);
            otra.setNivelEnergia(otra.getNivelEnergia() + 20);
            return;
        }

        if (thisAncestral || otraAncestral) {
            Criatura ganador = thisAncestral ? this : otra;
            Criatura perdedor = thisAncestral ? otra : this;

            ganador.setNivelEnergia(ganador.getNivelEnergia() + 20);

            int energiaRestante = perdedor.getEnergiaTotal() - 15;
            if (perdedor.esAncestral()) {
                perdedor.setNivelEnergia(Math.max(100, energiaRestante));
            } else {
                perdedor.setNivelEnergia(Math.max(0, energiaRestante));
            }
        }
    }
}
