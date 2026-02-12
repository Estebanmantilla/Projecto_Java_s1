package CONTROLADOR;

import MODELO.detalle_venta;
import MODELO.ventas;
import VISTA.Menu_detalleventa;
import VISTA.Menu_venta;


public class Ventacompleta {
    Conexion c = new Conexion();
    // funcion que registra la venta y la retorna
    
  
public void hacerVentaCompleta() {
    Menu_venta mv = new Menu_venta();
    Menu_detalleventa mdv = new Menu_detalleventa();
    Gestionarventa gv = new Gestionarventampl();

    ventas v = mv.registrar();

    detalle_venta dv = mdv.registrar(v);

    // RECARGAR VENTA DESDE BD
    v = gv.buscar(v.getId());

    System.out.println(v);
    System.out.println(dv);
}

    
    // funcion que registra el detalle de venta recibienso la venta como parametro y retorna el detalle de venta
    
    // imprimir factura 
}