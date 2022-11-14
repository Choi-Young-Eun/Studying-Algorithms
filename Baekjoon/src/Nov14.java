import java.io.*;

public class Nov14 {
    public static void main(String[] args) throws IOException {
        // 1문제 풀이 완료 : 정렬(10989), 카운팅정렬 이용
    }

    public void q_10989() throws IOException {
        int[] numbers = new int[10001];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        for (int i = 0; i < size; i++) {
            numbers[Integer.parseInt(br.readLine())] ++;
        }
        br.close();

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < 10001; i++){
            while(numbers[i] > 0){
                sb.append(i).append('\n');
                numbers[i]--;
            }
        }
        System.out.println(sb);
    }
}