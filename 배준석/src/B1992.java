import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B1992 {
    static int N;
    static int[][] map;
    static ArrayList<Character> result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        function(0, 0, N);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
        }
    }

    static void function(int x, int y, int size) {
        int sum = 0;
        for (int i = x, xEnd = x + size; i < xEnd; i++) {
            for (int j = y, yEnd = y + size; j < yEnd; j++) {
                sum += map[i][j];
            }
        }
        if (sum == size * size) {
            result.add('1');
            return;
        }
        if (sum == 0) {
            result.add('0');
            return;
        }

        int newSize = size / 2;
        result.add('(');
        function(x, y, newSize);
        function(x, y + newSize, newSize);
        function(x + newSize, y, newSize);
        function(x + newSize, y + newSize, newSize);
        result.add(')');
    }
}
