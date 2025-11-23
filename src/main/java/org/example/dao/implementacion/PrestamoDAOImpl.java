package org.example.dao.implementacion;
import org.example.dao.interfaz.PrestamoDAO;
import org.example.model.Prestamo;
import org.example.utils.ConexionBD;

import java.sql.*;
import java.util.ArrayList;

public class PrestamoDAOImpl implements PrestamoDAO {

    public ArrayList<Prestamo> obtenerPrestamos() {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo";
        try (Connection con = conexion.getConexion(); Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Prestamo rsPrestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId")
                );
                prestamos.add(rsPrestamo);
            }
            return prestamos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPrestamo(Prestamo prestamo) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "INSERT INTO Prestamo (id, fechaInicio, fechaFin, usuarioId, libroId) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, prestamo.getId());
            ps.setDate(2, new java.sql.Date(prestamo.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(prestamo.getFechaFin().getTime()));
            ps.setInt(4, prestamo.getUsuarioId());
            ps.setInt(5, prestamo.getLibroId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePrestamo(Prestamo prestamo) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "UPDATE Prestamo SET fechaInicio = ?, fechaFin = ?, usuarioId = ?, libroId = ? WHERE id = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(prestamo.getFechaInicio().getTime()));
            ps.setDate(2, new java.sql.Date(prestamo.getFechaFin().getTime()));
            ps.setInt(3, prestamo.getUsuarioId());
            ps.setInt(4, prestamo.getLibroId());
            ps.setInt(5, prestamo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePrestamo(int id) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "DELETE FROM Prestamo WHERE id = ?";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prestamo obtenerPrestamoPorId(int id) {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM Prestamo WHERE id = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int usuarioId) {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo WHERE usuarioId = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId")
                );
                prestamos.add(prestamo);
            }
            return prestamos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Prestamo> obtenerPrestamosPorLibro(int libroId) {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo WHERE libroId = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId")
                );
                prestamos.add(prestamo);
            }
            return prestamos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}