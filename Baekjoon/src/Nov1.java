import java.util.*;
import java.io.*;
public class Nov1 {

    public static void main(String[] args) {
        //오늘 총 4문제 풀이 완료!
    }

    public void q_2588(){
        Scanner scn=new Scanner(System.in);
        int a=scn.nextInt();
        int b=scn.nextInt();
        int c=a*(b%10);
        int d=a*(b%100-b%10)/10;
        int e=a*(b%1000-(b%100))/100;
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(c+d*10+e*100);
    }

    public void q_2480(){
        Scanner scn=new Scanner(System.in);
        int a=scn.nextInt();
        int b=scn.nextInt();
        int c=scn.nextInt();

        if(a==b && b==c){
            System.out.println(10000+a*1000);
        }else if(a==b){
            System.out.println(sum_2480(a));
        }else if(a==c){
            System.out.println(sum_2480(a));
        }else if(b==c){
            System.out.println(sum_2480(c));
        }else{
            if(a>b){
                if(a>c) System.out.println(a*100);
                else System.out.println(c*100);
            }else{
                if(b>c) System.out.println(b*100);
                else System.out.println(c*100);
            }
        }
    }
    public static int sum_2480(int su){ //두번째 문제에서 사용
        return 1000+su*100;
    }

    public void q_1110(){
        Scanner scn=new Scanner(System.in);
        int number=scn.nextInt();
        int i;
        int n=number;
        int su=0;
        for(i=0;i==0||n!=number;i++){
            if(n<10) su=n;
            su=n%10+n/10;
            n=n%10*10+su%10;
        }
        System.out.println(i);
    }

    public void q_8958() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(bf.readLine());

        String[] strings=new String[count];
        for(int i=0;i<count;i++){
            strings[i]= bf.readLine();
        }

        for(int i=0;i<count;i++){
            System.out.println(sum_8958(strings[i]));
        }
    }

    public static int sum_8958(String str){
        int sum=0, su=0;
        int len=str.length();
        char pre=str.charAt(0);

        for(int i=0;i<len;i++){
            if(i==0 || pre==str.charAt(i)){
                if(pre=='O') su++;
            } else if(str.charAt(i)=='X'){
                pre='X';
                su=0;
            } else {
                pre='O';
                su++;
            }
            sum+=su;
        }
        return sum;
    }
}