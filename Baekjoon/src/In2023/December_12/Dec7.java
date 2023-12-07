package In2023.December_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dec7 {
    //9205번
    static int S;
    static int[][] store;
    static boolean[] visit;
    //7569번
    static int X;
    static int Y;
    static int N;
    static int zeroCount=0;
    static Box[] boxes;
    static int[][] move = {{0, -1, 0}, {0, 1, 0}, {-1, 0, 0}, {1, 0, 0}, {0, 0, 1}, {0, 0, -1}};
    public static void main(String[] args) throws IOException {
        //2문제 풀이 완료 : 탐색(9205-맥주마시면서걸어가기, 7569-토마토마토)
    }

    public void q_9205() {
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
         */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0; i<T; i++){
            S = sc.nextInt();
            store = new int[S+2][2];
            visit = new boolean[S+1];
            store[0][0] = sc.nextInt();
            store[0][1] = sc.nextInt();

            for(int j=1; j<=S; j++){
                store[j][0] = sc.nextInt();
                store[j][1] = sc.nextInt();
            }
            store[S+1][0] = sc.nextInt();
            store[S+1][1] = sc.nextInt();
            bfs_9205(0);
        }
    }
    private static void bfs_9205(int home) {
        /*
        ** 탐색 함수
        근데 각 거리마다 x+y 차이가 1000을 초과하면 못가는 거 아닌가?
        : x좌표 차이+y좌표 차이 가 1000을 넘으면 그곳은 현재 위치에서는 못감
        1. 편의점 좌표가 순서대로 인가
         -> 그렇지 않다면, 매번 거리를 확인해야됨 (들린 편의점 체크)
                -> 그렇다면, 순서대로 차이가 1000을 초과하는지 확인하고 체크
        2. (막힌 이유) 좌표간 거리 차이를 양수에만 집중하고 음수일때의 좌표를 고려하지 않음.
         -> 거리 차이는 절대값으로 판단해야한다 제발!

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

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int crt;
        int[] xy;
        int[] festival = new int[]{store[S+1][0], store[S+1][1]};
        while(!queue.isEmpty()){
            crt = queue.remove();
            xy = new int[]{store[crt][0], store[crt][1]};
            int direct = Math.abs(festival[0]-xy[0])+Math.abs(festival[1]-xy[1]);
            if(direct<=1000){
                System.out.println("happy");
                return;
            }
            for(int i=1; i<=S; i++){
                if(visit[i])
                    continue;
                direct = Math.abs(store[i][0]-xy[0])+Math.abs(store[i][1]-xy[1]);
                if(direct>1000)
                    continue;
                queue.add(i);
                visit[i] = true;
            }
        }
        System.out.println("sad");
    }

    public void q_7569() throws IOException {
        /*
        ** 입력
        1줄 : 한 상자의 가로길이 X, 한 상자의 세로 길이 Y, 총 상자 개수 N
        2~Y줄*N줄 : 가장 아래에 있는 상자부터 총 상자 개수 만큼 한 상자 상태가 주어짐

        ** 방향 세팅 (가로 세로 높이 순)
        앞 : 북쪽으로 한칸 올라감 (윗줄로) 0, -1, 0
        뒤 : 남쪽으로 한칸 내려감 (아래줄로) 0, +1, 0
        왼 : 왼쪽으로 한칸 이동 (옆으로 -1) -1, 0, 0
        오 : 오른쪽으로 한칸 이동 (옆으로 +1) +1, 0, 0
        위 : 다음 층으로 올라감 (같은 좌표에 윗칸으로) 0, 0, +1
        아래 : 밑 층으로 내려감 (같은 좌표에 아랫칸으로) 0, 0, -1
        -> 유효한 좌표인지 확인 : x<0 || y<0 || x>X || y>Y || f<0 || f>N-1 //Box[] 도 인덱스가 0부터 시작
        int[][] move = {{0, -1, 0}, {0, 1, 0}, {-1, 0, 0}, {1, 0, 0}, {0, 0, 1}, {0, 0, -1}};

        ** 기본 아이디어
        : 해당 시점에서 1인 곳 근처의 앞, 뒤, 양옆, 위, 아래 방향에서 0인 걸 찾고 담음
        : 날수, 시점을 카운트 (매 횟수마다 업데이트 해주어야 됨) (근데 이건 while문으로 진행하기 때문에 횟수를 정확하게 나눠서 주기적으로 업데이트 할 수 없음)(그러므로 이전 횟수를 다음 횟수로 업데이트 할 수 있도록 좌표에 그때 그때 상황에 맞게 업데이트)
        : 마지막에 0인 곳이 한 곳이라도 있다면 그건 모두 익을 수 없는 것으로 -1 출력
        -입력 주의 사항
        1. 0이 몇개있는지 체크 해서 그 갯수를 업데이트 하자
         -> 처음 토마토 상태를 받을 때 배열을 업데이트 하면서 값이 0인 것의 갯수를 저장하자
        2. 처음에 1이 어디어디 있는지 알아야됨 그게 시작 점이니까
         -> 1이면 좌표를 기억(저장)해야한다
        : 그러므로 입력할 때 0인 것은 카운트를 세고, 1인 것은 좌표를 저장하도록 한다아악
        -탐색 주의 사항
        1. 상자 클래스를 만들어서 써야될까
         -> 가로 세로 뿐만이 아니라 갯수도(층수) 신경써야하는 상황
         -> 클래스로 만들기! 가로, 세로, 가로x세로 2차원 토마토 배열
        2. 그리고 상자 클래스를 배열 형태로 저장하기
         -> 상자 배열의 사이즈는 N개
         -> 매 상자를 받을 때 가로X 세로Y값을 기준으로 상자 클래스 생성
         -> 위에서 부터 차례대로 값을 입력 받음

        ** 출력
        모든 토마토가 익는 최소 일수를 구함
        만약 모두 익은 토마토들 이라면 0일이 걸렸으므로 0 출력
        만약 모두 익을 수 없는 상자 상태라면 -1 출력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        boxes = new Box[N];
        ArrayList<int[]> arr = new ArrayList<>();
        for(int r=0; r<N; r++){
            Box box = new Box(X, Y);
            for(int i=0; i<Y; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<X; j++){
                    int num = Integer.parseInt(st.nextToken());
                    box.tomato[i][j] = num;
                    if(num == 0) zeroCount++;
                    else if(num == 1) arr.add(new int[]{j,i,r});
                }
            }
            boxes[r] = box;
        }
        if(zeroCount == 0){
            System.out.println(0);
            return;
        }
        bfs_7569(arr);
    }
    private static void bfs_7569(ArrayList<int[]> arr) {
        /*
        ** BFS
        : 토마토들은 동시다발적으로 여기저기서 익어간다
        : 그러므로 한 우물씩 파는 게 아니라 여기저기서 상태를 체크해주어야 함

        1. 시작 : 현재 1인 것들의 좌표를 큐에 모두 담음
        2. while() : 큐가 빌 때까지 고고링
        -하나를 꺼냄 : 좌표를 저장해야됨. (가로, 세로, 층수)
        -for() : 그 하나의 위치를 기준으로 총 6군데를 체크해야됨  -> for(int m:Move)
         -> 좌표를 6가지 경우로 미리 세팅해놓음 -> int[][] move
          -돌아가면서 진행하는데 해당 좌표가 유효한지 체크 : x<0 || y<0 || x>X || y>Y || f<0 || f>N-1 //Box[] 도 인덱스가 0부터 시작
           -> '위, 아래' 라면 : 0층은 아닌지, N+1층은 아닌지 체크
           -> '앞, 뒤, 양옆' 이라면 : 가로와 세로가 0은 아닌지, 가로+1과 세로+1은 아닌지 체크
          -해당 좌표의 값이 0이라면 큐에 담고 방문 여부 업데이트 Box[층수].tomato[y][x] == 0
           -> 현재 좌표의 값+1 로 변경 (진행일수+1 된 날이니까 결과에서는 해당 값의 -1을 해주어야됨)
           -> zeroCount--
        3. 마지막에 0인 곳이 한 곳이라도 있다면 그건 모두 익을 수 없는 것으로 -1 출력 : zeroCount 확인
        4. 그렇지 않으면 마지막 확인한 현재 좌표의 업데이트된 값을 -1해서 출력 System.out.println(Box[층수].tomato[y][x]-1);
         */
        Queue<int[]> queue = new LinkedList<>();
        for(int[] a:arr){
            queue.add(a);
        }
        int[] crt = new int[3];
        while(!queue.isEmpty()){
            crt = queue.remove();
            for(int i=0; i<6; i++){
                int x = crt[0]+move[i][0];
                int y = crt[1]+move[i][1];
                int f = crt[2]+move[i][2];
                if(x<0 || y<0 || x>X-1 || y>Y-1 || f<0 || f>N-1)
                    continue;
                if(boxes[f].tomato[y][x] == 0){
                    queue.add(new int[]{x, y, f});
                    boxes[f].tomato[y][x] = boxes[crt[2]].tomato[crt[1]][crt[0]]+1;
                    zeroCount--;
                }
            }
        }
        if(zeroCount != 0){
            System.out.println(-1);
        }else{
            System.out.println(boxes[crt[2]].tomato[crt[1]][crt[0]]-1);
        }
    }
    //7569번에서 사용할 박스 클래스
    static class Box{
        int x;
        int y;
        int[][] tomato;
        Box(int x, int y){
            this.x = x;
            this.y = y;
            this.tomato = new int[y][x];
        }
    }
}
