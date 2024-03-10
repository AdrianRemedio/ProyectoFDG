package org.adrianremedio.servidor.servidor;

import org.adrianremedio.servidor.comunicacion.Emisor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Servidor {
    private final int puerto = 2003;
    private HashMap<String, HiloUsuario> hashUsuarios;

    public void activarServidor() {
        System.out.println("Preparando servidor...");
        hashUsuarios = new HashMap<>();
        Emisor emisor = new Emisor();
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor preparado.");
            while (true) {
                Socket socketUsuario = serverSocket.accept();
                System.out.println("\nSe ha recibido una conexión.");
                new HiloUsuario(this, socketUsuario, hashUsuarios, emisor).start();
            }
        } catch (Exception ignored) {
            System.err.println("Ha ocurrido un error al establecer la conexión en el puerto especificado.");
        }
    }
}
