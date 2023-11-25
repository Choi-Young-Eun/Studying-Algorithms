package In2022.Nov_11;

import java.util.*;

public class Nov2 {
    public static void main(String[] args){
        // 3문제 완료, 1문제 푸는 중 -> 실패. 내일 해설보고 공부하기!
        // 1문제 : 2563번
        Scanner scn=new Scanner(System.in);
        int count=scn.nextInt();
        int sum=0,m;
        ArrayList<ArrayList<Integer>> numbers=new ArrayList<>();
        for(int i=0;i<count;i++){
            ArrayList<Integer> xy=new ArrayList<>();
            xy.add(scn.nextInt());
            xy.add(scn.nextInt());
            numbers.add(xy);
        }
        for(int i=0;i<count;i++){
            m=func(i,numbers);
            sum+=100-m;
        }
        System.out.println(sum);
    }
    public static int func(int t,ArrayList<ArrayList<Integer>> numbers){
        int current_x=numbers.get(t).get(0),current_y=numbers.get(t).get(1);
        int temp_x,temp_y,sum=0;
        for(int i=t+1;i< numbers.size();i++){
            temp_x=numbers.get(i).get(0);
            temp_y=numbers.get(i).get(1);
            if((temp_x<current_x+10 && current_x+10<=temp_x+10) &&
                    (temp_y<=current_y && current_y<temp_y+10))
                sum += (current_x+10-temp_x) * (temp_y+10-current_y);
        }
        return sum;
    }



    public void q_3052(){
        Scanner scn=new Scanner(System.in);
        ArrayList<Integer> numbers=new ArrayList<>();
        ArrayList<Integer> rem=new ArrayList<>();
        Integer n;
        for(int i=0;i<10;i++){
            numbers.add(scn.nextInt());
        }
        for(int i=0;i<10;i++){
            n= numbers.get(i);
            if(numbers.isEmpty()) rem.add(n%42);
            else {
                if(!rem.contains(n%42))
                    rem.add(n%42);
            }
        }
        System.out.println(rem.size());
    }

    public void q_1546(){
        Scanner scn=new Scanner(System.in);
        ArrayList<Integer> numbers=new ArrayList<>();
        int size=scn.nextInt();
        double sum=0.0, max;
        for(int i=0;i<size;i++){
            numbers.add(scn.nextInt());
        }
        Collections.sort(numbers,Collections.reverseOrder());
        max=Double.valueOf(numbers.get(0));
        for(int i=0;i<size;i++){
            sum+=numbers.get(i)/max*100;
        }
        System.out.println(sum/size);
    }

    public void q_4344(){
        Scanner scn=new Scanner(System.in);
        int case_size=scn.nextInt();
        ArrayList<Integer> numbers=new ArrayList<>();
        int count=0;
        double avg=0.0;
        for(int i=0;i<case_size;i++){
            numbers.clear();
            numbers.add(scn.nextInt());
            for(int j=0;j<numbers.get(0);j++){
                numbers.add(scn.nextInt());
            }
            avg=func1_4344(numbers);
            count=func2_4344(avg,numbers);
            System.out.println(String.format("%,.3f",Double.valueOf(count)/numbers.get(0)*100)+"%");
        }
    }
    public static double func1_4344(ArrayList<Integer> numbers){
        int sum=0;
        int size=numbers.get(0);
        for(int i=1;i<=size;i++){
            sum+=numbers.get(i);
        }
        return sum/Double.valueOf(size);
    }
    public static int func2_4344(double avg,ArrayList<Integer> numbers){
        int count=0;
        int size=numbers.get(0);
        for(int i=1;i<=size;i++){
            if(numbers.get(i)>avg)
                count++;
        }
        return count;
    }
}

