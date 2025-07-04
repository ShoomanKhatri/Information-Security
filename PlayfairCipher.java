import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];

    public static void main(String[] args) {
        String key = "MONARCHY";
        String plaintext = "INSTRUMENT";

        generateMatrix(key);
        System.out.println("Key Matrix:");
        printMatrix();

        String prepared = prepareText(plaintext);
        String encrypted = encrypt(prepared);

        System.out.println("Prepared: " + prepared);
        System.out.println("Encrypted: " + encrypted);
    }

    static void generateMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        boolean[] used = new boolean[26];
        int row = 0, col = 0;

        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                matrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) { row++; col = 0; }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J' || used[c - 'A']) continue;
            matrix[row][col++] = c;
            if (col == 5) { row++; col = 0; }
        }
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (a == b) {
                sb.append(a).append('X');
            } else {
                sb.append(a).append(b);
                i++;
            }
        }

        if (sb.length() % 2 != 0)
            sb.append('X');

        return sb.toString();
    }

    static String encrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPos(a);
            int[] posB = findPos(b);

            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }

        return result.toString();
    }

    static int[] findPos(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    static void printMatrix() {
        for (char[] row : matrix) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }
}
