#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
#include<unordered_map>
#include<vector>
using namespace std;

int T = 10;
struct Node{
	int data;
	Node* next;
};

constexpr int MAX_NODE = 4501;

int node_count;
Node node_pool[MAX_NODE];

Node* new_node(int x) {
	node_pool[node_count].data = x;
	node_pool[node_count].next = nullptr;
	return &node_pool[node_count++];
}

class SingleLinkedList {
	Node head;
	Node* tail;
	int size;

	Node* getPrev(int idx) {
		Node* p = &head;
		for (int i = 0; i < idx && p->next != nullptr; i++) {
			p = p->next;
		}
		return p;
	}

public:
	SingleLinkedList() { init(); }
	
	void init() {
		head.next = nullptr;
		tail = &head;
		size = 0;
		node_count = 0;
	}

	int getSize() {
		return size;
	}

	void push_back(int x) {
		Node* nd = new_node(x);
		tail->next = nd;
		tail = nd;
		size++;
	}

	void build(vector<int>& v) {
		for (int x : v) {
			push_back(x);
		}
	}

	void insertAt(int idx, vector<int>& v) {
		Node* prev = getPrev(idx);
		for (int x : v) {
			Node* nd = new_node(x);
			nd->next = prev->next;
			prev->next = nd;
			prev = nd;
			size++;
		}
		if (prev->next == nullptr) tail = prev;
	}

	void deleteAt(int idx, int y) {
		Node* prev = getPrev(idx);
		while (y-- > 0 && prev->next != nullptr) {
			Node* tgt = prev->next;
			prev->next = tgt->next;
			if (tgt == tail) tail = prev;
			size--;
		}
	}

	void append(vector<int>& v) {
		for (int x : v) {
			push_back(x);
		}
	}

	void printFirst(int k) {
		Node* p = head.next;
		for (int i = 0; i < k && p != nullptr; i++) {
			cout << p->data << " ";
			p = p->next;
		}
		cout << '\n';
	}
};

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	for (int t = 1; t <= T; t++) {
		int n;
		cin >> n;
		vector<int> initV(n);
		for (int i = 0; i < n; i++) {
			cin >> initV[i];
		}
		SingleLinkedList L;
		L.build(initV);

		int m;
		cin >> m;
		for (int i = 0; i < m; i++) {
			char cmd;
			cin >> cmd;
			if (cmd == 'I') {
				int x, y;
				cin >> x >> y;
				vector<int> v(y);
				for (int j = 0; j < y; j++) {
					cin >> v[j];
				}
				L.insertAt(x, v);
			}
			else if (cmd == 'D') {
				int x, y;
				cin >> x >> y;
				L.deleteAt(x, y);
			}
			else if (cmd == 'A') {
				int y;
				cin >>  y;
				vector<int> v(y);
				for (int j = 0; j < y; j++) {
					cin >> v[j];
				}
				L.append(v);
			}
		}
		cout << "#" << t << " ";
		L.printFirst(10);
	}
}