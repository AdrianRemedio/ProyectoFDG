package org.adrianremedio.servidor.basededatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Comprobaciones {
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    protected Comprobaciones(Connection conexion) {
        this.conexion = conexion;
    }

    protected boolean verificarExistenciaBBDD(String nombreBBDD) {
        try {
            sentencia = conexion.prepareStatement("SHOW DATABASES LIKE ?");
            sentencia.setString(1, nombreBBDD);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            }
        } catch (Exception ignored) {
            System.err.println("Ha ocurrido un error al verificar la existencia de la base de datos + " + nombreBBDD + ".");
        }
        return false;
    }

    protected boolean verificarExistenciaUsuario(String usuario) {
        try {
            sentencia = conexion.prepareStatement("SELECT user FROM mysql.user WHERE user = ?");
            sentencia.setString(1, usuario);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            }
        } catch (Exception ignored) {
            System.err.println("Ha ocurrido un error al verificar la existencia del usuario " + usuario + ".");
        }
        return false;
    }

    protected void crearUsuario(String usuario, String clave) {
        try {
            sentencia = conexion.prepareStatement("CREATE USER ? IDENTIFIED BY ?");
            sentencia.setString(1, usuario);
            sentencia.setString(2, clave);
            sentencia.execute();
        } catch (Exception ignored) {
            System.err.println("Ha ocurrido un error al crear el usuario " + usuario + ".");
        }
    }

    protected void otorgarPrivilegiosUsuario(String nombreBBDD, String usuario) {
        try {
            sentencia = conexion.prepareStatement("GRANT CREATE, INSERT, UPDATE, DELETE, SELECT ON " + nombreBBDD + " TO " + usuario);
            sentencia.execute();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error al otorgar privilegios al usuario " + usuario + ".");
        }
    }

    protected void revisarTablas(String nombreBBDD) {
        new BaseDeDatos(nombreBBDD, sentencia);
    }

    protected void crearBaseDeDatos(String nombreBBDD) {
        try {
            sentencia = conexion.prepareStatement("CREATE DATABASE IF NOT EXISTS " + nombreBBDD);
            sentencia.execute();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error al crear la base de datos " + nombreBBDD+ ".");
        }
    }

}
