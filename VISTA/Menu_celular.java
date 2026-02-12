package VISTA;

import CONTROLADOR.Gestionarceular;
import CONTROLADOR.Gestionarcelularmpl;
import CONTROLADOR.Gestionarmarca;
import CONTROLADOR.Gestionarmarcampl;
import MODELO.Celular;
import MODELO.marca;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_celular {

    Gestionarceular gc = new Gestionarcelularmpl();
    Gestionarmarca gm = new Gestionarmarcampl();

    private void registrar() {
        Celular cel = new Celular();
        System.out.println("Ingrese la marca del celular:");
        System.out.println("*******MARCAS***********");
        ArrayList<marca> marcas = gm.listar();
        for (marca mar : marcas) {
            System.out.println(mar);
        }
        System.out.println("Ingrese el id de la marca");
        marca marca = gm.buscar(new Scanner(System.in).nextInt());
        cel.setMarca(marca);
        System.out.println("Ingrese el sistema operativo de la marca:");
        cel.setSistema_operativo(new Scanner(System.in).nextLine());
        System.out.println("Ingrese la gama del celular:");
        cel.setGama(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el precio del celular:");
        int precio = new Scanner(System.in).nextInt();

        System.out.println("Ingrese el stock del celular:");
        int stock = new Scanner(System.in).nextInt();
        //  Validacion de precio y stock positivos
        if (precio <= 0 || stock <= 0) {
            System.out.println("El precio y el stock deben ser mayores que 0");
            return;
        }
        cel.setPrecio(precio);
        cel.setStock(stock);

        System.out.println("Ingrese el modelo del celular:");
        cel.setModelo(new Scanner(System.in).nextLine());

        gc.guardar(cel);
        
    }

    private void actualizar() {
        System.out.println("Ingrese el id del celular a buscar");
        int id = new Scanner(System.in).nextInt();
        Celular cel = gc.buscar(id);
        if (cel != null) {

            System.out.println("cliente buscado");
            System.out.println(cel);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Marca
                               2.   Sistema operativo
                               3.   Gama
                               4.   Precio
                               5.   Stock
                               6.   Modelo
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:

                    ArrayList<marca> marcas = gm.listar();
                    for (marca mar : marcas) {
                        System.out.println(mar);
                    }
                    System.out.println("Ingrese id de la nueva marca ");
                    int marca = new Scanner(System.in).nextInt();
                    marca mar = gm.buscar(marca);
                    if (mar != null) {
                        cel.setMarca(mar);
                    } else {
                        System.out.println("MARCA NO ENCONTRADA, NO SE PUEDE ACTUALIZAR");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el nuevo sistema operativo");
                    cel.setSistema_operativo(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese la nueva gama");
                    cel.setGama(new Scanner(System.in).nextLine());
                    break;
                case 4:
                    // validacion
                    System.out.println("Ingrese el nuevo precio");
                    int precio = new Scanner(System.in).nextInt();

                    if (precio <= 0) {
                        System.out.println("El precio debe ser mayor que 0");
                        return;
                    }

                    cel.setPrecio(precio);
                    break;
                    
                case 5:
                    //validacion
                    System.out.println("Ingrese el nuevo stock");
                    int stock = new Scanner(System.in).nextInt();

                    if (stock <= 0) {
                        System.out.println("El stock debe ser mayor que 0");
                        return;
                    }

                    cel.setStock(stock);
                    break;
                    
                case 6:
                    System.out.println("Ingrese el nuevo modelo");
                    cel.setModelo(new Scanner(System.in).nextLine());
                    break;

            }
            gc.actualizar(cel, id);
        } else {
            System.out.println("No existe dicha area");
        }
    }

    private void buscar() {
        System.out.println("Ingrese el id del celular a buscar");
        int id = new Scanner(System.in).nextInt();
        Celular cel = gc.buscar(id);
        if (cel != null) {
            System.out.println(cel);
        } else {
            System.out.println("No existe dicho celular!");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id del celular a eliminar");
        int id = new Scanner(System.in).nextInt();
        gc.eliminar(id);
    }

    private void listar() {
        ArrayList<Celular> celulares = gc.listar();
        for (Celular cel : celulares) {
            System.out.println(cel);
        }
    }

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                                        CELULAR
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