#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
#include<unordered_map>
using namespace std;

int n;
long long m;
unordered_map<long long, long long> cnt;

int main() {
	cin >> n >> m;

	long long s = 0;
	long long ret = 0;
	cnt[0] = 1;
	for (int i = 0; i < n; i++) {
		long long num;
		cin >> num;
		s += num;

		auto it = cnt.find(s - m);
		if (it != cnt.end()) ret += it->second;
		cnt[s]++;
	}

	cout << ret << '\n';
	return 0;
}

