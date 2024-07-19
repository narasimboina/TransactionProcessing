package com.transaction.parsers;

import com.transaction.Transaction;

public interface TransactionParser {

     Transaction parseTransaction(String transactionLine) throws Exception;
}
