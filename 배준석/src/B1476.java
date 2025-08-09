import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        String[] input = br.readLine().split(" ");
        int[] input_to_int = new int[3];
        int age = 1;
        int[] s = { 1, 1, 1 };
        for (int i = 0; i < input.length; i++) {
            input_to_int[i] = Integer.parseInt(input[i]);
        }
        while (true) {
            if (s[0] == input_to_int[0] && s[1] == input_to_int[1] && s[2] == input_to_int[2]) {
                break;
            }
            // s[0]++;
            // s[1]++;
            // s[2]++;
            s[0] = s[0] % 15;
            s[1] = s[1] % 28;
            s[2] = s[2] % 19;
            age++;
        }
        System.out.println(age);
    }
}
