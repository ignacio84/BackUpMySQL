package com.gff.view;

import static com.gff.util.Enviroment.*;
import com.gff.view.gui.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javafx.scene.control.ScrollBar;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class BackUpView extends Frame {

    private Label lblNameBD;
    private TextField txtNameBD;

    private Label lblUser;
    private TextField txtUser;

    private Label lblPass;
    private PasswordField txtPass;

    private Label lblDump;
    private TextField txtDump;

    private Label lblSave;
    private TextField txtSave;

    private Label lblStartDate;
    private JDateChooser dateChooserStart;

    private Label lblScheduling;
    private RadioButton rdbScheOnce;
    private RadioButton rdbScheDaily;
    private RadioButton rdbScheMonthly;
    private ButtonGroup btgScheduling;

    private TextPane txpMessage;
    private JScrollPane scroll;

    private Button btnStart;
    private Button btnStop;

    public BackUpView(String string, Integer height, Integer width) {
        super(string, height, width);
        this.initComponents();
    }

    private void initComponents() {

        this.lblNameBD = new Label("Nombre BD : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 15, 130, 35);
        this.txtNameBD = new TextField(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 15, 215, 35);

        this.lblUser = new Label("Usuario BD : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 65, 130, 35);
        this.txtUser = new TextField(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 65, 215, 35);

        this.lblPass = new Label("Password BD : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 115, 130, 35);
        this.txtPass = new PasswordField(SwingConstants.CENTER, this, 150, 115, 215, 35);

        this.lblDump = new Label("MySQLDump.exe : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 165, 130, 35);
        this.txtDump = new TextField(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 165, 215, 35);

        this.lblSave = new Label("Guardar en : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 215, 130, 35);
        this.txtSave = new TextField(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 215, 215, 35);

        this.lblSave = new Label("Fecha Inicio : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 265, 130, 35);
        this.dateChooserStart = new DateChooser(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 265, 215, 35);

        this.lblScheduling = new Label("Planificación : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 315, 130, 35);
        this.btgScheduling = new ButtonGroup();
        this.rdbScheOnce = new RadioButton("Una vez", btgScheduling, SwingConstants.RIGHT, FONT_BOLD_14, this, 150, 315, 85, 35);
        this.rdbScheDaily = new RadioButton("Diario", btgScheduling, HEIGHT, FONT_BOLD_14, this, 235, 315, 70, 35);
        this.rdbScheMonthly = new RadioButton("Mensual", btgScheduling, HEIGHT, FONT_BOLD_14, this, 301, 315, 100, 35);
        this.rdbScheDaily.setSelected(true);

        this.txpMessage = new TextPane(StyleConstants.ALIGN_CENTER, FONT_PLAIN_15);
        this.scroll = new JScrollPane(this.txpMessage);
        this.add(this.scroll);
        this.scroll.setBounds(20, 365, 350, 250);
        this.repaint();

        this.btnStart = new Button("Iniciar", SwingConstants.CENTER, FONT_BOLD_14, this, 20, 620, 170, 35);
        this.btnStop = new Button("Detener", SwingConstants.CENTER, FONT_BOLD_14, this, 197, 620, 174, 35);

    }
}
