package org.example.dao.interfaz;

import org.example.model.Autor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AutorDAO {
    ArrayList<Autor> obtenerAutores() throws SQLException;

    void createAutor(Autor autor) throws SQLException;

    void updateAutor(Autor autor) throws SQLException;

    void deleteAutor(int id) throws SQLException;

}