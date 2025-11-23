package org.example.dao.interfaz;

import org.example.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {

    ArrayList<Usuario> obtenerUsuarios();

    void createUsuario(Usuario usuario) throws SQLException;

    void updateUsuario(Usuario usuario) throws SQLException;

    void deleteUsuario(int id) throws SQLException;

    Usuario obtenerUsuarioPorId(int id);
}