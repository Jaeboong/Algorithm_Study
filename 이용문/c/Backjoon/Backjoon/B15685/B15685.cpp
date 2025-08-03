#include<iostream>
#include<vector>
using namespace std;

int n, x, y, d, g;
int dy[] = { 0, -1, 0, 1 };
int dx[] = { 1, 0, -1, 0 };
bool visited[101][101];
int ret = 0;

int main() {
	cin >> n;
	for (int t = 0; t < n; t++) {
		cin >> x >> y >> d >> g;
		vector<int> dir;
		dir.push_back(d);
		for (int i = 0; i < g; i++) {
			int mx = dir.size();
			for (int j = 0; j < mx; j++) {
				dir.push_back((dir[mx - 1 - j] + 1) % 4);
			}
		}
		visited[y][x] = true;
		for (int i = 0; i < dir.size(); i++) {
			int ny = y + dy[dir[i]];
			int nx = x + dx[dir[i]];
			visited[ny][nx] = true;
			y = ny;
			x = nx;
		}
	}
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 100; j++) {
			if (visited[i][j] && visited[i][j + 1] && visited[i + 1][j] && visited[i + 1][j + 1]) {
				ret++;
			}
		}
	}
	cout << ret;
	return 0;
}