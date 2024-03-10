package org.adrianremedio.servidor.comunicacion;

import org.adrianremedio.servidor.servidor.HiloUsuario;

import java.io.DataInputStream;
import java.net.Socket;

public class HiloLector extends Thread {
    private Socket socketUsuario;
    private HiloUsuario hiloUsuario;
    private DataInputStream inputStream;

    public HiloLector(Socket socketUsuario, HiloUsuario hiloUsuario) {
        this.socketUsuario = socketUsuario;
        this.hiloUsuario = hiloUsuario;
    }

    public void run() {
        try {
            while(true){
                inputStream = new DataInputStream(socketUsuario.getInputStream());
                //TODO Se desarrollará más adelante.
            }
        } catch (Exception ignored) {
            System.err.println("Se ha generado un error.");
            System.err.println("Se procederá al cierre del hilo dedicado al usuario y este propio.");
            hiloUsuario.cerrarConexion();
        }
    }
}
