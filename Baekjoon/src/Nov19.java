import java.util.*;

public class Nov19 {
    static int[][] sorted; //q_11650

    public static void main(String[] args){
        // 2문제 풀이 완료 : 정렬(1427-카운팅정렬, 11650-병합정렬)
    }

    public void q_1427(){
        /*
        1. String으로 받고
        2. char로 떼서 하나씩 숫자를 확인하고 숫자범위배열내의 해당 숫자 인덱스에 1증가 - for문
        3. 9번인덱스부터 시작해서 -1해주며 StringBuilder에 넣기 - for문
        4. 출력
         */
        int[] counting=new int[10];
        Scanner scn=new Scanner(System.in);
        StringBuilder sb=new StringBuilder();

        String number=scn.nextLine();
        int size=number.length();

        for(int i=0;i<size;i++){
            counting[Character.getNumericValue(number.charAt(i))]++;
        }

        for(int i=9;i>=0;i--){
            while(counting[i]>0){
                sb.append(i);
                counting[i]--;
            }
        }

        System.out.println(sb);
    }


    public void q_11650(){
        /*
        입력 : 1. 점의 갯수(10만개 이하) 2. 점의 좌표(x좌표, y좌표)
        출력 : 오름차순으로 정렬한 점들
        -int[][] 배열 사용
        1. 일단 x를 기준으로 쌍을 다 분할함 //for문
        2. x가 작은지 비교하며 두 배열(?)을 하나로 합침 //for문
         1) x가 같은 경우 : y를 비교하여 더 작은 걸 넣기
         2) x가 다른 경우 : 더 작은 x쌍을 넣음
        3. 배열이 하나가 됐을 때 출력
        */
        Scanner scn=new Scanner(System.in);
        StringBuilder sb=new StringBuilder();
        int count = scn.nextInt();
        int[][] coordinate = new int[count][2];
        int x,y;

        for(int i=0;i<count;i++){
            coordinate[i][0]=scn.nextInt();
            coordinate[i][1]=scn.nextInt();
        }

        merge_sort(coordinate);

        for(int i = 0; i < count; i++){
            sb.append(coordinate[i][0]).append(" ").append(coordinate[i][1]).append('\n');
        }
        System.out.println(sb);
    }

    public static void merge_sort(int[][] a) {
        sorted = new int[a.length][];
        merge_sort(a, 0, a.length - 1);
        sorted = null;
    }

    private static void merge_sort(int[][] a, int left, int right) {
        for(int size = 1; size <= right; size += size) {
            for(int l = 0; l <= right - size; l += (2 * size)) {
                int low = l;
                int mid = l + size - 1;
                int high = Math.min(l + (2 * size) - 1, right);
                merge(a, low, mid, high);
            }
        }
    }

    static void merge(int[][] a, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while(l <= mid && r <= right) {
            if(a[l][0] < a[r][0]) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }else if(a[l][0] == a[r][0]){
                if(a[l][1] < a[r][1]){
                    sorted[idx] = a[l];
                    idx++;
                    l++;
                }else{
                    sorted[idx] = a[r];
                    idx++;
                    r++;
                }
            }else{
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        }

        if(l > mid) {
            while(r <= right) {
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        }else {
            while(l <= mid) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }
        }

        for(int i = left; i <= right; i++) {
            a[i] = sorted[i];
        }
    }
}
