package org.example.service;

import org.example.dao.interfaz.*;
import org.example.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private UsuarioDAO usuarioDAO;
    private LibroDAO libroDAO;
    private AutorDAO autorDAO;
    private PrestamoDAO prestamoDAO;
    private LibroAutorDAO libroAutorDAO;

    public BibliotecaService(UsuarioDAO usuarioDAO, LibroDAO libroDAO, AutorDAO autorDAO,
                             PrestamoDAO prestamoDAO, LibroAutorDAO libroAutorDAO) {
        this.usuarioDAO = usuarioDAO;
        this.libroDAO = libroDAO;
        this.autorDAO = autorDAO;
        this.prestamoDAO = prestamoDAO;
        this.libroAutorDAO = libroAutorDAO;
    }

    //Usuarios
    public void crearUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.createUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.updateUsuario(usuario);
    }

    public void eliminarUsuario(int id) throws SQLException {
        usuarioDAO.deleteUsuario(id);
    }

    public Usuario obtenerUsuario(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    //Autores
    public void crearAutor(Autor autor) throws SQLException {
        autorDAO.createAutor(autor);
    }

    public void actualizarAutor(Autor autor) throws SQLException {
        autorDAO.updateAutor(autor);
    }

    public void eliminarAutor(int id) throws SQLException {
        autorDAO.deleteAutor(id);
    }

    public ArrayList<Autor> listarAutores() throws SQLException {
        return autorDAO.obtenerAutores();
    }

    //Libros
    public void crearLibro(Libro libro, ArrayList<Integer> idsAutores) throws SQLException {
        libroDAO.createLibro(libro);
        for (int idAutor : idsAutores) {
            libroAutorDAO.insert(libro.getId(), idAutor);
        }
    }

    public void actualizarLibro(Libro libro) throws SQLException {
        libroDAO.updateLibro(libro);
    }

    public void eliminarLibro(int id) throws SQLException {
        libroDAO.deleteLibro(id);
    }

    public Libro obtenerLibro(int id) throws SQLException {
        return libroDAO.obtenerLibroPorId(id);
    }

    public ArrayList<Libro> listarLibros() throws SQLException {
        return libroDAO.obtenerLibros();
    }

    public List<Autor> obtenerAutoresDeLibro(int idLibro) throws SQLException {
        List<Integer> idsAutores = libroAutorDAO.buscarAutoresPorLibro(idLibro);
        List<Autor> autores = new ArrayList<>();
        for (int id : idsAutores) {
            autores.addAll(autorDAO.obtenerAutores().stream()
                    .filter(a -> a.getId() == id)
                    .toList());
        }
        return autores;
    }

    public ArrayList<Libro> obtenerLibrosDeAutor(int idAutor) throws SQLException {
        ArrayList<Integer> idsLibros = libroAutorDAO.buscarLibrosPorAutor(idAutor);
        ArrayList<Libro> libros = new ArrayList<>();
        for (int id : idsLibros) {
            libros.add(libroDAO.obtenerLibroPorId(id));
        }
        return libros;
    }

    //Prestamos
    public void registrarPrestamo(Prestamo prestamo) throws SQLException {
        // Validaciones simples
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(prestamo.getUsuarioId());
        Libro libro = libroDAO.obtenerLibroPorId(prestamo.getLibroId());

        if (usuario == null) throw new SQLException("Usuario no existe.");
        if (libro == null) throw new SQLException("Libro no existe.");

        prestamoDAO.createPrestamo(prestamo);
    }

    public void actualizarPrestamo(Prestamo prestamo) throws SQLException {
        prestamoDAO.updatePrestamo(prestamo);
    }

    public void eliminarPrestamo(int id) throws SQLException {
        prestamoDAO.deletePrestamo(id);
    }

    public Prestamo obtenerPrestamo(int id) {
        return prestamoDAO.obtenerPrestamoPorId(id);
    }

    public ArrayList<Prestamo> listarPrestamos() {
        return prestamoDAO.obtenerPrestamos();
    }

    public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int idUsuario) {
        return prestamoDAO.obtenerPrestamosPorUsuario(idUsuario);
    }

    public ArrayList<Prestamo> obtenerPrestamosPorLibro(int idLibro) {
        return prestamoDAO.obtenerPrestamosPorLibro(idLibro);
    }
}
