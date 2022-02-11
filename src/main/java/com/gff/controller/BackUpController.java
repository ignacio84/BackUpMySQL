package com.gff.controller;

import com.gff.model.datasource.ConexionMySql;
import com.gff.model.entity.Configuracion;
import com.gff.util.BackUp;
import com.gff.util.BackUpJob;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class BackUpController implements WindowStateListener, MouseListener, ActionListener {

    private BackUpView vBackUp;
    private WindowsSystemTray tray;
    private ConexionMySql connection;
    private BackUp backUp;
    private Configuracion config;

    private final String E_SERVER = "Favor de ingresar la dirección del servidor.";
    private final String E_NAME_DB = "Favor de ingresar nombre de BD.";
    private final String E_USER_DB = "Favor de ingresar usuario de BD.";
    private final String E_PASS_DB = "Favor de ingresar contraseña de BD.";
    private final String E_DUMP_PATH = "Favor de seleccionar mysql dump.";
    private final String E_SAVE_PATH = "Favor de seleccionar directorio.";
    private final String E_DATE_START = "Favor de seleccionar fecha de inicio.";
    private final String E_TIME_START = "Favor de seleccionar hora de inicio.";

    public BackUpController() {
        this.vBackUp = new BackUpView("BackUpMySQL", 400, 700);
        this.tray = new WindowsSystemTray(vBackUp);
//        this.enableControls();
        this.addListeners();
        this.connection = new ConexionMySql();
        this.vBackUp.setVisible(true);

    }

    private void addListeners() {
        this.vBackUp.addWindowStateListener(this);
        this.vBackUp.getTxtDumpPath().addMouseListener(this);
        this.vBackUp.getTxtSavePath().addMouseListener(this);
        this.vBackUp.getBtnStart().addActionListener(this);
        this.vBackUp.getBtnStop().addActionListener(this);
        this.vBackUp.getDateChooserStartDate().setMinSelectableDate(Date.from(LocalDate.now().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
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
        if (this.vBackUp.getDateChooserStartDate().getDate() == null) {
            JOptionPane.showMessageDialog(this.vBackUp, E_DATE_START, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.vBackUp.getSprTime() == null) {
            JOptionPane.showMessageDialog(this.vBackUp, E_TIME_START, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.config = new Configuracion();
        this.config.setServerAdress(this.vBackUp.getTxtServerAdress().getText().trim());
        this.config.setNameBD(this.vBackUp.getTxtNameBD().getText().trim());
        this.config.setUserBD(this.vBackUp.getTxtUserBD().getText().trim());
        this.config.setPasswordBD(this.vBackUp.getPwdPassBD().getText().trim());
        this.config.setMysqlDump(this.vBackUp.getTxtDumpPath().getText().trim());
        this.config.setSavePath(this.vBackUp.getTxtSavePath().getText().trim());
        this.config.setStartDate(this.vBackUp.getDateChooserStartDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.config.setScheduling(this.vBackUp.getBtgScheduling().getSelection().getActionCommand());
        this.config.setStarTime(this.vBackUp.getSprTime().getValue().toString());
        this.connection.setConfig(config);
        this.connectionTest();
    }

    private void connectionTest() {
        try {
            java.sql.Connection conn = this.connection.getConnection();
            if (conn != null && !conn.isClosed()) {
                JOptionPane.showMessageDialog(this.vBackUp, "Conexión OK", "Error", JOptionPane.ERROR_MESSAGE);
                this.connection.close(conn);
                if (conn.isClosed()) {
                    this.backUp = new BackUp(this.config);
//                    this.executeBackUp();
                    Scheduling();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.vBackUp, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    private void executeBackUp() {
//        try {
//            this.backUp.execute();
//        } catch (IOException | InterruptedException ex) {
//            JOptionPane.showMessageDialog(this.vBackUp, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    private void Scheduling() {
        BackUpJob back = new BackUpJob();
        back.setConfig(config);
        try {
            JobDetail job = JobBuilder.newJob(BackUpJob.class)
                    .withIdentity("backUp")
                    .build();

//            job.getJobDataMap().put("config", this.config);//SE ENVIA OBJETO AL A LA IMPLEMENTACION DEL LA INTERFACE JOB
            
            // Crear instancia de Trigger
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("backUpTrigger", "group1")
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(5).repeatForever())
                    .build();

            // Crea una instancia de Scheduler
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);// enlazar JobDetail y Trigger
        } catch (SchedulerException ex) {
            Logger.getLogger(BackUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
