import java.util.Scanner;

public class B16987 {
    static int n;
    static int[] s, w;
    static int ret = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = new int[n];
        w = new int[n];
        for(int i = 0; i < n; i++){
            s[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        solve(0, 0);
        System.out.println(ret);
    }

    public static void solve(int now, int cnt){
        if(now == n){
            ret = Math.max(cnt, ret);
            return;
        }
        if(s[now] <= 0){
            solve(now + 1, cnt);
            return;
        }
        boolean flag = false;
        for(int i = 0; i < n; i++){
            if(i == now) continue;
            if(s[i] <= 0) continue;
            flag = true;
            int tmp = 0;
            s[i] -= w[now];
            s[now] -= w[i];
            if(s[i] <= 0){
                tmp++;
            }
            if(s[now] <= 0){
                tmp++;
            }
            solve(now + 1, cnt + tmp);
            s[i] += w[now];
            s[now] += w[i];
        }
        if(!flag){
            solve(now + 1, cnt);
        }
    }
}
