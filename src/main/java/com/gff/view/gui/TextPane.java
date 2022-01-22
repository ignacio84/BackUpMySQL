package com.gff.view.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class TextPane extends JTextPane {

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private Color fontColor;
    private Integer align;
    private JFrame frame;
    private Font font;

    public TextPane(Integer align, Font font) {
        this.font = font;
        this.align = align;
        this.initComponents();
    }

    private void initComponents() {
        this.setEditable(false);
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (this.fontColor != null) {
            this.setForeground(this.fontColor);
        }
        if (x != null && y != null && w != null && h != null) {
            this.setBounds(x, y, w, h);
        }
        if (this.align != null) {
            this.align();
        }
        if (this.font != null) {
            this.setFont(font);
        }
    }

    private void align() {
        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, align);
        this.setDocument(document);
//            StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
    }

}
