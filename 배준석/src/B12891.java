import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class B12891 { // 접근 자체가 틀림, 슬라이딩 윈도우로 접근해야 한다고 함
    static int N;
    static int M;
    static String str = new String(); // 문자열 입력 받음
    static boolean[] isSelected;
    static int[] countDNA; // 결과가 이렇게 나와야 함을 받는 배열
    static int[] resultCountDNA; // 내가 만든 조합에서의 카운트
    static int count = 0;
    static HashSet<String> hash;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        str = sc.next();
        isSelected = new boolean[str.length()];
        countDNA = new int[4];
        resultCountDNA = new int[4];
        hash = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            countDNA[i] = sc.nextInt();
        }
        count(0, 0);
        System.out.println(count);
    }

    static void count(int cnt, int start) {

        if (cnt == M) {
            Arrays.fill(resultCountDNA, 0);
            boolean isOK = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    sb.append(str.charAt(i));
                }
            }
            String s = sb.toString();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'A') {
                    resultCountDNA[0]++;
                } else if (s.charAt(i) == 'C') {
                    resultCountDNA[1]++;
                } else if (s.charAt(i) == 'G') {
                    resultCountDNA[2]++;
                } else if (s.charAt(i) == 'T') {
                    resultCountDNA[3]++;
                } else {
                    continue;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (resultCountDNA[i] < countDNA[i]) {
                    isOK = false;
                    break;
                }
            }
            if (isOK && !hash.contains(s)) {
                hash.add(s);
                count++;
            }
        }

        for (int i = start; i < str.length(); i++) {
            isSelected[i] = true;
            count(cnt + 1, i + 1);
            isSelected[i] = false;
        }
    }
}
