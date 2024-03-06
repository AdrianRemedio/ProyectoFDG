package org.adrianremedio.servidor.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Revisor {
    private String usuarioRoot = "root";
    private String claveRoot = "MANAGER";
    private String nombreBBDD = "ActiveMeDatabase";
    private String usuario = "ActiveMeServer";
    private String clave = "proyectoFDG";
    private String rutaJDBC = "jdbc:mysql://localhost:3306/";
    private String error = null;

    public void revisarConexion() {
        System.out.println("\nRealizando análisis del entorno...");
        OperacionesRevision operaciones;
        Connection conexion = null;

        try {
            try {
                conexion = DriverManager.getConnection(rutaJDBC, usuarioRoot, claveRoot);
            } catch (Exception ignored) {
                throw new Exception("No se ha podido conectar a root para realizar el análisis." +
                        "\nRevisa que las creedenciales de acceso sean correctas.");
            }
            operaciones = new OperacionesRevision(conexion);

            System.out.print("Revisando existencia de " + usuario + "... ");
            if (!operaciones.verificarExistenciaUsuario(usuario)) {
                operaciones.crearUsuario(usuario, clave);
            }
            System.out.println("FINALIZADO.");

            System.out.println("Revisando privilegios de " + usuario + "... ");
            if (!operaciones.verificarPrivilegiosUsuario(usuario)) {
                operaciones.otorgarPrivilegiosUsuario(usuario);
            }
            System.out.println("FINALIZADO.");

            conexion.close();

            System.out.println("Revisando conexión a " + nombreBBDD + "...");


            System.out.print("Existencia de " + nombreBBDD + ": ");
            if (operaciones.verificarExistenciaBBDD(nombreBBDD)) {
                System.out.println("CORRECTO");
            } else {
                operaciones.crearBaseDeDatos();
            }

        } catch (Exception e) {
            error = e.getMessage();
        }
        mostrarError();
    }

    private void mostrarError() {
        if(error != null){
            System.err.println(error);
            System.exit(0);
        }
    }

    private boolean realizarConexion(){
        try {
            conexion = DriverManager.getConnection(rutaJDBC, usuarioRoot, claveRoot);
        } catch (Exception ignored) {
            throw new Exception("No se ha podido conectar a root para realizar el análisis." +
                    "\nRevisa que las creedenciales de acceso sean correctas.");
        }
        operaciones = new OperacionesRevision(conexion);
    }
}
