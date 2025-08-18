#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
#include<unordered_map>
using namespace std;

int n, m;
vector<vector<int>> v1, v2;
int visited[501];
int cnt = 0;

void dfs(int cur, vector<vector<int>> &v) {
	visited[cur] = 1;
	for (int next : v[cur]) {
		if (visited[next]) continue;
		visited[next] = 1;
		cnt++;
		dfs(next, v);
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> m;
	v1.resize(n + 1);
	v2.resize(n + 1);

	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		v1[a].push_back(b);
		v2[b].push_back(a);
	}

	int ret = 0;
	for (int i = 1; i <= n; i++) {
		fill(visited, visited + 501, 0);
		cnt = 0;
		dfs(i, v1);
		int big = cnt;
		
		fill(visited, visited + 501, 0);
		cnt = 0;
		dfs(i, v2);
		int small = cnt;
		
		if (big + small == n - 1) ret++;
	}
	cout << ret;
}