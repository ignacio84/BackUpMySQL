package com.gff.view.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TextField extends JTextField {

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private Color fontColor;
    private JFrame frame;
    private JDialog dialog;
    private Font font;

    public TextField(Integer align, Color fontColor, Font font, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        this.setHorizontalAlignment(align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.dialog = dialog;
        this.font = font;
        this.setForeground(fontColor);
        this.initComponents();
    }

    public TextField(Integer align, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        this.setHorizontalAlignment(align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.dialog = dialog;
        this.initComponents();
    }

    public TextField(Integer align, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        this.setHorizontalAlignment(align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.frame = frame;
        this.initComponents();
    }

    public TextField(Integer align, Font font, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        this.setHorizontalAlignment(align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.frame = frame;
        this.font = font;
        this.initComponents();
    }

    private void initComponents() {
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (dialog != null) {
            this.dialog.add(this);
        }
        if (fontColor != null) {
            this.setForeground(fontColor);
        }
        if (font != null) {
            this.setFont(font);
        }
        this.setBounds(x, y, w, h);
        this.addListeners();
    }

    private void addListeners() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JTextField) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }
}
