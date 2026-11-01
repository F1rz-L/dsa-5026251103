package lw04.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = scan.nextInt();
        int[] branch = new int[n];
        for (int i = 0; i < n; i++) {
            branch[i] = scan.nextInt();
        }
        int m = scan.nextInt();
        int[] target = new int[m];
        for (int i = 0; i < m; i++) {
            target[i] = scan.nextInt();
        }
        scan.close();

        for (int i = 1; i < 4; i++) {
            int temp = branch[i];
            int j = i;

            while (j > 0 && branch[j - 1] < temp) {
                branch[j] = branch[j - 1];
                j--;
            }
            branch[j] = temp;
        }

        for (int i : branch) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : target) {
            for (int j = 0; j < branch.length; j++) {
                if (i == branch[j]) {
                    System.out.println(i + ": FOUND at index " + j);
                    break;
                }
                if (i != branch[j] && branch[j] == branch[branch.length - 1]) {
                    System.out.println(i + ": NOT FOUND");
                }
            }
        }
    }
}
