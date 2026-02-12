
package CONTROLADOR;
import MODELO.ventas;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReporteVentas {

    public void generarReporte() {

        Gestionarventa gv = new Gestionarventampl();
        ArrayList<ventas> lista = gv.listar();

        try (FileWriter writer = new FileWriter("reporte_ventas.txt")) {

            writer.write("=========== REPORTE DE VENTAS ===========\n\n");

            double totalGeneral = 0;

            for (ventas v : lista) {

                writer.write("ID Venta: " + v.getId() + "\n");
                writer.write("Cliente: " + v.getId_cliente().getNombre() + "\n");
                writer.write("Fecha: " + v.getFECHA() + "\n");
                writer.write("Total: $" + v.getTotal() + "\n");
                writer.write("----------------------------------------\n");

                totalGeneral += v.getTotal();
            }

            writer.write("\nTOTAL GENERAL VENDIDO: $" + totalGeneral);

            System.out.println("Reporte generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
}