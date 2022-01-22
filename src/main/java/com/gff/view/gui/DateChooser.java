package com.gff.view.gui;

import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DateChooser extends JDateChooser {

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private Integer align;
    private JFrame frame;
    private Font font;
    private String dateFormat;

    public DateChooser(Integer align, Font font, JFrame frame, Integer x, Integer y, Integer w, Integer h) {
        this.align = align;
        this.font = font;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.frame = frame;
        this.initComponents();
    }

    private void initComponents() {
        this.dateFormat = "yyyy-MM-dd";
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (x != null && y != null && w != null && h != null) {
            this.setBounds(x, y, w, h);
        }
        if (this.font != null) {
            ((JTextField) this.getDateEditor().getUiComponent()).setFont(this.font);
        }
        if (this.align != null) {
            ((JTextField) this.getDateEditor().getUiComponent()).setHorizontalAlignment(this.align);
        }
        this.getDateEditor().setDateFormatString(new SimpleDateFormat(this.dateFormat).toPattern());
        ((JTextField) this.getDateEditor().getUiComponent()).setEditable(false);

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
