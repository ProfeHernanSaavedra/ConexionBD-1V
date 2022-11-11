package controlador;

import bd.Conexion;
import modelo.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author USRVI-LC2
 */
public class RegistroCarrera {

    public boolean agregarCarrera(Carrera carrera) {
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "INSERT INTO carrera(nombre) VALUES(?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, carrera.getNombre()); //esto hace referencia a las variables con simbolo ? 

            stmt.executeUpdate();
            stmt.close();
            cnx.close();

            return true;

        } catch (SQLException e) {

            System.out.println("Error en la consulta insertar carrera " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCarrera(String nombre, String nombreNew) {
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "UPDATE carrera set nombre = ? where nombre = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, nombreNew); //esto hace referencia a las variables con simbolo ? 
            stmt.setString(2, nombre);

            stmt.executeUpdate();
            stmt.close();
            cnx.close();

            return true;
        } catch (SQLException e) {

            System.out.println("Error en la consulta actualizar carrera " + e.getMessage());
            return false;
        }

    }

    public boolean eliminar(String nombre) {
       
        boolean dato = false;
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "DELETE FROM carrera where nombre = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, nombre); //esto hace referencia a las variables con simbolo ? 

            int op = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?", "Eliminando..", 0);

            if (op == 0) {
                stmt.executeUpdate();
                stmt.close();
                cnx.close();

                dato = true;
             
            }
            
        } catch (SQLException e) {

            System.out.println("Error en la consulta eliminar carrera " + e.getMessage());
            dato =  false;
        }
        return dato;
    }

    public List<Carrera> buscarTodos() {

        List<Carrera> lista = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "SELECT * FROM carrera";
            PreparedStatement stmt = cnx.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carrera car = new Carrera();
                car.setIdCarrera(rs.getInt("idCarrera"));
                car.setNombre(rs.getString("nombre"));

                lista.add(car);
            }

            rs.close();
            stmt.close();
            cnx.close();

        } catch (SQLException e) {

            System.out.println("Error al listar carrera " + e.getMessage());

        }
        return lista;

    }
    
    public Carrera buscarPorNombre(String nombre) {

        Carrera car = new Carrera();

        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "SELECT * FROM carrera where nombre = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                car.setIdCarrera(rs.getInt("idCarrera"));
                car.setNombre(rs.getString("nombre"));

                
            }

            rs.close();
            stmt.close();
            cnx.close();

        } catch (SQLException e) {

            System.out.println("Error al listar carrera " + e.getMessage());

        }
        return car;

    }
    

}
