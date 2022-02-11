package com.gff.util;

import com.gff.model.entity.Configuracion;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackUp {

    private Configuracion config;
    private String parameters;

    public BackUp(Configuracion config) {
        this.config = config;
        if (this.config != null) {
            this.loadParams();
        }
    }

    private void loadParams() {
        this.parameters = this.config.getMysqlDump()
                .concat(" -h ")
                .concat(this.config.getServerAdress())
                .concat(" -u ")
                .concat(this.config.getUserBD())
                .concat(" -p")
                .concat(this.config.getPasswordBD())
                .concat(" --routines ")
                .concat(this.config.getNameBD())
                .concat(" -r ")
                .concat(this.config.getSavePath())
                .concat(File.separator)
                .concat(config.getNameBD())
                .concat("_")
                .concat(this.buildFileName())
                .concat(".sql");
    }

    //EXECUTA RESPALDO
    public void execute() throws IOException, InterruptedException {
        int status;
        Process exec = Runtime
                .getRuntime()
                .exec(this.parameters);
        status = exec.waitFor();
        if (status == 0) {
//              RESPALDO GENERADO CORRECTAMENTE
        } else {
//            ERROR AL GENERAR EL RESPALDO
            InputStream errorStream = exec.getErrorStream();
            byte[] buffer = new byte[errorStream.available()];
            errorStream.read(buffer);
            String str = new String(buffer);
            throw new IOException(str);
        }
    }

    private String buildFileName() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return current.format(formatter).toString();
    }
}
