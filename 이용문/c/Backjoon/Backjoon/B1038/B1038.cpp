#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
using namespace std;

int n;
vector<long long> v;

void solve() {
	queue<long long> q;
	for (int i = 0; i <= 9; i++) {
		q.push(i);
		v.push_back(i);
	}
	while (q.size()) {
		long long num = q.front();
		q.pop();
		int last = num % 10;
		for (int i = 0; i < last; i++) {
			long long tmp = num * 10 + i;
			q.push(tmp);
			v.push_back(tmp);
		}
	}
}

int main() {
	cin >> n;
	solve();
	if (v.size() < n) cout << -1;
	else cout << v[n];
	return 0;
}