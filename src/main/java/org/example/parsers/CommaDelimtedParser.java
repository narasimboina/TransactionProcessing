package org.example.parsers;

import org.example.Transaction;

import java.util.logging.Logger;

public class CommaDelimtedParser {
    private static final Logger logger = Logger.getLogger(CommaDelimtedParser.class.getName());


    public static Transaction parseTransaction(String transactionLine){
        Transaction transaction = null;
        // Validate the input and part length
        if (null == transactionLine || transactionLine.isEmpty()) {
            logger.warning("Invalid input or part length.");
        }else{
            String[] partials = transactionLine.split(",");

            transaction = new Transaction();
            for (String partial : partials) {
                transaction.setNextProp(partial);
            }
        }
        return transaction;
    }
}
