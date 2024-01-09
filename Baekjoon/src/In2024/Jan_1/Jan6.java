package In2024.Jan_1;

import java.util.Scanner;

public class Jan6 {
    //q_1969
    static String[] strArr;
    static int[] find;
    public static void main(String[] args) {

    }

    public void q_1969() {
        /*
        -주어진 DNA들과 가장 차이가 적은 DNA와 총 몇개가 차이나는지 구하는 문제
        1. FOR1 : DNA 수인 N 만큼 돌기
            1) 등장하는 문자 카운트하는 int[] 초기화
        2. FOR2 : 한 DNA의 길이인 M만큼 돌기 - 등장하는 알파벳 갯수 count하는 작업으로 위에서 초기화한 int[]에 ++ 해주기
            2) 비중이 제일 높은 알파벳 구하는 메서드 호출
            3) N에서 등장한 횟수를 뺀 걸 총합에 더해주기
            4) 결과 담는 char[] 배열내에 가장 많이 등장한 char 넣기
        3. char[] result를 String 형으로 변환하여 출력
        4. count 출력
         */
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        strArr = new String[N];
        for(int i=0; i<N; i++){
            strArr[i] = sc.nextLine();
        }

        char[] result = new char[M];
        int count = 0;

        for(int i=0; i<M; i++){
            find = new int[26];
            for(int j=0; j<N; j++){
                int num = strArr[j].charAt(i)-'A';
                find[num]++;
            }
            int location = f_1969();
            count += N - find[location];
            result[i] = (char)(location+'A');
        }

        System.out.println(String.valueOf(result));
        System.out.println(count);
    }
    public static int f_1969(){
        int max = 0;
        for(int i=1; i<26; i++){
            if(find[max] < find[i]) max = i;
        }
        return max;
    }

    public void q_1251() {
        /*
        0. 변경된 문자열 중에 사전 맨 앞 단어니까 원본 문자열로 비교하면 안됨
            원본 문자열이 제일 사전 앞 단어면 변경되지 않고 원본 문자열이 그대로 출력될 수 있음
            따라서 맨처음에 변경되어 저장된 단어를 비교대상 단어로 설정해준다
        1. for1 : a는 str.length의 -2 전까지
            앞 인덱스 까지 strA에 담기
        2. for2 : b는 현재 인덱스의 다음 부터 str.length의 -1 전까지
            c는 b인덱스 다음부터 끝까지
            중간 인덱스 까지 strB에 담고
            끝 인덱스 까지 strC에 담기
            각각 뒤집기
            합치기
            현재 단어랑 비교하고 업데이트 하기
        4. 업데이트하던 문자열 변수 출력
         */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();

        String strA = str.substring(0, 1);
        String strB = str.substring(1, 2);
        String strC = new StringBuilder(str.substring(2, len)).reverse().toString();
        String result = strA+strB+strC;

        for(int i=0; i<len-2; i++){
            strA = new StringBuilder(str.substring(0, i+1)).reverse().toString();
            for(int j=i+1; j<len-1; j++){
                strB = new StringBuilder(str.substring(i+1, j+1)).reverse().toString();
                strC = new StringBuilder(str.substring(j+1,len)).reverse().toString();
                String newStr = strA+strB+strC;
                if(result.compareTo(newStr) > 0)
                    result = newStr;
            }
        }

        System.out.println(result);
    }
}
