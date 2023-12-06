package In2023.December_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dec4 {
    static int N;
    static int M;
    static int[][] space;
    static int[][] front = {{0, -1},{-1, 0},{0, 1},{1, 0}};
    static int[][] back = {{0, 1},{1, 0},{0, -1},{-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int crt_x = Integer.parseInt(st.nextToken());
        int crt_y = Integer.parseInt(st.nextToken());
        int direct = Integer.parseInt(st.nextToken());

        space = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(crt_x, crt_y, direct));
        /*
        //문제 이해가 안됨
        1. 3번의 경우가 무슨 말인지 모르겠음
        청소하고 앞에보고 90도 돌려서 진행하라는 건지 아님
        : (0,0) 시작이니까 (r,c)는 (r+1, c+1)번째 좌표임
        : 청소할 곳이 없는 경우 같은 방향으로 보고 한칸 후진 (후진한 값이 0이면 1부터 진행, 1이면 끝)
        : 있는 경우 90도 돌고 앞에 확인해서 아직 가지 않은 빈칸이면 앞으로 가서 1부터 진행)

        ** 배열
        방향은 0-3까지 돌아감 +1해서 4면 0으로
        한칸 전진 front = (0, -1) (+1, 0) (0, +1) (-1, 0)
        한칸 후진 back = (0, +1) (-1, 0) (0, -1) (+1, 0)
        int[N][M] space

        ** int DFS (x, y, dir)
        int배열로 진행
        청소 하는 걸 count+1값으로 하기
        int[][] space
        (현재 위치와 방향) - int crt_x, int crt_y, int direct (0-3)
        0) 해당 칸의 값이 0인 경우 int[][] space에 count+1 값 저장
            boolean check = false
            if(space[][] == 0) space[][] = count+1;
        1) 주변에서 0인곳을 찾기 (위, 아래, 양 옆) 확인 해당 값에서 방향을 기준으로 순서를 돌기 방향이 1이면 2 -> 3 -> 0 -> 1 순으로 한칸 앞의 값을 확인
            for(int i=1; i<=4; i++){
             direct = (direct+1)%4
             int x, int y = front[direct]
             if(space[x][y] == 0)
              crt_x, crt_y = x, y
              check = true  //다음으로 넘어갈지 체크하는 boolean 변수
              break
             }
        방향 +1 세팅 (direct = (direct+1)%4)
        해당 방향 +1 상태 확인 int front[direct]를 반영한 좌표 값 상태가 0이 아니라면 continue;
        상태가 0이라면 해당 좌표값과 방향을 세팅하여 다음으로 넘어감
        2) 4군데 다 돌았다면 원래 방향 (맨 마지막 방향이 원래 방향이 됨)을 기준으로 뒤로 한 칸의 값이 1인지 확인
             int crt_x, int crt_y = back[direct] //메인 x, y좌표를 이걸로 설정
             if(space[x][y] == 1) break; //뒤가 벽이라 종료
        3) count 반환
         */
    }

    static int dfs(int crt_x, int crt_y, int direct) {
        int count = 0;
        if(direct == 1) direct = 3;
        if(direct == 3) direct = 1;
        while(true){
            boolean check = false;
            if(space[crt_x][crt_y]==0){
                space[crt_x][crt_y]=-1;
                count++;
            } //count++1

            for(int i=1; i<=4; i++){
                direct = (direct+1)%4;
                int x = crt_x+front[direct][0];
                int y = crt_y+front[direct][1];
                if(x>M-1 || y>N-1 || x<0 || y<0) continue;
                if(space[x][y] == 0){
                    crt_x = x;
                    crt_y = y;
                    check = true;
                    break;
                }
            }
            if(check) continue;
            crt_x += back[direct][0];
            crt_y += back[direct][1];
            if(space[crt_x][crt_y] == 1) break;
        }
        return count;
    }
}
