#include <iostream>
#include <cmath>

using namespace std;

// Function to compute (base^exponent) % mod efficiently
long long modExp(long long base, long long exponent, long long mod) {
    long long result = 1;
    base = base % mod;
    while (exponent > 0) {
        if (exponent & 1)
            result = (result * base) % mod;
        exponent = exponent >> 1;
        base = (base * base) % mod;
    }
    return result;
}

// Compute GCD using Euclidean Algorithm
long long gcd(long long a, long long b) {
    while (b != 0) {
        long long temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

// Compute modular inverse of e mod phi using Extended Euclidean Algorithm
long long modInverse(long long e, long long phi) {
    long long m0 = phi, t, q;
    long long x0 = 0, x1 = 1;

    if (phi == 1) return 0;

    while (e > 1) {
        q = e / phi;
        t = phi;

        phi = e % phi;
        e = t;
        t = x0;

        x0 = x1 - q * x0;
        x1 = t;
    }

    if (x1 < 0)
        x1 += m0;

    return x1;
}

int main() {
    // Step 1: Choose two primes (small for demo)
    long long p = 61;
    long long q = 53;

    // Step 2: Compute n = p * q
    long long n = p * q;

    // Step 3: Compute phi = (p-1)*(q-1)
    long long phi = (p - 1) * (q - 1);

    // Step 4: Choose e such that 1 < e < phi and gcd(e, phi) = 1
    long long e = 17; // commonly used prime

    // Step 5: Compute d, modular inverse of e mod phi
    long long d = modInverse(e, phi);

    cout << "Public key (e, n): (" << e << ", " << n << ")\n";
    cout << "Private key (d, n): (" << d << ", " << n << ")\n";

    // Encrypt a message (integer)
    long long message;
    cout << "Enter message (number < " << n << "): ";
    cin >> message;

    long long encrypted = modExp(message, e, n);
    cout << "Encrypted message: " << encrypted << endl;

    // Decrypt the message
    long long decrypted = modExp(encrypted, d, n);
    cout << "Decrypted message: " << decrypted << endl;

    return 0;
}

