package In2023.December_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.StringTokenizer;

public class Dec6 {
    //14503번
    static int N;
    static int M;
    static int[][] space;
    static int[][] move = {{-1, 0},{0, 1},{1, 0},{0, -1}};

    public static void main(String[] args) throws IOException {
        //2문제 풀이 완료 : 탐색(1822-차집합, 14503-방청소), 1문제 풀이 중 : 탐색(9205-맥주마시면서걸어가기)

        /*
        ** 입력
        1줄 : 테스트 케이스 개수
        2줄 : 편의점 개수
        3줄 : 상근이 집 좌표 x,y
        ~3+N줄 : 편의점 좌표 x,y (갯수별로 N개줄)
        마지막줄 : 락페 좌표 x,y

        ** 전체적인 과정
        1. 테스트 케이스 T 받음
        -for:T만큼 돌림
        2. 편의점 개수 S 받음
        3. 상근이 집 좌표 home 받음
        4. S 사이즈의 store 배열 생성 //static
          -for:S만큼 돌림
          5. store 배열에 편의점 좌표 등록
        6. 락페 좌표 fes 받음 //static
        7. 방문 확인 배열 boolean[] visit 초기화 //static
        7. 탐색 함수(집 좌표);

        ** 출력
        갈 수 있으면 happy, 맥주가 동나면 sad
         -> 탐색 함수는 void 함수내에서 출력하고 종료

        ** 탐색 함수
        근데 각 거리마다 x+y 차이가 1000을 초과하면 못가는 거 아닌가?
        : x좌표 차이+y좌표 차이 가 1000을 넘으면 그곳은 현재 위치에서는 못감
        1. 편의점 좌표가 순서대로 인가
         -> 그렇지 않다면, 매번 거리를 확인해야됨 (들린 편의점 체크)
         -> 그렇다면, 순서대로 차이가 1000을 초과하는지 확인하고 체크

        -시작은 상근이 위치 탐색(0,0) -> x=0; y=0;
        -메인 : 매 과정 마다 갈 수 있는 곳을 체크하여 방문 (편의점들과 페스티벌 장소)
        1. 현재 위치 뽑기 (갈 수 있는 곳을 담은 배열 next에서 꺼내서 현재위치로 설정)
        2.  해당 위치에서 페스티벌에 갈 수 있는지 확인 (현재 위치와 락페 위치가 1000이하이면)
         -> true면 끝 - "happy" 바로 출력, 종료)
        3. 갈 수 있는 편의점을 담음 (기준은 visit이 false && 'x좌표 차이+ y좌표'가 1000이하일 때)
         -> 방문한 편의점은 visit으로 표시 = true
         -> 다음 갈 곳이므로 next에 추가
        4. 갈 수 있는 곳이 없어 while문 종료, 실패 - "sad" 바로 출력, 종료
         */

    }

    public void q_14503() throws IOException {
        /*
        ** 문제 이해중
        청소하고 왼쪽으로 90도 돌려서 (반시계방향으로 돌아서) 진행
        : (0,0) 시작이니까 (r,c)는 (r+1, c+1)번째 좌표임 -> 시작이 2,2 이면 인덱스를 2,2로 두고 진행 (인덱스는 0부터 이므로 +1씩 된 값)
        : 청소할 곳이 없는 경우 같은 방향으로 보고 한칸 후진 (후진한 값이 0이면 3부터 진행, 1이면 끝)
        : 있는 경우 90도 돌고 앞에 확인해서 청소 하지않은 칸이면 앞으로 가서 1부터 진행)

        ** 배열
        int[N][M] space : 방 상태가 나타난 배열
        방향은 반시계 방향으로 진행. 북-서-동-남 : 0-3-2-1 -> (기존방향+3)%4
        기본 전진 방향 배열front = (0, -1) (+1, 0) (0, +1) (-1, 0) //북-동-남-서
        한칸 후진 : 방향 그대로 뒤로 한칸만 이동

        ** 놓친 부분
        1. 반 시계 방향인 것 (시계 방향으로 돌아감)
        2. x와 y 좌표를 잘못 이동시킴 (줄을 x로 칸을 y로 두어야하는데 반대로 함)
        3. (풀다보니 문제를 어느순간 잘못이해함) 반대 방향으로 이동하는 거지 방향을 변경하는 것은 아님 (후진할 때 반대 방향으로 변경함)
        */
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
    }

    static int dfs(int crt_x, int crt_y, int direct) {
        /* int DFS (x, y, dir)
         : 청소 하는 걸 count+1값으로 하기
         -> 이경우 처음 청소한 곳이 벽과 같은 값인 1이 되므로 -1로 세팅하는 것으로 변경
         (현재 위치와 방향) - int crt_x, int crt_y, int direct (0-3)
         0) 해당 칸의 값이 0인 경우 int[][] space에 -1 저장 & 카운트++
         boolean check = false
         if(space[][] == 0) count++; space[][] = -1;
         1) 주변에서 0인곳을 찾기 (위, 아래, 양 옆) 확인 해당 값에서 방향을 기준으로 순서를 돌기
         -> 방향이 1이면 0 -> 3 -> 2 -> 1 순으로 한칸 앞의 값을 확인
         for(int i=1; i<=4; i++){
         direct = (direct+3)%4
         int x, int y = move[direct]
         if(space[x][y] == 0)
         crt_x, crt_y = x, y
         check = true  //다음으로 넘어갈지 체크하는 boolean 변수
         break
         }
         2) 4군데 다 돌았다면 원래 방향 (맨 마지막 방향이 원래 방향이 됨)을 기준으로 뒤로 한 칸의 값이 1인지 확인
         -> 후진할 좌표 방향 = 현재 방향의 반대 방향 -> back = (direct+2)%4
         -> int crt_x, int crt_y = move[back] //메인 x, y좌표를 이걸로 설정
         -> if(space[x][y] == 1) break; //뒤가 벽이라 종료
         3) count 반환
        */

        int count = 0;
        while(true){
            boolean check = false;
            if(space[crt_x][crt_y]==0){
                space[crt_x][crt_y]=-1;
                count++;
            }

            for(int i=1; i<=4; i++){
                direct = (direct+3)%4;
                int x = crt_x+move[direct][0];
                int y = crt_y+move[direct][1];
                if(x>N-1 || y>M-1 || x<0 || y<0) continue;
                if(space[x][y] == 0){
                    crt_x = x;
                    crt_y = y;
                    check = true;
                    break;
                }
            }
            if(check) continue;
            int d = (direct+2)%4;
            crt_x += move[d][0];
            crt_y += move[d][1];
            if(space[crt_x][crt_y] == 1) break;
        }
        return count;
    }


    public void q_1822() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());

        TreeSet<Integer> A = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<sizeA; i++){
            A.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<sizeB; i++){
            int n = Integer.parseInt(st.nextToken());
            if (A.contains(n))
                A.remove(n);
        }

        System.out.println(A.size());
        for (int i : A) {
            System.out.print(i + " ");
        }
    }
}
