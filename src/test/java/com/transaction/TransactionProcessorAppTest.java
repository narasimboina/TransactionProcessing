
package com.transaction;
import com.transaction.parsers.CustomParser;
import com.transaction.parsers.ParserFactory;
import com.transaction.parsers.TransactionParser;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class TransactionProcessorAppTest {
        private static final String CUSTOM_FILE_PATH = "/Users/narab/Documents/TestFiles/custom.txt";
         private static final String FIXED_FILE_PATH = "/Users/narab/Documents/TestFiles/FixedLengthFile.txt";
        private static final String CSV_FILE_PATH = "/Users/narab/Documents/TestFiles/Transactions.txt";

        @Test
        public void testInitialize() {
            // Mocking PropertyFileReader.readPropertyFile() to return a Properties object

            TransactionParser parser = ParserFactory.getParser("CUSTOM");
            List<String> transactionLines = TransactionProcessorApp.readFile(CUSTOM_FILE_PATH);

            Map<Integer, Transaction> transactionMap = TransactionProcessorApp.initialize(transactionLines, parser);

            assertNotNull(transactionMap);
            assertFalse(transactionMap.isEmpty());
            assertEquals(transactionLines.size(), transactionMap.size());

            // Optionally, you can add more assertions based on expected values in transactionMap
        }
    @Test
    public void testInitializeInvalid()  {
        // Mocking PropertyFileReader.readPropertyFile() to return a Properties object

        TransactionParser parser = ParserFactory.getParser("CUSTOM");
        List<String> transactionLines = TransactionProcessorApp.readFile("/Users/narab/Documents/TestFiles/custom_bad.txt");

        Map<Integer, Transaction> transactionMap = TransactionProcessorApp.initialize(transactionLines, parser);

        assertNotNull(transactionMap);
        assertNotEquals(transactionLines.size(), transactionMap.size());

        // Optionally, you can add more assertions based on expected values in transactionMap
    }

        @Test
        public void testReadFile() {
            List<String> transactions = TransactionProcessorApp.readFile(CUSTOM_FILE_PATH);

            assertNotNull(transactions);
            assertFalse(transactions.isEmpty());
            // Optionally, you can assert specific lines or sizes depending on your test file
        }

        @Test
        public void testParseTransaction() throws Exception {
            // Testing for each parser type
            String transactionLine = "Sample transaction line";



            TransactionParser parser = ParserFactory.getParser("CSV");
            Transaction csvTransaction = parser.parseTransaction(transactionLine);
            assertNotNull(csvTransaction);
            // Add more assertions for csvTransaction properties
            transactionLine = "N810020034A6QWERTYD71000034";
            parser = ParserFactory.getParser("CUSTOM");
            Transaction customTransaction = parser.parseTransaction(transactionLine);
            assertNotNull(customTransaction);

            // Add more assertions for customTransaction properties
            parser = ParserFactory.getParser("INVALID");
            Transaction invalidTransaction =parser.parseTransaction(transactionLine);
            assertNotNull(invalidTransaction);
        }

    @Test(expected = Exception.class)
    public void testParseTransactionWithException() throws Exception {
        String transactionLine = "Sample transaction line";
        TransactionParser parser = ParserFactory.getParser("FIXED");
        Transaction fixedTransaction = parser.parseTransaction(transactionLine);
        assertNotNull(fixedTransaction);
        // Add more assertions for fixedTransaction properties
    }


}