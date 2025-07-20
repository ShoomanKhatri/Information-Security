#include <iostream>

using namespace std;

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

int main() {
    long long a, p;
    cout << "Enter base a: ";
    cin >> a;
    cout << "Enter prime p: ";
    cin >> p;

    long long res = modExp(a, p - 1, p);
    cout << "a^(p-1) mod p = " << res << endl;

    return 0;
}

