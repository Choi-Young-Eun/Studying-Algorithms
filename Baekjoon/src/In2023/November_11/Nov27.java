package In2023.November_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Nov27 {
    public static void main(String[] args) throws IOException {
        //1문제 풀이 완료 : 탐색(2178-미로 탐색)

        /*
        1. 상하좌우 세팅
        : x와 y 모두 +1 해주기
        x배열 : -1 +1 0 0
        y배열 : 0 0 -1 +1

        2. 입력 값
        1줄 : x좌표 N, y좌표 M
        2줄~ : M칸의 1 or 0이 N줄 쭈르륵
        -> 공백이 없으므로 String으로 받아 하나씩 끊기

        3. 배열 2개
        boolean[][] : 위치 들렸는지 안들렸는지 체크
        int[][] : -1,0 배열 및 횟수 체크 배열

        4. int[][] 배열 세팅
        for i
        한 줄 str에 입력 받기
        for j
        [i][j]에 돌면서 str.j번째를 숫자로 변경하여 넣기

        5. BFS 돌리기
         */

        int[] X = {-1, +1, 0, 0};
        int[] Y = {0, 0, -1, +1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] visit = new boolean[N][M];
        int[][] target = new int[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                target[i][j] = str.charAt(j)-'0';
            }
        }


        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        visit[0][0]=true;
        while(!queue.isEmpty()){
            int[] t = queue.remove();
            int xx = t[0];
            int yy = t[1];
            for(int i=0; i<4; i++){
                int nx = xx+X[i];
                int ny = yy+Y[i];
                if(nx < 0 || ny < 0 || nx > N-1 || ny > M-1) //1. 좌표 값을 넘어가면 다음으로 넘어가기
                    continue;
                if(target[nx][ny] == 0 || visit[nx][ny]) //2. 0이거나, 방문한 곳이면 다음으로 넘어가기
                    continue;
                queue.add(new int[]{nx,ny});
                visit[nx][ny]=true;
                target[nx][ny] = target[xx][yy]+1;
                //-> 난 단계 수 세는 거에 이전꺼를 활용하려고 했는데 보니까 다음꺼를 담을 노드의 값을 ++해서 수정하네 대박
            }
        }
        System.out.print(target[N-1][M-1]);
    }
}
