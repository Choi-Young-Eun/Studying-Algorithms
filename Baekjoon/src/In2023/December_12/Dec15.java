package In2023.December_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dec15 {
    public static void main(String[] args){
        //1문제 풀이 완료 : 그리디(1744-수묶기), 1문제 풀이 중 : 그리디(7570-줄세우기)

        /*
        1. 5/2 (값이 홀수면 +1) <= 수찾기
         -> 여기서는 3이하. 배열에서 위치 확인. 인덱스 저장
        2. 값-1해서 더하기
        값-1 + 인덱스 앞 칸 수
        3. 뒷번호 확인하기 찾은 수 다음에 오는 숫자 확인하기
        -현재값 이하면 넘어가기
        -찾았으면 +1해서 그다음 값 찾기
        -더 크면 총 횟수+1 하기
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];
        for(int i=0; i<N; i++){
            numbers[i] = sc.nextInt();
        }
        int count = 0;
        int find;
        int idx = 0;
        if(N/2 == 0) find = N/2;
        else find = N/2+1;
        for(int i=0; i<N; i++){
            if(numbers[i] <= find){
                idx = i;
                break;
            }
        }
        int target = numbers[idx];
        count = target-1+idx;
        target++;
        for(int i = idx+1; i<N; i++){
            if(numbers[i] < target){
                continue;
            }else if(numbers[i] == target){
                target++;
            }else{
                count++;
            }
        }
        System.out.println(count);
    }

    public void q_1744(){
        /*
        입력 : N개의 수가 주어짐
        출력 : 최대가 되는 프로그램

        -최대가 되는 조건
        1. 양수와 0은 곱하지 않는다
        2. 음수와 0은 곱한다 (만약 -가 하나만 남은 경우에 한해 최대가 될 수 있음)
        3. 1과 곱하지 않는다 -> 무조건 더한다
        4. +는 무조건 +와 곱한다
        5. -는 무조건 -와 곱한다
        6. -1인 경우 -가 있다면 곱하는 것이 더 좋다

        -과정 ver.1 -> 실패. 너무 지저분해서 코드로 짜다가 포기 (시간도 1시간 정도 지나서 포기요!)
        0. N이 0이면 받아서 바로 출력
        1. 배열 하나에 숫자 받아서 정렬
        2. 끝부터 시작, 양수인 것만 진행
        - 1이면 sum에 더하기
        - 0이면 zero 변수에 true 설정 및 인덱스 다음 인덱스로 반환, i값을 out of size로 보내기
        - 그외 곱해서 sum에 더하기
        -> 양수와 음수를 구분하여 진행하려고 함
        3. 인덱스가 0일 때까지 진행, 음수 부분 진행
        - 위의 인덱스 범위가 유효하면 끝에서 부터 시작
        - 두개씩 곱하고 더하기
        - 다음 값이 없다면 현재 값을 세팅하고 0과 곱함

        -과정 ver.2 -> 답지보고 힌트 얻어서 진행, 성공
        0. N이 0이면 받아서 바로 출력
        1. 양수 배열(1이상)과 음수 배열에 나눠 담고 각각 정렬
        2. 양수 배열 시작 - 비어있는 동안, 뒤에서 부터 제거
        - 1개 남았을 땐 무조건 sum에 더하기
        - 2개 남았을 때 만약 두번째 수가 1이라면 sum에 각각 더하기
        - 그외 곱해서 sum에 더하기
        3. 음수 배열 시작 - 비어있는 동안, 앞에서 부터 제거
        - 1개 남았을 땐 무조건 sum에 더하기
        - 2개 남았을 땐 무조건 sum에 곱해서 더하기
        4. sum 출력
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if(N == 1){
            System.out.println(sc.nextInt());
            return;
        }
        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        for(int i=0; i<N; i++){
            int num = sc.nextInt();
            if(num > 0){
                plus.add(num);
            }else{
                minus.add(num);
            }
        }
        Collections.sort(plus);
        Collections.sort(minus);

        int sum = 0;
        int size = plus.size()-1;
        while(!plus.isEmpty()){
            int a = plus.remove(size--);
            if(size < 0){
                sum += a;
                break;
            }
            int b = plus.remove(size--);
            if(b == 1){
                sum += a+b;
            }else{
                sum += a*b;
            }
        }
        size = minus.size();
        while(!minus.isEmpty()){
            int a = minus.remove(0);
            if(--size == 0){
                sum += a;
            }else{
                int b = minus.remove(0);
                size--;
                sum += a*b;
            }
        }
        System.out.println(sum);
    }
}