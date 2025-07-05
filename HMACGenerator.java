import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.util.Base64;

public class HMACGenerator {

    public static String generateHMAC(String message, String key) {
        try {
            // Define HMAC with SHA-256
            String algorithm = "HmacSHA256";

            // Create secret key
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);

            // Get Mac instance and initialize
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);

            // Compute HMAC
            byte[] hmacBytes = mac.doFinal(message.getBytes());

            // Convert to base64 for readable output
            return Base64.getEncoder().encodeToString(hmacBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = scanner.nextLine();

        System.out.print("Enter secret key: ");
        String key = scanner.nextLine();

        String hmac = generateHMAC(message, key);
        System.out.println("HMAC (Base64): " + hmac);

        scanner.close();
    }
}
