package In2024.Jan_1;

import java.util.*;

public class Jan3 {
    //여행경로
    static boolean[] visit;
    static String[] result;
    static int rstIdx;
    static String[][] tks;
    //네트워크
    static ArrayList<Integer>[] ary;
    public static void main(String[] args) {
        //2문제 풀이 완료 : DFS(프로그래머스:Lev3-여행경로), BFS(프로그래머스:Lev3-네트워크)
    }

    public int network_solution(int n, int[][] computers) {
        /*
        입력 : 2차원 배열. n개의 컴퓨터. i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현 (양방향)
        출력 : 네트워크의 갯수
        -아이디어
        1. n개의 링크드리스트 생성
        2. 배열을 돌아가면서 1인 경우에 연결해주기
        3. 1부터 시작 : 방문 배열의 값이 false 인 경우 아래 진행
        4. BFS 메서드 호출
        5. count++
        6. 결과로 count 반환
         */
        ary = new ArrayList[n];
        visit = new boolean[n];
        int answer = 0;
        for(int i=0; i<n; i++){
            ary[i] = new ArrayList<>();
            for(int j=0; j<n; j++){
                if(computers[i][j] == 1){
                    ary[i].add(j);
                }
            }
        }

        for(int i=0; i<n; i++){
            if(!visit[i]) {
                bfs(i);
                answer++;
            }
        }

        return answer;
    }
    private void bfs(int i){
        /*
        -호출된 메서드 bfs
        1) BFS 진행 : 큐가 비어있기 전까지만 진행 (while)
        2) 큐에서 꺼낸 것과 연결된 것들 탐색
        3) 만약 아직 방문하지 않은 곳이라면 큐에 담고 방문 표시
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visit[i] = true;
        while(!queue.isEmpty()){
            int target = queue.remove();
            for(int linked : ary[target]){
                if(!visit[linked]){
                    queue.add(linked);
                    visit[linked] = true;
                }
            }
        }
    }

    public static String[] trip_tickets_solution(String[][] tickets) {
        /*
        -메인
        0) 전역 필드 초기화 : boolean[], String[],
        1) 메서드 호출 - 인천공항부터 시작
        2) 모두 돈 후 전역 String 배열인 result 반환
        */
        result = new String[tickets.length+1];
        visit = new boolean[tickets.length];
        rstIdx = 0;
        tks = tickets;
        result[rstIdx++] = "ICN";

        make_schedule("ICN");

        return result;
    }
    private static boolean make_schedule(String crt) {
        /*
        -호출 된 메서드 (인자: 탐색할 출발지. String) (반환: boolean)
        1) 처음부터 돌면서 갈 수 있는 곳 체크
        -> 우선 순위 큐에 담기면서 순서가 정렬됨
        2) 갈 수 있는 곳이 있는 경우 : 가장 빠른 곳부터 돌아가면서 다시 실행 (while)
        : visit[해당 인덱스]를 true로 이동 표시하기
        : result[인덱스위치++]에 현재 도착지 담기 (인덱스도 같이 늘려줌)
        : 빠른 곳 찾는 메서드 다시 호출 (재귀?) (반환값은 boolean)
         -> true : 완성! 전역 배열 반환하면 됨. return true;
         -> false : 더이상 갈 곳이 없는데 모든 티켓을 쓰지 못함. 더 진행해야됨
        : 이번 케이스는 실패했으니까 제자리로 돌려놓기
         -> visit[해당 인덱스]를 false로 되돌리기
         -> result[--인덱스위치]를 아무것도 없던 상태인 null로 되돌리기 (미리 늘어나 있던 인덱스도 먼저 빼주고 원상복귀)
        3) 갈 수 있는 곳이 없는 경우
        -> 우선순위 큐가 비어있는 경우에 해당
        : 만약 배열의 전역 인덱스가 끝까지 오지 않았다면 false 반환
        : 인덱스가 끝까지 갔다면 true 반환
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return tks[o1][1].compareTo(tks[o2][1]);
            }
        });

        for(int i=0; i< tks.length; i++){
            if(!visit[i] && tks[i][0].equals(crt))
                queue.add(i);
        }

        while(!queue.isEmpty()){
            int idx = queue.remove();
            visit[idx] = true;
            result[rstIdx++] = tks[idx][1];
            if(make_schedule(tks[idx][1])) return true;
            visit[idx] = false;
            result[--rstIdx] = null;
        }

        if(rstIdx == tks.length+1) return true;
        else return false;
    }
}
