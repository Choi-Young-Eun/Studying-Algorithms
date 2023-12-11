package In2023.December_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dec11 {
    public static void main(String[] args){
        //3문제 풀이 완료 : 그리디(2217-로프, 1026-보물, 2847-게임을만든동준이), 1문제 풀이 중 : 그리디(1541-잃어버린괄호)

        /* 1541번 슈도코드 작성 중
        : 자릿수는 최대 5개까지
        : 숫자에 해당하면 담기 (Array<> 숫자에 담기)
        : -나 +면 멈추고 (Array<> 부호에 담기)

        0. 처음 값에서 최대한 많이 빼는 게 중요하군
        0. 마이너스 부호를 보면 다음 마이너스가 나올 때까지 더하기는 빼기로 전환
        1. 이전 부호 기억하기
        2. 이전 부호가 -였고 현재 부호가 +라면 빼고 다음으로 넘어가기 continue
        3. 나머지의 경우 더하고 이전부호 업데이트

        1) 빼주기
        - +
        2) 더해주기
        - -
        + -
        + +
        */
    }

    public void q_2217(){
        /*
        ** 입력
        1줄 : 로프 갯수 N
        2줄~ : 각 로프당 버틸 수 있는 최대 중량 (N줄)

        ** 출력
        로프들을 이용하여 들어올릴 수 있는 물체의 최대 중량
        (모든 로프를 사용하지 않아도 됨. 몇 개의 로프를 골라 사용해도 됨!)

        ** 과정
        병렬로 걸면 그 중 최소 무게 기준으로만 각각 들 수 있음
        1. 최대 값을 먼저오게 정렬
        2. 앞에서 부터 체크. 제일 앞의 값을 최대 값 max으로 잡고
        3. 그 다음 부터 (자기의 위치)*(자기의 최대 중량) 값을 체크하고 제일 첫번째 값과 비교
        -> 작으면 넘어가고
        -> 크면 max에 담기
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];
        for(int i=0; i<N; i++){
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        int Max = numbers[N-1];
        for(int i=N-2; i>=0; i--){
            int target = numbers[i]*(N-i);
            if(Max<target) Max = target;
        }
        System.out.println(Max);
    }

    public void q_1026() throws IOException {
        /*
        **아이디어
        : 출력이 최대 값이니까 B는 재배열이 안된다고 했지만 A와 같이 재배열(정렬) 시키기
        : A[i]값과 B[i]값을 곱한 것이 최소가 되려면 최소 값과 최대 값을 곱하면 됨
        : 따라서 A는 제일 작은 값부터, B는 제일 큰 값부터 같이 차례대로 곱해서 더하면 됨
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int[] B = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++){
            A[j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++){
            B[j] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int sum = 0;
        for(int i=0; i<N; i++){
            sum += A[i]*B[N-1-i];
        }
        System.out.println(sum);
    }

    public void q_2847() {
        /*
        **아이디어
        : 최대로 정렬
        : 제일 마지막 레벨 (제일 큰 값) 부터 시작
        : 다음 값이 현재 값보다 작으면 continue
        : 크거나 같다면 다음값-현재값+1 을 result에 더함
        : 마지막까지 진행
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] scores = new int[N];

        for(int i=0; i<N; i++){
            scores[i] = sc.nextInt();
        }

        int count = 0;
        int pre = scores[N-1];
        for(int i=N-2; i>=0; i--){
            int crt = scores[i];
            int minus = 0;
            if(pre<=crt) {
                minus = Math.abs(crt - pre + 1);
            }
            pre = crt-minus;
            count += minus;
        }
        System.out.println(count);
    }
}
