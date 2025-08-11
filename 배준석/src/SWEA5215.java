import java.util.ArrayList;
import java.util.Scanner;

public class SWEA5215 {
    static int foodNum;
    static int calLimit;
    static int Max = 0;
    static ArrayList<Integer> arr;
    static int[] taste;
    static int[] cal;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        foodNum = sc.nextInt();
        calLimit = sc.nextInt();
        arr = new ArrayList<>();
        taste = new int[foodNum];
        cal = new int[foodNum];
        for (int i = 0; i < foodNum; i++) {
            taste[i] = sc.nextInt();
            cal[i] = sc.nextInt();
        }
        for (int i = 1; i <= foodNum; i++) {
            combination(0, 0, i);
        }
        System.out.println(Max);
    }

    static void combination(int cnt, int start, int choice) {
        if (cnt == choice) {
            int calSum = 0;
            int tasteSum = 0;
            for (int i = 0; i < arr.size(); i++) {
                calSum += cal[arr.get(i)];
                tasteSum += taste[arr.get(i)];
            }
            if (calSum > calLimit) {
                return;
            } else {
                if (Max < tasteSum)
                    Max = tasteSum;
            }
            return;
        }

        for (int i = start; i < foodNum; i++) {
            arr.add(i);
            combination(cnt + 1, i + 1, choice);
            arr.remove(arr.size() - 1);
        }
    }
}
