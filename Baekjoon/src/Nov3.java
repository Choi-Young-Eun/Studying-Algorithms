import java.util.*;

public class Nov3 {
    public static void main(String[] args){
        // 2문제 풀이 완료
    }

    long q_15596_sum(int[] a) {
        long ans = 0;
        int n=a.length;
        for(int i=0;i<n;i++){
            ans+=a[i];
        }
        return ans;
    }

    public void q_4673(){
        boolean[] result = new boolean[10001];
        Arrays.fill(result,true);
        int n;
        for(int i=1;i<10000;i++){
            if(i<10){
                n=2*i;
            }else if(i<100){
                n=i+i/10+i%10;
            }else if(i<1000){
                n=i+i/100+(i/10%10)+i%10;
            }else{
                n=i+i/1000+(i/100%10)+(i/10%10)+i%10;
            }
            if(n<=10000){
                result[n]=false;
            }
        }

        for(int i=1;i<=10000;i++){
            if(result[i]==true)
                System.out.println(i);
        }
    }

}
