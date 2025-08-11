#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
using namespace std;

int n, m;
int d, y, x;
int dy[] = { -1, 0, 1, 0 };
int dx[] = { 0, 1, 0, -1 };
int a[51][51];
int cnt = 0;

int main() {
	cin >> n >> m;
	cin >> y >> x >> d;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> a[i][j];
		}
	}
	while (true) {
		if (a[y][x] == 0) {
			a[y][x] = 2;
			cnt++;
		}
		bool flag = false;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
			if (a[ny][nx] == 0) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			int tmp = (d + 2) % 4;
			int ny = y + dy[tmp];
			int nx = x + dx[tmp];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m || a[ny][nx] == 1) {
				break;
			}
			y = ny;
			x = nx;
			continue;
		}
		if (flag) {
			for(int i = 0; i < 4; i++){
				d = (d + 3) % 4;
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				if (a[ny][nx] != 0) continue;
				y = ny;
				x = nx;
				break;
			}
		}
	}
	cout << cnt;
}