import java.util.Scanner;

public class B9095 {
    static int test_case; // 테케
    static int count = 0; // 결과값
    static int sum = 0; // 덧셈 결과가 목표하는 값과 같은지 비교 위한
    static int N = 0; // 목표 숫자 위한

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        test_case = sc.nextInt();
        for (int i = 0; i < test_case; i++) {
            N = sc.nextInt();
            count = 0;
            sum = 0;
            subset(0);
            System.out.println(count);
        }
    }

    static void subset(int sum) {
        if (sum > N)
            return;

        if (sum == N) {
            count++;
            return;
        }

        subset(sum + 3);
        subset(sum + 2);
        subset(sum + 1);
    }
}
