package com.gff.controller;

import com.gff.util.WindowsSystemTray;
import com.gff.view.BackUpView;
import java.awt.Frame;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFileChooser;

public class BackUpController implements WindowStateListener, MouseListener {

    private BackUpView vBackUp;
    private WindowsSystemTray tray;

    public BackUpController() {
        this.vBackUp = new BackUpView("BackUpMySQL", 400, 700);
        this.tray = new WindowsSystemTray(vBackUp);
//        this.enableControls();
        this.addListeners();
        this.vBackUp.setVisible(true);

    }

    private void addListeners() {
        this.vBackUp.addWindowStateListener(this);
        this.vBackUp.getTxtDump().addMouseListener(this);
        this.vBackUp.getTxtSave().addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(vBackUp.getTxtDump())) {
            if (vBackUp.getFchDump().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.vBackUp.getTxtDump().setText(vBackUp.getFchDump().getSelectedFile().getAbsolutePath());
//                vBackUp.getFchDump().setSelectedFile(new File(""));
            }
            return;
        }
         if (vBackUp.getFchSave().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.vBackUp.getTxtSave().setText(vBackUp.getFchSave().getSelectedFile().getAbsolutePath());
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
