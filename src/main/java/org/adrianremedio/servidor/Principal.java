package org.adrianremedio.servidor;

import org.adrianremedio.servidor.basededatos.Revisor;
import org.adrianremedio.servidor.servidor.Servidor;

import java.net.ServerSocket;

public class Principal {
    public static void main(String[] args) {
        new Revisor().revisarConexion();
        new Servidor().activarServidor();
    }
}