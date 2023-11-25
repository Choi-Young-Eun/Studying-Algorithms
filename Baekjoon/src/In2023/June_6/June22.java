package In2023.June_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class June22 {
    public static void main(String[] args) throws IOException {

    }

    public void stack_10799() throws IOException{
        /*
        --입력값
        1. 괄호 담을 char배열 필요
        2. 총 막대기 갯수 담을 result int변수 필요
        -순서
        1. 0부터 i전까지. char배열 처음부터 끝까지 돌기
        -아래 가이드 라인을 토대로 현재가 i번째 인덱스, i-1을 보고 판단
        (단, 처음이 )일 가능성과 잘못된 배열일 것을 배제하고 진행)
        -1 (면 스택에 넣기
        -2 )면 스택에서 하나 빼고 크기하나 늘리기 (막대개수++)
        -3 ()면 현재 스택 사이즈 만큼 더하기 (막대개수++)
        1) 이전이 (였을 때
                -만약 )라면 : 3번
                -만약 (라면 : 1번
        2) 이전이 )였을 때
                -만약 )라면 : 2번
                -만약 (라면 : 1번

        ex1)
        sum = 0+0+3+3+1+3+1+2+2+1 +1
        () 0
        ((( stack : 3
        () +3
        () +3
        ) +1 stack : 2
        ( stack : 3
        () +3
        ) +1 stack : 2
        () +2
        )) +2 stack : 0
        ( stack : 1
        () +1
        ) +1 stack : 0

        ex2)
        sum = 0+24
        ((( stack:3
        ( stack:4
        ) stack:3, +3
        ( stack:4
        ( stack:5
        ) stack:4. +4
        ( stack:5
        ) stack:4, +4
        ) +1 stack:3
        ) +1 stack:2
        ( stack:3
        ( stack:4
        ) stack:3, +3
        ) +1 stack:2
        ( stack:3
        ) stack:2, +2
        ) +1 stack:1
        ) +1 stack:0
        ( stack:1
        ( stack:2
        ) stack:1, +1
        ( stack:2
        ) stack:1, +1
        ) +1 stack:0
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] c=s.toCharArray();
        int result = 0;
        int stack = 1;
        for(int i=1; i<c.length; i++){
            if(c[i-1]=='('){
                if(c[i]=='('){
                    stack++;
                }else{ //)인 경우
                    result += --stack;
                }
            }else if(c[i-1]==')'){
                if(c[i]=='('){
                    stack++;
                }else{ //)인 경우
                    stack--;
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
