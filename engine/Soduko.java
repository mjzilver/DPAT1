package engine;

import java.util.Scanner;

public class Soduko {
    int[][] board = new int[9][9];
    Scanner scanner = new Scanner(System.in);
    
    public Soduko() {
        // Initialize the board with zeros
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
        System.out.println("Board initialized");
    }
}
