#include <iostream>
#include <string>
#include <cctype>

using namespace std;

void generateMatrix(const string& key, char matrix[5][5]) {
    bool used[26] = {false};
    used['J' - 'A'] = true; // exclude J

    int row = 0, col = 0;

    for (char ch : key) {
        ch = toupper(ch);
        if (ch == 'J') ch = 'I';
        if (ch < 'A' || ch > 'Z') continue;
        if (!used[ch - 'A']) {
            matrix[row][col++] = ch;
            used[ch - 'A'] = true;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }

    for (char ch = 'A'; ch <= 'Z'; ch++) {
        if (!used[ch - 'A']) {
            matrix[row][col++] = ch;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }
}

void findPosition(char matrix[5][5], char ch, int& row, int& col) {
    if (ch == 'J') ch = 'I';
    for (int r = 0; r < 5; r++) {
        for (int c = 0; c < 5; c++) {
            if (matrix[r][c] == ch) {
                row = r;
                col = c;
                return;
            }
        }
    }
}

string prepareText(string text) {
    string result = "";
    for (char ch : text) {
        if (isalpha(ch)) {
            ch = toupper(ch);
            if (ch == 'J') ch = 'I';
            result += ch;
        }
    }
    return result;
}

string encryptPlayfair(char matrix[5][5], string text) {
    string prepared = prepareText(text);
    string result = "";

    for (size_t i = 0; i < prepared.length(); i += 2) {
        char first = prepared[i];
        char second;

        if (i + 1 < prepared.length()) {
            second = prepared[i + 1];
            if (first == second) {
                second = 'X';
                i--; // stay on second char for next loop
            }
        } else {
            second = 'X'; // padding if odd length
        }

        int r1, c1, r2, c2;
        findPosition(matrix, first, r1, c1);
        findPosition(matrix, second, r2, c2);

        if (r1 == r2) {
            // same row: shift right
            result += matrix[r1][(c1 + 1) % 5];
            result += matrix[r2][(c2 + 1) % 5];
        } else if (c1 == c2) {
            // same column: shift down
            result += matrix[(r1 + 1) % 5][c1];
            result += matrix[(r2 + 1) % 5][c2];
        } else {
            // rectangle: swap columns
            result += matrix[r1][c2];
            result += matrix[r2][c1];
        }
    }

    return result;
}

int main() {
    string key, plaintext;
    char matrix[5][5];

    cout << "Enter key: ";
    getline(cin, key);

    generateMatrix(key, matrix);

    cout << "Enter plaintext: ";
    getline(cin, plaintext);

    string encrypted = encryptPlayfair(matrix, plaintext);

    cout << "Encrypted text: " << encrypted << endl;

    return 0;
}

