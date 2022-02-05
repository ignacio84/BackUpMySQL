package com.gff.controller;

import com.gff.model.entity.Configuracion;
import com.gff.util.WindowsSystemTray;
import com.gff.view.BackUpView;
import java.awt.Frame;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.time.ZoneId;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BackUpController implements WindowStateListener, MouseListener, ActionListener {

    private BackUpView vBackUp;
    private WindowsSystemTray tray;

    private final String E_SERVER = "Favor de ingresar la dirección del servidor.";
    private final String E_NAME_DB = "Favor de ingresar nombre de BD.";
    private final String E_USER_DB = "Favor de ingresar usuario de BD.";
    private final String E_PASS_DB = "Favor de ingresar contraseña de BD.";
    private final String E_DUMP_PATH = "Favor de seleccionar mysql dump.";
    private final String E_SAVE_PATH = "Favor de seleccionar directorio.";
    private final String E_DATE_START = "Favor de seleccionar fecha de inicio.";

    public BackUpController() {
        this.vBackUp = new BackUpView("BackUpMySQL", 400, 700);
//        this.vBackUp.getDateChooserStart().setMinSelectableDate(LocalDate.now().);
        this.tray = new WindowsSystemTray(vBackUp);
//        this.enableControls();
        this.addListeners();
        this.vBackUp.setVisible(true);

    }

    private void addListeners() {
        this.vBackUp.addWindowStateListener(this);
        this.vBackUp.getTxtDumpPath().addMouseListener(this);
        this.vBackUp.getTxtSavePath().addMouseListener(this);
        this.vBackUp.getBtnStart().addActionListener(this);
        this.vBackUp.getBtnStop().addActionListener(this);
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
        if (e.getSource().equals(vBackUp.getTxtDumpPath())) {
            if (vBackUp.getFchDumpPath().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.vBackUp.getTxtDumpPath().setText(vBackUp.getFchDumpPath().getSelectedFile().getAbsolutePath());
//                vBackUp.getFchDump().setSelectedFile(new File(""));
            }
            return;
        }
        if (vBackUp.getFchSavePath().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.vBackUp.getTxtSavePath().setText(vBackUp.getFchSavePath().getSelectedFile().getAbsolutePath());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vBackUp.getBtnStart())) {
            this.start();
        }
    }

    private void start() {
        Configuracion config;
        if (this.vBackUp.getTxtServerAdress().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vBackUp, E_SERVER, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.vBackUp.getTxtNameBD().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vBackUp, E_NAME_DB, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.vBackUp.getTxtUserBD().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vBackUp, E_USER_DB, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.vBackUp.getPwdPassBD().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vBackUp, E_PASS_DB, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.vBackUp.getTxtDumpPath().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vBackUp, E_DUMP_PATH, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.vBackUp.getTxtSavePath().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vBackUp, E_SAVE_PATH, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        config = new Configuracion();
        config.setServerAdress(this.vBackUp.getTxtServerAdress().getText().trim());
        config.setNameBD(this.vBackUp.getTxtNameBD().getText().trim());
        config.setUserBD(this.vBackUp.getTxtUserBD().getText().trim());
        config.setPasswordBD(this.vBackUp.getPwdPassBD().getText().trim());
        config.setMysqlDump(this.vBackUp.getTxtDumpPath().getText().trim());
        config.setSavePath(this.vBackUp.getTxtSavePath().getText().trim());
        config.setStartDate(this.vBackUp.getDateChooserStartDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        config.setScheduling(this.vBackUp.getBtgScheduling().getSelection().getActionCommand());
        System.out.println(config.toString());
    }

}
