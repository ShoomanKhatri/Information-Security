public class VigenereCipher {
    public static void main(String[] args) {
        String message = "HELLO";
        String key = "KEY";

        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original Message : " + message);
        System.out.println("Key              : " + key);
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);
    }

    // Encrypt the message using Vigenère cipher
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int shift = key.charAt(j) - 'A';
                char encryptedChar = (char) ((c - 'A' + shift) % 26 + 'A');
                result.append(encryptedChar);
                j = (j + 1) % key.length();
            } else {
                result.append(c); // Leave non-letter characters unchanged
            }
        }

        return result.toString();
    }

    // Decrypt the message using Vigenère cipher
    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int shift = key.charAt(j) - 'A';
                char decryptedChar = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                result.append(decryptedChar);
                j = (j + 1) % key.length();
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
