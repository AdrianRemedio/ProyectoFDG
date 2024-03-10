package org.adrianremedio.servidor.servidor;

import org.adrianremedio.servidor.comunicacion.Emisor;
import org.adrianremedio.servidor.comunicacion.HiloLector;

import java.net.Socket;
import java.util.HashMap;

public class HiloUsuario extends Thread {
    private Servidor servidor;
    private Socket socketUsuario;
    private HashMap<String, HiloUsuario> hashUsuarios;
    private Emisor emisor;
    private HiloLector hiloLector;

    public HiloUsuario(Servidor servidor, Socket socketUsuario, HashMap<String, HiloUsuario> hashUsuarios, Emisor emisor) {
        this.servidor = servidor;
        this.socketUsuario = socketUsuario;
        this.hashUsuarios = hashUsuarios;
        this.emisor = emisor;
    }

    public void run() {
        hiloLector = new HiloLector(socketUsuario, this);
        hiloLector.start();
        System.out.println("\nPreparado hilo dedicado al usuario...");
        pausarEjecucion();
        //TODO: Se desarrollará más adelante.
    }

    public synchronized void pausarEjecucion(){
        try{
            this.wait();
        }catch (Exception e){
            System.err.println("No se ha podido pausar la ejecución.");
        }
    }

    public synchronized void continuarEjecucion(){
        try{
            this.notify();
        }catch (Exception e){
            System.err.println("No se ha podido continuar la ejecución.");
        }
    }

    public void cerrarConexion(){
        //TODO: Se desarrollará más adelante.
        continuarEjecucion();
    }
}
