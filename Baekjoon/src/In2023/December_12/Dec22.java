package In2023.December_12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dec22 {
    public static void main(String[] args){
        //2문제 풀이 완료 : 완전 탐색(프로그래머스:Lev1-모의고사, 프로그래머스:Lev1-최소직사각형)
    }

    public static int[] mock_test_solution(int[] answers) {
        /*
        1) 1번은 (문제 번호+1)%5 와 같은지
        2) 2번은 홀수가 전부 2, 짝수는 순서대로 5 1 3 4
        (문제번호+1)%2 해서 1이면 2 이고 -> 2, 1%4 1
        (문제번호+1)%2 해서 0이면 문제번호/2하고, 그 몫을 4로 나눈 나머지
        3) 3번은 3 1 2 4 5 (문제 번호%10)이 아래와 같은지
        if 1, 2 -> 3
        if 3, 4 -> 1
        if 5, 6 -> 2
        if 7, 8 -> 4
        if 9, 0 -> 5
         */
        int[] result = new int[3];
        ArrayList<Integer> iii = new ArrayList<>();

        int N = answers.length;
        int count = 0;
        //1번
        for(int i = 0; i<N; i++){
            if((i+1)%5 == answers[i] || ((i+1)%5==0 && answers[i]==5))
                count++;
        }
        result[0] = count;
        count = 0;
        //2번
        int[] two_arr = {5, 1, 3, 4};
        for(int i = 0; i<N; i++){
            int num;
            if((i+1)%2 == 1)
                num = 2;
            else
                num = two_arr[((i+1)/2)%4];
            if(num == answers[i])
                count++;
        }
        result[1] = count;
        count = 0;
        //3번
        for(int i = 0; i<N; i++){
            int num = (i+1)%10;
            if(num == 1 || num == 2)
                num = 3;
            else if(num == 3 || num == 4)
                num = 1;
            else if(num == 5 || num == 6)
                num = 2;
            else if(num == 7 || num == 8)
                num = 4;
            else
                num = 5;

            if(num == answers[i])
                count++;
        }
        result[2] = count;
        //최대값 배열에 담기 - 1번과 2번 결과 비교
        if(result[0] > result[1]){
            iii.add(1);
        }else if(result[0] < result[1]){
            iii.add(2);
        }else{
            iii.add(1);
            iii.add(2);
        }
        //최대값 배열에 담기 - 위의 결과와 3번 결과 비교
        if(result[iii.get(0)-1] == result[2]){
            iii.add(3);
        }else if(result[iii.get(0)-1] < result[2]){
            iii.clear();
            iii.add(3);
        }
        return iii.stream().mapToInt(Integer::intValue)
                .toArray();
    }

    public static int minimum_rectangle_solution(int[][] sizes) {
        /*
        0.최대값 w : 19, h : 16 중 더 작은 것을 기준으로 함. (여기선 h 기준 탐색)
        1.우선 순위 큐 정의
        2.우선순위 큐에 하나씩 담기
        3.(배열 맨 처음인 최대값 0번부터 시작) - for
            1) 큐에서 제거. 제거한 값 탐색
            2) w값이 h보다 작은가? O or X
                2-1) X : w값이 h보다 작지 않다면 현재 h값이 최선이므로 현재 값을 바로 hMax로 설정 후 종료
            3) O : w값과 h값 반대로 돌리기 int[] = new int[]{[i][1],[i][0]}
            4) 위의 int[]를 우선순위 큐에 다시 넣기
         */
        int wMax = 0;
        int hMax = 0;
        int len = sizes.length;
        for(int i=0; i<len; i++){
            if(sizes[i][0] > wMax)
                wMax = sizes[i][0];
            if(sizes[i][1] > hMax)
                hMax = sizes[i][1];
        }
        if(wMax > hMax){
            Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] == o2[1]){
                        return o2[0]-o1[0];
                    }
                    return o2[1]-o1[1];
                }
            });
            for(int i=0; i<len; i++){
                queue.add(sizes[i]);
            }
            while(true){
                int[] target = queue.remove();

                if(! (target[0] < target[1])) {
                    hMax = target[1];
                    break;
                }
                int[] change = new int[] {target[1], target[0]};
                queue.add(change);
            }
        }else{
            Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] == o2[0]){
                        return o2[1]-o1[1];
                    }
                    return o2[0]-o1[0];
                }
            });
            for(int i=0; i<len; i++){
                queue.add(sizes[i]);
            }
            while(true){
                int[] target = queue.remove();
                if(! (target[1] < target[0])) {
                    wMax = target[0];
                    break;
                }
                int[] change = new int[] {target[1], target[0]};
                queue.add(change);
            }
        }
        return wMax*hMax;
    }
}
