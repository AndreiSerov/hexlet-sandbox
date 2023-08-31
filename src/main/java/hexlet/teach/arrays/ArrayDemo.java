package hexlet.teach.arrays;

import java.util.Arrays;

/**
 * @author andreiserov
 */
public class ArrayDemo {
    public static void main(String[] args) {

        final int[][] multiplicationTable = getMultiplicationTable();




//        System.out.println(print2DArray(multiplicationTable));

        System.out.println(print2DArray(
            angleUpRight(9)
        )
                .replace("0", " ")
        );

        System.out.println(print2DArray(
                angleDownRight(9)
            )
                .replace("0", " ")
        );
        System.out.println(print2DArray(
                angleLeftRight(9)
            )
                .replace("0", " ")
        );
        System.out.println(print2DArray(
                angle4(9)
            )
                .replace("0", " ")
        );

        System.out.println(print2DArray(
            star()
            ).replace("0", " ")
        );


    }

/*
                9
              8 9 8
            7 8 9 8 7
          6 7 8 9 8 7 6
        5 6 7 8 9 8 7 6 5
      4 5 6 7 8 9 8 7 6 5 4
    3 4 5 6 7 8 9 8 7 6 5 4 3
  2 3 4 5 6 7 8 9 8 7 6 5 4 3 2
1 2 3 4 5 6 7 8 9 8 7 6 5 4 3 2 1
  2 3 4 5 6 7 8 9 8 7 6 5 4 3 2
    3 4 5 6 7 8 9 8 7 6 5 4 3
      4 5 6 7 8 9 8 7 6 5 4
        5 6 7 8 9 8 7 6 5
          6 7 8 9 8 7 6
            7 8 9 8 7
              8 9 8
                9


9 8 7 6 5 4 3 2 1
9 8 7 6 5 4 3 2 1
9 8 7 6 5 4 3 2
9 8 7 6 5 4 3
9 8 7 6 5 4
9 8 7 6 5
9 8 7 6
9 8 7
9 8
9
 */
/*
def get_spiral_matrix(n):
    x, y, dx, dy, m = 0, 0, 0, 1, [[0] * n for _ in range(n)]
    for i in range(n * n):
        m[x][y] = str(i + 1)
        if x + dx >= n or x + dx < 0 or y + dy >= n or y + dy < 0 or m[x + dx][y + dy]:
            dx, dy = dy, -dx
        x, y = x + dx, y + dy

    return m
 */
    private static int[][] getSpiral(int size) {
        int[][] res = new int[size][size];
        int x = 0, y = 0, dx = 0, dy = 1;

        for (int i = 0; i < size * size; i++) {
            res[x][y] = i + 1;
            if (x + dx >= size || x + dx < 0 || y + dy >= size || y + dy < 0 || res[x + dx][y + dy] != 0) {
                int temp = dx;
                dx = dy;
                dy = -temp;
            }

            x += dx;
            y += dy;
        }

        return res;
    }

    private static String print2DArray(int[][] arr) {

        final String string = Arrays.deepToString(arr)
            .replace("], ", "]\n")
            .replace("[[", "[")
            .replace("]]", "]")
            .replaceAll("[\\[\\],]", "");

//        System.out.println(string);

        return string;
    }

    private static int[][] star() {
        int size = 9;

        int[][] res = new int[size*2][size*2];

        for (int row = 0; row < size; row++) {
            for (int column = size - row; column < size; column++) {
                res[row][column - 1] = column;
            }
        }

        int secondPart = size  * 2;

        for (int row = size; row < secondPart; row++) {
            for (int column = row; column < secondPart; column++) {
                res[secondPart - row - 1][secondPart - column - 2 + size] = column + 1 - size;
            }
        }

        for (int row = 1; row < size; row++) {
            for (int column = row; column < size; column++) {
                res[size + row - 1][column] = column + 1;
            }
        }

//        for (int row = size; row < secondPart; row++) {
//            for (int column = row; column < secondPart; column++) {
//                res[size + row - 1][secondPart - column - 1] = column;
//            }
//        }



        return res;
    }

    /*


     */

    private static int[][] angleUpRight(int size) {
        int[][] res = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = row; column < size; column++) {
                res[size - row - 1][size - column - 1] = column + 1;
            }
        }

        return res;
    }

    private static int[][] angleDownRight(int size) {
        int[][] res = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = row; column < size; column++) {
                res[row][size - column - 1] = column + 1;
            }
        }

        return res;
    }

    private static int[][] angleLeftRight(int size) {
        int[][] res = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = size - row; column < size; column++) {
                res[row][column - 1] = column;
//                if (res[row][column - 1] != 0) System.out.println(res[row][column - 1] + " ");
            }
        }

        return res;
    }

    private static int[][] angle4(int size) {
        int[][] res = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = row; column < size; column++) {
                res[row][column] = column + 1;
//                if (res[row][column - 1] != 0) System.out.println(res[row][column - 1] + " ");
            }
        }

        return res;
    }


    private static int[][] getMultiplicationTable() {
        return getMultiplicationTable(9);
    }

    private static int[][] getMultiplicationTable(int size) {
        int[][] table = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                table[row][column] = (row + 1)  * (column + 1);
            }
        }
        return table;
    }


}



// DualPivotQuicksort

/*
                9
              8 9 8
            7 8 9 8 7
          6 7 8 9 8 7 6
        5 6 7 8 9 8 7 6 5
      4 5 6 7 8 9 8 7 6 5 4
    3 4 5 6 7 8 9 8 7 6 5 4 3
  2 3 4 5 6 7 8 9 8 7 6 5 4 3 2
1 2 3 4 5 6 7 8 9 8 7 6 5 4 3 2 1
  2 3 4 5 6 7 8 9
    3 4 5 6 7 8 9
      4 5 6 7 8 9
        5 6 7 8 9
          6 7 8 9
            7 8 9
              8 9
                9



 */