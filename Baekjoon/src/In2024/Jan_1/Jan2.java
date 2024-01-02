package In2024.Jan_1;

import java.util.LinkedList;
import java.util.Queue;

public class Jan2 {
    public static void main(String[] args){
        //1문제 풀이 완료 : BFS(프로그래머스:Lev2-게임 맵), 1문제 풀이 중 : DFS(프로그래머스:Lev3-여행티켓)
    }

    public String[] solution(String[][] tickets) {
        /*
        0) 정답 배열의 길이는 갈 수 있는 경우의 수+1개
        1) ICN에서 시작
        2) 갈 수 있는 경로가 2개 이상의 경우 알파벳 순서가 앞서는 경로를 선택함
        - queue에서 꺼낸 값을 arr에 담음, arr 기록 인덱스 값++
        - queue에서 꺼낸 값과 0번째 값이 같은 걸 선택
        : 여러개인 경우가 존재할 수 있음. 그래서 처음부터 끝까지 한 번 도는데
        : 사전순으로 비교하여 갈 곳을 선택하는 메서드 만들어야됨
         -> 일단 유효한 티켓인지 확인 //boolean done 배열
         -> 거기에 0번째 값이 같다면 1번째 값의 인덱스를 기억하기
         -> 인덱스가 10000이라면 인덱스 바로 기억
         -> 만약 이전 인덱스가 있다면 알파벳 순서대로 비교, 사전순으로 가장 먼저 오는 곳이 새로운 값인 경우에 인덱스 업데이트
        -선택된 곳(done의 idx 위치)을 방문했다고 체크, queue에 idx의 1번째 값 넣기
        3) 모든 도시를 방문한 경우 종료. 큐가 빈 경우.
         */

        /*
        현재인덱스 값.compareTo(새로운 인덱스 후보 값)이 양수면 바꾸기
        -> 하지만 이렇게 갔을 때 다음 목적지가 없는 경우가 있을 수 있음. 이경우에는 다른 곳으로 진행해야함
        1) 갈 수 있는 곳 체크
        2) 그 중 가장 빠른 곳 선택
        3) 이동 표시 하기
        -> 근데 빠른 곳이 길이 아닐 수 있음
        -> 그럼 다른 곳으로 가야됨
        이럴 경우에는 그럼
        1) 갈 수 있는 곳 체크
        2) 그 중 가장 빠른 곳 선택
        3) 이동 표시하기

        4) 만약 모든 곳을 방문하지 못했다면 전으로 돌아가기
        -> 언제까지 돌아가야 되는 거지
        -> 가장 최근으로 돌아가서 없으면
        -> 그 전으로 돌아가기?
        -> 아님 가장 먼저 한 선택부터 변경시키기?
        -> 거슬러 올라가는 것이 맞을 것 같음
        -메인
        0) 전역 필드 초기화 : boolean[], String[],
        1) 메서드 호출 - 인천공항부터 시작
        2) 모두 돈 후에 반환 시
        : 전역 String 배열 반환

        -호출 된 메서드 (인자: 탐색할 출발지. String) (반환: boolean)
        1) 처음부터 돌면서 갈 수 있는 곳 체크
        -> 배열 하나 만들어서 갈 수 있는 곳 담기 (담으면서 순서 정렬하기)
        2) 갈 수 있는 곳이 없는 경우
        -> 위의 배열이 비어있는 경우에 해당
        : 만약 배열의 전역 인덱스가 끝까지 오지 않았다면 false 반환
        : 인덱스가 끝까지 갔다면 true 반환
        3) 갈 수 있는 곳이 있는 경우 : 가장 빠른 곳부터 돌아가면서 다시 실행 (for)
        -> 방문 체크 배열
        -> String 배열
        -> idx값 임시 값으로 담아놓기
        : 이동 표시하기 (전역배열에 체크)
        : 출력 배열에 담기 (전역배열에 체크)
        : 빠른 곳 찾는 메서드 다시 호출 (메이비 재귀?) (반환값은 boolean일 것)
         -> false : 더이상 갈 곳이 없는데 모든 티켓을 쓰지 못함. 더 진행해야됨
         -> true : 완성! 전역 배열 반환하면 됨. break
        : 이번 케이스는 실패했으니까 제자리로 돌려놓기
         -> boolean 배열을 현재 상태로 되돌리기
         -> String 배열을 현재 상태로 되돌리기
         -> idx 값을 현재 상태로 되돌리기
         */
        int size = tickets.length;
        String[] result = new String[size+1];
        boolean[] done = new boolean[size];
        int rstIdx = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add("ICN");
        while(!queue.isEmpty()){
            String crt = queue.remove();
            result[rstIdx++] = crt;
            int idx = 10001;
            for(int i=0;i<size; i++){
                if(!done[i] && tickets[i][0].equals(crt)){
                    if(idx == 10001 || tickets[idx][1].compareTo(tickets[i][1]) > 0)
                        idx = i;
                }
            }
            if(idx != 10001) {
                done[idx] = true;
                queue.add(tickets[idx][1]);
            }
        }
        return result;
    }


    public static int game_map_solution(int[][] maps) {
        /*
        -문제 이해하기
        1. 입력 값
        : NxM 크기 배열 N개의 숫자 배열, 줄당 M개의 칸으로 구성
        : 벽이 있는 경우 0, 길인 경우 1
        2. 반환 값
        : 내 위치는 1,1 상대 진영은 n,m
        : 상대 진영까지 가장 빨리 갈 수 있는 길 (갈 수 있는 경우의 이동 거리가 최솟값) 구하기
        : 만약 도달할 수 없는 경우 -1 반환

        -아이디어
        1. 이동하는 경우
        : 큐에 1,1을 담고 체크한 후 시작, 큐가 빌 때까지 진행
            1) 꺼낸 값이 n,m이라면 종료
            2) 동서남북 가능. 순서대로 갈 수 있는 곳인지 확인. 갔다 왔는지 확인하는 배열대신 횟수 체크 배열의 값이 0인지 확인
            : 갈 수 있는 범위를 벗어나면 넘어가기
            : 횟수 체크 배열의 값이 0이 아닌 것은 이미 방문하여 횟수가 업데이트 된 것이므로 패스
            3) 칸 상태가 0인 경우 가지 못하므로 넘어가기
            4) 1인 경우 큐에 담기, 방문 체크하기, 횟수 체크하기
        2. 결과 출력
        : n,m의 횟수 값이 0인 경우 -1출력
        : 그렇지 않으면 해당 값 출력
         */
        int X = maps.length; //아래로 최대 값
        int Y = maps[0].length; //옆으로 최대 값
        Queue<int[]> queue = new LinkedList<>();
        int[][] round = new int[X][Y];
        int[] moveX = {0,0,1,-1}; //동서남북
        int[] moveY = {1,-1,0,0};

        queue.add(new int[]{0,0});
        round[0][0] = 1;

        while(!queue.isEmpty()){
            int[] target = queue.remove();
            if(target[0] == X-1 && target[1] == Y-1){
                break;
            }
            for(int i=0; i<4; i++){
                int newX = target[0]+moveX[i];
                int newY = target[1]+moveY[i];
                if(newX < 0 || newY < 0 || newX>=X || newY>=Y)
                    continue;
                if(maps[newX][newY] == 1 && round[newX][newY] == 0){
                    queue.add(new int[]{newX, newY});
                    round[newX][newY] = round[target[0]][target[1]]+1;
                }
            }

        }

        if(round[X-1][Y-1] == 0) return -1;
        else return round[X-1][Y-1];
    }
}
