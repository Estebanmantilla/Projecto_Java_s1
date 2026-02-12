package CONTROLADOR;

import MODELO.ventas;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ventasTotalesPorMesmpl implements ventasTotalesPorMes {

    @Override
    public void listar() {
        Gestionarventa gv = new Gestionarventampl();
        ArrayList<ventas> lista = gv.listar();

        Map<String, Double> ventasPorMes = lista.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFECHA().substring(0, 7), // yyyy-MM
                        Collectors.summingDouble(ventas::getTotal)
                ));

        ventasPorMes.forEach((mes, total)
                -> System.out.println("Mes: " + mes + " | Total vendido: $" + total)
        );

    }

}
