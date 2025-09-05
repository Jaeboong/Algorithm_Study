import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class s5648 {

    static class Atom implements Comparable<Atom> {
        int x, y, dir, e;

        public Atom(int x, int y, int dir, int e) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.e = e;
        }

        @Override
        public int compareTo(Atom o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }
    }

    static class Pair implements Comparable<Pair> { // 충돌 이벤트 (정렬 기준: time 오름차순)
        int i, j, time;

        public Pair(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.time != o.time) return Integer.compare(this.time, o.time);
            if (this.i != o.i) return Integer.compare(this.i, o.i);
            return Integer.compare(this.j, o.j);
        }
    }

    static int N;
    static ArrayList<Atom> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken()) * 2; // 좌표를 2배로 스케일
                int y = Integer.parseInt(st.nextToken()) * 2;
                int d = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list.add(new Atom(x, y, d, e));
            }

            System.out.println("#" + t + " " + makeBoomPair());
        }
    }

    private static int makeBoomPair() {
        Collections.sort(list);
        ArrayList<Pair> boomList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                Atom a = list.get(i);
                Atom b = list.get(j);

                // 같은 x (수직 이동, y 기준)
                if (a.x == b.x) {
                    // a는 y가 더 작도록 정렬되어 있음
                    if (a.dir == 0 && b.dir == 1) {
                        int time = Math.abs(a.y - b.y) / 2; // 스케일 했으므로 정수
                        boomList.add(new Pair(i, j, time));
                    }
                }

                // 같은 y (수평 이동, x 기준)
                if (a.y == b.y) {
                    if (a.dir == 3 && b.dir == 2) {
                        int time = Math.abs(a.x - b.x) / 2;
                        boomList.add(new Pair(i, j, time));
                    }
                }

                // / 대각선 (x - y 같은 선)
                if (a.x - a.y == b.x - b.y) {
                    if ((a.dir == 3 && b.dir == 1) || (a.dir == 0 && b.dir == 2)) {
                        int time = Math.abs(a.x - b.x); // 스케일로 인해 정수
                        boomList.add(new Pair(i, j, time));
                    }
                }

                // \ 대각선 (x + y 같은 선)
                if (a.x + a.y == b.x + b.y) {
                    if ((a.dir == 1 && b.dir == 2) || (a.dir == 3 && b.dir == 0)) {
                        int time = Math.abs(a.x - b.x);
                        boomList.add(new Pair(i, j, time));
                    }
                }
            }
        }

        return getTotalEnergy(boomList);
    }

    private static int getTotalEnergy(ArrayList<Pair> boomList) {
        Collections.sort(boomList); // time 기준 정렬

        int sum = 0;
        int INF = Integer.MAX_VALUE;
        int boomTimes[] = new int[N];
        Arrays.fill(boomTimes, INF);

        for (Pair p : boomList) {
            // 이미 더 이른 시간에 폭발 예정이면 무시
            if (boomTimes[p.i] < p.time || boomTimes[p.j] < p.time) {
                continue;
            }
            if (boomTimes[p.i] == INF) {
                boomTimes[p.i] = p.time;
                sum += list.get(p.i).e;
            }
            if (boomTimes[p.j] == INF) {
                boomTimes[p.j] = p.time;
                sum += list.get(p.j).e;
            }
        }

        return sum;
    }
}
