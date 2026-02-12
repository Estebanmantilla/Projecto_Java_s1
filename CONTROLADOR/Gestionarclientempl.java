package CONTROLADOR;

import MODELO.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Gestionarclientempl implements Gestionarcliente {

    Conexion c = new Conexion();

    @Override
    public void guardar(cliente cli) {
        try (Connection con = c.conexion()) {

            PreparedStatement ps = con.prepareStatement("insert into cliente(nombre, identificacion, telefono, correo  ) values (?,?,?,?)");
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getIdentificacion());
            ps.setString(3, cli.getTelefono());
            ps.setString(4, cli.getCorreo());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void actualizar(cliente cli, int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("update cliente set nombre=?, identificacion=?, telefono=?, correo=? where id=?");
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getIdentificacion());
            ps.setString(3, cli.getTelefono());
            ps.setString(4, cli.getCorreo());
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
            PreparedStatement ps = con.prepareStatement("delete from cliente where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente?", null, JOptionPane.YES_NO_OPTION);
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
    public ArrayList<cliente> listar() {
        ArrayList<cliente> clientes = new ArrayList<>();
        try (Connection con = c.conexion()) {
            //creo el statement para que quede listo cuando quiera escribir en sql
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente");
            while (rs.next()) {
                clientes.add(new cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;

    }

    @Override
    public cliente buscar(int id) {

        cliente cli = new cliente();

        try (Connection con = c.conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where id=" + id);
            while (rs.next()) {
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setIdentificacion(rs.getString(3));
                cli.setTelefono(rs.getString(4));
                cli.setCorreo(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cli;
    }

    public boolean identificacionExiste(String identificacion) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("select count(*) from cliente where identificacion = ?");
            ps.setString(1, identificacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}