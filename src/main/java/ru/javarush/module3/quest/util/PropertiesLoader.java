package ru.javarush.module3.quest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A class for working with Text Quest application settings (first init and loading settings).
 */
public class PropertiesLoader {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    public static Properties load(String appPath) {
        String APP_CONFIG_PATH = appPath + "WEB-INF\\classes\\quest.properties";
        Properties appProps = new Properties();

        try (FileInputStream fis = new FileInputStream(APP_CONFIG_PATH)) {
            appProps.load(fis);
        } catch (IOException e) {
            logger.error("Problems with loading settings file: " + e.getMessage());
        }
        return appProps;
    }
}
