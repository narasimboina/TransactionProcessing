package org.example;

import java.util.*;


public class PaymentProcessor {
    private Map<String, Customer> customers;
    private StringBuilder transactionLog;

    public PaymentProcessor() {
        customers = new HashMap<>();
        transactionLog = new StringBuilder();
    }

    // Method to add a new customer to the processor
    public void addCustomer(String name, double initialBalance) {
        Customer customer = new Customer(name, initialBalance);
        customers.put(name, customer);
    }

    // Method to process a payment for a customer
    public boolean processPayment(String customerName, double amount) {
        if (customers.containsKey(customerName)) {
            Customer customer = customers.get(customerName);
            double currentBalance = customer.getBalance();
            if (currentBalance >= amount) {
                customer.setBalance(currentBalance - amount);
                logTransaction(customerName, amount, "Payment processed successfully.");
                return true;
            } else {
                logTransaction(customerName, amount, "Failed: Insufficient funds.");
                return false;
            }
        } else {
            logTransaction(customerName, amount, "Failed: Customer not found.");
            return false;
        }
    }

    // Method to get the current balance of a customer
    public double getCustomerBalance(String customerName) {
        if (customers.containsKey(customerName)) {
            return customers.get(customerName).getBalance();
        } else {
            System.out.println("Customer not found.");
            return -1; // or throw an exception, depending on requirements
        }
    }

    // Method to log transactions
    private void logTransaction(String customerName, double amount, String message) {
        transactionLog.append("Customer: ").append(customerName)
                .append(", Amount: $").append(amount)
                .append(", ").append(message).append("\n");
    }

    // Method to print transaction log
    public void printTransactionLog() {
        System.out.println("Transaction Log:");
        System.out.println(transactionLog.toString());
    }
}


