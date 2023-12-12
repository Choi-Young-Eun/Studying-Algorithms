package In2023.December_12;

import java.util.Arrays;
import java.util.Scanner;

public class Dec12 {
    //11501_fail에 사용한 배열
    static long[] tax;
    public static void main(String[] args){
        //3문제 풀이 완료 : 그리디(2217-로프, 1026-보물, 2847-게임을만든동준이)
    }

    public void q_1541(){
        /* 1541번 슈도코드 작성 중
        0. 처음 값에서 최대한 많이 빼는 게 중요하군
        0. 마이너스 부호를 보면 다음 마이너스가 나올 때까지 더하기는 빼기로 전환
        1. 이전 부호 기억하기
        2. 받은 값이 부호인 경우 현재 num을 pre로 더하기
        -> 단, 이전 부호(=pre)가 +고 현재 부호가 -인 경우, pre에 현재 부호 담아주기
        3. 숫자인 경우, num값의 자릿수를 한자리 올리고 현재 숫자를 num에 더함
        */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int num = 0;
        char pre = '+';
        int sum = 0;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c == '+' || c == '-'){
                if(pre == '-'){
                    sum -= num;
                }else if(pre == '+' && c == '-'){
                    sum += num;
                    pre = c;
                }else{
                    sum += num;
                }
                num = 0;
            }
            else {
                num = num*10 + c-'0';
            }
        }
        if(pre == '-'){
            sum -= num;
        }else{
            sum += num;
        }
        System.out.println(sum);
    }

    public void q_11501(){ //값의 범위가 크면 int로 불가능. long으로 변경함!
        /*
        ** 과정 : 마지막부터 시작
        1) 현재 값이 이전 값보다 큰경우 ex. 2 5 '9' 1
        -> 현재 값 : 9
        큰 값을 현재값으로 변경
        2) 현재 값이 이전 값과 같은 경우 ex. 2 5 '1' 1
        -> 현재 값 : 1
        넘어가기
        3) 현재 값이 이전 값보다 작은 경우 ex. 2 5 '1' 9
        -> 현재 값 : 1
        이득결과 += 이전값-현재값
        */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0; i<T; i++){
            int N = sc.nextInt();
            tax = new long[N];
            for(int j=0; j<N; j++){
                tax[j] = sc.nextLong();
            }
            long max = tax[N-1];
            long result = 0;
            for(int index = N-2; index>=0; index--){
                long target = tax[index];
                if(max<target) max = target;
                else if(max>target) result += max-target;
            }
            System.out.println(result);
        }
    }

    public void q_11501_fail() { //실패 : 4%에서 실패함 & 값의 범위가 크면 int로 불가능. long으로 변경함!
        /*
        ** 입력값
        1줄 : 테스트 수 T
        케이스당 1줄 : 원소 수N
        케이스당 2줄 : 순서대로 N일의 주식 가격

        ** 출력값
        ~T줄 : 최대 이익 값

        ** 과정
        //result = 0; -> 출력 할 최대 이익의 값임
        while(i가 원소수 N보다 작은 경우 진행)
        1. 최대값 찾기 함수에 넣기
         -> 최대값이 없으면 -1반환
         -> 최대값이 담긴 인덱스를 반환함
        2. 현재 값을 기준으로 비교
         -> -1이면 종료 //더이상 최대값이 없음
        3. 최대값 위치까지 돌리기 작업 메서드(현재 위치 인덱스, 최대 값 인덱스) 돌리기
        4. 최대값 인덱스를 i로 세팅

        ** 문제 이해하기
        -홍준이가 할 수 있는 것
        1. 주식 하나 사기
        2. 원하는 만큼 주식 팔기
        3. 아무것도 안하기 (넘어가기)
        -예시
        3 5 7 9 4 12 3
        사 사 사 사 사 팔 가
         -> 각 이득 : 9 7 5 3 8
        sum은 산 가격들을 다 합한 것이 들어있음
        count는 산 날수가 들어가 있음
        -진행 과정
        1) 오늘 주식 가격보다 뒷쪽에 더 비싼 날이 있는 경우 그날은 사야함
         -> sum+=현재 값
         -> count++
        2) 오늘 주식 가격이 가장 비싼 날이라면 있는 걸 다 팔아야함
         -> count*현재 값-sum = 이득 본 가격 -> result에 더하기
         -> sum, count = 0;
        : 앞에서부터 배열 값 중에 최대값 찾고, 최대값 인덱스 전까지 다 사야됨 (같은 경우는 넘어가기)
        */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0; i<T; i++){
            int N = sc.nextInt();
            tax = new long[N];
            for(int j=0; j<N; j++) {
                tax[j] = sc.nextLong();
            }
            int crt_index = 0;
            long result = 0;
            while(crt_index < N){
                int max_index = find_max_index(crt_index); //최대값 찾기 함수(crt_index)
                if(max_index == -1){
                    break;
                }
                result += find_plus(crt_index, max_index); //최대값 위치까지 돌리기 작업 메서드(index, max_index) 돌리기
                crt_index = max_index+1;
            }
            System.out.println(result);
        }
    }
    static int find_max_index(int start){
        //최대값 찾기 : 최대값이 담긴 인덱스를 반환함
        int max_index = start;
        for(int i=start+1; i<tax.length; i++){
            if(tax[i]>=tax[max_index])
                max_index=i;
        }
        return max_index;
    }
    static long find_plus(int start, int end){
        //최대값 위치까지 더하고 최대값*갯수에서 빼고 반환
        int sum = 0;
        int count = 0;
        for(int i=start; i<end; i++){
            if(tax[i] == tax[end]) continue;
            sum += tax[i];
            count++;
        }
        return count*tax[end]-sum;
    }

    public void q_15903(){ //값의 범위가 크면 int로 불가능. long으로 변경함!
        /*
        ** 입력값
        1줄 : 숫자 카드 갯수 N, 합치는 횟수 M
        2줄 : 숫자 카드 N개의 값

        ** 출력값
        가장 작은 점수를 계산 결과

        ** 과정
        0. 받으면서 총합을 더하기
        -아래는 합치는 횟수 만큼 반복
        1. 최소값으로 정렬
        2. 맨 앞 두개를 더한 값을 맨 앞 두개로 업데이트
        3. 더한 값을 총합에 더하기
        // 11 -> 2 3 6 -> 5 5 6 -> 11+5 = 16
        */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long result = 0;
        long[] numbers = new long[N];
        for(int i=0; i<N; i++){
            long num = sc.nextLong();
            result += num;
            numbers[i] = num;
        }
        int count = 1;
        while(count<=M){
            Arrays.sort(numbers);
            long sum = numbers[0]+numbers[1];
            result += sum;
            numbers[0] = sum;
            numbers[1] = sum;
            count++;
        }
        System.out.println(result);
    }
}
