package com.gff.util;

import com.gff.model.entity.Configuracion;
import java.io.IOException;
import java.io.InputStream;

public class BackUp {

    private Configuracion config;
    private String parameters;

    public void setConfig(Configuracion config) {
        this.config = config;
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
                .concat(this.config.getSavePath());
    }

    //EXECUTA RESPALDO
    public void executeBackUp() throws IOException, InterruptedException {
        this.loadParams();
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

}
