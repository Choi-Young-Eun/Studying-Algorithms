package In2022.Nov_11;

import java.util.Scanner;

public class Nov7 {
    public static void main(String[] args) {
        // 3문제 풀이 완료 : 정렬
    }

    public void q_2750(){
        Scanner scn = new Scanner(System.in);
        int size=scn.nextInt();
        int[] numbers=new int[size];
        int cur, next;
        for(int i=0;i<size;i++){
            numbers[i]=scn.nextInt();
        }
        for(int i=1;i<size;i++){
            for(int j=0;j<size-1;j++){
                cur=numbers[j];
                next=numbers[j+1];
                if(cur>next){
                    numbers[j]=next;
                    numbers[j+1]=cur;
                }
            }
        }
        for(int i=0;i<size;i++) {
            System.out.println(numbers[i]);
        }
    }

    public void q_25305(){
        Scanner scn=new Scanner(System.in);
        int size=scn.nextInt();
        int k=scn.nextInt();
        int max,index;
        int[] grades=new int[size];
        int[] result=new int[k];
        for(int i=0;i<size;i++){
            grades[i]=scn.nextInt();
        }
        for(int i=0;i<k;i++){
            max=grades[0];
            index=0;
            for(int j=0;j<size;j++){
                if(grades[j]>max){
                    max=grades[j];
                    index=j;
                }
            }
            result[i]=grades[index];
            grades[index]=0;
        }
        System.out.println(result[k-1]);
    }

    public void q_2587(){
        Scanner scn = new Scanner(System.in);
        int[] numbers=new int[5];
        int sum=0;
        int max=0, index;
        for(int i=0;i<5;i++){
            numbers[i]=scn.nextInt();
            sum+=numbers[i];
        }
        for(int i=0;i<3;i++){
            index=0;
            max=numbers[0];
            for(int j=1;j<5;j++){
                if(numbers[j]>max){
                    index=j;
                    max=numbers[j];
                }
            }
            if(i!=2)
                numbers[index]=0;
        }
        System.out.println(sum/5);
        System.out.println(max);
    }
}
