import java.util.Arrays;
import java.util.Scanner;

public class BOJ16200 {
    static int n;
    static int[] a;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        int ret = 0;
        int limit = -1;
        int cnt = 0;
        for(int i = 0; i < n; i++){
            if(limit == -1){
                limit = a[i];
            }
            cnt++;
            if(limit == cnt){
                limit = -1;
                cnt = 0;
                ret++;
            }
        }
        if(cnt != 0) ret++;
        System.out.println(ret);
    }
}
