package com.gff.util;

import com.gff.model.entity.Configuracion;
import java.io.IOException;
import java.io.InputStream;

public class BackUp {

    private Configuracion config;
    private String parameters;

    public BackUp(Configuracion config) {
        this.config = config;
        if (this.config != null) {
            this.createParams();
        }
    }

    private void createParams() {
        this.parameters = this.config.getMysqlDump()
                .concat(" -u ")
                .concat(this.config.getUserBD())
                .concat(" -p ")
                .concat(this.config.getPasswordBD())
                .concat(" --databases ")
                .concat(this.config.getNameBD())
                .concat(" --triggers --single-transaction --routines ")
                .concat(" --result-file=dump.sql");
        System.out.println(this.parameters);
    }

    public void execute() throws IOException, InterruptedException {
        Process exec = Runtime
                .getRuntime()
                .exec(this.parameters);
        //Wait for the command to complete, and check if the exit value was 0 (success)
        if (exec.waitFor() == 0) {
            //normally terminated, a way to read the output
            InputStream inputStream = exec.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            String str = new String(buffer);
            System.out.println(str);
        } else {
            // abnormally terminated, there was some problem
            //a way to read the error during the execution of the command
            InputStream errorStream = exec.getErrorStream();
            byte[] buffer = new byte[errorStream.available()];
            errorStream.read(buffer);

            String str = new String(buffer);
            System.out.println(str);
            throw new IOException(str);
        }
    }
}
