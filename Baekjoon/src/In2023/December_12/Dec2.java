package In2023.December_12;

import java.util.Arrays;
import java.util.Scanner;

public class Dec2 {
    public static void main(String[] args){
        //2문제 풀이 완료 : 탐색-이진탐색(2343-기타레슨, 16401-과자나눠주기)
    }

    public void q_2343(){
        /*
        ** 알고리즘
        1. 블루레이를 담을 수 있는 최소 크기는 가장 큰 강의부터 모든 강의를 합쳤을 때이다
        ex1. 가장 큰 강의 크기로 담는 경우 최소 6개의 테이프로 담을 수 있다
        ex2. 모든 강의를 합친 크기로 담는 경우 1개의 테이프로 담을 수 있다
        2. 내가 궁금한 것은 'M개의 테이프로 강의를 담으려고 할 때 테이프의 최소 사이즈'이다
        3. 사이즈의 순서는 변함이 없음! 정렬된 수를 탐색하는 가장 좋은 방법 중 하나인 이진 탐색 이용
        4. 전체 중 가운데에 위치한 사이즈 mid로 강의들을 테이프에 담을 수 있는지 확인
        : 한 테이프의 크기를 mid로 했을 때 총 몇개의 테이프가 필요한지 체크
        5. 충분히 담을 수 있다면 마지막 위치를 mid-1로 해서 앞부분 다시 탐색
        6. 테이프가 M개 보다 많이 필요하다면 처음 위치를 mid+1로 해서 뒷부분 다시 탐색
        7. M개로 담을 수 있을 때까지 반복
         */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] numbers = new int[N];
        int max = 0;
        int sum = 0;
        for(int i=0; i<N; i++){
            numbers[i] = sc.nextInt();
            if(max < numbers[i]) max = numbers[i];
            sum += numbers[i];
        }

        int start = max;
        int end = sum;
        while(start<=end){
            int target = (start+end)/2;
            int size = 0;
            int count = 0;
            for(int i:numbers){
                if(size+i > target) {
                    count++;
                    size=0;
                }
                size+=i;
            }
            if(size != 0) count++;
            if(count>M) start = target+1;
            else end = target-1;
        }
        System.out.println(start);
    }

    public void q_16401(){
        /*
        ** 아이디어
        0. 수를 순서대로 받는다는 얘기가 없으므로 꼭 정렬해주기!
        1. 명수로 나누기 = 35/4 = 8 = 한명이 가져갈 수 있는 맥시멈 값 자연수 8
        -> 이렇게 하면 1명 일 때 최대길이의 과자를 받을 수 없음! (와 나 한명일때를 생각 못했네)
        2. 과자 길이 중 최대 길이를 최대값으로 잡고 진행

        0. start<=end 까지 진행
        1. 1부터 정렬된 배열의 마지막 값까지 탐색
        2. 위의 평균값을 기준으로 진행
        for : snack[0]부터 끝까지 확인!
        1) 가운데 값보다 작으면 넘어가기
            2) 가운데 값과 같거나 크면 가운데 값으로 나눈 몫을 총 인원 수에 추가하기
            3. 총 인원 수와 조카 수를 비교
            -> 명수보다 작은 경우(나눠줘야 하는 아이들의 명수보다 작은 경우), 마지막 인덱스를 가운데-1로 설정하여 다시 진행
            -> 명수보다 같거나 큰 경우, 시작 인덱스를 가운데+1로 설정하여 다시 진행
         4. end 출력
         */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] snacks = new int[M];
        int sum = 0;
        for(int i=0; i<M; i++){
            snacks[i] = sc.nextInt();
            sum += snacks[i];
        }

        if(sum<N){
            System.out.println(0);
            return;
        }

        Arrays.sort(snacks);

        int start = 1;
        int end = snacks[M-1];

        while(start<=end){
            int target = (start+end)/2;
            int count = 0;
            for(int i:snacks){
                if(target>i) {
                    continue;
                }
                count+=i/target;
            }
            if(count>=N) start = target+1;
            else end = target-1;
        }
        System.out.println(end);
    }
}
