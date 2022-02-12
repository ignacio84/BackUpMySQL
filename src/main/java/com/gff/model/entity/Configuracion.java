package com.gff.model.entity;

import com.gff.view.gui.TextPane;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Configuracion {

    private String serverAdress;
    private String nameBD;
    private String userBD;
    private String passwordBD;
    private String mysqlDump;
    private String savePath;
    private String nameFile;
    private LocalDate startDate;
    private String scheduling;
    private String starMinute;
    private String starHour;
    private TextPane textpane;

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
        return savePath + this.nameFile;
    }

    public void buildName() {
        this.nameFile = this.getNameBD()
                .concat("_")
                .concat(this.buildFileName())
                .concat(".sql");
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath.concat(File.separator);
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

    public String getStarMinute() {
        return starMinute;
    }

    public void setStarMinute(String starMinute) {
        this.starMinute = starMinute;
    }

    public String getStarHour() {
        return starHour;
    }

    public void setStarHour(String starHour) {
        this.starHour = starHour;
    }

    public TextPane getTextpane() {
        return textpane;
    }

    public void setTextpane(TextPane textpane) {
        this.textpane = textpane;
    }

    private String buildFileName() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return current.format(formatter).toString();
    }

    @Override
    public String toString() {
        return "Configuracion{" + "serverAdress=" + serverAdress + ", nameBD=" + nameBD + ", userBD=" + userBD + ", passwordBD=" + passwordBD + ", mysqlDump=" + mysqlDump + ", savePath=" + savePath + ", nameFile=" + nameFile + ", startDate=" + startDate + ", scheduling=" + scheduling + ", starMinute=" + starMinute + ", starHour=" + starHour + ", textpane=" + textpane + '}';
    }
}
