package com.gff.view.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton implements MouseMotionListener {

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private JFrame frame;
    private Font font;
    private ButtonGroup btnGroup;
    private String valueActionCommand;

    public RadioButton(String string) {
        super(string);
        this.initComponents();
    }

    public RadioButton(String string, ButtonGroup btnGroup, Integer align, Font font, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.font = font;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.btnGroup = btnGroup;
        this.initComponents();
    }

    public RadioButton(String string, String valueActionCommand, ButtonGroup btnGroup, Integer align, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        super(string);
        this.valueActionCommand = valueActionCommand;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.btnGroup = btnGroup;
        this.initComponents();
    }

    public RadioButton(String string, String valueActionCommand, ButtonGroup btnGroup) {
        super(string);
        this.btnGroup = btnGroup;
        this.valueActionCommand = valueActionCommand;
        this.initComponents();
    }

    private void initComponents() {
        if (this.valueActionCommand != null && !this.valueActionCommand.isEmpty()) {
            this.setActionCommand(this.valueActionCommand);
        }
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (this.btnGroup != null) {
            this.btnGroup.add(this);
        }
        if (this.font != null) {
            this.setFont(font);
        }
        if (x != null && y != null && w != null && h != null) {
            this.setBounds(x, y, w, h);
        }
        if (this.valueActionCommand != null && !this.valueActionCommand.isEmpty()) {
            this.setActionCommand(this.valueActionCommand);
        }

        this.addMouseMotionListener(this);
        this.setFocusable(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ((JRadioButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}
