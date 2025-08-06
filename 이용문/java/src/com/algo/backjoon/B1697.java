import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1697{

    static int n, k;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        int ret = 0;
        while(q.size() != 0){
            int t = q.size();
            for(int i = 0; i < t; i++){
                int cur = q.poll();
                if(cur == k){
                    System.out.println(ret);
                    return;
                }
                if(cur + 1 <= 100000 && !visited[cur + 1]){
                    q.offer(cur + 1);
                    visited[cur + 1] = true;
                }
                if(cur - 1 >= 0 && !visited[cur - 1]){
                    q.offer(cur - 1);
                    visited[cur - 1] = true;
                }
                if(cur * 2 <= 100000 && !visited[cur * 2]){
                    q.offer(cur*2);
                    visited[cur * 2] = true;
                }
            }
            ret++;
        }
    }
}