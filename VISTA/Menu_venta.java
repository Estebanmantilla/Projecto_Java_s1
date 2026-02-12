
package VISTA;

import CONTROLADOR.Gestionarcliente;
import CONTROLADOR.Gestionarclientempl;
import CONTROLADOR.Gestionarventa;
import CONTROLADOR.Gestionarventampl;
import MODELO.ventas;
import MODELO.cliente;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu_venta {
        Gestionarventa gv = new Gestionarventampl();
        Gestionarcliente gc = new Gestionarclientempl();

    public ventas registrar() {
        ventas v = new ventas();
        System.out.println("clientes disponibles:");
        ArrayList<cliente> clientes = gc.listar();
        for (cliente cli : clientes) {
            System.out.println(cli);
        }
        System.out.println("Ingrese el id del cliente");
        cliente cliente = gc.buscar(new Scanner(System.in).nextInt());
        v.setId_cliente(cliente);
        
        gv.guardar(v);

        return v;
    }

    public void actualizar() {
        System.out.println("Ingrese el id de la venta a buscar");
        int id = new Scanner(System.in).nextInt();
        ventas v = gv.buscar(id);
        if (v != null) {
            System.out.println("cliente buscado");
            System.out.println(v);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Id del cliente
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 1) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    ArrayList<cliente> clientes = gc.listar();
                    for (cliente cli : clientes) {
                        System.out.println(cli);
                    }
                    System.out.println("Ingrese id de lal nuevo cliente ");
                    int cliente = new Scanner(System.in).nextInt();
                    cliente cli = gc.buscar(cliente);
                    if (cli != null) {
                        v.setId_cliente(cli);
                    } else {
                        System.out.println("CLIENTE NO ENCONTRADA, NO SE PUEDE ACTUALIZAR");
                    }
                    break;
                    
                    
            }
            gv.actualizar(v, id);
        } else {
            System.out.println("No existe dicha venta");
        }
    }

    public void buscar() {
        System.out.println("Ingrese el id de la venta a buscar");
        int id = new Scanner(System.in).nextInt();
        ventas v = gv.buscar(id);
        if (v != null) {
            System.out.println(v);
        } else {
            System.out.println("No existe dicha venta!");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id del cliente a eliminar");
        int id = new Scanner(System.in).nextInt();
        gv.eliminar(id);
    }

    private void listar() {
        ArrayList<ventas> ventitas = gv.listar();
        for (ventas v : ventitas) {
            System.out.println(v);
        }
}



 public void menu() {
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
                    registrar();
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