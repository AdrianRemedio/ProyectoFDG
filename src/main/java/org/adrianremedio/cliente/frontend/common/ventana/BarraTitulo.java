package org.adrianremedio.cliente.frontend.common.ventana;

import org.adrianremedio.cliente.frontend.common.funciones.Funciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarraTitulo extends JPanel {
    private static int xInicial;
    private static int yInicial;

    private final JFrame ventana;

    protected BarraTitulo(JFrame ventana) {
        this.ventana = ventana;
    }

    public JPanel generarBarra() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(new Color(223, 245, 255));
        agregarMovimiento();

        JLabel botonMinimizar = new Funciones().cargarImagen(ventana, null, "Minimizar Iddle.png", 0.02, 0.02);
        configurarBoton(botonMinimizar, "botonMinimizar");
        add(botonMinimizar);

        add(Box.createHorizontalStrut(5));

        JLabel botonCerrar = new Funciones().cargarImagen(ventana, null, "Cerrar Iddle.png", 0.02, 0.02);
        configurarBoton(botonCerrar, "botonCerrar");
        add(botonCerrar);
        return this;
    }

    private void agregarMovimiento() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xInicial = e.getX();
                yInicial = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xFinal = ventana.getX() + (e.getX() - xInicial);
                int yFinal = ventana.getY() + (e.getY() - yInicial);
                ventana.setLocation(xFinal, yFinal);
            }
        });
    }

    private void configurarBoton(JLabel boton, String nombreBoton) {
        switch (nombreBoton) {
            case "botonCerrar":
                boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        new Funciones().cargarImagen(ventana, boton, "Cerrar Pressed.png", 0.02, 0.02);
                        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        new Funciones().cargarImagen(ventana, boton, "Cerrar Iddle.png", 0.02, 0.02);
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.exit(0);
                    }
                });
                break;
            case "botonMinimizar":
                boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        new Funciones().cargarImagen(ventana, boton, "Minimizar Pressed.png", 0.02, 0.02);
                        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        new Funciones().cargarImagen(ventana, boton, "Minimizar Iddle.png", 0.02, 0.02);
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ventana.setState(JFrame.ICONIFIED);
                    }
                });
                break;
        }
    }
}
