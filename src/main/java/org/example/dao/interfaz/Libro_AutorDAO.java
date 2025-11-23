package org.example.dao.interfaz;

<<<<<<< Updated upstream:src/main/java/org/example/dao/interfaz/Libro_AutorDAO.java
public class Libro_AutorDAO {
=======
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LibroAutorDAO {
    void insert(int idLibro, int idAutor)throws SQLException;

    void delete(int idLibro, int idAutor)throws SQLException;

    List<Integer> buscarAutoresPorLibro(int idLibro)throws SQLException;

    ArrayList<Integer> buscarLibrosPorAutor(int idAutor)throws SQLException;
>>>>>>> Stashed changes:src/main/java/org/example/dao/interfaz/LibroAutorDAO.java
}
