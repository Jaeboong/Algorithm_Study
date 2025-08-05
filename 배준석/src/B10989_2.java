import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

// 계수 정렬, BufferedWriter 사용
public class B10989_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] count = new int[10001];

        for (int i = 0; i < n; i++) {
            count[Integer.parseInt(br.readLine())]++;
        }

        for (int i = 1; i < 10001; i++) {
            for (int j = 0; j < count[i]; j++) {
                bw.write(i + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}