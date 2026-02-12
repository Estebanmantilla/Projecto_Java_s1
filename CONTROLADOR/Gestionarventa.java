package CONTROLADOR;

import MODELO.ventas;
import java.util.ArrayList;

public interface Gestionarventa {

    void guardar(ventas v);

    void actualizar(ventas v, int id);

    void eliminar(int id);

    ArrayList<ventas> listar();

    ventas buscar(int id);
    
    void actualizarTotal(int idVenta, double subtotal);
}