import java.util.Scanner;

public class B10971 {
    static int N;
    static int[] result; // 결과를 위한 배열
    static int[][] length; // 거리를 입력 받기 위한 배열
    static boolean[] isVisited; // 방문했는지 체크하기 위한 배열
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        result = new int[N + 1];
        length = new int[N][N];
        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                length[i][j] = sc.nextInt();
            }
        }
        func(0);
        System.out.println(min);
    }

    static void func(int cnt) {
        if (cnt == N) {
            int sum = 0;
            result[N] = result[0];
            for (int i = 0; i < N; i++) {
                if (length[result[i]][result[i + 1]] != 0) {
                    sum += (length[result[i]][result[i + 1]]);
                } else {
                    sum = 10000000;
                }

            }
            if (sum < min)
                min = sum;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isVisited[i])
                continue;
            isVisited[i] = true;
            result[cnt] = i;
            func(cnt + 1);
            isVisited[i] = false;
        }
    }
}
