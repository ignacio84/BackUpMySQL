package com.gff.controller;

import com.gff.util.WindowsSystemTray;
import com.gff.view.BackUpView;
import java.awt.Frame;
import java.awt.TrayIcon;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class BackUpController implements WindowStateListener {

    private BackUpView vBackUp;
    private WindowsSystemTray tray;

    public BackUpController() {
        this.vBackUp = new BackUpView("BackUpMySQL", 400, 700);
        this.tray = new WindowsSystemTray(vBackUp);
//        this.enableControls();
        this.addListeners();
        System.out.println("vBackUp" + vBackUp);
        this.vBackUp.setVisible(true);

    }

    private void addListeners() {
        this.vBackUp.addWindowStateListener(this);
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        //VENTANA MINIMIZADA
        if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
            this.tray.addTray();
            this.tray.setMessage("BackUpMySQL", "Executandose en seguno plano..", TrayIcon.MessageType.NONE);
            this.vBackUp.setVisible(false);
        }
    }

}
