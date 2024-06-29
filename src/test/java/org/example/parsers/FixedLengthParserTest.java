package org.example.parsers;

import org.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedLengthParserTest {
    @Test
    void parseTransaction() {
        Transaction t = FixedLengthParser.parseTransaction("123QWERTYASDFZXCVB");
        Assertions.assertNotNull(t);
        Assertions.assertEquals("123", t.getProp1());
        Assertions.assertEquals("QWER", t.getProp2());
        Assertions.assertEquals("TYASD", t.getProp3());
        Assertions.assertEquals("FZXCVB", t.getProp4());
    }
}