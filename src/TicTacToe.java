/**
 * Java 1. Homework 4
 *
 * @author Juliya Veyukova
 * @version 17.10.2021
 */

import java.util.Random;
import java.util.Scanner;


public class TicTacToe {
    final char SIGN_X = 'x';
    final char SIGN_O = '0';
    final char SIGN_EMPTY = '.';
    char[][] table;
    Random random;
    Scanner scanner;

    TicTacToe() {
        table = new char[3][3];
        random = new Random();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    void game() {
        initTable();
        while (true) {
            printTable();
            turnHuman();
            if (isWin(SIGN_X)) {
                System.out.println("Победа!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Победила дружба!");
                break;
            }
            turnAi();
            if (isWin(SIGN_O)) {
                System.out.println("Вы проиграли");
                break;
            }
            if (isTableFull()) {
                System.out.println("Победила дружба!");
                break;
            }
        }
        printTable();
    }


    void initTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = SIGN_EMPTY;
            }
        }
    }

    void printTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + "");
            }
            System.out.println();
        }
    }

    void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter [1..3] x y : ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[x][y] = SIGN_X;
    }

    void turnAi() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        while (!isCellValid(x, y));
        table[x][y] = SIGN_O;

    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            return false;
        }
        return table[x][y] == SIGN_EMPTY;
    }

    boolean isWin(char ch) {
        int i = 0;
        while (i < 3) {
            boolean cols = true;
            boolean rows = true;
            int j = 0;
            while (j < 3) {
                cols &= (table[i][j] == ch);
                rows &= (table[j][i] == ch);
                j++;
            }
            if (cols || rows) return true;
            i++;
        }
        // диагонали
        boolean toright = true;
        boolean toleft = true;
        i = 0;
        while (i < 3) {
            toright &= (table[i][i] == ch);
            toleft &= (table[3 - i - 1][i] == ch);
            i++;
        }
        if (toright || toleft) return true;
        return false;
    }

    boolean isTableFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == SIGN_EMPTY) ;
                {
                    return false;
                }
            }
        }
        return true;
    }
}


