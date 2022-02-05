package com.gff.view.gui;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser {

    private String filters;
    private Boolean multipleFiles;
    private Boolean allFilerOption;
    private Integer selectionMode;
    private Boolean disableNewFolder;
    private Boolean disableInputName;
    private String startPath;
    private String fileMatchFilter;

    public FileChooser(String filters, String startPath, Integer selectionMode, Boolean multipleFiles, Boolean allFilerOption, Boolean disableNewFolder, Boolean disableInputName) {
        this.filters = filters;
        this.multipleFiles = multipleFiles;
        this.allFilerOption = allFilerOption;
        this.selectionMode = selectionMode;
        this.disableNewFolder = disableNewFolder;
        this.disableInputName = disableInputName;
        this.startPath = startPath;
        this.initComponents();
    }

    public FileChooser(String filters, Integer selectionMode, Boolean multipleFiles, Boolean allFilerOption, Boolean disableNewFolder, Boolean disableInputName) {
        this.filters = filters;
        this.multipleFiles = multipleFiles;
        this.allFilerOption = allFilerOption;
        this.selectionMode = selectionMode;
        this.disableNewFolder = disableNewFolder;
        this.disableInputName = disableInputName;
        this.initComponents();
    }

    public FileChooser(Integer selectionMode, String startPath, String fileMatchFilter, Boolean multipleFiles, Boolean allFilerOption, Boolean disableNewFolder, Boolean disableInputName) {
        this.fileMatchFilter = fileMatchFilter;
        this.startPath = startPath;
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

        //extensiones permitidas
        if (this.filters != null && !this.filters.isEmpty()) {
            String[] filters = this.filters.split(",");
            for (int i = 0; i < filters.length; i++) {
                this.setFileFilter(new FileNameExtensionFilter("." + filters[i], filters[i]));
            }
        }

        if (this.startPath != null && !this.startPath.isEmpty()) {
            //directorio predeterminado
            this.setCurrentDirectory(new File(this.startPath));
        }

        //documento permitido
        if (this.fileMatchFilter != null && !this.fileMatchFilter.isEmpty()) {
            this.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.getName().equals(fileMatchFilter) || f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return fileMatchFilter;
                }
            });
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
