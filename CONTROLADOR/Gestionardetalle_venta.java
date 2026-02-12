
package CONTROLADOR;

import MODELO.detalle_venta;
import java.util.ArrayList;


public interface Gestionardetalle_venta {
        void guardar(detalle_venta dv);

    void actualizar(detalle_venta dv, int id);

    void eliminar(int id);

    ArrayList<detalle_venta> listar();

    detalle_venta buscar(int id);
    
}