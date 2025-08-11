import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class monthly_test_02 {
    static int test_case;
    static int pond_size;
    static int stone_number;
    static int[] power;
    static Point[] p;
    static int[] dx = { -1, 0, 1, 0, -1, -1, 1, 1 };
    static int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
    static int[][] pond;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        test_case = sc.nextInt();
        for (int i = 0; i < test_case; i++) {
            pond_size = sc.nextInt();
            stone_number = sc.nextInt();
            power = new int[stone_number];
            for (int j = 0; j < stone_number; j++) {
                power[j] = sc.nextInt();
            }
            p = new Point[stone_number];
            pond = new int[pond_size][pond_size];
            for (int j = 0; j < stone_number; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                p[j] = new Point(x - 1, y - 1);
            }
            for (int j = 0; j < stone_number; j++) {
                int cur_x = p[j].x;
                int cur_y = p[j].y;
                pond[cur_x][cur_y]++;
                for (int k = 1; k <= power[j]; k++) {
                    for (int dir = 0; dir < 8; dir++) {
                        int nx = cur_x + (k * dx[dir]);
                        int ny = cur_y + (k * dy[dir]);
                        if (nx < 0 || nx >= pond_size || ny < 0 || ny >= pond_size)
                            continue;
                        pond[nx][ny]++;
                    }
                }
            }
            for (int j = 0; j < pond_size; j++) {
                for (int k = 0; k < pond_size; k++) {
                    System.out.printf("%d ", pond[j][k]);
                }
                System.out.println();
            }
            int Max = 0;
            for (int j = 0; j < pond_size; j++) {
                for (int k = 0; k < pond_size; k++) {
                    if (pond[j][k] > Max)
                        Max = pond[j][k];
                }
            }
            System.out.println(Max);
        }

    }

    static class Point {
        int x;
        int y;

        Point() {

        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
