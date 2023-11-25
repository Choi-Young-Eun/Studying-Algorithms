package In2022.Nov_11;

import java.util.Scanner;

public class Nov8 {
    public static void main(String[] args){
        // 1문제 푸는 중 : 정렬(10989) - 시간초과

        Scanner scn = new Scanner(System.in);
        int size=scn.nextInt();
        int[] numbers=new int[size];
        int temp;
        for(int i=0;i<size;i++){
            numbers[i]=scn.nextInt();
        }
        for(int i=size-1;i>0;i--){
            for(int j=0;j<size-1;j++){
                if(numbers[j]>numbers[j+1]){
                    temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }
        for(int i=0;i<size;i++){
            System.out.println(numbers[i]);
        }
    }

}