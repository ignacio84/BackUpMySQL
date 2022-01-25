package com.gff.view.gui;

import java.awt.Component;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser {

    private String filters;
    private Boolean multipleFiles;
    private Boolean allFilerOption;
    private Integer selectionMode;
    private Boolean disableNewFolder;
    private Boolean disableInputName;

    public FileChooser(String filters, Integer selectionMode, Boolean multipleFiles, Boolean allFilerOption, Boolean disableNewFolder, Boolean disableInputName) {
        this.filters = filters;
        this.multipleFiles = multipleFiles;
        this.allFilerOption = allFilerOption;
        this.selectionMode = selectionMode;
        this.disableNewFolder = disableNewFolder;
        this.disableInputName = disableInputName;
        this.initComponents();
    }

    private void initComponents() {
        if (this.multipleFiles) {
            //Indicamos que podemos seleccionar varios ficheros
            this.setMultiSelectionEnabled(multipleFiles);
        }
        if (this.selectionMode != null) {
            //Indicamos lo que podemos seleccionar
            this.setFileSelectionMode(selectionMode);
        }

        if (this.allFilerOption != null) {
            //deshabilita opcion todos los archivos
            this.setAcceptAllFileFilterUsed(allFilerOption);
        }
        if (this.disableNewFolder) {
            this.disableNewFolderButton(this);//METODO DESHABILITA BOTON DE CREAR CARPETA NUEVA;
        }
        if (this.filters != null && !this.filters.isEmpty()) {
            String[] filters = this.filters.split(",");
            for (int i = 0; i < filters.length; i++) {
                this.setFileFilter(new FileNameExtensionFilter("." + filters[i], filters[i]));
            }
        }
    }

    private void disableNewFolderButton(Container c) {
        int len = c.getComponentCount();
        for (int i = 0; i < len; i++) {
            Component comp = c.getComponent(i);

            /*DESHABILITA TEXTFIELD DONDE SE ESCRIBE EL DIRECTORIO*/
            if (comp instanceof JTextField && this.disableInputName) {
                JTextField t = (JTextField) comp;
                t.setEditable(false);
            }

            /*DESHABILITA BOTON DE CREAR CARPETA NUEVA*/
            if (comp instanceof JButton) {
                JButton b = (JButton) comp;

                Icon icon = b.getIcon();
                if (icon != null
                        && icon == UIManager.getIcon("FileChooser.newFolderIcon")) {
                    b.setEnabled(false);
                }
            } else if (comp instanceof Container) {
                disableNewFolderButton((Container) comp);
            }
        }
    }
}
