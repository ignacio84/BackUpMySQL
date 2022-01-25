package com.gff.view;

import static com.gff.util.Enviroment.*;
import com.gff.view.gui.*;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.text.StyleConstants;

public class BackUpView extends Frame {

    private Label lblNameBD;
    private TextField txtNameBD;

    private Label lblUser;
    private TextField txtUser;

    private Label lblPass;
    private PasswordField txtPass;

    private Label lblDump;
    private TextField txtDump;
    private FileChooser fchDump;

    private Label lblSave;
    private TextField txtSave;
    private JFileChooser fchSave;

    private Label lblStartDate;
    private DateChooser dateChooserStart;

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
        this.fchDump = new FileChooser("exe", JFileChooser.FILES_ONLY, false, false, true, true);
        this.fchSave = new FileChooser("sql", JFileChooser.DIRECTORIES_ONLY, false, false, true, true);

        this.lblNameBD = new Label("Nombre BD : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 15, 130, 35);
        this.txtNameBD = new TextField(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 15, 215, 35);

        this.lblUser = new Label("Usuario BD : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 65, 130, 35);
        this.txtUser = new TextField(SwingConstants.CENTER, FONT_BOLD_14, this, 150, 65, 215, 35);

        this.lblPass = new Label("Password BD : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 115, 130, 35);
        this.txtPass = new PasswordField(SwingConstants.CENTER, this, 150, 115, 215, 35);

        this.lblDump = new Label("MySQLDump.exe : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 165, 130, 35);
        this.txtDump = new TextField(SwingConstants.CENTER, FONT_BOLD_14, false, this, 150, 165, 215, 35);

        this.lblSave = new Label("Guardar en : ", SwingConstants.RIGHT, FONT_BOLD_14, this, 15, 215, 130, 35);
        this.txtSave = new TextField(SwingConstants.CENTER, FONT_BOLD_14, false, this, 150, 215, 215, 35);

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

    public TextField getTxtDump() {
        return txtDump;
    }

    public JFileChooser getFchDump() {
        return fchDump;
    }

    public TextField getTxtSave() {
        return txtSave;
    }

    public JFileChooser getFchSave() {
        return fchSave;
    }
}
