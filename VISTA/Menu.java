package VISTA;

import CONTROLADOR.Ventacompleta;
import java.util.Scanner;

public class Menu {

    public void Menu_Principal() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                           1.   Gestionar Cliente.
                           2.   Gestionar Celular.
                           3.   Gestionar ventas.
                           4.   Gestionar marca.
                           5.   Reportes
                           6.   ventas por mes
                           7.   Salir.
                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 7) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    Menu_cliente cli = new Menu_cliente();
                    cli.menu();
                    break;
                case 2:
                    Menu_celular cel = new Menu_celular();
                    cel.menu();
                    break;
                case 3:
                    Ventacompleta vc = new Ventacompleta();
                    vc.hacerVentaCompleta();
                    break;
                case 4:
                    Menu_marca mar = new Menu_marca();
                    mar.menu();
                    break;
                case 5:
                    ReporteVentas rv = new ReporteVentas();
                    rv.generarReporte();
                case 6:
                    Menu_ventastotales mvt = new Menu_ventastotales();
                    mvt.menu();

                

            }
        } while (op != 7);
    }
}
