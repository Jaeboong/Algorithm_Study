import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class B4673 {
    static HashSet<Integer> arr; // 중복을 방지하기 위해서는 hashset 사용하고

    public static void main(String[] args) {
        // String[] str = new String[] { "1", "3", "5", "7", "9", "20", "31", "42",
        // "53", "64", "75", "86", "97" };
        arr = new HashSet<>();
        for (int i = 1; i <= 10000; i++) { // 문제에서 주어지는 것 뿐만 아니라 다른 값들도 검사해야함
            transform(String.valueOf(i));
        }

        for (int i = 1; i <= 10000; i++) {
            if (!arr.contains(i)) {
                System.out.println(i);
            }
        } // 결과값 출력
    }

    static void transform(String str) {
        int number = 0;
        String num = "";
        if (Integer.parseInt(str) > 10000)
            return;

        for (int i = 0; i < str.length(); i++) {
            number += (str.charAt(i) - '0');
        }
        number += Integer.parseInt(str);
        arr.add(number);
        num = String.valueOf(number);
        transform(num);
    }
}
