package controlador;

import bd.Conexion;
import modelo.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
