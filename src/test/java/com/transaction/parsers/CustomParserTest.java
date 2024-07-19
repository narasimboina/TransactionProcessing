package com.transaction.parsers;
import com.transaction.Transaction;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CustomParserTest {



    @Test
    public void testParseTransaction() throws Exception {
        TransactionParser parser = new CustomParser();

        // Test case 1: Valid transaction line
        String transactionLine1 = "N804022004A3AB1D551024";
        Transaction transaction1 = parser.parseTransaction(transactionLine1);
        assertNotNull(transaction1);
        assertEquals(4022004, transaction1.getTransactionId());
        assertEquals("AB1", transaction1.getTransactionType());
        System.out.println(transaction1.getAmount());
        assertEquals(510.24d, transaction1.getAmount(),0.0);

        // Test case 2: Another valid transaction line
        String transactionLine2 = "N823456789A3XY2D71234550";
        Transaction transaction2 = parser.parseTransaction(transactionLine2);
        assertNotNull(transaction2);
        assertEquals(23456789, transaction2.getTransactionId());
        assertEquals("XY2", transaction2.getTransactionType());
        assertEquals(12345.50d, transaction2.getAmount(),0.0);


    }
    @Test(expected = Exception.class)
    public void testParseTransactionWithException() throws Exception {
        TransactionParser parser = new CustomParser();
        // Test case 3: Transaction line with different segments
        String transactionLine3 = "AABCD12345D99999";

        Transaction transaction3 = parser.parseTransaction(transactionLine3);
    }

}