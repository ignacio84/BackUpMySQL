package com.gff.util;

import com.gff.model.entity.Configuracion;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BackUpJob implements Job {

    private Configuracion config;
    private BackUp backUp = new BackUp();

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        this.config = (Configuracion) jec.getJobDetail().getJobDataMap().get("config");
        this.config.buildName();
        this.backUp.setConfig(config);
        this.executeBackUp();
    }

    private void executeBackUp() {
        try {
            this.backUp.executeBackUp();
            this.messageOk();
        } catch (IOException | InterruptedException ex) {
            this.messageError(ex.getMessage());
        }
    }

    private void messageOk() {
        String msj = this.config.getTextpane().getText()
                .concat("RESPALDO GENERADO CORRECTAMENTE\r")
                .concat("FECHA:\r")
                .concat(toDateString())
                .concat("\r")
                .concat("DIRECTORIO:\r")
                .concat(config.getSavePath())
                .concat("\r \r");
        this.config.getTextpane().setText(msj);
    }

    private void messageError(String error) {
        String msj = this.config.getTextpane().getText()
                .concat("ERROR : \r")
                .concat(error)
                .concat("\r \r");
        this.config.getTextpane().setText(msj);
    }

    private String toDateString() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm.ss");
        return current.format(formatter).toString();
    }
}
