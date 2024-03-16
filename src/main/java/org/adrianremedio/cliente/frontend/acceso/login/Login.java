package org.adrianremedio.cliente.frontend.acceso.login;

import org.adrianremedio.cliente.frontend.common.funciones.Funciones;
import org.adrianremedio.cliente.frontend.common.funciones.RoundedBorder;
import org.adrianremedio.cliente.frontend.common.funciones.RoundedPane;
import org.adrianremedio.cliente.frontend.common.ventana.Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {
    private JFrame ventana;

    public Login() {
        generarVentanaLogin();
    }

    public void generarVentanaLogin() {
        double ancho = 0.675f;
        double alto = 0.85f;

        ventana = new Ventana().generarVentana(ancho, alto);
        JLayeredPane panelContenedor = (JLayeredPane) ventana.getContentPane().getComponent(0);

        JPanel panelIzquierdo = crearPanelIzquierdo();
        JPanel panelLogotipo = crearPanelLogotipo();
        JPanel panelLogin = crearPanelLogin();

        panelContenedor.add(panelIzquierdo, 0);
        panelContenedor.add(panelLogotipo, 0);
        panelContenedor.add(panelLogin, 0);
        ventana.setVisible(true);
        ventana.requestFocusInWindow();
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panelIzquierdo = new RoundedPane(25, 25);
        panelIzquierdo.setBounds(25, 25, (int) (ventana.getWidth() * 0.485), (int) (ventana.getHeight() * 0.925));
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(new Color(41, 171, 226));

        JLabel slogan1 = crearLabelTexto("Dream it.", 60, Color.WHITE);
        JLabel slogan2 = crearLabelTexto("Then Plan it.", 60, Color.WHITE);
        JLabel labelImagen = new Funciones().cargarImagen(ventana, null, "Dreaming guy.png", 0.6, 0.6);

        JPanel panelSlogan = new JPanel();
        panelSlogan.setLayout(new BoxLayout(panelSlogan, BoxLayout.Y_AXIS));
        panelSlogan.setBackground(null);

        panelSlogan.add(slogan1);
        panelSlogan.add(slogan2);

        panelSlogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelIzquierdo.add(Box.createVerticalGlue());
        panelIzquierdo.add(panelSlogan);
        panelIzquierdo.add(labelImagen);
        panelIzquierdo.add(Box.createVerticalGlue());

        return panelIzquierdo;
    }

    private JPanel crearPanelLogotipo() {
        JPanel panelLogotipo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogotipo.setBounds((int) (ventana.getWidth() / 1.6), (int) (ventana.getHeight() * 0.115), (int) (ventana.getWidth() * 0.3), (int) (ventana.getHeight() * 0.105));
        panelLogotipo.setBackground(new Color(223, 245, 255));
        JLabel labelLogotipo = new Funciones().cargarImagen(ventana, null, "Plan It Logo.png", 0.43, 0.10);
        panelLogotipo.add(labelLogotipo);
        return panelLogotipo;
    }

    private JPanel crearPanelLogin() {
        RoundedPane panelContenedorLogin = new RoundedPane(25, 25);
        panelContenedorLogin.setLayout(new BorderLayout());
        panelContenedorLogin.setBackground(Color.WHITE);
        panelContenedorLogin.setBounds((int) (ventana.getWidth() / 1.7), (int) (ventana.getHeight() * 0.25), (int) (ventana.getWidth() * 0.35), (int) (ventana.getHeight() * 0.6));

        JPanel panelLogin = new RoundedPane(25, 25);
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBackground(null);
        panelLogin.setBorder(new EmptyBorder(0, (int) (ventana.getWidth() * 0.025), 0, (int) (ventana.getWidth() * 0.025)));

        JLabel labelMensaje = crearLabelTexto("Iniciar Sesión", 25, Color.DARK_GRAY);
        labelMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelUsuarioField = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUsuarioField.setBackground(null);

        JTextField textoField = new JTextField(18);
        textoField.setFont(new Font("Arial", Font.PLAIN, 12));
        textoField.setBorder(new RoundedBorder(15, Color.GRAY, 2));
        new Funciones().establecerPlaceholder(textoField, "Usuario / Correo electrónico");
        new Funciones().gestionarFoco(ventana, textoField);

        JLabel labelUsuarioIcono = new Funciones().cargarImagen(ventana, null, "User Icon.png", 0.04, 0.04);

        panelUsuarioField.add(labelUsuarioIcono);
        panelUsuarioField.add(textoField);

        JPanel panelClaveField = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelClaveField.setBackground(null);

        JPasswordField claveField = new JPasswordField(18);
        claveField.setFont(new Font("Arial", Font.PLAIN, 12));
        claveField.setBorder(new RoundedBorder(15, Color.GRAY, 2));
        new Funciones().establecerPlaceholder(claveField, "Clave");
        new Funciones().gestionarFoco(ventana, claveField);

        JLabel labelClaveIcono = new Funciones().cargarImagen(ventana, null, "Key Icon.png", 0.04, 0.04);

        panelClaveField.add(labelClaveIcono);
        panelClaveField.add(claveField);

        RoundedPane panelBoton = new RoundedPane(15, 15);
        panelBoton.setBackground(new Color(41, 171, 226));
        panelBoton.setLayout(new BoxLayout(panelBoton, BoxLayout.PAGE_AXIS));
        configurarBoton(panelBoton);

        JLabel labelBoton = crearLabelTexto("INICIAR SESIÓN", 16, Color.WHITE);
        labelBoton.setBorder(new EmptyBorder((int) (ventana.getHeight() * 0.025), (int) (ventana.getHeight() * 0.04), (int) (ventana.getHeight() * 0.025), (int) (ventana.getHeight() * 0.04)));
        labelBoton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBoton.add(labelBoton);

        JLabel labelRegistro = crearLabelTexto("Regístrate", 14, new Color(41, 171, 226));
        configurarBoton(labelRegistro);

        JPanel panelRegistro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelRegistro.setBackground(null);

        panelRegistro.add(crearLabelTexto("¿No tienes cuenta?", 14, Color.GRAY));
        panelRegistro.add(labelRegistro);

        panelLogin.add(Box.createVerticalStrut((int) (ventana.getHeight() * 0.05)));
        panelLogin.add(labelMensaje);
        panelLogin.add(Box.createVerticalStrut((int) (ventana.getHeight() * 0.05)));
        panelLogin.add(panelUsuarioField);
        panelLogin.add(panelClaveField);
        panelLogin.add(Box.createVerticalStrut((int) (ventana.getHeight() * 0.03)));
        panelLogin.add(panelBoton);
        panelLogin.add(Box.createVerticalStrut((int) (ventana.getHeight() * 0.05)));
        panelLogin.add(panelRegistro);

        panelContenedorLogin.add(panelLogin, BorderLayout.CENTER);
        return panelContenedorLogin;
    }

    private JLabel crearLabelTexto(String texto, int size, Color color) {
        JLabel labelSlogan = new JLabel(texto);
        labelSlogan.setFont(new Font("Arial", Font.BOLD, size));
        labelSlogan.setForeground(color);
        return labelSlogan;
    }

    private void configurarBoton(JComponent boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (boton instanceof JPanel) {
                    boton.setBackground(new Color(41, 125, 226));
                } else {
                    boton.setForeground(new Color(41, 125, 226));
                }
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (boton instanceof JPanel) {
                    boton.setBackground(new Color(41, 171, 226));
                } else {
                    boton.setForeground(new Color(41, 171, 226));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO HACER DESARROLLO DE BOTONES
            }
        });
    }

}
