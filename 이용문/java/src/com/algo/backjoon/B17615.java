import java.util.Scanner;

public class B17615 {
    static int n;
    static String s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.next();
        char start = s.charAt(0);
        char end = s.charAt(n - 1);
        int cnt_s = 0;
        int cnt_e = 0;
        int cnt = 0;
        char prev = start;
        boolean flag = false;
        for(int i = 1; i < n; i++){
            if(prev != s.charAt(i) && s.charAt(i) == start){
                flag = true;
            }
            if(flag && s.charAt(i) == start){
                cnt_s++;
            }
            if(s.charAt(i) != start) cnt++;
            prev = s.charAt(i);
        }
        cnt_s = Math.min(cnt, cnt_s);
        prev = end;
        flag = false;
        cnt = 0;
        for(int i = n - 2; i >= 0; i--){
            if(prev != s.charAt(i) && s.charAt(i) == end){
                flag = true;
            }
            if(flag && s.charAt(i) == end){
                cnt_e++;
            }
            if(s.charAt(i) != end) cnt++;
            prev = s.charAt(i);
        }
        cnt_e = Math.min(cnt, cnt_e);
        int ret = Math.min(cnt_s, cnt_e);
        System.out.println(ret);
    }
}
