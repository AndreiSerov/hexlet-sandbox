package org.example;

/**
 * @author andreiserov
 */
public class Main {
    public static void main(String[] args) {
        int[][] battleField1 = {
            {1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0},
        };

        int[][] invalidField = {
            {1, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0},
        };

        int[][] battleField3 = {
            {1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0, 0},
        };


        int[][] battleField4 = {
            {1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
        };


//        System.out.println(isValidField(battleField1));
//        System.out.println(isValidField(invalidField));
        System.out.println(calcShipsCount(battleField1));
//        System.out.println(calcShipsCount(battleField3));
//        System.out.println(calcShipsCount(battleField4));
    }

    public static int calcShipsCount(int[][] field) {
        if (!isValidField(field)) {
            return 0;
        }

        int result = 0;
        boolean isShipCell = false;
        int prevI = 0;
        int prevJ = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == 1) {
                    if (isShipCell && j == 0) {
                        result++;
                    }
                    isShipCell = true;
                } else if (isShipCell && field[i][j] == 0) {
                    if (i == 0 || field[prevI - 1][prevJ - 1] == 0) result++;
                    isShipCell = false;
                }
                prevJ = j;
            }
            prevI = i;
        }

        return result;

    }

    public static boolean isValidField(int[][] pole) {
        int y = pole.length;
        int x = pole[0].length;
        for (int i = 1; i < y - 1; y++) {
            for (int j = 1; j < x - 1; x++) {
                if ((pole[i][j] == 1) && (pole[i - 1][j - 1] == 1 || pole[i - 1][j + 1] == 1 || pole[i + 1][j - 1] == 1 || pole[i + 1][j + 1] == 1) ) {
                    return false;
                }
            }
        }
        return true;
    }
    // END
}
