import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1730 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = '.';
            }
        }
        int cur_x = 0;
        int cur_y = 0;

        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'D') {
                if (cur_x + 1 >= n)
                    continue;
                if (arr[cur_x][cur_y] == '-') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '|';
                }
                cur_x++;
                if (arr[cur_x][cur_y] == '-') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '|';
                }
            } else if (input.charAt(i) == 'R') {
                if (cur_y + 1 >= n)
                    continue;
                if (arr[cur_x][cur_y] == '|') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '-';
                }
                cur_y++;
                if (arr[cur_x][cur_y] == '|') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '-';
                }
            } else if (input.charAt(i) == 'U') {
                if (cur_x - 1 < 0)
                    continue;
                if (arr[cur_x][cur_y] == '-') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '|';
                }
                cur_x--;
                if (arr[cur_x][cur_y] == '-') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '|';
                }
            } else {
                if (cur_y - 1 < 0)
                    continue;
                if (arr[cur_x][cur_y] == '|') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '-';
                }
                cur_y--;
                if (arr[cur_x][cur_y] == '|') {
                    arr[cur_x][cur_y] = '+';
                } else {
                    arr[cur_x][cur_y] = '-';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
