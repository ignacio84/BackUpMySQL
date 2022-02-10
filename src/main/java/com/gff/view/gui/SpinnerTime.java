package com.gff.view.gui;

import static com.gff.util.Enviroment.FONT_BOLD_14;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class SpinnerTime extends JSpinner {
    
    private JFrame frame;
    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private Integer align;
    
    public SpinnerTime(JFrame frame, Integer align, Integer x, Integer y, Integer w, Integer h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.align = align;
        this.frame = frame;
        this.initComponents();
    }
    
    private void initComponents() {
        if (this.frame != null) {
            this.frame.add(this);
        }
        if (x != null && y != null && w != null && h != null) {
            this.setBounds(x, y, w, h);
        }
        
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.HOUR_OF_DAY);//obtiene la hora actual
        this.setModel(model);
        //APLICA FORMATO 24 HRS
        JSpinner.DateEditor edt = new JSpinner.DateEditor(this, "HH:mm");
        this.setEditor(edt);

        //APLICA FORMATO AL TEXTO DENTRO DEL SPINNER
        JComponent editor = this.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
            spinnerEditor.getTextField().setFont(FONT_BOLD_14);
        }
    }
}
