package org.example.dao.interfaz;
import org.example.model.Libro;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LibroDAO {
    ArrayList<Libro> obtenerLibros() throws SQLException;

    void createLibro(Libro libro) throws SQLException;

    void updateLibro(Libro libro) throws SQLException;

    void deleteLibro(int id) throws SQLException;

    Libro obtenerLibroPorId(int id) throws SQLException;

}