import java.util.ArrayList;
import java.util.Scanner;

public class B15686 {
    static int N;
    static int M;
    static int[][] chicken;
    static boolean[] isSelected;
    static ArrayList<int[]> arr;
    static ArrayList<int[]> arr2;
    static int[] result;
    static int total = Integer.MAX_VALUE;
    static int Dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        chicken = new int[N][N];
        arr2 = new ArrayList<>();
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chicken[i][j] = sc.nextInt();
                if (chicken[i][j] == 1) {
                    arr.add(new int[] { i, j });
                }
                if (chicken[i][j] == 2) {
                    arr2.add(new int[] { i, j });
                }
            }
        }
        isSelected = new boolean[arr2.size()];
        result = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = Integer.MAX_VALUE;
        }
        func(0, 0);

        System.out.println(total);
    }

    static void func(int cnt, int idx) {
        if (cnt == M) {
            for (int i = 0; i < arr.size(); i++) {
                result[i] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < arr2.size(); j++) {
                    if (isSelected[j]) {
                        int sum = Math.abs(arr.get(i)[0] - arr2.get(j)[0]) + Math.abs(arr.get(i)[1] - arr2.get(j)[1]);
                        if (result[i] > sum) {
                            result[i] = sum;
                        }
                    }
                }
            }
            Dist = 0;
            for (int i = 0; i < result.length; i++) {
                Dist += result[i];
            }
            if (Dist < total) {
                total = Dist;
            }
            return;
        }

        for (int i = idx; i < arr2.size(); i++) {
            isSelected[i] = true;
            func(cnt + 1, i + 1);
            isSelected[i] = false;
        }

    }
}
