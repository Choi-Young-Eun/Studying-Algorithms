package In2024.Jan_1;

import java.util.Scanner;

public class Jan25 {
    public static void main(String[] args) {
        //1문제 풀이 완료 : 구현(백준:1913)
    }

    public void q_1913() {
        Scanner sc = new Scanner(System.in);
        StringBuilder str = new StringBuilder();
        int N = sc.nextInt();
        int num = sc.nextInt();
        int count = N*N;
        int[][] arr = new int[N][N];
        int[] result = new int[2];
        int[] moveX = {1, 0, -1, 0};
        int[] moveY = {0, 1, 0, -1};
        int x = 0;
        int y = 0;
        int dir = 0;

        while(count >= 1){
            if(count == num) {
                result[0] = x+1;
                result[1] = y+1;
            }
            arr[x][y] = count--;
            if(x+moveX[dir] < 0 || y+moveY[dir] < 0 || x+moveX[dir] >= N || y+moveY[dir] >= N || arr[x+moveX[dir]][y+moveY[dir]] != 0){
                if(dir == 3)
                    dir = 0;
                else dir += 1;
            }
            x += moveX[dir];
            y += moveY[dir];
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                str.append(arr[i][j]+" ");
            }
            str.append("\n");
        }
        str.append(result[0]+" "+result[1]);
        System.out.println(str);
    }
}
