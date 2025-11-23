package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public String URL="jdbc:mysql://localhost:3306/biblioteca";
    public String USERNAME="root";
    public String PASSWORD="";

    public Connection getConexion (){
        try {
            return  DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
