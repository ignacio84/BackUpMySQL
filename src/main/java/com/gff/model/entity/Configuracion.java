package com.gff.model.entity;

import java.time.LocalDate;
import java.util.Date;

public class Configuracion {

    private String serverAdress;
    private String nameBD;
    private String userBD;
    private String passwordBD;
    private String mysqlDump;
    private String savePath;
    private LocalDate startDate;
    private String scheduling;

    public String getNameBD() {
        return nameBD;
    }

    public void setNameBD(String nameBD) {
        this.nameBD = nameBD;
    }

    public String getUserBD() {
        return userBD;
    }

    public void setUserBD(String userBD) {
        this.userBD = userBD;
    }

    public String getPasswordBD() {
        return passwordBD;
    }

    public void setPasswordBD(String passwordBD) {
        this.passwordBD = passwordBD;
    }

    public String getMysqlDump() {
        return mysqlDump;
    }

    public void setMysqlDump(String mysqlDump) {
        this.mysqlDump = mysqlDump;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getScheduling() {
        return scheduling;
    }

    public void setScheduling(String scheduling) {
        this.scheduling = scheduling;
    }

    public String getServerAdress() {
        return serverAdress;
    }

    public void setServerAdress(String serverAdress) {
        this.serverAdress = serverAdress;
    }

    @Override
    public String toString() {
        return "Configuracion{" + "serverAdress=" + serverAdress + ", nameBD=" + nameBD + ", userBD=" + userBD + ", passwordBD=" + passwordBD + ", mysqlDump=" + mysqlDump + ", savePath=" + savePath + ", startDate=" + startDate + ", scheduling=" + scheduling + '}';
    }
}
