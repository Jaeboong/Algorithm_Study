#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int main() {
	iostream::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n;

	cin >> n;

    vector<int> arr(n + 1, 0);
    vector<int> size(n + 1, 0);
    vector<pair<int, int>> dist(n + 1, { -1, -1 });
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    stack <int> l;
    stack <int> r;

    for (int i = n - 1; i >= 1; i--) {
        if (arr[i + 1] > arr[i]) {
            r.push(i + 1);
        }
        else {
            while (!r.empty() && arr[r.top()] <= arr[i]) {
                r.pop();
            }
        }
        size[i] += r.size();
        if (!r.empty()) dist[i].second = r.top();
    }
    for (int i = 2; i <= n; i++) {
        if (arr[i - 1] < arr[i]) {
            while (!l.empty() && arr[l.top()] <= arr[i]) {
                l.pop();
            }
        }
        else if (arr[i - 1] > arr[i]) {
            l.push(i - 1);
        }
        size[i] += l.size();
        if (!l.empty()) dist[i].first = l.top();
    }

    for (int i = 1; i <= n; i++) {
        cout << size[i] << " ";
        if (size[i] > 0) {
            if (dist[i].first == -1) {
                cout << dist[i].second;
            }
            else if (dist[i].second == -1) {
                cout << dist[i].first;
            }
            else {
                if ((i - dist[i].first) <= (dist[i].second - i)) {
                    cout << dist[i].first;
                }
                else {
                    cout << dist[i].second;
                }
            }
        }
        cout << '\n';
    }

}