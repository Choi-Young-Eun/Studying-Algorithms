package In2024.Jan_1;

import java.util.Scanner;

public class Jan4 {
    //q_7568
    static int[][] ary;
    public static void main(String[] args) {
        //2문제 풀이 완료 : 완전탐색(백준:1436, 7568)
    }

    private void q_1436() {
        /*
        원래는 패턴을 찾아보려고 이리저리 자릿수를 기준으로 만져보려고 했었음
        근데 너무 안풀리고 이게 완전 탐색인데 이렇게 어려울라나 싶으면서 생각이 복잡해지니까 답을 찾아보게 되었음
        그냥 수를 하나씩 올려가면서 666이 표함된 것만 세고 그게 목표 카운트가 되면 현재 숫자를 반환하면 되는 거였음
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int number = 665;
        int count = 0;
        while(count < n){
            if(count == n) break;
            number++;
            if(String.valueOf(number).contains("666"))
                count++;
        }
        System.out.print(number);
    }

    public void q_7568() {
        /*
        -처음부터 끝까지 돌기
        -둘 다 큰 경우만 +1하기
        -등수 출력하기
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ary = new int[n][2];
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i][0] = sc.nextInt();
            ary[i][1] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            result[i] = find_grade(ary[i][0], ary[i][1]);
        }

        for (int i : result) {
            System.out.printf(i + " ");
        }
    }
    private static int find_grade(int x, int y) {
        int grade = 1;
        for(int i=0; i< ary.length; i++){
            if(ary[i][0] > x && ary[i][1] > y){
                grade++;
            }
        }
        return grade;
    }
}
