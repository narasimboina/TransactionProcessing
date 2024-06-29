package org.example.parsers;

import org.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommaDelimtedParserTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseTransaction() {
        Transaction t = CommaDelimtedParser.parseTransaction("1234,QWERTY,ASDF,ZXCVB");
        Assertions.assertNotNull(t);
        Assertions.assertEquals("1234", t.getProp1());
        Assertions.assertEquals("QWERTY", t.getProp2());
        Assertions.assertEquals("ASDF", t.getProp3());
        Assertions.assertEquals("ZXCVB", t.getProp4());
    }
}