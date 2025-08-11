package backtracking;

import java.io.*;
import java.util.*;

public class b1325_2 {

    // ---------- 입력 파서 (바이트 스트리밍) ----------
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            // skip non-digit
            do { c = read(); } while (c <= 32 && c != -1);
            // sign
            if (c == '-') { s = -1; c = read(); }
            // number
            while (c > 32) {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * s;
        }
    }

    // ---------- 그래프: 압축 인접 리스트 (역방향) ----------
    static int N, M;
    static int[] head;   // 각 정점의 간선 시작 인덱스
    static int[] to;     // 간선의 도착 정점
    static int[] next;   // 같은 시작 정점의 다음 간선 인덱스
    static int edgePtr;  // 간선 카운터

    // 방문 스탬프(라운드 번호) & 큐
    static int[] visited;   // visited[v] == runId 면 이번 라운드에 방문됨
    static int runId = 0;   // 라운드 번호
    static int[] q;         // BFS용 정수 큐 (크기 N면 충분)
    
    // 결과 수집
    static int maxReach = 0;
    static int[] best;      // 최대 도달 수를 갖는 정점 목록
    static int bestCnt = 0;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        // ---------- 입력 ----------
        N = fs.nextInt();
        M = fs.nextInt();

        head = new int[N + 1];
        Arrays.fill(head, -1);

        to   = new int[M];
        next = new int[M];
        edgePtr = 0;

        // 입력 A B : A가 B를 신뢰 → B를 해킹하면 A로 전파 ⇒ 역방향 간선 B -> A
        for (int i = 0; i < M; i++) {
            int A = fs.nextInt();
            int B = fs.nextInt();
            addEdge(B, A); // 역방향 저장
        }

        visited = new int[N + 1]; // 초기 0
        q = new int[N + 1];

        best = new int[N]; // 최악은 전 정점이 동률

        // ---------- 모든 정점에서 BFS, 최대 도달 수/동률 수집 ----------
        for (int s = 1; s <= N; s++) {
            int cnt = bfsCount(s);

            if (cnt > maxReach) {           // 새 최대 발견
                maxReach = cnt;
                bestCnt = 0;
                best[bestCnt++] = s;
            } else if (cnt == maxReach) {   // 동률
                best[bestCnt++] = s;
            }
        }

        // ---------- 출력 ----------
        StringBuilder sb = new StringBuilder(bestCnt * 3);
        for (int i = 0; i < bestCnt; i++) {
            if (i > 0) sb.append(' ');
            sb.append(best[i]);
        }
        System.out.print(sb);
    }

    // 역방향 간선 추가: u -> v
    static void addEdge(int u, int v) {
        to[edgePtr] = v;
        next[edgePtr] = head[u];
        head[u] = edgePtr++;
    }

    // start에서 시작하여 역방향 그래프에서 도달 가능한 정점 수(BFS)
    static int bfsCount(int start) {
        runId++; // 라운드 시작에서 스탬프 증가 (초기화 비용 0)
        int h = 0, t = 0;  // 큐 head/tail

        visited[start] = runId;
        q[t++] = start;

        int cnt = 1; // start 포함
        while (h < t) {
            int u = q[h++];

            // u를 통해 전파 가능한 이웃들 순회
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (visited[v] != runId) {
                    visited[v] = runId;
                    q[t++] = v;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
