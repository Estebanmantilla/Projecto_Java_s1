package VISTA;

import CONTROLADOR.Gestionarcliente;
import CONTROLADOR.Gestionarclientempl;
import MODELO.cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_cliente {

    private boolean correoValido(String correo) {
        return correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    Gestionarcliente gc = new Gestionarclientempl();

    private void registrar() {
        cliente cli = new cliente();
        System.out.println("Ingrese el nombre del cliente:");
        cli.setNombre(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el documento del cliente:");
        String identificacion = new Scanner(System.in).nextLine();
        // validacion de la cc
        if (((Gestionarclientempl) gc).identificacionExiste(identificacion)) {
            System.out.println("el numero de identificación ya existe");
            return;
        }
        cli.setIdentificacion(identificacion);
        System.out.println("Ingrese el telefono del cliente:");
        cli.setTelefono(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el correo del cliente:");
        String correo = new Scanner(System.in).nextLine();
        //validacion del correo
        if (!correoValido(correo)) {
            System.out.println("Formato de correo inválido pruebe uno valido porfavor");
            return;
        }
        cli.setCorreo(correo);

        gc.guardar(cli);
    }

    private void actualizar() {
        System.out.println("Ingrese el id del cliente a buscar");
        int id = new Scanner(System.in).nextInt();
        cliente cli = gc.buscar(id);
        if (cli != null) {
            System.out.println("cliente buscado");
            System.out.println(cli);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Nombre
                               2.   Identificacion
                               3.   Telefono
                               4.   Correo
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 4) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    cli.setNombre(new Scanner(System.in).nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese la nueva identificacion");
                    cli.setIdentificacion(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo telefono");
                    cli.setTelefono(new Scanner(System.in).nextLine());
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo correo");
                    String correo = new Scanner(System.in).nextLine();

                    if (!correoValido(correo)) {
                        System.out.println("Formato de correo inválido pruebe uno valido porfavor");
                        return;
                    }
                    cli.setCorreo(correo);
                    break;

            }
            gc.actualizar(cli, id);
        } else {
            System.out.println("No existe dicha area");
        }
    }

    private void buscar() {
        System.out.println("Ingrese el id del cliente a buscar");
        int id = new Scanner(System.in).nextInt();
        cliente cli = gc.buscar(id);
        if (cli != null) {
            System.out.println(cli);
        } else {
            System.out.println("No existe dicho cliente!");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id del cliente a eliminar");
        int id = new Scanner(System.in).nextInt();
        gc.eliminar(id);
    }

    private void listar() {
        ArrayList<cliente> clientes = gc.listar();
        for (cliente cli : clientes) {
            System.out.println(cli);
        }
    }

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                                        CLIENTE
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