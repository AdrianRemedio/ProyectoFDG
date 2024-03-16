package org.adrianremedio.cliente.frontend.common.funciones;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

public class Funciones {
    public JLabel cargarImagen(JFrame unaVentana, JLabel labelImagen, String nombreImagen, double nuevoAncho, double nuevoAlto) {
        if (labelImagen == null) {
            labelImagen = new JLabel();

        }
        labelImagen.setIcon(new ImageIcon(
                new ImageIcon("src/main/resources/" + nombreImagen).getImage().getScaledInstance(
                        (int) (unaVentana.getHeight() * nuevoAncho), (int) (unaVentana.getHeight() * nuevoAlto),
                        Image.SCALE_SMOOTH)));
        return labelImagen;
    }

    public void establecerPlaceholder(JTextComponent field, String placeHolder) {
        field.setText(placeHolder);
        if(field instanceof JPasswordField){
            ((JPasswordField) field).setEchoChar((char) 0);
        }
        field.setForeground(Color.GRAY);
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeHolder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
                if (field instanceof JPasswordField) {
                    ((JPasswordField) field).setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeHolder);
                    field.setForeground(Color.GRAY);
                    if(field instanceof JPasswordField){
                        ((JPasswordField) field).setEchoChar((char) 0);
                    }
                }
            }
        };

        field.addFocusListener(focusListener);

        if (field instanceof JPasswordField) {
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (String.valueOf(((JPasswordField) field).getPassword()).equals(placeHolder)) {
                        field.setText("");
                        field.setForeground(Color.BLACK);
                    }else{
                        ((JPasswordField) field).setEchoChar('*');
                    }
                }
            });
        }
    }

    public void gestionarFoco(JFrame ventana, JComponent field){
        ventana.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!field.getBounds().contains(e.getPoint())) {
                    ventana.requestFocus();
                }
            }
        });
    }

}
