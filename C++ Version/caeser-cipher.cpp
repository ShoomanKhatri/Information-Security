#include <iostream>
#include <string>
using namespace std;

string caesarCipher(const string& text, int shift) {
    string result = "";

    for (char c : text) {
        if (isalpha(c)) {
            char base = isupper(c) ? 'A' : 'a';
            result += char((c - base + shift) % 26 + base);
        } else {
            result += c;
        }
    }

    return result;
}

int main() {
    string text;
    int shift;

    cout << "Enter text to encrypt: ";
    getline(cin, text);

    cout << "Enter shift (key): ";
    cin >> shift;

    shift = shift % 26;

    string encrypted = caesarCipher(text, shift);
    cout << "Encrypted text: " << encrypted << endl;

    return 0;
}

