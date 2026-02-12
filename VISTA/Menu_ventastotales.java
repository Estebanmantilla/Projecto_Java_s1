package VISTA;

import CONTROLADOR.Gestionarventa;
import CONTROLADOR.Gestionarventampl;
import MODELO.ventas;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu_ventastotales {


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

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                                        ventatotales
                           1.   listar
                           2.   salir

                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 2) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    listar();
                    break;
            }
        } while (op != 2);
    }
}