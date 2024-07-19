package com.transaction.parsers;



import com.transaction.Transaction;

import java.util.logging.Logger;

public class FixedLengthParser implements TransactionParser{
    private static final Logger logger = Logger.getLogger(FixedLengthParser.class.getName());
    private static final int[]  partialLengths = {3, 4, 5};

    public Transaction parseTransaction(String transactionLine) {
        Transaction transaction = null;
        // Validate the input and part length
        if (null == transactionLine || transactionLine.isEmpty()) {
           logger.warning("Invalid input or part length.");
        } else {
            int length = transactionLine.length();
            int currentIndex = 0;
            transaction = new Transaction();
            int attributeSequence = 0;
            for (int partialLength : partialLengths) {
                int endIndex = Math.min(currentIndex + partialLength, length);

                if (currentIndex > length) {
                    break;
                }
                String partial = transactionLine.substring(currentIndex, endIndex);
                currentIndex = currentIndex + partialLength;
                System.out.println(partial);
                attributeSequence++;
                if (attributeSequence == 1) {
                    transaction.setTransactionId(Integer.parseInt(partial) );
                } else if (attributeSequence == 2) {
                    transaction.setTransactionType(partial);
                } else if (attributeSequence == 3) {
                    transaction.setAmount(Double.parseDouble(partial) / 100);
                }

            }
        }
        logger.info(transaction.toString());
        return transaction;
    }
}
