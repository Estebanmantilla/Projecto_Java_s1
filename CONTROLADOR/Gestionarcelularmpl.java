package CONTROLADOR;

import MODELO.Celular;
import MODELO.marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Gestionarcelularmpl implements Gestionarceular {

    Conexion c = new Conexion();

    @Override
    public void guardar(Celular cel) {
        try (Connection con = c.conexion()) {

            PreparedStatement ps = con.prepareStatement("insert into celular(marca, sistema_operativo, gama, precio, stock, modelo   ) values (?,?,?,?,?,?)");
            ps.setString(1, String.valueOf(cel.getMarca().getId()));
            ps.setString(2, cel.getSistema_operativo());
            ps.setString(3, cel.getGama());
            ps.setInt(4, cel.getPrecio());
            ps.setInt(5, cel.getStock());
            ps.setString(6, cel.getModelo());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Celular cel, int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("update celular set marca=?, sistema_operativo=?, gama=?, precio=?, stock=?, modelo=? where id=?");
            ps.setString(1, String.valueOf(cel.getMarca()));
            ps.setString(2, cel.getSistema_operativo());
            ps.setString(3, cel.getGama());
            ps.setInt(4, cel.getPrecio());
            ps.setInt(5, cel.getStock());
            ps.setString(6, cel.getModelo());
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("delete from celular where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el celular?", null, JOptionPane.YES_NO_OPTION);
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
    public ArrayList<Celular> listar() {
                ArrayList<Celular> celulares = new ArrayList<>();
                Gestionarmarca gm = new Gestionarmarcampl();
        try (Connection con = c.conexion()) {
            //creo el statement para que quede listo cuando quiera escribir en sql
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from celular");
            while (rs.next()) {
                marca mar = gm.buscar(Integer.parseInt(rs.getString(2)));
                celulares.add(new Celular(rs.getInt(1), mar, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celulares;
    }

    @Override
    public Celular buscar(int id) {
                Celular cel = new Celular();
                Gestionarmarca gm = new Gestionarmarcampl();

        try (Connection con = c.conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from celular where id=" + id);
            while (rs.next()) {
                marca mar = gm.buscar(Integer.parseInt(rs.getString(2)));
                cel.setId(rs.getInt(1));
                cel.setMarca(mar);
                cel.setSistema_operativo(rs.getString(3));
                cel.setGama(rs.getString(4));
                cel.setPrecio(rs.getInt(5));
                cel.setStock(rs.getInt(6));
                cel.setModelo(rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cel;
    }
    @Override
    public void descontarStock(int idCelular, int cantidad) {
    try (Connection con = c.conexion()) {
        PreparedStatement ps = con.prepareStatement(
            "UPDATE celular SET stock = stock - ? WHERE id = ?"
        );
        ps.setInt(1, cantidad);
        ps.setInt(2, idCelular);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}


    }