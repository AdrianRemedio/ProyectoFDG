package org.adrianremedio.servidor.basededatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase que contiene una serie de peticiones al SGBD.
 */
public class Comprobaciones {
    //Parámetros globales.
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    /**
     * Constructor de la clase.
     * @param conexion Conexión establecida con el SGBD.
     */
    protected Comprobaciones(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que comprueba si la base de datos existe.
     * @param nombreBBDD Nombre de la base de datos.
     * @return True si existe; false si no existe.
     */
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

    /**
     * Método que verifica si el usuario dedicado al servidor existe en el SGBD.
     * @param usuario Nombre del usuario dedicado al servidor.
     * @return True si existe; false si no existe.
     */
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

    /**
     * Método que crea el usuario dedicado al servidor.
     * @param usuario Nombre asignado al usuario.
     * @param clave Clave asignada al usuario.
     */
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

    /**
     * Clase que otorga privilegios al usuario dedicado al servidor.
     * @param nombreBBDD Nombre de la base de datos sobre la que se aplican los privilegios.
     * @param usuario Nombre del usuario que obtiene los privilegios.
     */
    protected void otorgarPrivilegiosUsuario(String nombreBBDD, String usuario) {
        try {
            sentencia = conexion.prepareStatement("GRANT CREATE, INSERT, UPDATE, DELETE, SELECT ON " + nombreBBDD + " TO " + usuario);
            sentencia.execute();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error al otorgar privilegios al usuario " + usuario + ".");
        }
    }

    /**
     * Clase que revisa si existen las tablas principales.
     * @param nombreBBDD Nombre de la base de datos.
     */
    protected void revisarTablas(String nombreBBDD) {
        new BaseDeDatos(nombreBBDD, sentencia);
    }

    /**
     * Clase que crea la base de datos.
     * @param nombreBBDD Nombre de la base de datos.
     */
    protected void crearBaseDeDatos(String nombreBBDD) {
        try {
            sentencia = conexion.prepareStatement("CREATE DATABASE IF NOT EXISTS " + nombreBBDD);
            sentencia.execute();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error al crear la base de datos " + nombreBBDD+ ".");
        }
    }

}
