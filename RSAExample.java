import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAExample {
    private BigInteger n, d, e;
    private int bitlen = 1024; // key size

    // Constructor to generate keys
    public RSAExample() {
        SecureRandom r = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitlen, r);
        BigInteger q = BigInteger.probablePrime(bitlen, r);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        e = new BigInteger("65537"); // Common public exponent
        d = e.modInverse(phi);
    }

    // Encrypt message
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decrypt message
    public BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }

    // Convert string to BigInteger
    public static BigInteger stringToBigInt(String message) {
        return new BigInteger(message.getBytes());
    }

    // Convert BigInteger back to string
    public static String bigIntToString(BigInteger message) {
        return new String(message.toByteArray());
    }

    public static void main(String[] args) {
        RSAExample rsa = new RSAExample();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a short message to encrypt: ");
        String input = scanner.nextLine();

        BigInteger plaintext = stringToBigInt(input);
        BigInteger encrypted = rsa.encrypt(plaintext);
        BigInteger decrypted = rsa.decrypt(encrypted);

        System.out.println("\nOriginal Message: " + input);
        System.out.println("Encrypted (numeric): " + encrypted);
        System.out.println("Decrypted Message: " + bigIntToString(decrypted));

        scanner.close();
    }
}
