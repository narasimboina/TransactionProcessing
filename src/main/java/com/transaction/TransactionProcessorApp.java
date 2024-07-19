package com.transaction;


import com.transaction.parsers.ParserFactory;
import com.transaction.parsers.TransactionParser;
import com.transaction.util.PropertyFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TransactionProcessorApp {
    private static final Logger logger = Logger.getLogger(TransactionProcessorApp.class.getName());

    public static void main(String[] args) throws Exception {

        Properties props = PropertyFileReader.readPropertyFile();
        logger.info("props:" + props.toString());
        System.out.println("props:" + props.toString());
        List<String> transactionLines = readFile("/Users/narab/Documents/TestFiles/custom.txt");
        String parserType = props.getProperty("parser.type");
        TransactionParser parser = ParserFactory.getParser(parserType);
        initialize(transactionLines, parser);
    }

    public static Map<Integer, Transaction> initialize(List<String> transactionLines, TransactionParser parser) {
        Map<Integer, Transaction> transactionMap = new HashMap<>();
        for (String transactionLine : transactionLines) {
            Transaction transaction = null;
            try {
                transaction = parser.parseTransaction(transactionLine);
            } catch (Exception e) {
                logger.info("Ignored Transaction:" + transactionLine);
            }
            if (null != transaction) {
                transactionMap.put(transaction.getTransactionId(), transaction);
            }
        }
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            logger.info(entry.toString());
        }
        return transactionMap;
    }

    public static List<String> readFile(String filePath) {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        return transactions;
    }


    private static Map<String, Customer> parseFile(String file) throws IOException {

        Map<String, Customer> customerMap = Files.lines(Paths.get(file)).map(line -> line.split(",")).
                collect(Collectors.toMap(data -> data[0], data -> new Customer(data[0], Double.parseDouble(data[1]))));
        return customerMap;

    }
}


