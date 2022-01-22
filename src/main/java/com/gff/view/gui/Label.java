package com.gff.view.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Label extends JLabel {

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private Color fontColor;
    private JDialog dialog;
    private JFrame frame;
    private Font font;

    public Label(String string, Integer align, Color fontColor, Font font, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        super(string, align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fontColor = fontColor;
        this.dialog = dialog;
        this.initComponents();
    }

    public Label(String string, Integer align, Font font, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        super(string, align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.font = font;
        this.dialog = dialog;
        this.initComponents();
    }

    public Label(String string, Integer align, JDialog dialog, Integer x, Integer y, Integer w, Integer h) {
        super(string, align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.dialog = dialog;
        this.initComponents();
    }

    public Label(String string, Integer align, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        super(string, align);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.frame = frame;
        this.initComponents();
    }

    public Label(String string, Integer align, Font font, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        super(string, align);
        this.font = font;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.initComponents();
    }

    public Label(String string, Font font) {
        super(string);
        this.font = font;
        this.initComponents();
    }

    public Label(String string, Integer align, Font font, Color fontColor) {
        super(string, align);
//        this.setOpaque(true);
//        this.setBackground(backgroundColor);
        this.font = font;
        this.fontColor = fontColor;
//        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
//        this.setBorder(border);
        this.initComponents();
    }

    public Label(String string, Integer align, Font font) {
        super(string, align);
        this.font = font;
        this.initComponents();
    }

    private void initComponents() {
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (this.dialog != null) {
            this.dialog.add(this);
        }
        if (this.fontColor != null) {
            this.setForeground(this.fontColor);
        }
        if (x != null && y != null && w != null && h != null) {
            this.setBounds(x, y, w, h);
        }
        if (this.font != null) {
            this.setFont(font);
        }
    }
}
