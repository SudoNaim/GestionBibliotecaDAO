package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private final String url = "jdbc:mysql://localhost:3306/biblioteca";
    private final String usuario = "root";
    private final String contraseña = "";

    public Connection getConexion() throws SQLException {
        try {
            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}