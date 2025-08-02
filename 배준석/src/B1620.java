import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// 처음에 배열로 접근, 시간초과 
public class B1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        int pocketmon = Integer.parseInt(str[0]);
        int question = Integer.parseInt(str[1]);
        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = new String[pocketmon + 1];
        for (int i = 1; i <= pocketmon; i++) {
            String input = br.readLine();
            map.put(input, i);
            arr[i] = input;
        }
        for (int i = 0; i < question; i++) {
            String input = br.readLine();
            if (Character.isDigit(input.charAt(0))) {
                sb.append(arr[Integer.parseInt(input)]).append("\n");
            } else {
                sb.append(map.get(input)).append("\n");
            }
        }
    }
}
