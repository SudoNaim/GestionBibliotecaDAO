package org.example.dao.implementacion;

import org.example.dao.interfaz.UsuarioDAO;
import org.example.model.Usuario;
import org.example.utils.ConexionBD;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAOImpl implements UsuarioDAO {

    public ArrayList<Usuario> obtenerUsuarios() {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Connection con = conexion.getConexion(); Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Usuario rsUsuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                usuarios.add(rsUsuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsuario(Usuario usuario) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "INSERT INTO Usuario (id, nombre) VALUES (?, ?)";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUsuario(Usuario usuario) throws SQLException {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "UPDATE Usuario SET nombre = ? WHERE id = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUsuario(int id) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection con = conexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
