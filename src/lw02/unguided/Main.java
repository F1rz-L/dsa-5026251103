package lw02.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (Main.class.getResourceAsStream("orders.txt"));
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> isProcessed = new LinkedList<>();
        
        LinkedList<String[]> foods = new LinkedList<>();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});
        
        LinkedList<String[]> drinks = new LinkedList<>();
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        
        while (sc.hasNext()) {
            String[] order = new String[4];
            order[0] = sc.next();
            order[1] = sc.next();
            order[2] = sc.next();
            order[3] = sc.next();
            orders.add(order);
        }

        Queue<String[]> processOrders = new LinkedList<>();
        processOrders.addAll(orders);

        Stack<String[]> failed = new Stack<>();

        while (!processOrders.isEmpty()) {
            String[] order = processOrders.poll();
            String foodName = order[1];
            String drinkName = order[2];

            boolean foodAvailable = true;
            String[] foodTarget = null;
            if (!foodName.equals("-")) {
                foodAvailable = false;
                for (String[] f : foods) {
                    if (f[0].equals(foodName)) {
                        foodTarget = f;
                        if (Integer.parseInt(f[1]) > 0) {
                            foodAvailable = true;
                        }
                        break;
                    }
                }
            }

            boolean drinkAvailable = true;
            String[] drinkTarget = null;
            if (!drinkName.equals("-")) {
                drinkAvailable = false;
                for (String[] d : drinks) {
                    if (d[0].equals(drinkName)) {
                        drinkTarget = d;
                        if (Integer.parseInt(d[1]) > 0) {
                            drinkAvailable = true;
                        }
                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (foodTarget != null) {
                    int currentStock = Integer.parseInt(foodTarget[1]);
                    foodTarget[1] = String.valueOf(currentStock - 1);
                }
                if (drinkTarget != null) {
                    int currentStock = Integer.parseInt(drinkTarget[1]);
                    drinkTarget[1] = String.valueOf(currentStock - 1);
                }
                isProcessed.add(order);
            } else {
                failed.push(order);
            }
        }

        sc.close();

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : isProcessed) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
        System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }
        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }
        System.out.println();

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}