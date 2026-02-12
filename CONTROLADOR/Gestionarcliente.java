
package CONTROLADOR;

import MODELO.cliente;
import java.util.ArrayList;


public interface Gestionarcliente {
    void guardar(cliente cli);

    void actualizar(cliente cli, int id);

    void eliminar(int id);

    ArrayList<cliente> listar();

    cliente buscar(int id);
}