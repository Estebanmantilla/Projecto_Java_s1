package CONTROLADOR;

import MODELO.cliente;
import MODELO.ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Gestionarventampl implements Gestionarventa {

    Conexion c = new Conexion();

    @Override
    public void guardar(ventas v) {
        try (Connection con = c.conexion()) {

            PreparedStatement ps = con.prepareStatement("insert into ventas(id_cliente,total) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(v.getId_cliente().getId()));
            ps.setDouble(2, v.getTotal());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                v.setId(rs.getInt(1));
            }
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void actualizar(ventas v, int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("update ventas set id_cliente=? where id=?");
            ps.setString(1, String.valueOf(v.getId_cliente()));
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("delete from ventas where id=?");
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
    public ArrayList<ventas> listar() {
        ArrayList<ventas> ventitas = new ArrayList<>();
        Gestionarcliente gc = new Gestionarclientempl();
        try (Connection con = c.conexion()) {
            //creo el statement para que quede listo cuando quiera escribir en sql
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ventas");
            while (rs.next()) {
                cliente cli = gc.buscar(Integer.parseInt(rs.getString(2)));

                ventitas.add(new ventas(rs.getInt(1), cli, rs.getString(3), rs.getDouble(4)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ventitas;

    }

    @Override
    public ventas buscar(int id) {
        ventas v = new ventas();
        Gestionarcliente gc = new Gestionarclientempl();

        try (Connection con = c.conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ventas where id=" + id);
            while (rs.next()) {
                cliente cli = gc.buscar(Integer.parseInt(rs.getString(2)));
                v.setId(rs.getInt(1));
                v.setId_cliente(cli);
                v.setFECHA(rs.getString(3));
                v.setTotal(rs.getDouble(4));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    @Override
    public void actualizarTotal(int idVenta, double subtotal) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement(
                    "update ventas set total = total + ? where id = ?"
            );
            ps.setDouble(1, subtotal);
            ps.setInt(2, idVenta);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}