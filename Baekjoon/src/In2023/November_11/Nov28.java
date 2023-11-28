package In2023.November_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Nov28 {
    static boolean[][] visit;
    static int[][] target;
    static int N;
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        //2문제 풀이 완료 : 탐색(2667-단지번호붙이기 & 2468-안전영역)
    }

    public void q_2667() throws IOException {
        /*
        * 입력 값
        1줄 : 한 줄의 크기 N (5~25사이)
        2줄~ : NxN의 0,1로 이루어진 2차원 배열

        * 필요한 것
        int[] X : 상하좌우 x좌표
        int[] Y : 상하좌우 y좌표
        boolean[][] visit 배열 : 이미 간 건지 확인
        int[][] target 배열 : 1,0 값으로 이루어진 배열
        ArrayList<Integer> result 배열 : BFS 종료 후 값이 담길 리스트
        -> BFS를 void가 아닌 int로 하면 static으로 하지 않아도 됨

        * BFS vs DFS
        BFS 가 나을 것 같음 : 깊이대로 내려갔다가 다시 올라오는 것보다 넓게 퍼지면서 ++하는 게 나을듯

        1. for : 차례대로 0,0부터 N-1,N-1까지 돌기
        - 0이거나 visit true면 넘어가기
        - 아니면 그 점 기준으로 BFS 시작
        2. BFS(n,m) 실행 후 return 값 ArrayList<Integer>에 add
        3. ArrayList<Integer> sort : 오름차순
        4. ArrayList<Integer> size 출력하고
        5. ArrayList<Integer> for문으로 한줄에 하나씩 출력
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        //if(N<5 || N>25) return;
        visit = new boolean[N][N];
        target = new int[N][N];
        ArrayList<Integer> result = new ArrayList<>(); // BFS 종료 후 단지 수가 담길 리스트

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                target[i][j] = str.charAt(j)-'0';
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0;j<N;j++){
                if(target[i][j] == 0 || visit[i][j]){
                    continue;
                }
                result.add(bfs_2667(i,j));
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for(int i:result){
            System.out.println(i);
        }
    }
    private static Integer bfs_2667(int x, int y) {
        /* BFS(n,m)
        1. 큐에 넣기
        2. visit = true로 변경
        3. while (큐.is not empty)
         상하좌우 각각 체크 (현재 배열에 +1씩 해서 탐색)
         1) x, y좌표가 유효한 범위를 넘어가면 continue
         2) 값이 0 혹은 visit true인 경우 continue
         3) 통과하는 경우
            큐에 넣기
            visit = true로 변경
            갯수 세는 변수에 +1하기
        4. 갯수 세는 변수 return
        */

        Queue<int[]> queue = new LinkedList<>();
        int result = 1;
        queue.add(new int[]{x,y});
        visit[x][y]=true;

        while(!queue.isEmpty()){
            int[] current =queue.remove();
            int cx = current[0];
            int cy = current[1];
            for(int i=0; i<4; i++){
                int tx = cx+X[i];
                int ty = cy+Y[i];
                if(tx<0 || ty<0 || tx>N-1 || ty>N-1){
                    continue;
                }
                if(target[tx][ty] == 0 || visit[tx][ty]){
                    continue;
                }
                queue.add(new int[]{tx,ty});
                visit[tx][ty] = true;
                result++;
            }
        }
        return result;
    }

    public void q_2468() throws IOException {
        /* 이전과 비슷
        -높이는 1-100 (높이가 1-9까지 일줄 알았음 ㅠ.ㅠ)
        -for문을 돌릴 범위를 어떻게 정하지?
        -중간에 나오지 않는 숫자들도 있을텐데 그건 어떻게 거르지?
        : int[][]에 값을 넣으면서 최대값을 삽입하는 방법
        : ArrayList<Integer>에 값을 넣는 방법
         -> int[][]에 넣는 숫자 중에 ArrayList에 없는 값이면 넣기 - 포함된 값인지 확인
         -> 앞에서 부터 꺼내서 for문을 돌리는 거징
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visit = new boolean[N][N];
        target = new int[N][N];
        int result = 1; // BFS 종료 후 단지 수가 담길 리스트

        ArrayList<Integer> numberList = new ArrayList<>(); // 높이가 담길 배열 (이 배열에 담긴 수들을 기준으로

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int num = Integer.parseInt(st.nextToken());
                target[i][j] = num;
                if(!numberList.contains(num)){
                    numberList.add(num);
                }
            }
        }
        Collections.sort(numberList);
        numberList.remove(numberList.size()-1);
        for(int su : numberList){
            int rrr = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    int ttt = target[i][j];
                    if(ttt <= su || visit[i][j])
                        continue;
                    bfs_2468(i,j,su);
                    rrr++;
                }
            }
            visit=new boolean[N][N];
            if(rrr>result) result=rrr;
        }
        System.out.println(result);
    }
    private static void bfs_2468(int x, int y, int su) {
        /* BFS(n,m,su)
        -위와 동일 (아래만 다름)
        -2) 값이 su 이하 혹은 visit true인 경우 continue
        -반환 값 없음 (void)
        */

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visit[x][y]=true;

        while(!queue.isEmpty()){
            int[] current =queue.remove();
            int cx = current[0];
            int cy = current[1];
            for(int i=0; i<4; i++){
                int tx = cx+X[i];
                int ty = cy+Y[i];
                if(tx<0 || ty<0 || tx>N-1 || ty>N-1){
                    continue;
                }
                if(target[tx][ty] <= su || visit[tx][ty]){
                    continue;
                }
                queue.add(new int[]{tx,ty});
                visit[tx][ty] = true;
            }
        }
    }
}
