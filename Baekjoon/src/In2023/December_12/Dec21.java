package In2023.December_12;

import java.util.Arrays;
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

        -문제이해하기
        근처에 같은 색이 많은 경우에 진행해야함
        -> 근처 : 사방으로 같은 색이 있거나 같은 색이 옆옆옆으로 이어져 있어야함
        각 색별로 케이스를 돌아봐야함
        그 색별로 가장 길게 이어져 있는 길이를 찾고 최댓값을 비교해야함
        하나를 제외하고 이어져 있다

        -이렇게 하면 될까?
        1. 한 줄에 해당 색의 사탕이 몇개가 있는지 체크
        2. 어떻게 이어져 있는지 체크 (하나가 떨어져 있는지 그 위치를 확인?)
         -> 위에서 아래도 체크해야하고, 왼쪽에서 오른쪽도 체크해야함
        3. 떨어져 있는 부분의 크로스 라인으로 사탕이 존재하는지 확인
        -> 음... 이렇게 하는 게 맞을까?

        0. 왼쪽에서 오른쪽으로, 위에서 아래로 각각 수행! 해당 줄에 컬러가 몇개 있는지 확인 (컬러별로 진행?) (배열에 값을 받을 때 등장한 컬러 담아야되나?) (배열을 왼->오, 위->아래 로 탐색하는 걸 찾고 해당 케이스 마다 라인 별로 색이 몇개 있는지 확인!
         -> 색 별로 가장 색이 많이 분포 되어 있는 라인을 확인한다
        1. 가장 길게 연결된 부분을 찾는다
        2. 그 근처로 하나 떨어진 곳에 사탕이 있는지 확인한다
         -> 없으면 그 갯수를 최대로 반환..?!
        //만약 길게 연결된 부분이 N이라면 바로 반환
        - 왼->오 케이스
        0. int[][] left_right = new int[4][N]초기화
         -> color 입력 받으면서 각 색 배열에 count 담기 : 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y
         -> 색별로 그 중 최댓값 찾기
         -> 탐색하기 : 최댓값인 케이스만 담아서 진행 - 만약 찾는 색이면 count++, 이전이 찾는 색이였는데 지금이 찾는 색이 아니라면 양 옆에 찾는 색이 있다면 ++
         -> count를 담고 마지막 인덱스를 업데이트 하기 - 만약 찾는 색이면 이전 인덱스가
         -> 제일 길게 이어진 구간을 찾고 양 끝에 한칸 넘어 연결되어 있는 케이스가 있는지 찾기. 만약 없다면 지금 길이를 최댓값으로 함
        1. j가 0부터 N-1인 곳까지 체크, 각 배열에

        0. num[0][0]부터 num[N-1][N-1]까지 진행
        1. num[0][1]과 오른쪽num[0][2]이랑 바꾸고 탐색하기 : 바꾼 배열만 보기, 바꾸기 전꺼랑 같으면 count++ 다르면 count=1
        -> num[0][all] : num[0][0]을 기준으로 num[0][1]-num[0][N-1] 탐색. 이전 문자랑 같은지 확인
        -> num[all][1]
        -> num[all][2]
        2. num[0][1]과 아래쪽num[1][1]이랑 바꾸기
        -> num[all][1]
        -> num[0][all]
        -> num[1][all]
        */
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        colors = new char[N][N];
        for(int i=0; i<N; i++){
            String str = sc.next();
            for(int j=0; j<N; j++){
                colors[i][j] = str.charAt(j);
            }
        }
        int result = 0;
        boolean found = false;
        for(int i=0; i<N; i++){
            //i가 N-1이면 오른쪽만 탐색
            for(int j=0; j<N; j++){
                //j가 N-1이면 아래만 탐색
                /*
                1. num[0][1]과 오른쪽num[0][2]이랑 바꾸고 탐색하기 : 바꾼 배열만 보기, 바꾸기 전꺼랑 같으면 count++ 다르면 count=1
                -> num[0][all] : num[0][0]을 기준으로 num[0][1]-num[0][N-1] 탐색. 이전 문자랑 같은지 확인
                -> num[all][1]
                -> num[all][2]
                2. num[0][1]과 아래쪽num[1][1]이랑 바꾸기
                -> num[all][1]
                -> num[0][all]
                -> num[1][all]
                 */
                if(i <= N-1){
                    char target = colors[i][j];
                    swap(i,j,i,j+1);
                    int[] arr = new int[3];
                    arr[0] = find_max_right(i,target);
                    arr[1] = find_max_down(j, target);
                    arr[2] = find_max_down(j, target);
                    int max = Arrays.stream(arr).max().getAsInt();
                    if(result < max)
                        result = max;
                    swap(i,j,i,j+1);
                    if(result == N){
                        found = true;
                        break;
                    }
                }else if(j <= N-1) {
                    char target = colors[i][j];
                    swap(i, j, i + 1, j);
                    int[] arr = new int[3];
                    arr[0] = find_max_right(i, target);
                    arr[1] = find_max_right(i + 1, target);
                    arr[2] = find_max_down(j, target);
                    int max = Arrays.stream(arr).max().getAsInt();
                    if (result < max)
                        result = max;
                    swap(i, j, i + 1, j);
                    if (result == N) {
                        found = true;
                        break;
                    }
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
    //위부터 아래까지 탐색 (탐색 대상 컬러도 받기)
    private static int find_max_down(int y, char c){
        int count = 0;
        int max = 0;
        for(int i=0; i<N; i++){
            if(colors[y][i] == c){
                if(i != 0 && colors[y][i-1] == colors[y][i]){
                    count++;
                }else{
                    count = 1;
                }
            }else{
                if(max < count){
                    max = count;
                }
                count = 0;
            }
        }
        return max;
    }
    //왼쪽부터 오른쪽까지 탐색 (탐색 대상 컬러도 받기)
    private static int find_max_right(int x, char c){
        int count = 0;
        int max = 0;
        for(int i=0; i<N; i++){
            if(colors[i][x] == c){
                if(i != 0 && colors[i-1][x] == colors[i][x]){
                    count++;
                }else{
                    count = 1;
                }
            }else{
                if(max < count){
                    max = count;
                }
            }
        }
        return max;
    }
}
