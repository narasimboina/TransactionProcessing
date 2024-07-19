

package com.transaction.parsers;
import com.transaction.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CommaDelimtedParserTest {



    @Test
    public void parseTransaction() throws Exception {
        TransactionParser parser = new CommaDelimtedParser();
        Transaction t = parser.parseTransaction("1234,QWERTY,ASDF,ZXCVB");
        assertNotNull(t);
        assertEquals("1234", t.getTransactionId());
        assertEquals("QWERTY", t.getTransactionType());
        assertEquals("ASDF", t.getAmount());

    }
}