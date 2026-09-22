package lw01.unguided;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        ArrayList<WashService> wash = new ArrayList<WashService>();
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            String type = scan.next();
            String id = scan.next();
            int days = scan.nextInt();
            int units = scan.nextInt();
            if (type.equals("MOTORCYCLE")) {
                wash.add(new MotorcycleWash(id, days, units));
            } else if (type.equals("CAR")) {
                wash.add(new CarWash(id, days, units));
            }
        }

        for (WashService w : wash) {
            System.out.println(w.summary());
        }
        scan.close();
    }
}
