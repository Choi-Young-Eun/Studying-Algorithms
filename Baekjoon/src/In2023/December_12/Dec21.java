package In2023.December_12;

import java.util.Scanner;

public class Dec21 {
    static char[][] colors;
    static int N;
    public static void main(String[] args){
        /*
        -입력
        1줄 : 배열의 칸 크기 N
        2줄~ : 각 줄당 사탕의 색

        -출력
        상근이가 먹을 수 있는 사탕의 최대 개수
         -> 최대 개수는 N을 넘지 못함 (인접한, 연속 부분의 사탕이 대상이기 때문에)

        -조건
        1. 인접한 두칸을 고르고 위치를 바꾼다
        2. 같은 색으로 된 가장 긴 연속 부분을 고르고 그 사탕을 모두 먹는다

        -잘못 생각한 것
        : 색을 나눠서 진행하려고 했음
            -> 하지만 이건 그냥 단순히 연결된 갯수 자체만 궁금한 것으로 색깔 구분은 상관없음.
        : 돌기 전에 라인별로 해당 색 사탕의 갯수를 세고 최대값만 확인할까도 생각했음
            -> 하지만 이것은 완전 탐색 문제... 꼼수를 부리려 하다가 문제가 더 복잡해지고 있었음

        -새로 적용한 아이디어
        : 이전의 색과 같으면 count++
        : 아니라면 현재 색을 이전 색으로 (비교 대상으로) 바꾸고 현재까지 쌓은 count와 max값을 비교하고 업데이트한 다음 count를 1로 초기화
        */
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        colors = new char[N][N];
        int result = 0;
        for(int i=0; i<N; i++){
            String str = sc.next();
            for(int j=0; j<N; j++){
                colors[i][j] = str.charAt(j);
            }
            result = Math.max(result, find_max_right(i));
        }
        for(int i=0; i<N; i++){
            result = Math.max(result, find_max_down(i));
        }

        if (result == N) {
            System.out.println(result);
            return;
        }

        boolean found = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(j < N-1){
                    swap(i,j,i,j+1);
                    result = Math.max(result, find_max_right(i));
                    result = Math.max(result, find_max_down(j));
                    result = Math.max(result, find_max_down(j+1));
                    swap(i,j,i,j+1);
                }
                if(i < N-1) {
                    swap(i, j, i + 1, j);
                    result = Math.max(result,find_max_right(i));
                    result = Math.max(result,find_max_right(i + 1));
                    result = Math.max(result,find_max_down(j));
                    swap(i, j, i + 1, j);
                }
                if (result == N) {
                    found = true;
                    break;
                }
            }
            if(found)
                break;
        }
        System.out.println(result);
    }
    //위치 바꾸기 (받은 위치 두값 바꾸기)
    private static void swap(int ay,int ax, int by, int bx){
        char temp = colors[ay][ax];
        colors[ay][ax] = colors[by][bx];
        colors[by][bx] = temp;
    }
    //왼쪽부터 오른쪽 끝까지 탐색 후 최장 연속 구간 출력
    private static int find_max_right(int x){
        int count = 1;
        int max = 1;
        char c = colors[x][0];
        for(int i=1; i<N; i++){
            if(colors[x][i] != c){
                c = colors[x][i];
                max = Math.max(count, max);
                count = 1;
            }else{
                count++;
            }
        }
        return Math.max(max, count);
    }
    //위부터 아래 끝까지 탐색 후 최장 연속 구간 출력
    private static int find_max_down(int y){
        int count = 1;
        int max = 1;
        char c = colors[0][y];
        for(int i = 1; i < N; i++) {
            if(colors[i][y] != c) {
                c = colors[i][y];
                max = Math.max(max, count);
                count = 1;
            } else count++;
        }
        return Math.max(max, count);
    }
}
