package com.gff.view.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Button extends JButton implements MouseMotionListener {

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private Integer align;
    private Font font;
    private Color fontColor;
    private JFrame frame;
    private JDialog dialog;
    private JPanel panel;

    public Button(String string, Integer align, Color fontColor, Font font, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fontColor = fontColor;
        this.dialog = dialog;
        this.align = align;
        this.font = font;
        this.initComponents();
    }

    public Button(String string, Integer align, Color fontColor, Font font, JPanel panel, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fontColor = fontColor;
        this.align = align;
        this.font = font;
        this.panel = panel;
        this.initComponents();
    }

    public Button(String string, Integer align, JPanel panel, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.align = align;
        this.panel = panel;
        this.initComponents();
    }

    public Button(String string, Integer align, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.align = align;
        this.dialog = dialog;
        this.initComponents();
    }

    public Button(String string, Integer align, Font font, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.font = font;
        this.align = align;
        this.frame = frame;
        this.initComponents();
    }

    public Button(String string) {
        super(string);
        this.initComponents();
    }

    private void initComponents() {
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (this.dialog != null) {
            this.dialog.add(this);
        }
        if (this.panel != null) {
            this.panel.add(this);
        }
        if (this.align != null) {
            this.setHorizontalAlignment(this.align);
        }
        if (this.font != null) {
            this.setFont(font);
        }
        if (this.fontColor != null) {
            this.setForeground(fontColor);
        }
        if (this.x != null || y != null || this.w != null || this.h != null) {
            this.setBounds(x, y, w, h);
        }
        this.addMouseMotionListener(this);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setFocusable(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));

    }
}
