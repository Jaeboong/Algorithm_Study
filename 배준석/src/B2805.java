import java.util.Arrays;
import java.util.Scanner;

public class B2805 {
    static int N;
    static int M;
    static int[] arr;
    static int[] result;
    static int Max = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            if (arr[i] > Max) {
                Max = arr[i];
            }
        }

        int end = Max;
        int start = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] - mid > 0) {
                    sum += (arr[i] - mid);
                }
            }
            if (sum >= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }
}
