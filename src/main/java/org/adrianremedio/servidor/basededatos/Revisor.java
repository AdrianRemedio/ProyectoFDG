package org.adrianremedio.servidor.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Revisor {
    //Creedenciales usuario root:
    private String usuarioRoot = "root";
    private String claveRoot = "MANAGER";

    //Nombre base de datos:
    private String nombreBBDD = "ActiveMeDatabase";

    //Creedenciales usuario dedicado al servidor:
    private String usuario = "ActiveMeServer";
    private String clave = "proyectoFDG";

    //Ruta JDBC:
    private String rutaJDBC = "jdbc:mysql://localhost:3306/";

    //Parámetros globales:
    private Connection conexion = null;
    private Comprobaciones comprobaciones;

    /**
     * Método que realiza una serie de comprobaciones para asegurar de que
     * el funcionamiento es correcto antes de activar el servidor.
     */
    public void revisarConexion() {
        System.out.println("\nRealizando comprobación inicial...");
        try {
            //Intenta conectar como root
            if (!realizarConexion(rutaJDBC, "", usuarioRoot, claveRoot)) {
                throw new Exception("No se ha podido conectar a root para realizar el análisis." +
                        "\nRevisa que las creedenciales de acceso sean correctas.");
            }

            //Comprueba si el usuario dedicado al servidor existe.
            System.out.print("Comprobando existencia de " + usuario + "... ");
            if (!comprobaciones.verificarExistenciaUsuario(usuario)) {
                comprobaciones.crearUsuario(usuario, clave);
            }
            System.out.println("FINALIZADO.");

            //Comprueba si la base de datos existe.
            System.out.print("Comprobando existencia de " + nombreBBDD + "... ");
            if (!comprobaciones.verificarExistenciaBBDD(nombreBBDD)) {
                comprobaciones.crearBaseDeDatos(nombreBBDD);
            }
            System.out.println("FINALIZADO.");

            //Comprueba si las tablas necesarias existen.
            System.out.print("Comprobando estado de las tablas... ");
            comprobaciones.revisarTablas(nombreBBDD);
            System.out.println("FINALIZADO.");

            //Comprueba si el usuario dedicado al servidor posee los privilegios necesarios.
            System.out.print("Comprobando privilegios de " + usuario + "... ");
            comprobaciones.otorgarPrivilegiosUsuario(nombreBBDD, usuario);
            System.out.println("FINALIZADO.");

            //Cierra la conexión como root.
            conexion.close();

            //Intenta conectar a la base de datos con el usuario dedicado al servidor.
            System.out.print("Conectando a " + nombreBBDD + " con " + usuario + "... ");
            if (!realizarConexion(rutaJDBC, nombreBBDD, usuario, clave)) {
                throw new Exception("No se ha podido conectar a " + usuario + " para continuar el análisis." +
                        "\nRevisa que las creedenciales de acceso sean correctas.");
            }
            System.out.println("FINALIZADO.");

            System.out.println("\nComprobación inicial completada.\n");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Método que intenta conectar al la base de datos.
     *
     * @param rutaJDBC   Ruta JDBC.
     * @param nombreBBDD Nombre de la base de datos.
     * @param usuario    Usuario al que se va a intentar conectar.
     * @param clave      Clave de acceso al usuario.
     * @return true si consigue conectar; false si no lo consigue.
     */
    private boolean realizarConexion(String rutaJDBC, String nombreBBDD, String usuario, String clave) {
        try {
            conexion = DriverManager.getConnection(rutaJDBC + nombreBBDD, usuario, clave);
            comprobaciones = new Comprobaciones(conexion);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
