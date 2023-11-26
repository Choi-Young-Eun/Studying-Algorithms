package In2023.November_11;

import java.util.*;

public class Nov26 {
    static int size;
    static int connection;
    static boolean[] pc_visit;
    static ArrayList<Integer>[] number_connection;

    public static void main(String[] args){
        // 1문제 풀이 완료 : 탐색(1260-DFS,BFS)

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

        dfs(start);
        System.out.println();
        pc_visit = new boolean[size + 1];
        bfs(start);
    }

    private static void dfs(int target){
            pc_visit[target] = true;
            System.out.print(target+" ");
            for (int i : number_connection[target]) {
                if (!pc_visit[i]) {
                    dfs(i);
                }
            }

    }

    private static void bfs(int target){
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
}
