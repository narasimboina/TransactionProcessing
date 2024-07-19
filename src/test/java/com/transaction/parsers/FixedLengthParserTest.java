package com.transaction.parsers;


import com.transaction.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FixedLengthParserTest {
    @Test
    public void parseTransaction() throws Exception {
        TransactionParser parser = new FixedLengthParser();
        Transaction t = parser.parseTransaction("123QWER12345");
        assertNotNull(t);
        assertEquals(123, t.getTransactionId());
        assertEquals("QWER", t.getTransactionType());
        assertEquals(123.45d, t.getAmount(), 0.0);
    }
}