package com.transaction.util;

import org.junit.Test;
import java.io.File;
import java.util.Properties;
import static org.junit.Assert.*;

public class PropertyFileReaderTest {

    @Test
    public void testReadPropertyFile_Successful() {
        // Call the method under test
        Properties properties = PropertyFileReader.readPropertyFile();

        // Assertions
        assertNotNull(properties);
        assertEquals("CUSTOM", properties.getProperty("parser.type")); // Adjust based on your properties file content
        assertEquals("true", properties.getProperty("db.enabled"));
    }


    @Test
    public void testReadPropertyFile_FileNotFoundException() {
        // Move the original config file temporarily
        File originalFile = new File("config.properties");
        File tempFile = new File("src/test/resources/config.properties");

        // Rename the file to simulate FileNotFoundException
        boolean fileMoved = originalFile.renameTo(tempFile);

        try {
            // Call the method under test
            Properties properties = PropertyFileReader.readPropertyFile();

            // Assertions
            assertNotNull(properties); // Handle the exception gracefully, return empty properties or log appropriately
        } finally {
            // Restore the original file name
            if (fileMoved) {
                tempFile.renameTo(originalFile);
            }
        }
    }

}

