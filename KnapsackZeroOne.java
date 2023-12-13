package DynamicProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KnapsackZeroOne {
    static final int mx = 100;
    private static int calculateMaxWidth(int[][] array) {
        int max = Integer.MIN_VALUE;

        for (int[] row : array) {
            for (int element : row) {
                int width = String.valueOf(element).length();
                if (width > max) {
                    max = width;
                }
            }
        }

        return max;
    }
    static void printTabular(int tabuler[][]){
        System.out.println("The result table: ");
        int maxElementWidth = calculateMaxWidth(tabuler);
        for (int[] tabuler1 : tabuler) {
            for (int j = 0; j < tabuler1.length; j++) {
                System.out.printf("%" + maxElementWidth + "d   ", tabuler1[j]);
            }
            System.out.println();
        }
    }
    public static int knapsack(int W, int[] w, int[] v, int n, boolean TraceBack[][]) {
        int[][] tabuler = new int[n + 1][W + 1];
        for (int i = 0; i <= W; i++) {
            tabuler[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (j >= w[i - 1]) {
                    tabuler[i][j] = Math.max(tabuler[i - 1][j], tabuler[i - 1][j - w[i - 1]] + v[i - 1]);
                    
                    TraceBack[i][j] = (tabuler[i - 1][j - w[i - 1]] + v[i - 1]) > tabuler[i - 1][j];

                } else {
                    tabuler[i][j] = tabuler[i - 1][j];
                }
            }
        }
        printTabular(tabuler);
        return tabuler[n][W];
    }

    public static void traceback(int W, int[] w, boolean TraceBack[][]) {
        int n = TraceBack.length - 1;
        ArrayList<Integer> selectedItems = new ArrayList<>();

        while (n > 0 && W > 0) {
            if (TraceBack[n][W]) {
                selectedItems.add(n);
                W -= w[n - 1]; 
            }
            n--;
        }

        Collections.reverse(selectedItems);

        System.out.println("Selected items: " + selectedItems);
    }

    public static void main(String[] args) {
        int weights[] = new int[mx];
        int profit[] = new int[mx];
        int numItems = 0;
        try {
            Scanner sc = new Scanner(new File("Knapsack 01.txt"));
            while(sc.hasNextLine()){
                weights[numItems] = sc.nextInt();
                profit[numItems] = sc.nextInt();
                numItems++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found.");
        }
        System.out.print("Enter the Knapsack Capacity: ");
        int knapsackCapacity = new Scanner(System.in).nextInt();
        boolean TraceBack[][] = new boolean[numItems+1][knapsackCapacity + 1];
        System.out.println("Item "+"  Weight "+" Profit");
        for(int i = 0; i<numItems; i++){
            System.out.println(i+1+"\t"+weights[i]+"\t"+profit[i]);
        }
        int result = knapsack(knapsackCapacity, weights, profit, numItems, TraceBack);
        System.out.println("Maximum Profit: "+result);
        traceback(knapsackCapacity, weights, TraceBack);

    }

}
