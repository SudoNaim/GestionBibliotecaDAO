package org.example.dao.implementacion;

<<<<<<< Updated upstream:src/main/java/org/example/dao/implementacion/Libro_AutorDAOImpl.java
public class Libro_AutorDAOImpl {
=======
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.interfaz.LibroAutorDAO;
import org.example.utils.ConexionBD;

public class LibroAutorDAOImpl implements LibroAutorDAO {

    @Override
    public void insert(int idLibro, int idAutor) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "INSERT INTO Libro_Autor(idLibro, idAutor) VALUES(?, ?)";
        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            stmt.setInt(2, idAutor);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idLibro, int idAutor) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "DELETE FROM Libro_Autor WHERE idLibro=? AND idAutor=?";
        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            stmt.setInt(2, idAutor);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> buscarAutoresPorLibro(int idLibro) {
        ConexionBD conexionBD = new ConexionBD();
        List<Integer> autores = new ArrayList<>();
        String sql = "SELECT idAutor FROM Libro_Autor WHERE idLibro=?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                autores.add(rs.getInt("idAutor"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    @Override
    public ArrayList<Integer> buscarLibrosPorAutor(int idAutor) {
        ConexionBD conexionBD = new ConexionBD();
        ArrayList<Integer> libros = new ArrayList<>();
        String sql = "SELECT idLibro FROM Libro_Autor WHERE idAutor=?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idAutor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                libros.add(rs.getInt("idLibro"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }
>>>>>>> Stashed changes:src/main/java/org/example/dao/implementacion/LibroAutorDAOImpl.java
}
