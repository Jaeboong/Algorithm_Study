#include <iostream>
#include <pair>
#include <stack>

using namespace std;

int n;
    int Map[10000], Cnt[10000], near[10000];
    stack<pair<int, int>> Stack, Stack_2;
int main(){
    cin >> n;
    for(int i = 1; i <= n; i++){
        cin >> Map[i];
    }

    for(int i = 1; i <= n; i++){
        while(!Stack.empty() && Stack.top() <= Map[i]) Stack.pop();
        Cnt[i] += Stack.size();
        if(!Stack.empty() && near[i] == 0) near[i] = Stack.top().second;
        Stack.push({Map[i], i});
    }

    for(int i = n -1; i >= 1; i--){
        while(!Stack)
    }
}