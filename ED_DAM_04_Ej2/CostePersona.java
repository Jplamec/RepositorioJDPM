public class CostePersonal {
 
    static final int VALOR_HORA_EXTRA = 20;

    static float CosteDelPersonal(Trabajador trabajadores[]) {
        float costeFinal = 0;
        for (Trabajador trabajador : trabajadores) {
            costeFinal += calcularCosteTrabajador(trabajador);
        }
        return costeFinal;
    }

    static float calcularCosteTrabajador(Trabajador trabajador) {
        if (trabajador.getTipoTrabajador() == Trabajador.DIRECTOR || trabajador.getTipoTrabajador() == Trabajador.SUBDIRECTOR) {
            return trabajador.getNomina();
        } else {
            return trabajador.getNomina() + (trabajador.getHorasExtras() * VALOR_HORA_EXTRA);
        }
    }
}
