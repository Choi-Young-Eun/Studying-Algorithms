package In2023.December_12;

import java.util.*;

public class Dec1 {
    //공통
    static boolean[] visit;
    static int[] count;
    static int[] move;
    //5014번
    static int F;
    //1920번
    static int[] number;
    public static void main(String[] args) {
        //3문제 풀이 완료 : 탐색(1697-숨바꼭질, 5014-스타트링크, 1920-이진탐색)
    }

    public void q_1920(){
        /*
        배열 내에 X라는 정수가 존재하는지 알아내는 프로그램
        존재하면 1, 존재하지 않으면 0을 출력

        ** 입력
        1줄 : 배열의 길이 N
        2줄 : N개의 배열에 담길 자연수들 (1-100000 사이)
        3줄 : 찾을 수의 갯수 M
        4줄 : M개의 찾고자하는 자연수들

        ** 배열
        static int[] numbers //N개

        ** 출력
        반환값 true, false //마지막까지 오면 false
        */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        number = new int[N];
        for(int i=0; i<N; i++){
            number[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        int[] target = new int[M];
        for(int i=0; i<M; i++){
            target[i] = sc.nextInt();
        }
        Arrays.sort(number);
        for(int su : target) {
            if (bs_1920(0, N - 1, su)) System.out.println(1);
            else System.out.println(0);
        }
    }

    private static boolean bs_1920(int a, int b, int su) {
        while(a<=b){
            int idx = (a+b)/2;
            if(number[idx] == su) return true;
            else if(number[idx] > su){
                b=idx-1;
            }
            else {
                a=idx+1;
            }
        }
        return false;
        /*
        ** 이진탐색(i,j,target) //i와 j는 index 값 target은 찾을 수
        처음과 끝의 인덱스가 같은데 타겟값과 다름 false 같음 true
        int idx = (i+j)/2;
        if(numbers[idx]>target) 작을 때 반환
        else if() 클 때 반환
        else if() 같을 때 반환 true

        ** 실패. 런타임 에러 (stackOverFlow 에러 발생)
        if(a==b && number[a]!=su) return false;
        else if(a==b && number[a]==su) return true;
        int idx = (a+b)/2;
        if(number[idx] == su) return true;
        else if(number[idx] > su) return bs(a,idx-1, su);
        else return bs(idx+1,b,su);
         */
    }

    public void q_1697(){
        /*
        ** 입력
        1줄 : 수빈이 위치 N, 동생 위치 K

        ** 출력
        가장 빠른 시간 result

        수빈이 위치에서 시작
        점은 100000까지 가능 (몇부터 몇일지 모르니 visit[100001] 으로 세팅)
        이동 값도 (몇부터 몇일지 모르니 visit[100001] 으로 세팅)
        매 위치마다 걷거나 순간 이동 가능. (X-1, X+1, 2*X)
         */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        if(N == K){
            System.out.println(0);
            return;
        }
        visit = new boolean[100001];
        count = new int[100001];

        bfs_1697(N,K);
        System.out.println(count[K]);
    }
    private static void bfs_1697(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        visit[n] = true;

        while(!queue.isEmpty()){
            int crn = queue.remove();
            for(int i=1; i<=3; i++){
                int tar;
                switch (i) {
                    case 1: tar = crn+1;
                    break;
                    case 2: tar = crn-1;
                    break;
                    case 3: tar = crn*2;
                    break;
                    default: tar = crn;
                }
                if(tar < 0 || visit[tar]) continue;
                count[tar] = count[crn]+1;
                if(tar == k) return;
                visit[tar] = true;
                queue.add(tar);
            }
        }
    }


    public void q_5014() {
        /*
        ** 입력값
        1줄 : 총 층수 F, 현재 층수 S, 목표 층수 G, 엘리베이터 위로 U, 엘리베이터 아래로 D

        ** 배열
        static boolean[] visit 방문 배열
        static int[] count 버튼 횟수
        static int[] move 버튼 두개 값

        ** 출력값
        count[G] 값이 0이면  "use the stairs" 출력
        아니면 count[G] 출력
        */

        Scanner sc = new Scanner(System.in);
        F = sc.nextInt();
        int S = sc.nextInt();
        int G = sc.nextInt();
        int U = sc.nextInt();
        int D = sc.nextInt();

        visit = new boolean[F+1];
        count = new int[F+1];
        move = new int[] {U,Math.abs(D)};

        bfs_5014(S,G);

        if(count[G] == 0)
            System.out.println("use the stairs");
        else System.out.println(count[G]);
    }
    private static void bfs_5014(int s, int g) {
        /*
        ** BFS(S,G)
        1. BFS 메인 아이디어
        1) 엘리베이터 이동
        1] +U층
        2] -D층
                -> 만약 +U or -D가 F를 넘어가거나 1보다 작으면 넘어감
        2) 없으면 종료 or S==G면 종료
        2. 슈도 코드
        1) 큐 생성 후 S 넣기
        2) 방문 배열[S] true로 변경
        3) while(큐 빌 때까지)
         -> int crn = 큐에서 꺼내기
         -> for(int num : move)
            -> int tar = num+crn
            -> if(tar < 1 || tar > F || visit[tar]) continue;  //0층은 없다 바보야
            -> count[tar] = count[crn]+1  //버튼 누른 횟수 저장
            -> if(tar == G) return;  //도착! 종료
            -> 큐에 tar add
            -> 방문 배열[tar] true로 변경
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visit[s] = true;
        while(!queue.isEmpty()){
            int crn = queue.remove();
            for(int num:move){
                int tar = crn+num;
                if(tar < 1 || tar > F || visit[tar])
                    continue;
                count[tar] = count[crn]+1;
                if(tar == g) return;
                queue.add(tar);
                visit[tar] = true;
            }
        }
    }
}
