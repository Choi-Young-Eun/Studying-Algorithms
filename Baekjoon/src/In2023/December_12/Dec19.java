package In2023.December_12;

import java.util.Arrays;
import java.util.Scanner;

public class Dec19 {
    //10448번
    static int[] numbers;
    //9095번
    static int[] countArr;
    public static void main(String[] args){
        //3문제 풀이 완료 : 완전 탐색(10448-유레카이론, 2309-일곱난쟁이, 9095-123더하기)
    }

    public void q_10448(){
        //완전 탐색! : 배열에 미리 값을 세팅 해놓고 처음부터 될 때까지 하나하나 더해보기
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        numbers = new int[45];
        for(int i=1; i<45; i++){
            numbers[i] = i*(i+1)/2;
        }
        for(int i=0; i<T; i++){
            int N = sc.nextInt();
            System.out.println(test_10448(N));
        }
    }
    private static int test_10448(int N){
        for(int i=1; i<45; i++){
            for(int j=1; j<45; j++){
                for(int k=1; k<45; k++){
                    if(numbers[i]+numbers[j]+numbers[k] == N)
                        return 1;
                }
            }
        }
        return 0;
    }
    private static int fail_10448(int n) {
        /*
        : 지금 수와 같지 않을 때까지 가장 큰 수 찾기
        : 넘어가면 그 전 수를 마이너스 하고 그 수를 원래 수에서 빼기 (남은 걸로 다시 돌기) -> 세번 돌기
        -> 최대 값만 찾으면 될 거라고 잘못 생각했음
         */
        int count = 0;
        int sum, i;
        while(n > 0){
            sum = 1;
            for(i=2; sum+i<n; i++){
                sum += i;
            }
            n -= sum;
            count++;
        }
        if(count == 3) return 1;
        else return 0;
    }


    public void q_1010(){
        //아홉 난쟁이의 키를 다 더하고
        //-100한 값을 담아서 두 난쟁이 키의 합이 일치하는 경우를 찾는다
        Scanner sc = new Scanner(System.in);
        int[] cm = new int[9];
        int sum = 0;
        for(int i=0; i<9; i++){
            int num = sc.nextInt();
            cm[i] = num;
            sum += num;
        }
        Arrays.sort(cm);
        int i=0, j=0;
        boolean find = false;
        sum -= 100;
        for(; i<8; i++){
            for(j=8; j>i; j--){
                if(cm[i]+cm[j] == sum) {
                    find = true;
                    break;
                }
            }
            if(find) break;
        }
        for(int k=0; k<9; k++){
            if(k==i || k==j)
                continue;
            System.out.println(cm[k]);
        }
    }


    public void q_9095(){
        /*
        DP! : 1, 2, 3으로 만들 수 있는 경우의 수는 자신의 -1, -2, -3이 1,2,3으로 만들어질 수 있는 경우의 수의 총합이다
        : 자신보다 3개 이전의 수들의 경우의 수만 알면 지금의 경우의 수를 알 수 있음
        : N의 최대값은 10이므로 배열에 더한 값을 저장해 놓기
        : countArr[N] = countArr[N-1] + countArr[N-2] + countArr[N-3];
        1=1, 2=2, 3=4
        -> 4=4+2+1
        -> 5=7+4+2 = 13
        -> 6=13+7+4 = 24
        -> 7=24+13+7 = 44
         */
        Scanner sc = new Scanner(System.in);
        countArr = new int[11];
        int T =  sc.nextInt();

        for(int i=0; i<T; i++){
            int N = sc.nextInt();
            System.out.println(dp_9095(N));
        }
    }
    private static int dp_9095(int N){
        if(N == 1){
            return 1;
        }else if(N == 2){
            return 2;
        }else if(N==3){
            return 4;
        }else{
            if(countArr[N] == 0) {
                countArr[N] = dp_9095(N-1) + dp_9095(N-2) + dp_9095(N-3);
            }
            return countArr[N];
        }
    }
}
