package org.example;

import org.example.dao.implementacion.*;
import org.example.dao.interfaz.*;
import org.example.model.*;
import org.example.service.BibliotecaService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        AutorDAO autorDAO = new AutorDAOImpl();
        LibroDAO libroDAO = new LibroDAOImpl();
        PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
        LibroAutorDAO libroAutorDAO = new LibroAutorDAOImpl();

        BibliotecaService service = new BibliotecaService(usuarioDAO, libroDAO, autorDAO, prestamoDAO, libroAutorDAO);

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- MENÚ BIBLIOTECA ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Listar autores");
            System.out.println("4. Crear autor");
            System.out.println("5. Listar libros");
            System.out.println("6. Crear libro con autores");
            System.out.println("7. Registrar préstamo");
            System.out.println("8. Listar préstamos");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> {
                        ArrayList<Usuario> usuarios = service.listarUsuarios();
                        for (Usuario u : usuarios) System.out.println(u.getId() + " - " + u.getNombre());
                    }
                    case 2 -> {
                        System.out.print("Nombre del usuario: ");
                        String nombre = sc.nextLine();
                        service.crearUsuario(new Usuario(0, nombre));
                        System.out.println("Usuario creado.");
                    }
                    case 3 -> {
                        ArrayList<Autor> autores = service.listarAutores();
                        for (Autor a : autores) System.out.println(a.getId() + " - " + a.getNombre());
                    }
                    case 4 -> {
                        System.out.print("Nombre del autor: ");
                        String nombre = sc.nextLine();
                        service.crearAutor(new Autor(0, nombre));
                        System.out.println("Autor creado.");
                    }
                    case 5 -> {
                        ArrayList<Libro> libros = service.listarLibros();
                        for (Libro l : libros) System.out.println(l.getId() + " - " + l.getTitulo() + " (" + l.getIsbn() + ")");
                    }
                    case 6 -> {
                        System.out.print("Título del libro: ");
                        String titulo = sc.nextLine();
                        System.out.print("ISBN del libro: ");
                        String isbn = sc.nextLine();

                        ArrayList<Integer> idsAutores = new ArrayList<>();
                        System.out.print("Cuántos autores tiene el libro? ");
                        int n = sc.nextInt();
                        sc.nextLine();
                        for (int i = 0; i < n; i++) {
                            System.out.print("ID del autor " + (i + 1) + ": ");
                            idsAutores.add(sc.nextInt());
                        }
                        sc.nextLine();

                        Libro libro = new Libro(0, titulo, isbn);
                        service.crearLibro(libro, idsAutores);
                        System.out.println("Libro creado con autores.");
                    }
                    case 7 -> {
                        System.out.print("ID del usuario: ");
                        int idUsuario = sc.nextInt();
                        System.out.print("ID del libro: ");
                        int idLibro = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Fecha inicio (yyyy-mm-dd): ");
                        Date fechaInicio = Date.valueOf(sc.nextLine());
                        System.out.print("Fecha fin (yyyy-mm-dd): ");
                        Date fechaFin = Date.valueOf(sc.nextLine());

                        Prestamo prestamo = new Prestamo(0, fechaInicio, fechaFin, idUsuario, idLibro);
                        service.registrarPrestamo(prestamo);
                        System.out.println("Préstamo registrado.");
                    }
                    case 8 -> {
                        ArrayList<Prestamo> prestamos = service.listarPrestamos();
                        for (Prestamo p : prestamos) {
                            System.out.println("ID: " + p.getId() + " Usuario: " + p.getUsuarioId() +
                                    " Libro: " + p.getLibroId() + " Inicio: " + p.getFechaInicio() + " Fin: " + p.getFechaFin());
                        }
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
