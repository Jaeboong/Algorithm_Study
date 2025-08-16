#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
#include<unordered_map>
using namespace std;

int T;

int main() {
	cin >> T;
	for (int t = 1; t <= T; t++) {
		int n, m;
		cin >> n >> m;
		int bit = (1 << n) - 1;
		if ((m & bit) == bit) cout << "#" << t << " ON" << '\n';
		else cout << "#" << t << " OFF" << '\n';
	}
}