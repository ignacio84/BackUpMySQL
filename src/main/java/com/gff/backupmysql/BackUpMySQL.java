package com.gff.backupmysql;

import com.gff.controller.BackUpController;
import com.gff.util.LoadImages;
import com.gff.util.RegisterFonts;
import com.gff.util.ThemePropierties;

public class BackUpMySQL {

    public static LoadImages IMG;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        try {
            new RegisterFonts();
            IMG = new LoadImages();
            new ThemePropierties();
            new BackUpController();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
