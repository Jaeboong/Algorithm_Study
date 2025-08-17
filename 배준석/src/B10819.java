import java.util.Scanner;

public class B10819 {
    static int N;
    static int[] arr;
    static int Max = 0;
    static boolean[] isSelected;
    static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        isSelected = new boolean[N];
        result = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        findMax(0);
        System.out.println(Max);
    }

    static void findMax(int cnt) {
        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(result[i] - result[i + 1]);
            }
            if (sum > Max)
                Max = sum;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            result[cnt] = arr[i];
            findMax(cnt + 1);
            isSelected[i] = false;
        }
    }
}
