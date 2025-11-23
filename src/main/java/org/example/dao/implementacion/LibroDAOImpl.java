package org.example.dao.implementacion;


import org.example.dao.interfaz.LibroDAO;
import org.example.model.Libro;
import org.example.utils.ConexionBD;

import java.sql.*;
import java.util.ArrayList;

public class LibroDAOImpl implements LibroDAO {

    public ArrayList<Libro> obtenerLibros() {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libro";
        try (Connection con = conexion.getConexion(); Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Libro rsLibro = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("isbn")
                );
                libros.add(rsLibro);
            }
            return libros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createLibro(Libro libro) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "INSERT INTO Libro (titulo, isbn) VALUES (?, ?)";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    libro.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateLibro(Libro libro) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "UPDATE Libro SET titulo = ?, isbn = ? WHERE id = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.setInt(3, libro.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLibro(int id) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "DELETE FROM Libro WHERE id = ?";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Libro obtenerLibroPorId(int id) {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM Libro WHERE id = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("isbn")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}