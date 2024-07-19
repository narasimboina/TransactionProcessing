package com.transaction.parsers;

public class ParserFactory {

    public static TransactionParser getParser(String parserType) {
        TransactionParser parser = null;
        if ("FIXED".equals(parserType)) {
            parser = new FixedLengthParser();
        } else if ("CSV".equals(parserType)) {
            parser = new CommaDelimtedParser();
        } else if ("CUSTOM".equals(parserType)) {
            parser = new CustomParser();
        } else {
            parser = new CustomParser();
        }
        return parser;
    }
}
