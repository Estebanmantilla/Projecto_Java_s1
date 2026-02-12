package VISTA;

import CONTROLADOR.Gestionarcelularmpl;
import CONTROLADOR.Gestionarceular;
import CONTROLADOR.Gestionardetalle_venta;
import CONTROLADOR.Gestionardetalle_ventamlp;
import CONTROLADOR.Gestionarventa;
import CONTROLADOR.Gestionarventampl;
import MODELO.Celular;
import MODELO.detalle_venta;
import MODELO.ventas;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_detalleventa {

    Gestionarventa gv = new Gestionarventampl();
    Gestionardetalle_venta gdv = new Gestionardetalle_ventamlp();
    Gestionarceular gc = new Gestionarcelularmpl();


    public detalle_venta registrar(ventas v) {
        detalle_venta dv = new detalle_venta();

        System.out.println("celulares disponibles:");
        ArrayList<Celular> celulares = gc.listar();
        for (Celular cel : celulares) {
            System.out.println(cel);
        }
        System.out.println("Ingrese el id del celular");
        Celular idcelular = gc.buscar(new Scanner(System.in).nextInt());
        dv.setId_celular(idcelular);
        System.out.println("Ingrese la cantidad:");
        dv.setCantidad(new Scanner(System.in).nextInt());
        dv.setId_venta(v);
        double subtotal = idcelular.getPrecio() * dv.getCantidad();
        dv.setSubtotal(subtotal);

        gdv.guardar(dv);
        gv.actualizarTotal(v.getId(), subtotal);
        gc.descontarStock(idcelular.getId(), dv.getCantidad());
        return dv;
    }

    public void actualizar() {
        System.out.println("Ingrese el id de la venta a buscar");
        int id = new Scanner(System.in).nextInt();
        detalle_venta dv = gdv.buscar(id);
        if (dv != null) {
            System.out.println("celular encontrado");
            System.out.println(dv);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Id del celular
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 1) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    ArrayList<Celular> celulares = gc.listar();
                    for (Celular cel : celulares) {
                        System.out.println(cel);
                    }
                    System.out.println("Ingrese id de el nuevo celular ");
                    int Celular = new Scanner(System.in).nextInt();
                    Celular cel = gc.buscar(Celular);
                    if (cel != null) {
                        dv.setId_celular(cel);
                    } else {
                        System.out.println("CELULAR NO ENCONTRADO, NO SE PUEDE ACTUALIZAR");
                    }
                    break;

            }
            gdv.actualizar(dv, id);
        } else {
            System.out.println("No existe dicha venta");
        }
    }

    public void buscar() {
        System.out.println("Ingrese el id de la venta a buscar");
        int id = new Scanner(System.in).nextInt();
        detalle_venta dv = gdv.buscar(id);
        if (dv != null) {
            System.out.println(dv);
        } else {
            System.out.println("No existe dicha venta!");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id de la venta a eliminar");
        int id = new Scanner(System.in).nextInt();
        gdv.eliminar(id);
    }

    private void listar() {
        ArrayList<detalle_venta> detallitos = gdv.listar();
        for (detalle_venta d : detallitos) {
            System.out.println(d);
        }
    }

    public void menu(ventas v) {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                                        VENTA
                           1.   Registrar
                           2.   Actualizar
                           3.   Eliminar.
                           4.   Listar.
                           5.   Buscar.
                           6.   Regresar.
                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    registrar(v);
                    break;
                case 2:
                    actualizar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    buscar();
                    break;
            }
        } while (op != 6);
    }
}