import java.io.*;
import java.util.*;

public class Nov4 {
    public static void main(String[] args) {
        // 3문제 풀이 완료 : 문자열
    }

    public void q_5622(){
        Scanner scn=new Scanner(System.in);
        String str=scn.nextLine();
        int size=str.length();
        int sum=0;
        for(int i=0;i<size;i++){
            sum+=func_5622(str.charAt(i));
        }
        System.out.println(sum);
    }
    public static int func_5622(char c){
        int n=c;
        if(n<68){
            n=2;
        }else if(n<71){
            n=3;
        }else if(n<74){
            n=4;
        }else if(n<77){
            n=5;
        }else if(n<80){
            n=6;
        }else if(n<84){
            n=7;
        }else if(n<87){
            n=8;
        }else{
            n=9;
        }
        return n+1;
    }


    public void q_1157(){
        Scanner scn = new Scanner(System.in);
        String str=scn.nextLine();
        str=str.toUpperCase();
        int[] atz=func1_1157(str);
        char result = func2_1157(atz);
        System.out.println(result);
    }

    public static int[] func1_1157(String str){
        int[] atz=new int[26];
        int size=str.length();
        int number;
        for(int i=0;i<size;i++){
            number=str.charAt(i);
            atz[number-65]++;
        }
        return atz;
    }
    public static char func2_1157(int[] atz){
        int count=0,m=0;
        boolean rep=false;
        for(int i=0;i<26;i++){
            if(atz[i]>count){
                count=atz[i];
                m=i;
                if(rep==true)
                    rep=false;
            }else if(atz[i]==count){
                rep=true;
            }
        }
        if(rep==true)
            return '?';
        return (char)(m+65);
    }

    public void q_1316() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int times = Integer.parseInt(bf.readLine());
        String[] words = new String[times];
        int count=0;
        String current;
        for(int i=0;i<times;i++){
            words[i]=bf.readLine();
        }

        for(int i=0;i<times;i++){
            current=words[i];
            if(check_word_1316(current)) {
                count++;
            }
        }
        System.out.println(count);
    }
    public static boolean check_word_1316(String word){ //해당 단어가 그룹 단어인지 체크하는 함수
        int size=word.length();
        boolean[] atz=new boolean[26];
        char pre=' ',cur;
        int integer;
        for(int i=0;i<size;i++){
            cur=word.charAt(i);
            integer=(int)cur-97;
            if(i==0){
                pre=cur;
                atz[integer]=true;
            } else if(pre!=cur){
                if(atz[integer]) return false; //이미 등장했던 알파벳이므로 그룹단어가 아님! false 반환
                atz[integer]=true;
                pre=cur;
            }
        }
        return true;
    }
}
