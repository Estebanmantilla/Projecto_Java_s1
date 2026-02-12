package CONTROLADOR;

import MODELO.marca;
import java.util.ArrayList;

public interface Gestionarmarca {

    void guardar(marca mar);

    void actualizar(marca mar, int id);

    void eliminar(int id);

    ArrayList<marca> listar();

    marca buscar(int id);
}