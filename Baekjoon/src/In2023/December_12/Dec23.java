package In2023.December_12;

import java.util.LinkedList;
import java.util.Queue;

public class Dec23 {
    public static void main(String[] args){
        //1문제 풀이 완료 : 완전 탐색(프로그래머스:Lev2-카펫)
    }
    public static int[] carpet_solution(int brown, int yellow) {
        /*
        -아이디어
        : brown이 왜 주어지지 했는데 내가 잘못 생각했음
        : 두 조건을 만족해야됨 brown보다 작으면 안됨. yellow를 brown으로 딱 맞게 감쌀 수 있어야함

        1. 카펫으로 만들어 질 수 있는 것들을 추려서 큐에 담기
        : i로 나눌 수 있으면 큐에 담기
        -> 1-3까지는 돌지 않음
        -> 10까지는 반만 돌기
        -> 11이상은 반의 반만 돌기
        2. 큐가 빌 때까지 확인하기
        -> 꺼낸 값으로 yellow를 나눈 값을 구함
        -> 그 둘을 더한 값의 두배인 값에서 +4를 해서 brown과 같은지 확인
        -> 같다면 멈추기
        3. 찾은 값 first와 그 값으로 나눈 몫 second 중에 작은 걸 세로 값으로 주기
        4. 위 결과에 +2씩 해서 넘기기
         */
        int[] answer = new int[2];
        Queue<Integer> cond = new LinkedList<>();
        int first = 0;
        int second = 0;
        cond.add(1);
        if(yellow > 10){
            int size = yellow/4;
            for(int i=2; i<=size; i++){
                if(yellow%i == 0)
                    cond.add(i);
            }
        }else if(yellow > 3){
            int size = yellow/2;
            for(int i=2; i<=size; i++) {
                if (yellow % i == 0)
                    cond.add(i);
            }
        }

        while(!cond.isEmpty()){
            first = cond.remove();
            second = yellow/first;
            if(brown == (first+second)*2+4)
                break;
        }

        if(first<second){
            answer[0] = second+2;
            answer[1] = first+2;
        }else{
            answer[0] = first+2;
            answer[1] = second+2;
        }

        return answer;
    }
}
