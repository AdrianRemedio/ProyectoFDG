package org.adrianremedio.servidor.basededatos;

import java.sql.Statement;

public class BaseDeDatos {
    public BaseDeDatos(String nombreBBDD, Statement sentencia) {
        try {
            sentencia.execute("USE " + nombreBBDD);

            sentencia.execute("CREATE TABLE IF NOT EXISTS Usuarios (" +
                    "idUsuario VARCHAR(50) UNIQUE PRIMARY KEY, " +
                    "clave VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL " +
                    ");"
            );

            sentencia.execute("CREATE TABLE IF NOT EXISTS Publicaciones (" +
                    "idPublicacion INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
                    "titulo VARCHAR(50) NOT NULL, " +
                    "descripcion VARCHAR(50) NOT NULL" +
                    ");"
            );

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
