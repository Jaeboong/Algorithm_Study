import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9037 {
    public static void main(String[] args) throws IOException {
        int n = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int Student = Integer.parseInt(br.readLine());
            int count = 0;
            int[] arr_candy = new int[Student];
            int[] arr_temp = new int[Student];
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < Student; j++) {
                arr_candy[j] = Integer.parseInt(str[j]);
                if (arr_candy[j] % 2 != 0) {
                    arr_candy[j]++;
                }
            }
            while (true) {
                int b = 0;
                for (int j = 0; j < Student; j++) {
                    arr_temp[j] = arr_candy[j] / 2;

                }
                for (int j = 0; j < Student; j++) {
                    arr_candy[j] = arr_candy[j] / 2;
                }
                for (int j = 0; j < Student; j++) {
                    arr_candy[(j + 1) % Student] = arr_candy[(j + 1) % Student] + arr_temp[j];
                }
                for (int j = 0; j < Student; j++) {
                    if (arr_candy[j] % 2 != 2) {
                        arr_candy[j]++;
                    }
                }
                count++;
                for (int j = 1; j < Student; j++) {
                    if (arr_candy[0] == arr_candy[j]) {
                        b++;
                    }
                }
                if (b == (Student - 1)) {
                    break;
                }
            }
            System.out.println(count);
        }
    }
}
