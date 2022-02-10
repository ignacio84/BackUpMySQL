package com.gff.view;

import static com.gff.util.Enviroment.*;
import com.gff.view.gui.*;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.text.DateFormatter;
import javax.swing.text.StyleConstants;

public class BackUpView extends Frame {

    private final String PATH_DUMP = "C:\\Program Files\\MySQL\\";
    private final String FILE_NAME = "mysqldump.exe";

    private Label lblServerAdress;
    private TextField txtServerAdress;

    private Label lblNameBD;
    private TextField txtNameBD;

    private Label lblUserBD;
    private TextField txtUserBD;

    private Label lblPassBD;
    private PasswordField pwdPassBD;

    private Label lblDumpPath;
    private TextField txtDumpPath;
    private FileChooser fchDumpPath;

    private Label lblSavePath;
    private TextField txtSavePath;
    private JFileChooser fchSavePath;

    private Label lblStartDate;
    private DateChooser dateChooserStartDate;

    private Label lblTime;
    private SpinnerTime sprTime;

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
        this.fchDumpPath = new FileChooser(JFileChooser.FILES_ONLY, PATH_DUMP, FILE_NAME, false, false, true, true);
        this.fchSavePath = new FileChooser("sql", JFileChooser.DIRECTORIES_ONLY, false, false, true, true);

        this.lblServerAdress = new Label("Dirección IP : ", SwingConstants.RIGHT, this, 15, 15, 130, 35);
        this.txtServerAdress = new TextField(SwingConstants.CENTER, this, 150, 15, 215, 35);

        this.lblNameBD = new Label("Nombre BD : ", SwingConstants.RIGHT, this, 15, 65, 130, 35);
        this.txtNameBD = new TextField(SwingConstants.CENTER, this, 150, 65, 215, 35);

        this.lblUserBD = new Label("Usuario BD : ", SwingConstants.RIGHT, this, 15, 115, 130, 35);
        this.txtUserBD = new TextField(SwingConstants.CENTER, this, 150, 115, 215, 35);

        this.lblPassBD = new Label("Contraseña BD : ", SwingConstants.RIGHT, this, 15, 165, 130, 35);
        this.pwdPassBD = new PasswordField(SwingConstants.CENTER, this, 150, 165, 215, 35);

        this.lblDumpPath = new Label("MySQLDump.exe : ", SwingConstants.RIGHT, this, 15, 215, 130, 35);
        this.txtDumpPath = new TextField(SwingConstants.CENTER, false, this, 150, 215, 215, 35);

        this.lblSavePath = new Label("Guardar en : ", SwingConstants.RIGHT, this, 15, 265, 130, 35);
        this.txtSavePath = new TextField(SwingConstants.CENTER, false, this, 150, 265, 215, 35);

        this.lblSavePath = new Label("Fecha Inicio : ", SwingConstants.RIGHT, this, 15, 315, 130, 35);
        this.dateChooserStartDate = new DateChooser(SwingConstants.CENTER, this, 150, 315, 215, 35);

        this.lblTime = new Label("Horario Inicio : ", SwingConstants.RIGHT, this, 15, 365, 130, 35);
        this.sprTime = new SpinnerTime(this, SwingConstants.CENTER, 150, 365, 215, 35);

        this.lblScheduling = new Label("Planificación : ", SwingConstants.RIGHT, this, 15, 415, 130, 35);
        this.btgScheduling = new ButtonGroup();
        this.rdbScheOnce = new RadioButton("Una vez", "one", btgScheduling, this, 150, 415, 85, 35);
        this.rdbScheDaily = new RadioButton("Diario", "daily", btgScheduling, this, 235, 415, 70, 35);
        this.rdbScheMonthly = new RadioButton("Mensual", "montly", btgScheduling, this, 301, 415, 100, 35);
        this.rdbScheDaily.setSelected(true);

        this.txpMessage = new TextPane(StyleConstants.ALIGN_CENTER, FONT_PLAIN_15);
        this.scroll = new JScrollPane(this.txpMessage);
        this.add(this.scroll);
        this.scroll.setBounds(20, 455, 350, 160);
        this.repaint();

        this.btnStart = new Button("Iniciar", SwingConstants.CENTER, FONT_BOLD_14, this, 20, 620, 170, 35);
        this.btnStop = new Button("Detener", SwingConstants.CENTER, FONT_BOLD_14, this, 197, 620, 174, 35);

    }

    public TextField getTxtServerAdress() {
        return txtServerAdress;
    }

    public TextField getTxtNameBD() {
        return txtNameBD;
    }

    public TextField getTxtUserBD() {
        return txtUserBD;
    }

    public PasswordField getPwdPassBD() {
        return pwdPassBD;
    }

    public TextField getTxtDumpPath() {
        return txtDumpPath;
    }

    public FileChooser getFchDumpPath() {
        return fchDumpPath;
    }

    public TextField getTxtSavePath() {
        return txtSavePath;
    }

    public JFileChooser getFchSavePath() {
        return fchSavePath;
    }

    public TextPane getTxpMessage() {
        return txpMessage;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public Button getBtnStop() {
        return btnStop;
    }

    public ButtonGroup getBtgScheduling() {
        return btgScheduling;
    }

    public DateChooser getDateChooserStartDate() {
        return dateChooserStartDate;
    }

    public SpinnerTime getSprTime() {
        return sprTime;
    }
}