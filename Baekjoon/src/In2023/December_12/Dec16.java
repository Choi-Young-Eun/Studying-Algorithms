package In2023.December_12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dec16 {
    public static void main(String[] args){
        //1문제 풀이 완료 : 그리디(11000-강의실배정), 1문제 풀이 중 : 그리디(2457-공주님의정원)

        /*
        : 3월 1일부터 11월 30일까지 최소의 꽃이 매일 피어있어야함
        //입력 받을 때 만약 시작 날짜의 월이 3보다 작은 경우
        //두 조건을 만족하는 꽃들을 선택할 수 없다면 0을 출력

        : 가장 오래 피어 있는 걸 선택하는 것이 좋음
        1. 시작 날짜 순으로 정렬 (오름차순)
        2. 같은 시작 날짜라면 더 오래피어 있는 것이 좋음 (뒤 날짜가 더 긴or큰 것)

        끝날짜 시작
        1. 시작 날짜가 직전 꺼와 같으면 버리기
        2. 비교 대상의 끝 날짜 보다 이전이면 넘어가기
        3. 비교 대상의 시작날짜가 끝 날짜보다 이전이거나 같고 끝날짜가 끝 날짜보다 크면 담기
        4. 끝날짜 보다 나중이면 담기
        */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] days = new int[N][2];
        for(int i=0; i<N; i++){
            int start_m = sc.nextInt();
            int start_d = sc.nextInt();
            int end_m = sc.nextInt();
            int end_d = sc.nextInt();
            days[i][0] = start_m*100+start_d;
            days[i][1] = end_m*100+end_d;
        }
        Arrays.sort(days, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });
        int count = 0;
        int idx = 0;
        for(int i=1; i<N; i++){
            if(days[i][1] < 302) continue;
            if(days[idx][0] == days[i][0] || days[idx][1] >= days[i][1]) continue;
            if(days[idx][1] >= days[i][0] && days[idx][1] < days[i][1]) {
                idx = i;//비교 대상의 시작날짜가 끝 날짜보다 이전이거나 같고 끝날짜가 끝 날짜보다 크면 담기
                count++;
                if(days[i][1] >= 1201) break;
            }
        }
        if(count == 0 || days[idx][1] < 1201) System.out.println(0);
        else System.out.println(count);
    }

    public void q_11000(){
        /*
        1줄 : 강의 갯수 N
        2줄~ : N개의 강의 시작시간 및 종료시간
        -1단계
        2차원 배열 {시작, 종료}
        시작 시간 기준 오름차순 정렬. 최소 강의실이기 때문에 빨리 시작해야함
        만일 시작 시간이 같다면 종료 시간이 더 빠른 것이 먼저오도록 함
        -2단계
        맨 처음 시작 수업의 종료 시간을 우선순위 큐(오름차순이라 가장 빨리 끝나는 강의실부터 정렬되어 있을 예정!)에 넣고 시작, 끝까지 순회
        현재 강의의 시작 시간과 큐 상단의 종료시간을 비교
        -> 강의실이 빈 상태(시작 시간이 종료 시간보다 작거나 같은 경우)라면 현재 강의가 진행될 수 있으므로 현재 강의의 종료시간으로 덮어쓰기
        -3단계
        큐사이즈 출력 (큐 사이즈가 곧 강의실의 갯수)
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] times = new int[N][2];
        for(int i=0; i<N; i++){
            times[i][0] = sc.nextInt();
            times[i][1] = sc.nextInt();
        }
        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(times[0][1]);
        for(int i=1; i<N; i++){
            int start = times[i][0];
            if(queue.peek() <= start){
                queue.poll();
                queue.add(times[i][1]);
            }else{
                queue.add(times[i][1]);
            }
        }
        System.out.println(queue.size());
    }
}
