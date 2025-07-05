import java.util.Scanner;

public class FeistelCipher {

    // Simple XOR-based round function
    public static String roundFunction(String input, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char bit = input.charAt(i) == key.charAt(i) ? '0' : '1'; // XOR
            result.append(bit);
        }
        return result.toString();
    }

    // Feistel cipher encryption
    public static String encrypt(String plaintext, String key, int rounds) {
        int len = plaintext.length();
        String left = plaintext.substring(0, len / 2);
        String right = plaintext.substring(len / 2);

        System.out.println("Initial L: " + left + ", R: " + right);

        for (int i = 1; i <= rounds; i++) {
            String temp = right;
            right = roundFunction(right, key.substring(0, right.length())); // Simple round function
            right = xor(left, right); // Feistel logic
            left = temp;

            System.out.println("After Round " + i + ": L = " + left + ", R = " + right);
        }

        return left + right;
    }

    // XOR helper
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 8-bit binary plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter 8-bit binary key: ");
        String key = scanner.nextLine();

        System.out.print("Enter number of rounds: ");
        int rounds = scanner.nextInt();

        String encrypted = encrypt(plaintext, key, rounds);
        System.out.println("Encrypted Text: " + encrypted);

        scanner.close();
    }
}
