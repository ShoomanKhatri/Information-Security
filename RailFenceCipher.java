public class RailFenceCipher {

    // Encrypts text using the Rail Fence Cipher
    public static String encrypt(String text, int rails) {
        if (rails <= 1 || text.length() == 0) return text;

        StringBuilder[] fence = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            fence[i] = new StringBuilder();
        }

        int rail = 0;
        boolean down = true;

        for (char ch : text.toCharArray()) {
            fence[rail].append(ch);

            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }

            rail += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : fence) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String message = "HELLO WORLD";
        int rails = 3;

        // Remove spaces for simplicity
        message = message.replaceAll("\\s", "");

        String encrypted = encrypt(message, rails);
        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + encrypted);
    }
}
