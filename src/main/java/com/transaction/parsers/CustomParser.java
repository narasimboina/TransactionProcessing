package com.transaction.parsers;

import com.transaction.Transaction;

import java.util.logging.Logger;


public class CustomParser implements TransactionParser {
    private static final Logger logger = Logger.getLogger(CustomParser.class.getName());

    public Transaction parseTransaction(String transactionLine) throws Exception {
        //  List<String> parsedValues = new ArrayList<>();
        Transaction transaction = new Transaction();
        int i = 0;
        int attributeSequence = 0;
        while (i < transactionLine.length()) {
            char segmentType = transactionLine.charAt(i);
            i++;
            int segmentLength = Character.getNumericValue(transactionLine.charAt(i));
            i++;
            String parsedValue;
            if (i + segmentLength > transactionLine.length()) {
                parsedValue = transactionLine.substring(i, transactionLine.length() - 1);
            } else {
                parsedValue = transactionLine.substring(i,i+ segmentLength);
            }

            i = i + segmentLength;

            attributeSequence++;
            if (attributeSequence == 1) {
                transaction.setTransactionId((Integer) validate(segmentType, segmentLength, parsedValue));
            } else if (attributeSequence == 2) {
                transaction.setTransactionType((String) validate(segmentType, segmentLength, parsedValue));
            } else if (attributeSequence == 3) {
                transaction.setAmount(((Double) validate(segmentType, segmentLength, parsedValue)) / 100);
            }
        }
        return transaction;
    }

    private Object validate(char segmentType, int segmentLength, String parsedValue) throws Exception {
        Object returnObject = null;
        validateLength(segmentLength, parsedValue);
        switch (segmentType) {
            case 'N':
                returnObject = Integer.parseInt(parsedValue);
                break;
            case 'A':
                returnObject = parsedValue;
                break;
            case 'D':
                returnObject = Double.parseDouble(parsedValue);
                break;
            default:
                // Handle invalid segmentType or other cases
                break;
        }
        return returnObject;
    }

    private void validateLength(int length, String value) throws Exception {
        System.out.println(length +"-"+ value);
        if (null == value || value.length() != length) {
            throw new Exception("Value length is not matching");
        }
    }
}

