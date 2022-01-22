package com.gff.util;

import static com.gff.backupmysql.BackUpMySQL.IMG;
import static com.gff.util.Enviroment.APP_LOGO;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.JFrame;

public class WindowsSystemTray {

    private TrayIcon trayIcon;
    private SystemTray tray;
    private JFrame frame;

    public WindowsSystemTray() {
        this.trayIcon = new TrayIcon(IMG.loadBufferImage(APP_LOGO));
        this.tray = SystemTray.getSystemTray();
        this.addListeners();
    }

    public WindowsSystemTray(JFrame frame) {
        this.frame = frame;
        this.trayIcon = new TrayIcon(IMG.loadBufferImage(APP_LOGO));
        this.tray = SystemTray.getSystemTray();
        this.addListeners();
    }

    private void addListeners() {
        this.trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    tray.remove(trayIcon);
                    if (frame != null) {
                        frame.setVisible(true);
                        frame.setState(0);
                    }
                }
            }
        });
    }

    public void addTray() {
        try {
            if (SystemTray.isSupported() && this.validIfAdded() == null) {
                tray.add(this.trayIcon);
            }
        } catch (AWTException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setMessage(String titleMsj, String msj, TrayIcon.MessageType messageType) {
        if (this.validIfAdded() != null) {
//            trayIcon.displayMessage(titleMsj, msj, TrayIcon.MessageType.NONE);
            trayIcon.displayMessage(titleMsj, msj, messageType);
        }
    }

    private TrayIcon validIfAdded() {
        TrayIcon[] trayIcons = tray.getTrayIcons();
        TrayIcon trayIcon = Arrays.stream(trayIcons).filter((ti1) -> ti1 == this.trayIcon).findAny().orElse(null);
        return trayIcon;
    }

}
