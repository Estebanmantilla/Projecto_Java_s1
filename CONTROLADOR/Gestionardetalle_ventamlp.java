package CONTROLADOR;

import MODELO.Celular;
import MODELO.detalle_venta;
import MODELO.ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Gestionardetalle_ventamlp implements Gestionardetalle_venta {

    Conexion c = new Conexion();

    @Override
    public void guardar(detalle_venta dv) {
        try (Connection con = c.conexion()) {

            PreparedStatement ps = con.prepareStatement("insert into detalle_ventas(id_venta, id_celular, cantidad, subtotal  ) values (?,?,?,?)");
            ps.setString(1, String.valueOf(dv.getId_venta().getId()));
            ps.setString(2, String.valueOf(dv.getId_celular().getId()));
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getSubtotal());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void actualizar(detalle_venta dv, int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("update detalle_ventas set id_venta=?, id_celular=?, cantidad=?, subtotal=? where id=?");
            ps.setString(1, String.valueOf(dv.getId_venta()));
            ps.setString(2, String.valueOf(dv.getId_celular()));
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getSubtotal());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("delete from detalle_ventas where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la venta?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA!");
            } else {
                System.out.println("Operacion cancelada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<detalle_venta> listar() {
        ArrayList<detalle_venta> detallitos = new ArrayList<>();
        Gestionarceular gc = new Gestionarcelularmpl();
        Gestionarventa gv = new Gestionarventampl();
        try (Connection con = c.conexion()) {
            //creo el statement para que quede listo cuando quiera escribir en sql
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from detalle_ventas;");
            while (rs.next()) {
                ventas v = gv.buscar(Integer.parseInt(rs.getString(2)));
                Celular cel = gc.buscar(Integer.parseInt(rs.getString(3)));
                detallitos.add(new detalle_venta(rs.getInt(1), v, cel, rs.getInt(4), rs.getDouble(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return detallitos;

    }

    @Override
    public detalle_venta buscar(int id) {
        detalle_venta dv = new detalle_venta();
        Gestionarceular gc = new Gestionarcelularmpl();
        Gestionarventa gv = new Gestionarventampl();

        try (Connection con = c.conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from detalle_ventas where id=" + id);
            while (rs.next()) {
                ventas v = gv.buscar(Integer.parseInt(rs.getString(2)));
                Celular cel = gc.buscar(Integer.parseInt(rs.getString(3)));
                dv.setId(rs.getInt(1));
                dv.setId_venta(v);
                dv.setId_celular(cel);
                dv.setCantidad(rs.getInt(4));
                dv.setSubtotal(rs.getDouble(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dv;

    }

}