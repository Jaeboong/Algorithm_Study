import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SWEA2382 {
    static int[] dx = { 0, -1, 1, 0, 0 }; // 1:상, 2:하, 3:좌, 4:우
    static int[] dy = { 0, 0, 0, -1, 1 };
    static int N, M, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            ArrayList<Microorganism> microlist = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int size = sc.nextInt();
                int dir = sc.nextInt();
                microlist.add(new Microorganism(size, x, y, dir));
            }

            // M시간 동안 시뮬레이션
            for (int t = 0; t < M; t++) {
                // 1. 이동
                for (int j = 0; j < microlist.size(); j++) {
                    microlist.get(j).move();
                }

                // 2. 좌표별 그룹화
                HashMap<String, ArrayList<Microorganism>> map = new HashMap<>();
                for (int j = 0; j < microlist.size(); j++) {
                    Microorganism m = microlist.get(j);
                    if (m.size == 0)
                        continue; // 죽은 군집 무시
                    String key = m.x + "," + m.y;
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<Microorganism>());
                    }
                    map.get(key).add(m);
                }

                // 3. 충돌 처리
                ArrayList<Microorganism> newList = new ArrayList<>();
                ArrayList<ArrayList<Microorganism>> values = new ArrayList<>(map.values());

                for (int j = 0; j < values.size(); j++) {
                    ArrayList<Microorganism> group = values.get(j);
                    if (group.size() == 1) {
                        newList.add(group.get(0));
                    } else {
                        int totalSize = 0;
                        int maxSize = -1;
                        int dir = 0;
                        int x = group.get(0).x;
                        int y = group.get(0).y;

                        for (int k = 0; k < group.size(); k++) {
                            Microorganism m = group.get(k);
                            totalSize += m.size;
                            if (m.size > maxSize) {
                                maxSize = m.size;
                                dir = m.dir; // 가장 큰 군집 방향 유지
                            }
                        }
                        newList.add(new Microorganism(totalSize, x, y, dir));
                    }
                }

                // 4. 리스트 갱신
                microlist = newList;
            }

            // 최종 결과 계산
            int answer = 0;
            for (int i = 0; i < microlist.size(); i++) {
                answer += microlist.get(i).size;
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static class Microorganism {
        int size;
        int x, y;
        int dir;

        public Microorganism(int size, int x, int y, int dir) {
            this.size = size;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void move() {
            x += dx[dir];
            y += dy[dir];
            // 약품 구역 도착 시 처리
            if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
                size /= 2;
                if (size == 0)
                    return;
                if (dir % 2 == 0)
                    dir -= 1; // 2,4 → 1,3
                else
                    dir += 1; // 1,3 → 2,4
            }
        }
    }
}
