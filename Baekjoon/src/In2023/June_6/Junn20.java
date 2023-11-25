package In2023.June_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Junn20 {
    public static void main(String[] args) throws IOException {
        /*
        1. 입력된 사이즈 만큼의 배열에 숫자를 입력 받아 차례대로 집어 넣는다
        2. 가리키는 값이
        while(배열의 크기와 가리키는 인덱스가 같아지기 전까지)
        기본 전제 : number을 증가시킨다
        -숫자를 나타낼 int 변수 1개
                -숫자가 들어갈 스택 1개
                -출력을 담을 버퍼 1개
                -배열을 가리키는 값을 나타내는 int 변수 1개
        0) 숫자 확인하기 - peek()
        1) 스택이 비었거나 peek 값이 배열내 값보다 작은 경우
        -스택에 숫자 number 넣기 - push()
                -버퍼에 '+' 추가하기
                -number 증가시키기
        2) 같은 경우
        -스택에서 제일 위에꺼 삭제하기 - pop()
                -버퍼에 '-' 추가하기
                -인덱스 증가시키고 값 새로 담기
        3) peek 값이 배열내 값보다 큰 경우
        -종료 후 NO 출력
        다 돌고 스택이 비었으면 버퍼 값 출력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int i=0, su=1;
        int target=numbers[i];
        Stack<Integer> stack = new Stack<>();
        while (i<N){
            if(stack.size()==0 || stack.peek()< numbers[i]){
                stack.push(su);
                sb.append('+').append('\n');
                su++;
            }else if(stack.peek() == numbers[i]){
                stack.pop();
                sb.append('-').append('\n');
                i++;
            }else{
                break;
            }
        }
        if(stack.size() != 0){
            System.out.println("NO");
        }else{
            System.out.print(sb);
        }
    }
    
    public void stack_1874() throws IOException{
        /*
        1. 입력된 사이즈 만큼의 배열에 숫자를 입력 받아 차례대로 집어 넣는다
        2. 가리키는 값이
        while(배열의 크기와 가리키는 인덱스가 같아지기 전까지)
        기본 전제 : number을 증가시킨다
        -숫자를 나타낼 int 변수 1개
                -숫자가 들어갈 스택 1개
                -출력을 담을 버퍼 1개
                -배열을 가리키는 값을 나타내는 int 변수 1개
        0) 숫자 확인하기 - peek()
        1) 스택이 비었거나 peek 값이 배열내 값보다 작은 경우
        -스택에 숫자 number 넣기 - push()
                -버퍼에 '+' 추가하기
                -number 증가시키기
        2) 같은 경우
        -스택에서 제일 위에꺼 삭제하기 - pop()
                -버퍼에 '-' 추가하기
                -인덱스 증가시키고 값 새로 담기
        3) peek 값이 배열내 값보다 큰 경우
        -종료 후 NO 출력
        다 돌고 스택이 비었으면 버퍼 값 출력
         */
        //1. 준비
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        //2. 메인
        int i=0, su=1;
        Stack<Integer> stack = new Stack<>();
        while (i<N){
            if(stack.size()==0 || stack.peek()< numbers[i]){
                stack.push(su);
                sb.append('+').append('\n');
                su++;
            }else if(stack.peek() == numbers[i]){
                stack.pop();
                sb.append('-').append('\n');
                i++;
            }else{
                break;
            }
        }
        //3. 출력
        if(stack.size() != 0){
            System.out.println("NO");
        }else{
            System.out.print(sb);
        }
    }
}
