package org.adrianremedio.cliente.frontend.common.funciones;

import javax.swing.*;
import java.awt.*;

public class RoundedPane extends JPanel {
    private final int arcWidth;
    private final int arcHeight;
    public RoundedPane(int arcWidth, int arcHeight){
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        graphics.dispose();
    }
}
