import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Nov24 {

    // 2문제 풀이 완료 : 정렬(1181,10814) - Arrays.sort() 사용
    // 1문제 풀이 실패 : 정렬(2108). 예제 돌려봤을 때는 다 잘됐는데 채점 42퍼 정도에서 실패함. 아무래도 잘못 짠 듯 싶다..

    public static void main(String[] args) throws IOException {
        // 실패한 문제 : 2108번
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        int[] numbers = new int[size];
        ArrayList<Integer> max_number=new ArrayList<>();
        int sum=0;

        for(int i=0;i<size;i++){
            numbers[i]=Integer.parseInt(br.readLine());
            sum+=numbers[i];
        }
        br.close();

        Arrays.sort(numbers);

        System.out.println(Math.round(sum/(double)size));
        System.out.println(numbers[size/2]);
        System.out.println(find_number(numbers));
        System.out.println(numbers[size-1]-numbers[0]);

    }
    public static int find_number(int[] numbers){
        int size=numbers.length;
        int cur_number;
        int pre_number=numbers[0];
        int pre_count=1;
        int max_count=1;
        ArrayList<Integer> max_number=new ArrayList<>();
        int most;
        if(size==1) return numbers[0];

        for(int i=1;i<size;i++){
            cur_number=numbers[i];
            if(cur_number == pre_number){
                pre_count++;
                if(i==size-1 && pre_count==max_count){
                    max_number.add(pre_number);
                }
            }else {
                if(pre_count>max_count){
                    max_number.clear();
                    max_count=pre_count;
                    max_number.add(pre_number);
                }else if(pre_count == max_count && pre_count != 1){
                    max_number.add(pre_number);
                }
                pre_count=1;
                pre_number=cur_number;
            }
        }

        if(max_number.size()==1)
            most=max_number.get(0);
        else if(max_number.size()>1)
            most = max_number.get(1);
        else most = numbers[1];
        return most;
    }

    // 1번 문제 : 1181번
    public void q_1181() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        String[] words=new String[size];
        for (int i = 0; i < size; i++) {
            words[i]=br.readLine();
        }

        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()-o2.length() == 0)
                    return compare_word(o1,o2);
                else return o1.length()-o2.length();
            }
        });

        for(int i=0;i<size;i++){
            if(i==0 || !words[i-1].equals(words[i]))
                sb.append(words[i]).append('\n');
        }
        System.out.println(sb);
    }

    static int compare_word(String a, String b){
        int len=a.length();
        for(int i=0;i<len;i++){
            if(a.charAt(i)<b.charAt(i)){
                return -1;
            }else if(a.charAt(i)>b.charAt(i)){
                return 2;
            }
        }
        return 0;
    }

    // 2번 문제 : 10814번
    public static class People {
        int age;
        String name;
        public People(int age, String name){
            this.age=age;
            this.name=name;
        }
        public String toString(){
            return age+" "+name;
        }
    }
    public void q_10814() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        People[] words=new People[size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            words[i]=new People(Integer.parseInt(st.nextToken()),st.nextToken());
        }
        Arrays.sort(words, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.age-o2.age;
            }
        });
        for(int i=0;i<size;i++){
            sb.append(words[i].toString()).append('\n');
        }
        System.out.println(sb);
    }
}
