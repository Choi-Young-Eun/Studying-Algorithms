package In2024.Jan_1;

import java.util.Scanner;

public class Jan9 {
    //q_14225
    static boolean[] check;
    static int[] numbers;
    static int N;
    public static void main(String[] args){
        //2문제 풀이 완료 : 완전탐색(백준:1254, 14225)
    }

    public void q_14225(){
        /*
        - 과정
        나는 최대값을 다 더할 생각을 못하고 무작정 S를 이루고있는 수가 100,000 보다 작다길래 100,000으로 확인 배열 크기를 잡았다
        수를 받으면서 나올 수 있는 수를 체크 한다는 생각만 했지 그것들을 다 더해 나올 수 있는 최댓값이 몇인지는 생각하지 못했다
        자릿수를 하나씩 늘려주면서 더해야되는데 그럼 for문이 도대체 몇개인지... 인덱스랑 자릿수를 주면 계산을 어떻게 해야될지 생각하다
        도저히 안돼서 다른 사람들 풀이를 참고하니 세상에 재귀로 풀었구나
        dfs 느낌으로 푼 것 같았다
        그리고 수를 체크하는 배열은 최대값의 +2 사이즈 였다
        이유는 최솟값을 찾는 거지만 모두 1로 되어 있는 경우에는 max내에서 등장하지 않는 최솟값을 찾을 수 없고 max+1이 최솟값이 되기 때문에
        이러한 경우를 포함한 것 같았다
         */
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        numbers = new int[N];
        int max = 0;
        for(int i=0; i<N; i++){
            int num = sc.nextInt();
            numbers[i] = num;
            max += num;
        }

        check = new boolean[max+2];
        find_sum(0, 0);

        int idx = 1;
        for(; idx<=max+1; idx++){
            if(!check[idx])
                break;
        }

        System.out.println(idx);
    }
    public static void find_sum(int idx, int sum){ //idx 부터 시작
        for(int i=idx; i<N; i++){
            int temp = sum+numbers[i];
            check[temp] = true;
            find_sum(i+1, temp);
        }
    }

    public void q_1254() {
        /*
        - 이동
        : 앞 인덱스에 집중하다보니 문제가 안풀려서 뒷 인덱스 부터 생각해봄
        : 어쨌든 뒤부터 어느지점까지 중복인지 체크하고 줄여나가야 하니까 쭉쭉쭉쭉 같다가도 중간에 틀리면 다시 처음부터 돌아가야함
        1) 같은 부분이 보일 때까지 앞 인덱스를 줄여나감
        2) 그리고 같은 부분부터는 함께 줄여나감
        3) 그러다가 다시 다른 부분이 나오면 맨끝부터 다시 확인
         -> 대신 맨처음부터가 아닌 아까 같은 부분의 시작 그 다음 인덱스부터 보기
         -> 왜냐면 이미 그 인덱스까지는 중복된 부분이 있어도 중간이 틀려서 쓸 수 없는 구간이기 때문에 스킵하기 (이미 확인을 마친 부분임)
        - 결과
        1) 인덱스가 같은 경우 : (앞에서 체크하던 인덱스+1)*2-1
        2) 인덱스가 다른 경우 : 앞에서 체크하던 인덱스*2
         */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int a = 0;
        int b = str.length()-1;
        boolean find = false;
        int next_idx = -1;
        while(a<b){
            if(str.charAt(a) == str.charAt(b)) {
                if(!find) {
                    find = true;
                    next_idx = a+1;
                }
                a++;
                b--;
            }
            else{
                if(find){
                    find = false;
                    a = next_idx;
                }else{
                    a++;
                }
                b = str.length()-1;
            }
        }
        if(a==b){
            System.out.println((a+1)*2-1);
        }
        else{
            System.out.println(a*2);
        }
    }
}
