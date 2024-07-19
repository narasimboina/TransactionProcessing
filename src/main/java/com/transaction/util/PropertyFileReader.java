package com.transaction.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyFileReader {
    private static final Logger logger = Logger.getLogger(PropertyFileReader.class.getName());

    public static Properties readPropertyFile() {
       Properties prop = new Properties();
       InputStream input = null;
       try {
           // Load the property file
           input = new FileInputStream("config.properties");
           // Load properties from input stream
           prop.load(input);
       } catch (IOException  ex) {
           logger.log(Level.SEVERE, ex.getMessage());
       } finally {
           if (input != null) {
               try {
                   input.close();
               } catch ( IOException e) {
                   logger.log(Level.SEVERE, e.getMessage());
               }
           }
       }
       return prop;
   }
}
