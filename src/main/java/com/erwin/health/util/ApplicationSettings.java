package com.erwin.health.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationSettings {

    private static ApplicationSettings instance = null;
    private static Properties prop = null;

    public static ApplicationSettings getInstance(){
        if (instance == null){
            instance = new ApplicationSettings();
            prop = new Properties();
            instance.load();
        }

        return instance;
    }

    private void load() {
        InputStream input = null;
        try {

            input = this.getClass().getClassLoader().getResourceAsStream("application.properties");

            prop.load(input);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String getProperty(String name){
        return prop.getProperty(name);
    }

    public static void main(String args[]){
        String host = ApplicationSettings.getInstance().getProperty("app.kafka_host");
        System.out.println(host);
    }

}
