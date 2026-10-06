package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner registrations = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Scanner checkins = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        int succesfulCheckin = 0;
        int absent = 0;
        int rejected = 0;

        var studentStatus = new LinkedHashMap<String, String>();
        while (registrations.hasNext()) {
            String student = registrations.next();
            studentStatus.put(student, "ABSENT");
        }
        registrations.close();

        var checkinList = new ArrayList<String>();
        System.out.println("===== Event Check-In Results =====");
        while (checkins.hasNext()) {
            String student = checkins.next();
            checkinList.add(student);
            if (studentStatus.containsKey(student)) {
                if (studentStatus.get(student).equals("ABSENT")) {
                    studentStatus.put(student, "CHECKED IN");
                    System.out.println(student + ": Checked in");
                    succesfulCheckin++;
                } else if (studentStatus.get(student).equals("CHECKED IN")) {
                    System.out.println(student + ": Rejected (already registered)");
                    rejected++;
                }
            } else {
                System.out.println(student + ": Rejected (not registered)");
                rejected++;
            }
        }
        checkins.close();

        for (String student : studentStatus.keySet()) {
            if (!checkinList.contains(student)) {
                studentStatus.put(student, "ABSENT");
                absent++;
            }
        }

        System.out.println("\n===== Final Event Summary =====");
        System.out.println("Registered students: " + studentStatus.size());
        System.out.println("Successful check-ins: " + succesfulCheckin);
        System.out.println("Absent students: " + absent);
        System.out.println("Rejected attempts: " + rejected);
    }
}
