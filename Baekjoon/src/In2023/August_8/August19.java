package In2023.August_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class August19 {
    public static void main(String[] args) throws IOException {
        /*배열 내의 값을 기준으로 보는데
값이 클수록 우선순위가 높고
우선순위 큐에 넣을 때는 인덱스를 기준으로 한다.
location에 담긴 숫자가 몇인지 보기
같은 값이면 자리를 바꾸지 않는다고 가정했을 때!
: 2 0 1 3 4 5
같은 값이어도 자리를 바꾼다고 가정했을 때
: 2 5 4 3 1 0
인덱스가 location이 나올 때 까지 반복
1) 꺼낸 값이 같고
last인덱스보다 크거나, location보다 작을 경우
count++
2) 다른 경우
-> last에 인덱스를 담음
-> count++
         */
        /*
        모든 음식의 스코빌 지수를 K이상으로 만들 수 없는 경우 return -1;
-우선순위 기준
1. 작은 숫자가 우선순위를 높게 갖도록
-스코빌 지수 만들기
for 0부터 시작, 우선순위 큐가 비지 않은 경우
1) 만약 루트값을 peek한 게 K보다 같거나 크면
-> 중지
2) 만약 K보다 작은데 pq사이즈가 1인 경우
바로 -1 반환
3) else의 경우임
두 개를 우선순위 큐에서 꺼냄
꺼낸 것 1 = pq.remove();
꺼낸 것 2 = pq.remove();
pq.add(꺼낸 것1 + 꺼낸 것2*2)
-반환하기
//새로운 것 = 꺼낸 것1 + 꺼낸 것2*2
// pq.add(새로운 것)
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });


    }

    public void priorityQueue_11286() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //1. 연산의 갯수 N
        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        //2. 우선순위 큐 선언!
        //: 기준 - 절대값이 작은 값이 우선순위가 높고, 같은 값이면 음수가 더 우선순위가 높음
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2)
                    return 0;
                if(Math.abs(o1) == Math.abs(o2))
                    return o1-o2;
                return Math.abs(o1)-Math.abs(o2);
            }
        });
        int su=0;
        for(int i=0; i<N; i++){
            //3. 정수 x (N+1번째 줄까지 입력. 입력받은 걸 바로 우선순위 큐에 넣기)
            st = new StringTokenizer(br.readLine());
            su = Integer.parseInt(st.nextToken());
            //1) 값이 0이 아닌 경우, 힙 배열에 추가
            if(su != 0){
                pq.add(su);
            //2) 값이 0이고 힙 배열이 빈 경우, 0 출력(sb에 담기)
            }else if(pq.isEmpty()){
                sb.append(0).append('\n');
            //3) 우선순위 큐에서 한 개 제거
            }else{
                sb.append(pq.remove()).append('\n');
            }
        }
        // 4. sb 출력
        System.out.print(sb);
    }
}
