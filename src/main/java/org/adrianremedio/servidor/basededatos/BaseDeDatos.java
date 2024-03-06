package org.adrianremedio.servidor.basededatos;

import java.sql.Statement;

/**
 * Clase que contiene la generación de las tablas de la base de datos.
 */
public class BaseDeDatos {
    /**
     * Constructor de la clase.
     *
     * @param nombreBBDD Nombre de la base de datos.
     * @param sentencia  Objeto para realizar ejecuciones en la base de datos.
     */
    public BaseDeDatos(String nombreBBDD, Statement sentencia) {
        try {
            //Informa al SGBD que las siguientes acciones deben producirse en la base de datos indicada.
            sentencia.execute("USE " + nombreBBDD);

            //Crea la tabla Usuarios.
            sentencia.execute("CREATE TABLE IF NOT EXISTS Usuarios (" +
                    "idUsuario VARCHAR(50) UNIQUE PRIMARY KEY, " +
                    "clave VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL " +
                    ");"
            );

            //Crea la tabla Publicaciones.
            sentencia.execute("CREATE TABLE IF NOT EXISTS Publicaciones (" +
                    "idPublicacion INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
                    "titulo VARCHAR(50) NOT NULL, " +
                    "descripcion VARCHAR(50) NOT NULL" +
                    ");"
            );

            //Crea la tabla UsuarioInscrito.
            sentencia.execute("CREATE TABLE IF NOT EXISTS UsuarioInscrito (" +
                    "idUsuario VARCHAR(50), " +
                    "idPublicacion INT UNSIGNED, " +
                    "PRIMARY KEY (idUsuario, idPublicacion), " +
                    "FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario), " +
                    "FOREIGN KEY (idPublicacion) REFERENCES Publicaciones(idPublicacion)" +
                    ");");
        } catch (Exception ignored) {
        }
    }
}
