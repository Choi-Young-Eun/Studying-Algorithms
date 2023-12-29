package In2023.December_12;

import java.util.*;

public class Dec26 {
    //소수찾기
    static HashSet<Integer> hashSet;
    static boolean[] visit;
    static int count;
    static String str;
    //피로도
    static int[][] tiredArr;
    static int tired;
    public static void main(String[] args){
        //2문제 풀이 완료 : 완전 탐색(프로그래머스:Lev2-소수찾기, 프로그래머스:Lev2-피로도)
    }

    public int tired_solution(int k, int[][] dungeons) {
        /*
        ** 문제 이해하기
        -입력 값 설명
        1. k : 현재 유저의 피로도
        2. dungeons : int[][] 배열. 배열[?][0] - 최소 필요 피로도, 배열[?][1] - 소모 피로도
            1) 최소 필요 피로도 : 탐험하기 위해 가지고 있어야하는 피로도의 양
            2) 소모 피로도 : 탐험 후 차감되는 피로도의 양
        -진행 내용 설명
        1. 최소 필요 피로도 만큼 피로도를 가지고 있어야 해당 던전을 탐험할 수 있음
        2. 탐험 후에는 소모 피로도 만큼 피로도가 차감됨

        배열의 사이즈 만큼 돌 수 있는 던전의 수가 있음
        이 때 최대한 많은 던전을 도는 것이 중요
        제일 먼저 체크해야되는 건 최소 필요 피로도!
        입장 조건이므로 최대한 많은 던전을 들어가야 하니까 매 순간 최대한 많은 던전을 가는 게 중요

        -던전의 수만큼 for을 돌며 체크
        -지금 상태에서 갈 수 있는 던전은 하루에 한 번 방문할 수 있으므로 방문한 던전을 체크하기 위한 visit 배열 필요
        -배열을 끝까지 돌거나, 남은 피가 0인 경우 해당 케이스는 break
            -> 그리고 현재의 max값과 비교해서 더 많은 던전 수를 돌았으면 max값을 현재 값으로 업데이트

         ** 12월 27일 풀이 완료
         -놓치고 있던 부분
         : visit만 reset하고 tired값을 초기화 하지 않고 있었음.
         : start_game()에서도 탐색 후에는 횟수를 처음 받은 횟수로 돌려 놓아야 다음 탐색도 동일한 조건에서 할 수 있음
            -> 그래서 계산은 round로 받아서 하고 매번 탐색이 끝난 후에는 이번 탐색 최종값 rouond와 최대값 max를 비교하여 max업데이트
            -> visit과 tired 초기화시 round도 기존 crt로 되돌려주기
         */
        int answer = -1;
        int size = dungeons.length;
        tiredArr = dungeons;
        visit = new boolean[size];
        for(int i=0; i<size; i++){
            tired = k;
            visit[i] = true;
            tired -= tiredArr[i][1];
            int count = start_game(1);
            if(answer < count)
                answer = count;
            visit[i] = false;
            tired -= tiredArr[i][1];
        }
        return answer;
    }

    private static int start_game(int cut){
        int max = 0;
        int round = cut;
        for(int i=0; i<tiredArr.length; i++){
            if(visit[i])
                continue;
            if(tiredArr[i][0] <= tired) {
                visit[i] = true;
                tired -= tiredArr[i][1];
                round = start_game(++round);
                if(max < round)
                    max = round;
                visit[i] = false;
                tired += tiredArr[i][1];
                round = cut;
            }
        }
        if(max < round) return round;
        else return max;
    };


    public static int find_decimal_solution(String numbers) {
        /*
        ** 문제 이해하기
        주어진 숫자들로 만들 수 있는 소수는 몇개인가
        소수 : 1과 자신 외의 어느 숫자로도 나눌 수 없는 수 (약수가 1과 자기 자신인 수)
        -> 소수인지 알 수 있는 법 : 그 수를 나눠 본다

        ** 초반에 생각한 방법
        1. 받은 문자열로 숫자를 만들어 배열에 담는다
        : 낮은 숫자 순으로 정렬시키기
        : 만약 우선 순위 큐에 넣기 (같은 숫자가 있으면 넘어가기)
        2. 소수인지 탐색한다 (1외의 다른 숫자로 나눌 수 있는지 체크)
        : 돌면서 만약 나머지가 0이 되는 케이스가 존재한다면 그 다음 숫자로 넘어간다.
         -> 자기 자신의 반 만큼 돌아가면서 나눌까..? ex. 23/2 -> 11까지 돌기
        3. 소수인 경우 count를 늘린다
        4. count를 반환한다

        ** 최종적으로 진행한 방법
        1. 인자로 받은 numbers를 처음부터 끝까지 탐색
        2. 현재 인덱스의 숫자에서 조합할 수 있는 모든 숫자를 차례대로 만들어보고 소수인지 확인
            -> 내가 생각했던 수를 고정했다는 걸 방문했다는 걸로 생각
            -> 현재 값(이미 탐색이 끝난 고정 수)을 str으로 해서 아직 탐색하지 않은 수를 붙여서 재귀 호출
            -> 또 그 기준에서 인덱스 0번부터 돌면서 방문하지 않은 수만 새로 붙여주고 (붙이면서 방문했다고 표시하고)
            -> 작업이 끝나면 해당 수는 방문 여부를 다시 false로 바꿔준다
         3. 소수이면 count++. 최종적으로 구해진 count를 반환
         */
        str = numbers;
        hashSet = new HashSet<>();
        int len = numbers.length();
        visit = new boolean[len];
        count = 0;
        combination_number(0);
        return count;
    }

    private static void combination_number(int crt){
        /*
        ** combination_number : 탐색할 숫자를 조합해서 차례대로 탐색을 진행하는 메서드. 인자는 int형, 반환은 아무것도 안함
        -탐색 기준: str의 첫번째부터 마지막까지 탐색
        1. 지금 만들어진 숫자 넣기 (배열에 있으면 넘어가기)
            -> 만들어진 숫자가 소수인지 체크하고 count 올리기
        2. for : 이 숫자에서 만들어 질 수 있는 숫자 탐색하기
            1) 지금 숫자열까지는 만들어 졌으니까 그 다음에 붙일 수 있는 숫자만 붙이기
            2) 아직 붙여지지 않은 숫자에 한해서 붙이기
            3) 다음 상태로 넘어가야 되니까 지금 방문한 건 취소 (붙였다가 다시 내려놓기, 방문을 false로 변경하기)
         */
        if(!hashSet.contains(crt)){
            if(find_decimal(crt))
                count++;
        }
        for(int i=0; i< visit.length; i++){
            if(visit[i])
                continue;
            visit[i] = true;
            combination_number(Integer.parseInt(String.valueOf(crt)+str.charAt(i)));
            visit[i] = false;
        }
    }

    private static boolean find_decimal(int target){
        /*
        ** find_decimal : 인자로 받은 숫자가 소수인지 확인하는 메서드. 인자는 int형, 반환은 소수면 true, 아니면 false
        1. 이 수가 0 혹은 1이면 false 반환
        2. for : 소수인지 탐색 (1외의 다른 숫자로 나눌 수 있는지 체크)
            1) 2부터 자기 자신의 반까지 탐색
            2) 만약 나머지가 0이 되는 케이스가 존재한다면 false 반환
        3. 1외의 나눠지는 수가 없으므로 소수! true 반환
         */
        if(target == 0 || target == 1) {
            return false;
        }
        int half = target / 2;
        for (int i = 2; i <= half; i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }
}
