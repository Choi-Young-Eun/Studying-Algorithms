package In2023.November_11;

import java.util.*;

public class Nov25 {
    public static void main(String[] args) {
        // 1문제 풀이 실패 : 탐색(2606-BFS)
        // 다음날 풀이 완료!

        /*
        2606번 : 1과 연결돼서 바이러스에 몇대가 걸리는지니까 1부터 몇개가 연결됐는지만 알면 됨
        -> BFS로 풀기! 어차피 연관된 가지는 끝까지 다 돌아야되니까!

        1줄 : 컴퓨터 수
        2줄 : 연결 수
        3줄~ : 연결 쌍
        1과 연결돼서 몇대가 걸리는지니까 1부터 몇개가 연결됐는지만 알면됨

        ** BFS 사용하기
        1. 컴퓨터 수 만큼 배열리스트? 생성
        2. 연결 수 만큼 돌면서 리스트에 연결해주기(넣어주기)
        3. 큐에 1번 넣기
            for : Start! 반복문 시작, 큐 비어있을 때까지 진행
            4. 큐에서 하나 빼고 연관된 노드 추가하기
            5. 노드 추가하면서 출입체크 배열에 값 true 변경
            6. 빼면서 전체 수 ++ 하기
        7. 전체 수 출력
         */

        Scanner sc = new Scanner(System.in);
        int pc = sc.nextInt();
        int connection = sc.nextInt();
        int result = 0;
        boolean[] pc_visit = new boolean[pc + 1];
        ArrayList<Integer>[] number_connection = new ArrayList[pc + 1];
        for (int i = 1; i <= pc; i++) {
            number_connection[i] = new ArrayList<>();
        }
        for (int i = 0; i < connection; i++) {
            int fir = sc.nextInt();
            int sec = sc.nextInt();
            number_connection[fir].add(sec);
            number_connection[sec].add(fir);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        pc_visit[1] = true;
        while (!queue.isEmpty()) {
            int num = queue.remove();
            for (int i : number_connection[num]) {
                if (!pc_visit[i]) {
                    queue.add(i);
                    pc_visit[i] = true;
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
