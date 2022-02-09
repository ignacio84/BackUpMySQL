package com.gff.model.datasource;

import com.gff.model.entity.Configuracion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql {

    private Configuracion config;

    public Connection getConnection() throws SQLException {
        String JDBC_URL = "jdbc:mysql://" + this.config.getServerAdress() + ":3306/" + this.config.getNameBD() + "?serverTimezone=America/Chihuahua&useSSL=false";
        return DriverManager.getConnection(JDBC_URL, this.config.getUserBD(), this.config.getPasswordBD());
    }

    public void close(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }
}
