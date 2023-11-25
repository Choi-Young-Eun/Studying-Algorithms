package In2023.June_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class June15 {
    public static void main(String[] args) throws IOException {
        
    }

    public void SectionSum_11659() throws IOException{
        /*
        --입력 값
        1) N개의 수, 합구하는 (반복하는) 횟수 M
        2) N개의 수 목록
        3~) 구간 횟수 목록

        --순서
        1. N을 입력 받고 사이즈가 N인 int 배열 생성
        2. 수를 입력 받으며 즉시 합배열 완성
        -> i가 0인 경우 현재 입력값 기입
        -> i가 0보다 큰 경우 i-1번째의 값과 현재 입력 값을 더한 걸 i번째에 기입
        3. M만큼 반복
        -> 처음 인덱스 a와 마지막 인덱스 b를 받음
        -> 합배열의 b번째 값 - a-1번째 값을 출력함
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] Sum = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            Sum[i] = Sum[i-1] + Integer.parseInt(st.nextToken());
        }

        for(int j = 0; j < M; j++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(Sum[b]-Sum[a-1]);
        }
    }

    public void sectionSum_11660() throws IOException{
        /*
        --입력값
        1) 정사각형 사이즈 2차 배열 수 N, 구할 구간 횟수 M
        2~) 배열 값
        2+N~) 구할 구간 처음과 끝 좌표

        --순서
        1. 사이즈와 횟수 입력 받음
        2. 사이즈로 이차배열 생성하고 구간합으로 배열 채워넣기
        3. 구할 구간의 처음과 끝 좌표를 입력 받아 해당 구간의 구간합 출력, 횟수 만큼 반복
        --아래는 코드+슈도코드여요
        Sum[i][j] = Sum[i][i-j]+Sum[i-1][j]+CurrentSu-Sum[i-1][j-1]
        long[][] Sum = new long[N+1][N+1]
        for(int i=1;i<=N;i++){
            줄띄우기
            for(int j=1;j<=N;j++){
                Sum[i][j] = 입력 받기
            }
        }

        for(int k=0;k<M;k++){
            줄 띄우기
            x1입력
            x2입력
            y1입력
            y2입력
            y1,y2값에서 - (x1-1,x2) - (x1,x2-1) + (x1-1,x2-1) 한 값 출력하기
        }
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] Sum = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                Sum[i][j]= Sum[i][j-1] +Sum[i-1][j] -Sum[i-1][j-1] +Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            System.out.println(Sum[x2][y2]-Sum[x2][y1-1]-Sum[x1-1][y2]+Sum[x1-1][y1-1]);
        }
    }

    public void twoPointer_2018(){
        /*
        --입력값
        1) 자연수 N
        --순서
        1. 자연수 N 입력 받음
        2. 포인트 체크할 두 수를 준비 : start와 end. 둘 다 1에서 시작
        3. 총합을 계산할 sum과 횟수를 계산할 result를 준비. 둘 다 1에서 시작
        3. 아래 과정을 start가 n/2+1보다 작을때까지만 반복 해주기
        -N보다 작은 경우 : 앞으로 가면서 Sum에 더해가다가
        -N일 경우 : Result++ 하고 큰쪽 숫자를 한 칸 올리고 Sum에 ++해줌
        -N보다 클경우 : 작은 쪽 숫자를 한 칸올리고 Sum에 -- 해줌
        4. 끝나면 Result 출력
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int start=1, end=1;
        int sum=1, result=1;
        while(start<N/2+1){
            if(sum<N){
                sum+=++end; //end를 증가시킨 다음 sum에 end를 더했는데 그걸 한줄로 줄임
            }else if(sum>N){
                sum-=start++; //sum에서 start를 뺀 다음 start를 하나 증가했는데 그걸 한줄로 줄임
            }else{
                result++;
                sum+=++end; //end를 증가시킨 다음 sum에 end를 더했는데 그걸 한줄로 줄임
            }
        }
        System.out.println(result);
    }

    public void jumongCode_1940() throws IOException{
        /*
        --입력값
        1) 갯수 N
        2) 만족해야하는 합 M
        3) N개의 수
        --순서
        1. 갯수 N과 만족해야하는 합 M 입력
        2. N개의 수 배열에 입력
        3. 결과 담을 변수 result 0으로 초기화
        4. 배열 정렬
        5. 배열의 앞과 뒤에 인덱스의 값을 기준으로 진행, 앞이 뒤 인덱스보다 작은 동안 반복
        -배열의 앞 뒤 인덱스 값 더한 값이 M에 비해 작으면 앞++
        -M에 비해 크면 뒤--
        -M이면 result++, 앞++, 뒤--
        6. result 출력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int Goal = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        int result = 0, start = 0;
        int end=N-1;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        while(start<end){
            int sum = numbers[start]+numbers[end];
            if(sum == Goal){ //같을 때
                result++;
                end--;
                start++;
            }else if(sum < Goal){ //작을 때
                start++;
            }else{ //클 때
                end--;
            }
        }
        System.out.println(result);
    }
    
    public void goodNumber_1253() throws IOException{
        /*
        --입력 값
        1) 수의 갯수 N
        2) N개의 수
        --순서
        1. 입력 받을 수의 갯수와 숫자들을 입력 받음
        2. 정렬
        3. 0에서 N-1까지 반복
        -배열의 인덱스로 찾을 값을 지정해 담고 앞 뒤 인덱스를 하나씩 포인터로 지정
        4. 앞 인덱스가 뒤 인덱스보다 작을 동안에 반복
        -줄여나가면서 전에 했던거 하기
        -앞 뒤 인덱스를 더한 값이 찾을 값과 동일하면 result++하고 break; (다음으로 넘어감)
        5. result 출력
        --틀렸음. 놓친 부분
        : 다른 두 수의 합이 해당 수여야하는데 만약 다른 한 수가 0이고 자신과 더한 경우도 자신이기 때문에 앞과 뒤 인덱스가 자기 자신이 아닌지
          확인하는 것이 필요했음! 나는 그걸 놓쳤당...
          읽을 때는 이해 못했는데 이젠 이해 완료! 따라서 합이 같은 경우 인덱스를 확인하는 작업이 필요함!
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        int result = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        for(int i=0; i<N; i++){
            int k = numbers[i];
            int start = 0, end = N-1;
            while(start < end){
                if(numbers[start]+numbers[end] == k){
                    result++;
                    break;
                }else if((numbers[start]+numbers[end] < k)){
                    start++;
                }else{
                    end--;
                }
            }
        }
        System.out.println(result);
    }
}
