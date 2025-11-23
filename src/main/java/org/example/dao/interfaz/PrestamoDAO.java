package org.example.dao.interfaz;

import org.example.model.Prestamo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PrestamoDAO {
    ArrayList<Prestamo> obtenerPrestamos();

    void createPrestamo(Prestamo prestamo) throws SQLException;

    void updatePrestamo(Prestamo prestamo) throws SQLException;

    void deletePrestamo(int id) throws SQLException;

    Prestamo obtenerPrestamoPorId(int id);

    ArrayList<Prestamo> obtenerPrestamosPorUsuario(int usuarioId);

    ArrayList<Prestamo> obtenerPrestamosPorLibro(int libroId);
}
