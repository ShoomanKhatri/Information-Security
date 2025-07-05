public class CaesarCipher {
    public static void main(String[] args) {
        String message = "HELLO";
        int key = 3;

        String encrypted = encrypt(message, key);
        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + encrypted);
    }

    // Caesar cipher encryption
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encryptedChar = (char) ((ch - base + shift) % 26 + base);
                result.append(encryptedChar);
            } else {
                result.append(ch); // Keep non-alphabet characters unchanged
            }
        }

        return result.toString();
    }
}
