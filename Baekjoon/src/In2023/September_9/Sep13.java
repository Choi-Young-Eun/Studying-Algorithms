package In2023.September_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sep13 {
    public static void main(String[] args) throws IOException {
        
    }


    private void not_yet() throws IOException {
        // -> N 배열의 크기
        // -> A 정렬해야하는 배열. 1부터 시작
        // -> 원래 자리에서 큰 인덱스가 온 게 자리가 바꼈다는 거잖아. 오른쪽은 밀리는 거고 왼쪽은 정렬이니까?
        // 정렬된 걸 기준으로 전에서 얼마나 이동 된 건지 파악. 그리고 제일 많이 이동한 거에 +1하기.
        // 이동횟수에다가 마지막에 잘 정렬이 됐다고 확인한 것까지 같이 더해주기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        for (int i = 0; i < N; i++) {

        }
    }

    private void sort_11399() throws IOException {
        //삽입 정렬로 오름차순 정렬 후 합배열을 구해서 합배열의 총합을 출력함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num[] = new int[N];
        int sum[] = new int[N];
        int i;
        String[] str = br.readLine().split(" ");
        for(i=0; i<N; i++){
            num[i] = Integer.parseInt(str[i]);
        }
        for(i=0; i<N-1; i++){
            int min = i;
            for(int j=i+1; j<N; j++){
                if(num[min]>num[j]){
                    min=j;
                }
            }
            if(min != i) {
                int temp = num[min];
                num[min] = num[i];
                num[i] = temp;
            }
        }
        sum[0]=num[0];
        for(i=1; i<N; i++){
            sum[i] = sum[i-1] + num[i];
        }
        int result = 0;
        for(i=0; i<N; i++){
            result += sum[i];
        }
        System.out.println(result);
    }
}
