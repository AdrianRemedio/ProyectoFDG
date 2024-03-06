package org.adrianremedio.servidor;

import org.adrianremedio.servidor.basededatos.Revisor;

public class Principal {
    public static void main(String[] args) {
        new Revisor().revisarConexion();
    }
}