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
        //2문제 풀이 완료 : DFS(프로그래머스:Lev2-타겟넘버, 프로그래머스:Lev3-단어변환)
    }

    public static int word_translate_solution(String begin, String target, String[] words) {
        /*
        -아이디어
        1. 인자로 받은 초기 단어에서 갈 수 있는 단어를 큐에 삽입
        : 큐에는 인덱스가 담김
        : 방문 체크 - done[인덱스] = true
        : 이동 횟수 체크 - round[인덱스] = 1
        2. while : 큐가 비어있을 때까지 or 현재 단어가 타겟 단어일때
            1) queue.remove() : 지금 단어 업데이트
            2) compare_word() : 처음부터 돌면서 방문하지 않은 단어 중 지금 단어에서 한가지만 다른 걸 담기
            3) 담은 단어들의 경우 방문 체크 및 이동 횟수 업데이트
        3. 만약 현재 idx의 단어가 목표 단어라면 이동 횟수 값인 round[idx] 반환
        4. 아니라면 배열 내의 단어로는 목표 단어에 도달할 수 없으므로 0 반환

        * fail - 런타임 에러
        : 런타임 에러가 난 이유가 idx의 초기값을 -1로 주었기 때문
        : 만약 초기 단어에서 주어진 단어로 변경가능한 케이스가 없는 경우 while을 넘어가고 바로 idx 위치의 단어와 목표단어가 동일한지 확인함
        : 이때 배열의 인덱스 값은 0부터 시작하기 때문에 에러가 나는 것
        (해결) idx 초기 값을 0으로 설정
        */
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
        int idx = 0;
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
        /*
        1. 두 단어를 비교하고 서로 다른 부분의 갯수를 카운트
        2. 한 자리만 다르다면 변경할 수 있는 단어이므로 true 반환
        3. 그렇지 않으면 변경할 수 없는 단어이므로 false 반환
        */
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
