package In2023.August_8;

import java.io.*;
import java.util.Stack;

public class August12 {
    public static void main(String[] args) throws IOException {

    }

    public void stack_17298_mine() throws IOException{
        /*
        수열 하나를 입력 받고 각 값마다 자신보다 큰 수가 오른쪽에 있으면 수 반환, 없으면 -1 반환
        1. 수열 크기 N(배열 크기도 N)
        2. 수열 N개 데이터 한 줄에 한꺼번에 입력
        3. 결과 담을 배열 size는 N result[N]
        4.스택 준비
        ---1번 방법 : for(배열 0부터 N-1(배열 size-1)까지. i 기준)
        1) stack.size == 0 || stack.peek() > 배열[i]
         -> 스택에 i 추가
        2) while(배열[stack.peek()] < 배열[i]) ----> 해당되는 경우 여러번 반복(스택 내의 값 중 여러개보다 배열의 값이 클 수 있음. 그렇게 되면 걔네 다 배열[i]가 오큰수가 됩!)
         -> result[stack.peek{}] = 배열[i]
        ---2번 방법 : while(i가 N보다 작을 경우) //i는 0에서 시작
        1) 스택.size() == 0 || 스택.peek() > 배열[i]
         -> 스택.push(배열[i])
         -> i++
        2) 스택.peek() < 배열[i]
         -> 결과배열[스택.pop()] = 배열[i]
        5. stack.size() != 0 -> result[stack.pop()]=-1;
        6. 배열 출력. i기준 1) 0부터 N-2까지 (result[i]+' '), 2) 만약 i==N-1이면 (result[i])
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num[] = new int[N];
        int result[] = new int[N];
        int i;
        String[] str = br.readLine().split(" ");
        for(i=0; i<N; i++){
            num[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> stack = new Stack<>();
        //1. while 사용
        i=0;
        while(i<N){
            if(stack.size() ==0 || num[stack.peek()] > num[i]){
                stack.push(i);
                i++;
            }
            else if(num[stack.peek()] < num[i]){
                result[stack.pop()] = num[i];
            }
        }
        //2. for 사용
        for(i=0; i<N; i++){
            if(stack.size() ==0 || num[stack.peek()] > num[i]){
                stack.push(i);
            }
            else if(num[stack.peek()] < num[i]){
                result[stack.pop()] = num[i];
            }
        }
        // 스택이 비어있지 않은 경우
        while(stack.size() != 0) {
            result[stack.pop()] = -1;
        }
        
        for(i=0; i<N-1; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println(result[i]);
    }

    public void stack_17298_answer() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num[] = new int[N];
        int result[] = new int[N];
        int i;
        String[] str = br.readLine().split(" ");
        for(i=0; i<N; i++){
            num[i] = Integer.parseInt(str[i]);
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(i=1; i<N; i++) {
            while (stack.size() != 0 && num[stack.peek()] < num[i]) {
                result[stack.pop()] = num[i];
            }
            stack.push(i);
        }
        while(stack.size() != 0) {
            result[stack.pop()] = -1;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(i=0; i<N; i++){
            bw.write(result[i]+" ");
        }
        bw.write("\n");
        bw.flush();
    }
}
