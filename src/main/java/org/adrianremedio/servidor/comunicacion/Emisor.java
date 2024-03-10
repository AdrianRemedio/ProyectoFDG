package org.adrianremedio.servidor.comunicacion;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Emisor {
    private final Semaphore accesoMetodo = new Semaphore(1, true);

    public void enviarDatos(Socket socketCliente, ArrayList<String> arrayDatos) {
        try {
            accesoMetodo.acquire();
            DataOutputStream outputStream = new DataOutputStream(socketCliente.getOutputStream());
            for (String unDato : arrayDatos) {
                outputStream.writeUTF(unDato);
                outputStream.flush();
            }
        } catch (Exception ignored) {
            System.err.println("Ha ocurrido un error durante el envío de información.");
        }
        accesoMetodo.release();
    }
}
