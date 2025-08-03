#include <iostream>

using namespace std;

int main() {
	iostream::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n, m, R;
	int map[301][301];


	cin >> n >> m >> R;


	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			cin >> map[i][j];
		}
	}

	int layers = min(n,m)/2;

	for (int i = 0; i < R; ++i) {
		for (int layer = 0; layer < layers; ++layer) {
			int tmp = map[layer][layer];
			int r = n - layer - 1, c = m - layer - 1;

			for (int j = layer; j < c;++j) {
				map[layer][j] = map[layer][j + 1];
			}
			for (int j = layer; j < r; ++j) {
				map[j][c] = map[j+1][c];
			}
			for (int j = c; j > layer; --j) {
				map[r][j] = map[r][j-1];
			}
			for (int j = r; j > layer; --j) {
				map[j][layer] = map[j-1][layer];
			}
			map[layer + 1][layer] = tmp;
		}
	}

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m;++j) {
			cout << map[i][j] << " ";
		}
		cout << '\n';
	}


}