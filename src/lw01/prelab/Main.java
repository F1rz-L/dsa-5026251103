package lw01.prelab;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("jobs.txt"));
        ArrayList<PrintJob> jobs = new ArrayList<PrintJob>();

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            if (type == "MONO") {
                jobs.add(new MonoPrint(id, pages));
            } else if (type == "COLOUR") {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }

    }
}
