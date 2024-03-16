package org.adrianremedio.cliente.frontend.common.funciones;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private final int radio;
    private final int grosor;
    private final Color color;

    public RoundedBorder(int radio, Color color, int grosor) {
        this.radio = radio;
        this.color = color;
        this.grosor = grosor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(grosor));
        graphics.setColor(color);
        graphics.drawRoundRect(x, y, width - 1, height - 1, radio, radio);
        graphics.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radio + grosor, radio + grosor, radio + grosor, radio + grosor);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = radio + grosor;
        return insets;
    }
}
