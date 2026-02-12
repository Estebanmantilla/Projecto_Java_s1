
package CONTROLADOR;

import MODELO.marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Gestionarmarcampl implements Gestionarmarca {
    Conexion c = new Conexion();

    @Override
    public void guardar(marca mar) {
        try (Connection con = c.conexion()) {

            PreparedStatement ps = con.prepareStatement("insert into marca(nombre) values (?)");
            ps.setString(1, mar.getNombre());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(marca mar, int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("update marca set nombre=? where id=?");
            ps.setString(1, mar.getNombre());
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conexion()) {
            PreparedStatement ps = con.prepareStatement("delete from marca where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el marca?", null, JOptionPane.YES_NO_OPTION);
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
    public ArrayList<marca> listar() {
        ArrayList<marca> marcas = new ArrayList<>();
        try (Connection con = c.conexion()) {
            //creo el statement para que quede listo cuando quiera escribir en sql
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marca");
            while (rs.next()) {
                marcas.add(new marca(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return marcas;

    }

    @Override
    public marca buscar(int id) {
        marca mar = new marca();

        try (Connection con = c.conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marca where id=" + id);
            while (rs.next()) {
                mar.setId(rs.getInt(1));
                mar.setNombre(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mar;
    }

}