
package VISTA;

import CONTROLADOR.Gestionarmarca;
import CONTROLADOR.Gestionarmarcampl;
import MODELO.marca;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu_marca {
    Gestionarmarca gm = new Gestionarmarcampl();

    private void registrar() {
        marca mar = new marca();
        System.out.println("Ingrese el nombre de la marca:");
        mar.setNombre(new Scanner(System.in).nextLine());
        
        gm.guardar(mar);
    }

    private void actualizar() {
        System.out.println("Ingrese el id de la marca a buscar");
        int id = new Scanner(System.in).nextInt();
        marca mar = gm.buscar(id);
        if (mar != null) {
            System.out.println("marca buscado");
            System.out.println(mar);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Nombre
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 1) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    mar.setNombre(new Scanner(System.in).nextLine());
                    break;
                    
            }
            gm.actualizar(mar, id);
        } else {
            System.out.println("No existe dicha area");
        }
    }

    private void buscar() {
        System.out.println("Ingrese el id de la marca a buscar");
        int id = new Scanner(System.in).nextInt();
        marca mar = gm.buscar(id);
        if (mar != null) {
            System.out.println(mar);
        } else {
            System.out.println("No existe dicha marca!");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id del cliente a eliminar");
        int id = new Scanner(System.in).nextInt();
        gm.eliminar(id);
    }

    private void listar() {
        ArrayList<marca> marcas = gm.listar();
        for (marca mar : marcas) {
            System.out.println(mar);
        }
}



 public void menu() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                                        MARCA
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