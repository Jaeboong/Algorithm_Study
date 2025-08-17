import java.util.Scanner;

public class practice {
    static int N;
    static int M;
    static boolean[] isSelected;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        isSelected = new boolean[N + 1];
        arr = new int[M];
        // permutation(0);
        // duplication_permutation(0);
        combination(0, 1);
        System.out.println(sb);
    }

    static void permutation(int cnt) {
        if (cnt == M) {
            for (int k = 0; k < M; k++) {

                sb.append(arr[k]).append(" ");

            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i])
                continue;
            arr[cnt] = i;
            isSelected[i] = true;
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }

    static void duplication_permutation(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[cnt] = i;
            duplication_permutation(cnt + 1);
        }
    }

    static void combination(int cnt, int start) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }
}