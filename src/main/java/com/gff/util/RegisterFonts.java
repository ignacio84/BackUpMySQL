package com.gff.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RegisterFonts {

    private static final String PATH_FONTS = "/fonts/";

    public void RegisterFont() throws Exception {
        ArrayList<String> names = this.getPaths(PATH_FONTS);
        GraphicsEnvironment ge = null;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String name : names) {
            System.out.println("name : " + name);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(name)));
        }
        for (String name : ge.getAvailableFontFamilyNames()) {
            System.out.println(name);
        }
    }

    private ArrayList<String> getNames(String path) throws IOException {
        ArrayList<String> fileNames = new ArrayList<String>();
        InputStream in = this.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String resource;
        while ((resource = br.readLine()) != null) {
            System.out.println(resource);
            fileNames.add(resource);
        }
        return fileNames;
    }

    private ArrayList<String> getPaths(String path) throws IOException {
        ArrayList<String> fileNames = new ArrayList<String>();
        InputStream in = this.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String resource;
        while ((resource = br.readLine()) != null) {
            fileNames.add(path + resource);
        }
        return fileNames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
