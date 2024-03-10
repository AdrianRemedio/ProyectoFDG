package org.adrianremedio.servidor.seguridad;

import java.security.MessageDigest;

public class Cifrado {
    private String cifrarDato(String dato) {
        try {
            MessageDigest cifrador = MessageDigest.getInstance("SHA-256");
            byte[] datoBytes = cifrador.digest(dato.getBytes());
            StringBuilder nuevoDato = new StringBuilder();
            for (byte unByte : datoBytes) {
                nuevoDato.append(String.format("%02x", unByte));
            }
            return nuevoDato.toString();
        } catch (Exception ignored) {
            System.err.println("Ha ocurrido un error al cifrar la información.");
        }
        return null;
    }
}
