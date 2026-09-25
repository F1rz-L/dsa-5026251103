package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));
        LinkedList<String[]> transactions = new LinkedList<String[]>();

        while (sc.hasNext()) {
            String name = sc.next();
            String type = sc.next();
            String amount = sc.next();
            transactions.add(new String[] { name, type, amount });
        }

        LinkedList<String[]> customers = new LinkedList<String[]>();

        for (String[] t : transactions) {
            String name = t[0];
            if (findCustomer(customers, name) == null) {
                customers.add(new String[] { name, "0" });
            }
        }

        Queue<String[]> queue = new LinkedList<String[]>();
        queue.addAll(transactions);

        Stack<String[]> failed = new Stack<String[]>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance = balance + amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failed.push(t);
                } else {
                    balance = balance - amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] t = failed.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }

        sc.close();
    }

    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                return customer;
            }
        }
        return null;
    }

}
