# Gestión de Biblioteca con JDBC

Este proyecto es una pequeña aplicación de consola para gestionar una biblioteca usando Java y JDBC. 
Permite realizar operaciones básicas de CRUD sobre usuarios, autores, libros y préstamos.

El objetivo principal del proyecto era entender cómo trabajar con JDBC, relaciones entre tablas 
y la gestión de datos mediante DAOs y un Service que centraliza la lógica de negocio.

## Funcionalidades

- Crear, listar, actualizar y eliminar usuarios.
- Crear, listar, actualizar y eliminar autores.
- Crear, listar, actualizar y eliminar libros, asociando autores a cada libro.
- Registrar y listar préstamos de libros por usuarios.
- Consultar libros de un autor o autores de un libro.

## Estructura del proyecto

- `model/` → Clases que representan las entidades (Usuario, Autor, Libro, Prestamo).  
- `dao/interfaz/` → Interfaces DAO con métodos CRUD para cada entidad.  
- `dao/implementacion/` → Implementaciones concretas de cada DAO usando JDBC.  
- `service/` → `BibliotecaService` que coordina la lógica de negocio entre DAOs.  
- `utils/` → Clase `ConexionBD` para obtener la conexión a la base de datos.  
- `main/` → Clase `Main` con menú de consola para probar todas las funcionalidades.  
