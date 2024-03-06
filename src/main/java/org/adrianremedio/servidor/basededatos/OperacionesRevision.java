package org.adrianremedio.servidor.basededatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OperacionesRevision {
    private Connection conexion;
    PreparedStatement sentencia;

    protected OperacionesRevision(Connection conexion) {
        this.conexion = conexion;
    }

    protected boolean verificarExistenciaBBDD(String nombreBBDD) {
        try {
            sentencia = conexion.prepareStatement("SHOW DATABASES LIKE ?");
            sentencia.setString(1, nombreBBDD);
            if (sentencia.executeQuery().next()) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}
