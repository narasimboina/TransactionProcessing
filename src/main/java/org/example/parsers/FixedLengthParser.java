package org.example.parsers;

import org.example.Main;
import org.example.Transaction;

import java.util.logging.Logger;

public class FixedLengthParser {
    private static final Logger logger = Logger.getLogger(FixedLengthParser.class.getName());
    private static final int[]  partialLengths = {3, 4, 5, 7};

    public static Transaction parseTransaction(String transactionLine){
        Transaction transaction = null;
        // Validate the input and part length
        if (null == transactionLine || transactionLine.isEmpty()) {
           logger.warning("Invalid input or part length.");
        }else{
            int length = transactionLine.length();
            int currentIndex = 0;
            transaction = new Transaction();
            for (int partialLength : partialLengths) {
                int endIndex = Math.min(currentIndex + partialLength, length);

                if (currentIndex > length) {
                    break;
                }
                String partial = transactionLine.substring(currentIndex, endIndex);
                currentIndex = currentIndex + partialLength;
                System.out.println(partial);
                transaction.setNextProp(partial);
            }
        }
        return transaction;
    }
}
