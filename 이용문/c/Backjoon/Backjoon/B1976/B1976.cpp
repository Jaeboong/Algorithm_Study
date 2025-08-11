#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
using namespace std;

int n, m;
int parent[201];

int find(int x) {
	if (x == parent[x]) return x;
	return parent[x] = find(parent[x]);
}

void merge(int x, int y) {
	x = find(x);
	y = find(y);
	if (x == y) return;
	parent[x] = y;
}

int main() {
	cin >> n >> m;
	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int num;
			cin >> num;
			if (num == 1) {
				merge(i, j);
			}
		}
	}
	int prev = -1;
	for (int i = 0; i < m; i++) {
		int next;
		cin >> next;
		if (prev == -1) {
			prev = find(next);
		}
		else if (prev != find(next)) {
			cout << "NO";
			return 0;
		}
	}
	cout << "YES";
	return 0;
}