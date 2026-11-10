package lw04.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("parcels.txt"));
        int n = scan.nextInt();
        int[] parcel = new int[n];
        for (int i = 0; i < n; i++) {
            parcel[i] = scan.nextInt();
        }
        int m = scan.nextInt();
        int[] target = new int[m];
        for (int i = 0; i < m; i++) {
            target[i] = scan.nextInt();
        }
        scan.close();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (parcel[j] < parcel[j + 1]) {
                    int temp = parcel[j];
                    parcel[j] = parcel[j + 1];
                    parcel[j + 1] = temp;
                }
            }
        }

        int[] pass2 = new int[parcel.length];
        for (int i = 0; i < parcel.length; i++) {
            pass2[i] = parcel[i];
        }

        for (int i = 2; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (parcel[j] < parcel[j + 1]) {
                    int temp = parcel[j];
                    parcel[j] = parcel[j + 1];
                    parcel[j + 1] = temp;
                }
            }
        }

        System.out.println("==== After 2 Passes ====");
        for (int i : pass2) {
            System.out.print(i + " ");
        }

        System.out.println("\n\n==== Fully Sorted ====");
        for (int i : parcel) {
            System.out.print(i + " ");
        }

        System.out.println("\n\n==== Search Results ====");
        for (int i : target) {
            int step = 0;
            int bawah = 0;
            int atas = n - 1;
            int found = -1;

            while (bawah <= atas) {
                int mid = (bawah + atas) / 2;
                step++;

                if (parcel[mid] == i) {
                    found = mid;
                    break;
                } else if (parcel[mid] < i) {
                    atas = mid - 1;
                } else {
                    bawah = mid + 1;
                }
            }

            if (found != -1) {
                System.out.println(i + ": FOUND at index " + found + " (steps: " + step + ")");
            } else {
                System.out.println(i + ": NOT FOUND (steps: " + step + ")");
            }
        }

        /*
         * for i = 0 to passes - 1:
         * for j = 0 to n - 1 - i:
         * if A[j] < A[j+1]: swap A[j] and A[j+1]
         */
    }
}
