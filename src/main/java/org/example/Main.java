package org.example;

import org.example.parsers.CommaDelimtedParser;
import org.example.parsers.FixedLengthParser;
import org.example.util.Database;
import org.example.util.PropertyFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Properties props = PropertyFileReader.readPropertyFile();
        Database.createSampleData();
        List<String> transactionLines = readFile("/Users/narab/Documents/TestFiles/FixedLengthFile.txt");
        List<Transaction> transactions = new ArrayList<Transaction>();
        for(String transactionLine: transactionLines){
            transactions.add(parseTransaction(transactionLine, props.getProperty("parser.type")));
        }
        for(Transaction tr: transactions){
            logger.info(tr.getProp1());
            logger.info(tr.getProp2());
            logger.info(tr.getProp3());
            logger.info(tr.getProp4());
        }
    }

    public static List<String> readFile(String filePath){
        List<String> transactions = new ArrayList<String>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
               transactions.add(line);
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        return transactions;
    }
    private static Transaction parseTransaction(String transaction, String parserType){
        if("FIXED".equals(parserType)){
            return FixedLengthParser.parseTransaction(transaction);
        }else if("CSV".equals(parserType)){
            return CommaDelimtedParser.parseTransaction(transaction);
        }else{
            return null;
        }
    }


    public void processTransaction () {
        PaymentProcessor processor = new PaymentProcessor();

        // Adding customers
        processor.addCustomer("Alice", 100.0);
        processor.addCustomer("Bob", 50.0);

        // Processing payments
        processor.processPayment("Alice", 20.0); // should succeed
        processor.processPayment("Bob", 70.0);   // should fail (insufficient funds)

        // Checking balances
        System.out.println("Alice's balance: $" + processor.getCustomerBalance("Alice"));
        System.out.println("Bob's balance: $" + processor.getCustomerBalance("Bob"));

        // Printing transaction log
        processor.printTransactionLog();
    }
}


