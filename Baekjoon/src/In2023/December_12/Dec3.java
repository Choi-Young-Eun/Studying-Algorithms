package In2023.December_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dec3 {
    public static void main(String[] args){
        //1문제 풀이 완료 : 이진탐색(10815-숫자카드), 1문제 풀이 실패 : 이진탐색(1822-차집합)
    }

    public void q_1822(){
        //시간 초과로 실패
        //: 너무 이진탐색 문제라고 이진탐색 이용해서 풀려고 생각
        //: 다른 풀이들 보니 굉장히 간단하고 쉽게 풀었음 - 내일 다시 참고해서 수정할 계획!
        // ex1. A배열을 다 받고 B배열을 받을 때 A에 포함된 값이면 삭제
        // ex2. A,B배열을 딱 한번씩 훑는데 B값이 더 크면 A를 결과에 포함한 다음 A쪽 인덱스를 늘리고,
        //     A값이 더 크면 B인덱스를 늘리는 방식으로 진행. B를 다 훑은 후 A가 남았다면 남은 A의 값은 다 결과에 포함하기

        /*
        -알고리즘
        궁금한 값이 담긴 배열 A
        찾아볼 배열(비교 대상) B
        B에서 직전까지 탐색한 위치 기억해야함. 거기부터 이어서 탐색 할 것이기 때문에

        언제까지 탐색? A를 끝까지 다볼 때까지
        1. 같은 값이 있을 때 - 지금 인덱스(mid) +1를 반환 = 다음 탐색할 시작점
         -> 다음 인덱스 값이 M 이상이면 종료 (B배열 끝까지 다 돌았는데 A가 남아 있는 경우)
         -> 남은 A값을 다 배열에 저장 (B 배열에서 찾을 수 없는 값이기 때문에)
         */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];
        for(int i=0; i<N; i++){
            A[i] = sc.nextInt();
        }
        int[] B = new int[M];
        for(int i=0;i<M; i++){
            B[i] = sc.nextInt();
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int start = 0;
        int end;
        ArrayList<Integer> result = new ArrayList<>();
        for(int find:A){
            boolean check = false;
            if(start>=M) {
                result.add(find);
                continue;
            }
            end = M-1;
            while(start<=end){
                int mid = (start+end)/2;
                if(B[mid] == find){
                    start = mid+1;
                    check = true;
                    break;
                }
                if(B[mid]>find) end = mid-1;
                else start = mid+1;
            }
            if(!check) result.add(find);

        }
        if(result.isEmpty()){
            System.out.println(0);
        }else{
            System.out.println(result.size());
            for(int i:result){
                System.out.print(i+" ");
            }
        }
    }

    public void q_10815() {
        /*
        ** 10815번 숫자카드
        -입력
        1. 가지고 있는 숫자 카드 개수 N
        2. 숫자카드 값 N개
        3. 소유 여부를 알고자하는 숫자 카드 개수 M개
        4. 숫자후보 M개

        -알고리즘
        1. 배열 cards 정렬
        2. 각각 돌리기
        w(start<=end)
        1) 반띵
        2) 같은지 보기 같으면 System.out.print(1+" ") 반환 //아님 stringbuilder로
        3) 찾을 값이 중간 값보다 더 크면 start=중간값+1
        4) 찾을 값이 중간 값보다 작은 경우이므로 end=중간값-1
        없으면 System.out.print(0+" ") 반환 //아님 stringbuilder로

        - 출력
        가지고 있으면 1
        없으면 0
        한줄에 공백으로 구분
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }
        Arrays.sort(cards);
        int M = sc.nextInt();
        int[] targets = new int[M];
        for (int i = 0; i < M; i++) {
            targets[i] = sc.nextInt();
        }

        for (int find : targets) {
            int start = 0;
            int end = N - 1;
            boolean check = false;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (cards[mid] == find) {
                    check = true;
                    break;
                }
                if (cards[mid] > find) end = mid - 1;
                else start = mid + 1;
            }
            if (check) System.out.print(1 + " ");
            else System.out.print(0 + " ");
        }
    }
}
