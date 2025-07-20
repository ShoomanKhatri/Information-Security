#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

long long modExp(long long base, long long exp, long long mod) {
    long long result = 1;
    base %= mod;
    while (exp > 0) {
        if (exp & 1) result = (result * base) % mod;
        base = (base * base) % mod;
        exp >>= 1;
    }
    return result;
}

bool millerTest(long long d, long long n, long long a) {
    long long x = modExp(a, d, n);
    if (x == 1 || x == n - 1) return true;
    while (d != n - 1) {
        x = (x * x) % n;
        d <<= 1;
        if (x == 1) return false;
        if (x == n - 1) return true;
    }
    return false;
}

bool isPrime(long long n, int k) {
    if (n <= 1 || n == 4) return false;
    if (n <= 3) return true;
    long long d = n - 1;
    while ((d & 1) == 0) d >>= 1;
    srand(time(0));
    for (int i = 0; i < k; i++) {
        long long a = 2 + rand() % (n - 4);
        if (!millerTest(d, n, a)) return false;
    }
    return true;
}

int main() {
    long long n;
    int k = 5;
    cout << "Enter number to test primality: ";
    cin >> n;
    if (isPrime(n, k))
        cout << n << " is probably prime.\n";
    else
        cout << n << " is composite.\n";
    return 0;
}

