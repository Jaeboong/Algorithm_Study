#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

int n, m, x, y, k;
int a[21][21];
int dy[] = { 0, 0, -1, 1 };
int dx[] = { 1, -1, 0, 0 };
int dice[7] = { 0, };

int main() {
    cin >> n >> m >> y >> x >> k;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }
    for (int i = 0; i < k; i++) {
        int dir;
        cin >> dir;
        int ny = y + dy[dir - 1];
        int nx = x + dx[dir - 1];
        if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
        if (dir == 1) {
            int tmp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = tmp;
        }
        else if (dir == 2) {
            int tmp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        }
        else if (dir == 3) {
            int tmp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        }
        else if (dir == 4) {
            int tmp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }
        if (a[ny][nx] == 0) {
            a[ny][nx] = dice[6];
        }
        else {
            dice[6] = a[ny][nx];
            a[ny][nx] = 0;
        }
        cout << dice[1] << '\n';
        y = ny;
        x = nx;
    }
}