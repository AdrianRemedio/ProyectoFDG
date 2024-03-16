package org.adrianremedio.cliente.frontend.common.ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Ventana extends JFrame {
    public JFrame generarVentana(double escalaAncho, double escalaAlto) {
        GraphicsEnvironment entornoGrafico = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle areaVentana = entornoGrafico.getMaximumWindowBounds();
        setSize((int) (escalaAncho * areaVentana.getWidth()), (int) (escalaAlto * areaVentana.getHeight()));
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setAutoRequestFocus(false);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        configurarVentana();
        return this;
    }

    private void configurarVentana() {
        JLayeredPane panelContenedor = new JLayeredPane();
        panelContenedor.setPreferredSize(getSize());

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(223, 245, 255));
        panelFondo.setBounds(0, 0, getWidth(), getHeight());
        panelContenedor.add(panelFondo, 1);

        JPanel panelBarraTitulo = new BarraTitulo(this).generarBarra();
        panelBarraTitulo.setBounds(-25, 10, getWidth(), getHeight() / 10);
        panelContenedor.add(panelBarraTitulo, 0);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelContenedor);
    }
}
