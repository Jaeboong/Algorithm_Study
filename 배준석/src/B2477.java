import java.util.*;
import java.lang.*;
import java.io.*;

class B2477 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine();
        int[] sequence = new int[6];
        int[] length = new int[6];
        int Max_sero = 0;

        int Max_garo = 0;

        for (int i = 0; i < 6; i++) {
            sequence[i] = sc.nextInt();
            length[i] = sc.nextInt();
        }
        for (int i = 0; i < 6; i++) {
            if (sequence[i] == 4 || sequence[i] == 3) {
                if (Max_sero < length[i]) {
                    Max_sero = length[i];
                }
            } else if (sequence[i] == 1 || sequence[i] == 2) {
                if (Max_garo < length[i]) {
                    Max_garo = length[i];
                }
            }
        }
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < 6; i++) {
            if (length[i] == Max_garo) {
                index1 = i;
            }
            if (length[i] == Max_sero) {
                index2 = i;
            }
        }
        index1 = (index1 + 3) % 6;
        index2 = (index2 + 3) % 6;
        int result = 0;
        result = a * ((Max_garo * Max_sero) - (length[index1] * length[index2]));
        System.out.println(result);
    }
}