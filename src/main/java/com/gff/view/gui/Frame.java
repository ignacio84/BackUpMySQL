package com.gff.view.gui;

import static com.gff.backupmysql.BackUpMySQL.IMG;
import static com.gff.util.Enviroment.*;
import java.awt.Window;
import javax.swing.JFrame;

public class Frame extends JFrame {

    private int height;
    private int width;
    private int maximezed;

    public Frame(String string, Integer height, Integer width) {
        super(APP_TITLE + " - " + string);
        this.height = height;
        this.width = width;
        this.initComponents();
    }

    public Frame(String string, Integer height, Integer width, Integer maximezed) {
        super(APP_TITLE + string);
        this.height = height;
        this.width = width;
        this.maximezed = maximezed;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.setSize(this.height, this.width);
        this.setType(Window.Type.NORMAL);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//PROPIEDAD PARA QUE CUENDO CIERREN LA VENTANA SE TEMINE LA APLICACION DE EJECUTAR
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.maximezed);
        this.setIconImage(IMG.loadBufferImage(APP_LOGO));
        this.setResizable(false);//FRAME NO REDIMENSIONABLE
    }
}
