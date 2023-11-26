package In2023.November_11;

import java.util.*;

public class Nov26 {
    //1260번 문제
    static int size;
    static int connection;
    static boolean[] pc_visit;
    static ArrayList<Integer>[] number_connection;

    //2644번 문제
    static int[] bridge;
    static boolean[] p_visit;
    static ArrayList<Integer>[] family;

    public static void main(String[] args){
        // 2문제 풀이 완료 : 탐색(1260-DFS,BFS & 2644-촌수계산)

    }

    // 1번 문제 : https://www.acmicpc.net/problem/1260
    public void q_1260(){
        /*
        -DFS
        : 깊이를 먼저 들어가기
        3에서 시작
        3찍고 - 들어온 거 보고
        3에 연결된 거 있으면 DFS 들어가기
        연결된 거 없으면 끝
        -BFS
        : 넓게 들어가기
        3에서 시작
        3찍고
        3이랑 연결된 거 다 넣고 (찍고)
        연결된 거 없으면 끝
        */

        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        connection = sc.nextInt();
        int start = sc.nextInt();

        pc_visit = new boolean[size + 1];
        number_connection = new ArrayList[size + 1];
        for (int i = 1; i <= size; i++) {
            number_connection[i] = new ArrayList<>();
        }
        for (int i = 0; i < connection; i++) {
            int fir = sc.nextInt();
            int sec = sc.nextInt();
            number_connection[fir].add(sec);
            number_connection[sec].add(fir);
        }

        for (int i = 1; i <= size; i++) {
            Collections.sort(number_connection[i]);
        }

        dfs_1260(start);
        System.out.println();
        pc_visit = new boolean[size + 1];
        bfs_1260(start);
    }

    private static void dfs_1260(int target){
            pc_visit[target] = true;
            System.out.print(target+" ");
            for (int i : number_connection[target]) {
                if (!pc_visit[i]) {
                    dfs_1260(i);
                }
            }

    }

    private static void bfs_1260(int target){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(target);
        pc_visit[target] = true;

        while (!queue.isEmpty()) {
            int num = queue.remove();
            System.out.print(num+" ");
            for (int i : number_connection[num]) {
                if (!pc_visit[i]) {
                    queue.add(i);
                    pc_visit[i] = true;
                }
            }
        }
    }


    // 2번 문제 : https://www.acmicpc.net/problem/2644
    public void q_2644(){
        /*
        1줄 : 전체 사람 수 int people
        2줄 : 계산할 두 사람 int t_a, t_b
        3줄 : 관계 수 int connection
        4줄~ : 부모-자식 관계 ArrayList<Integer> []
        bfs(t_a) 실행
        결과 : 있으면 촌수 출력, 없으면 (0이면) -1 반환
         */

        Scanner sc = new Scanner(System.in);
        int people = sc.nextInt();
        int targetA = sc.nextInt();
        int targetB = sc.nextInt();
        int connection = sc.nextInt();

        p_visit = new boolean[people + 1];
        family = new ArrayList[people + 1];
        for (int i = 1; i <= people; i++) {
            family[i] = new ArrayList<>();
        }
        for (int i = 0; i < connection; i++) {
            int fir = sc.nextInt();
            int sec = sc.nextInt();
            family[fir].add(sec);
            family[sec].add(fir);
        }

        bridge = new int[people+1];
        bfs_2644(targetA);
        if(bridge[targetB] == 0) System.out.println(-1);
        else System.out.println(bridge[targetB]);
    }

    private static void bfs_2644(int a){
        /*
        -큐가 빈 상태일 때까지 하기
        1) 큐에서 하나 꺼내서 연관된 num 있는지, 배열[num]이 false인지 확인 (있으면 다음 단계로)
        2) 관련된 거 다 담기 (queue.add(num))
        3) 방문 상태 등록하기 (배열[num] true로 변환)
        4) 이전 꺼에 촌 수 ++해서 bridge[num] 업데이트하기
         */

        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        while(!queue.isEmpty()){
            int num = queue.remove();
            for(int i : family[num]){
                if(!p_visit[i]){
                    queue.add(i);
                    p_visit[i]=true;
                    bridge[i] = bridge[num]+1;
                }
            }
        }
    }
}
