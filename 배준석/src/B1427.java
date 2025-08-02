import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class B1427 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        Integer[] arr = new Integer[a.length()];
        for (int i = 0; i < a.length(); i++) {
            arr[i] = a.charAt(i) - '0';
        }
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < a.length(); i++) {
            System.out.println(arr);
        }
    }
}
