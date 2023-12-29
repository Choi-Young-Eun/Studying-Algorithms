package In2023.December_12;

import java.util.LinkedList;
import java.util.Queue;

public class Dec29 {
    //타겟넘버
    static int goal;
    static int count;
    static int[] numArr;
    //단어변환
    static int len;
    public static void main(String[] args) {
        //1문제 풀이 완료 : DFS(프로그래머스:Lev2-타겟넘버), 1문제 풀이 중 : BFS(프로그래머스:Lev2-단어변환)
    }

    public static int word_translate_solution(String begin, String target, String[] words) { //fail - 런타임 에러
        /*
        인자로 받은 초기 단어를 큐에 삽입 //인덱스를 담기
        탐색 횟수 0부터 시작
        int형 횟수배열 만들기 //처음엔 다 0으로 초기화
        int형 result 0으로 초기화
        (while문 진행) : 큐가 비어있을 때까지 or 현재 단어가 타겟 단어일때
        지금 단어 업데이트 //호출 시에는 begin을 주겠죵
        처음부터 돌면서
        비교하지 않은 단어 중 지금 단어에서 한가지만 다른 걸 담기
        담은 단어들 비교했다고 표시하기 //boolean[] done`
        */
        //먼저 시작 단어에서 갈 수 있는 단어 담기
        //만약 큐가 비었다면 0 반환
        //그렇지 않다면 while 실행
        boolean[] done = new boolean[words.length];
        int[] round = new int[words.length];
        Queue<Integer> queue = new LinkedList<>();
        len = begin.length();
        for(int i=0; i<words.length; i++){
            if(compare_word(begin,words[i])) {
                queue.add(i);
                done[i] = true;
                round[i] = 1;
            }
        }
        int idx = -1;
        while(!queue.isEmpty()){
            idx = queue.remove();
            if(words[idx].equals(target))
                break;
            for(int i=0; i<words.length; i++){
                if(done[i]) continue;
                if(compare_word(words[idx],words[i])) {
                    queue.add(i);
                    done[i] = true;
                    round[i] = round[idx]+1;
                }
            }
        }
        if(words[idx].equals(target))
            return round[idx];
        else return 0;
    }

    private static boolean compare_word(String begin, String word) {
        int size = 0;
        for(int i=0; i<len; i++){
            if(begin.charAt(i) != word.charAt(i)) {
                size++;
            }
        }
        if(size == 1) return true;
        else return false;
    }


    public static int target_number_solution(int[] numbers, int target) {
        /*
        -문제 이해하기
        1. 주어진 숫자가 이동하지 않아야됨 (고정)
        2. 주어진 숫자들의 부호만 변경할 수 있음 (+, -)
        3. target값을 만들 수 있는 수식이 총 몇개인지 구하기
        -> 수식의 끝까지 탐색해야 최종 값이 나오기 때문에 하나당 깊게 들어가야 된다고 생각, 따라서 DFS로 진행
        -> 종료 조건은 배열 끝까지 다 탐색한 경우 현재 인덱스의 값이 배열 사이즈와 같아졌을 때의 현재값을 비교한 후 종료
        -> 자기 자신을 호출하는 재귀로 진행
        */
        goal = target;
        count = 0;
        numArr = numbers;
        dfs(0, numArr.length, 0);
        return count;
    }
    static void dfs(int idx, int size, int crt) {
        /*
        1. 총 숫자수와 인덱스가 같으면 현재 수가 target과 같은지 확인
        : 같으면 count++
        : 종료
        2. 현재 인덱스 값 더하고, 다음 인덱스로 넘어가기
        : 다음 인덱스(=현재 인덱스+1) , 업데이트 된 현재 수(=현재 수+배열 내 현재 인덱스의 값)
        3. 현재 인덱스 값 빼고, 다음 인덱스로 넘어가기
        : 다음 인덱스(=현재 인덱스+1) , 업데이트 된 현재 수(=현재 수-배열 내 현재 인덱스의 값)
        */
        if(idx == size){
            if(crt == goal)
                count++;
            return;
        }
        dfs(idx+1, size, crt+numArr[idx]);
        dfs(idx+1, size, crt-numArr[idx]);
    }
}
