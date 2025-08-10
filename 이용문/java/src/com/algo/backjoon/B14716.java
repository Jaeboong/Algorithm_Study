import java.util.Scanner;

public class B14716 {
    static int n, m;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1 ,-1, -1};
    static int cnt = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                a[i][j] = sc.nextInt();
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]) continue;
                if(a[i][j] == 1){
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void dfs(int y, int x){
        visited[y][x] = true;
        for(int i = 0; i < 8; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
            if(a[ny][nx] == 0 || visited[ny][nx]) continue;
            dfs(ny, nx);
        }
    }
}