package In2024.Jan_1;

import java.util.Scanner;

public class Jan10 {
    static int X;
    static int Y;
    static int[][] prices;
    static int MIN;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();
        Y = sc.nextInt();
        prices = new int[X][Y];
        for(int i=0; i<X; i++){
            for(int j=0; j<Y; j++){
                prices[i][j] = sc.nextInt();
            }
        }

        MIN = X*100;

        for(int i=0; i<Y; i++){
            for(int j=-1; j<2; j++){
                if(i+j < 0 || i+j >= Y)
                    continue;
                dfs(j, 1, i+j, prices[0][i+j]);
            }
        }

        System.out.println(MIN);
    }

    public static void dfs(int dir, int x, int y, int sum){
        /*
        출발은 각각 진행
                sum = 처음 좌표 값
        방향은 셋다 가능
        좌표는 x는 1에 y가 위 방향 3개에 맞게 진행됨. 0-N + 1,2,3 케이스

        1. x가 N인지 확인 -> N이면 현재 sum값을 min과 비교후 종료
        2. sum에 현재 좌표 값  더하기
        3. 1,2,3 케이스 중에 가능한 것들로만 진행 : 좌표 이동 하고 현재 정보로 다음 메서드 호출
         */
        if(x == X){
            if(sum < MIN)
                MIN = sum;
            return;
        }

        int temp = sum + prices[x][y];
        for(int i=-1; i<2; i++){
            if(dir == i || y+i < 0 || y+i >= Y)
                continue;
            dfs(i, x+1, y+i, temp);
        }
    }
}
