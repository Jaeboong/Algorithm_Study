#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
using namespace std;

// 배열 크기 [n + 1] 1 ~ n까지
// 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름
// n을 넘어가면 1로 넘어간다
// 8방향 1부터 왼쪽부터 시계방향
// 1. 모든 구름이 d 방향으로 s칸 이동
// 2. 모든 구름이 있는 칸의 바구니 +1
// 3. 구름 모두 삭제
// 4. 물이 증가한칸(구름이 있었던 칸) 물복사 -> 대각선 방향 거리가 1인 칸에 물이있는 바구니의 수만큼 물의 양 증가(최대 4)
// -> 복사는 칸의 경계를 넘을 수 없다.
// 5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생기고 물의 양 -2 하지만 3에서 구름이 사라진 칸에서는 생성 불가

int n, m;
int a[51][51];
int d, s;
bool visited[51][51];
int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
queue<pair<int,int>> q;

int main(){
    cin >> n >> m;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            cin >> a[i][j];
        }
    }
    q.push({n, 1});
    q.push({n, 2});
    q.push({n - 1, 1});
    q.push({n - 1, 2});

    for(int t = 0; t < m; t++){
        fill(&visited[0][0], &visited[0][0] + 51*51, false);
        int d, s;
        cin >> d >> s;
        int k = q.size();
        for(int i = 0; i < k; i++){
            int y = q.front().first;
            int x = q.front().second;
            q.pop();
            int ny = ((y - 1 + s * dy[d - 1]) % n + n) % n + 1;
            int nx = ((x - 1 + s * dx[d - 1]) % n + n) % n + 1;
            a[ny][nx]++;
            visited[ny][nx] = true;
            q.push({ny, nx});
        }
        k = q.size();
        for(int i = 0; i < k; i++){
            int y = q.front().first;
            int x = q.front().second;
            q.pop();
            int cnt = 0;
            for(int j = 1; j < 8; j += 2){
                int ny = y + dy[j];
                int nx = x + dx[j];
                if(ny < 1 || nx < 1 || ny > n || nx > n) continue;
                if(a[ny][nx]) cnt++;
            }
            a[y][x] += cnt;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(a[i][j] >= 2 && !visited[i][j]){
                    q.push({i, j});
                    a[i][j] -= 2;
                }
            }
        }
    }
    int ret = 0;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            ret += a[i][j];
        }
    }
    cout << ret;
}
