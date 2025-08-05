#include<iostream>
#include<stack>
#include<vector>
using namespace std;

int n;
vector<int> h, cnt, ret;

int main() {
	cin >> n;
	h.resize(n);
	cnt.assign(n, 0);
	ret.assign(n, -1);
	for (int i = 0; i < n; i++) {
		cin >> h[i];
	}
	//�����ʿ��� �������� ���� ���� üũ
	stack<int> stk;
	for (int i = 0; i < n; i++) {
		while (!stk.empty() && h[stk.top()] <= h[i]) {
			stk.pop();
		}
		// h[i] ���� ������ stk�� ���������� ���� ������ cnt[i]�� ����� ���ص� ������.
		cnt[i] += stk.size(); 
		if (stk.size()) {
			ret[i] = stk.top();
		}
		stk.push(i);
	}

	while (stk.size()) stk.pop();

	//���ʿ��� ���������� ���� ����
	for (int i = n - 1; i >= 0; i--) {
		while (!stk.empty() && h[stk.top()] <= h[i]) {
			stk.pop();
		}
		cnt[i] += stk.size();
		if (stk.size()) {
			if (ret[i] == -1) ret[i] = stk.top();
			else {
				int a = abs(ret[i] - i);
				int b = abs(stk.top() - i);
				if (a > b || (a == b) && stk.top() < ret[i]) {
					ret[i] = stk.top();
				}
			}
		}
		stk.push(i);
	}
	for (int i = 0; i < n; i++) {
		if (cnt[i] == 0) cout << 0 << '\n';
		else cout << cnt[i] << ' ' << ret[i] + 1 << '\n';
	}
	return 0;
}