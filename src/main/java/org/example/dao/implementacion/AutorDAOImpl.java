package org.example.dao.implementacion;

<<<<<<< Updated upstream
public class AutorDAOImpl {
=======
import org.example.dao.interfaz.AutorDAO;
import org.example.model.Autor;
import org.example.utils.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
public class AutorDAOImpl implements AutorDAO {

    public ArrayList<Autor> obtenerAutores() {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Autor> autores = new ArrayList<>();
        String sql = "select * from Autor";
        try (Connection con = conexion.getConexion(); Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Autor rsAutor = new Autor(
                        rs.getInt(1),
                        rs.getString(2)
                );
                autores.add(rsAutor);
            }
            return autores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAutor(Autor autor) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "INSERT INTO Autor (id, nombre) VALUES (?, ?)";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, autor.getId());
            ps.setString(2, autor.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAutor(Autor autor) throws SQLException {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "UPDATE Autor SET nombre = ? WHERE id = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ps.setInt(2, autor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAutor(int id) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "DELETE FROM Autor WHERE id = ?";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

>>>>>>> Stashed changes
}
