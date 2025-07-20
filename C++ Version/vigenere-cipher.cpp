#include <iostream>
#include <string>
#include <cctype>

using namespace std;

string encryptVigenere(const string& plaintext, const string& key) {
    string ciphertext = "";
    int keyLength = key.length();
    int keyIndex = 0;

    for (char c : plaintext) {
        if (isalpha(c)) {
            char base = isupper(c) ? 'A' : 'a';
            char k = toupper(key[keyIndex % keyLength]) - 'A';
            char encryptedChar = (c - base + k) % 26 + base;
            ciphertext += encryptedChar;
            keyIndex++;
        } else {
            // Non-alphabet characters unchanged
            ciphertext += c;
        }
    }

    return ciphertext;
}

int main() {
    string plaintext, key;

    cout << "Enter plaintext: ";
    getline(cin, plaintext);

    cout << "Enter key (letters only): ";
    getline(cin, key);

    string ciphertext = encryptVigenere(plaintext, key);

    cout << "Encrypted text: " << ciphertext << endl;

    return 0;
}

