package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner playlist = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        Scanner participants = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Scanner inventory = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        var playlistList = new ArrayList<String>();
        while (playlist.hasNext()) {
            String type = playlist.next();
            switch (type) {
                case "ADD":
                    playlistList.add(playlist.next());
                    break;
                case "INSERT":
                    playlistList.add(Integer.parseInt(playlist.next()), playlist.next());
                    break;
                case "REMOVE":
                    playlistList.remove(playlist.next());
                    break;
            }
        }
        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlistList.size());
        for (int i = 0; i < playlistList.size(); i++) {
            System.out.println((i + 1) + ": " + playlistList.get(i));
        }
        playlist.close();

        var participantSet = new LinkedHashSet<String>();
        int pDupes = 0;
        while (participants.hasNext()) {
            String pName = participants.next();
            if (participantSet.contains(pName)) {
                pDupes++;
                continue;
            }
            participantSet.add(pName);
        }
        System.out.println("\n===== Problem 2 =====");
        System.out.println("Unique participants: " + participantSet.size());
        int i = 1;
        for (String parts : participantSet) {
            System.out.println(i++ + ": " + parts);
        }
        System.out.println("Duplicate registrations: " + pDupes);
        participants.close();

        var inventoryMap = new LinkedHashMap<String, Integer>();
        int failedSales = 0;
        while (inventory.hasNext()) {
            String type = inventory.next();
            String product = inventory.next();
            int amount = inventory.nextInt();
            switch (type) {
                case "ADD":
                    inventoryMap.put(product, inventoryMap.getOrDefault(product, 0) + amount);
                    break;
                case "SELL":
                    if (inventoryMap.getOrDefault(product, 0) < amount) {
                        failedSales++;
                    } else {
                        inventoryMap.put(product, (inventoryMap.get(product) - amount));
                    }
                    break;
            }
        }
        System.out.println("\n===== Problem 3 =====");
        for (String inv : inventoryMap.keySet()) {
            System.out.println(inv + ": " + inventoryMap.get(inv));
        }
        System.out.println("Failed sales: " + failedSales);
        inventory.close();
    }
}
