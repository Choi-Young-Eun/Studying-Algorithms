package In2022.Nov_11;

import java.io.*;

public class Nov9{
    // 1문제 풀이 완료 : 정렬(2751), 병합정렬 이용

    static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(br.readLine());
        int[] numbers = new int[size];

        for(int i = 0; i < size; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        merge_sort(numbers);

        for(int i = 0; i < size; i++){
            sb.append(numbers[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void merge_sort(int[] a) {
        sorted = new int[a.length];
        merge_sort(a, 0, a.length - 1);
        sorted = null;
    }

    // *** 배열 분할하는 작업 후, 다음 단계 (합치는 함수) 호출하기
    // - 이 부분 더 공부하기! 이해가 덜 됨
    private static void merge_sort(int[] a, int left, int right) {
        for(int size = 1; size <= right; size += size) {
            for(int l = 0; l <= right - size; l += (2 * size)) {
                int low = l;
                int mid = l + size - 1;
                int high = Math.min(l + (2 * size) - 1, right);
                merge(a, low, mid, high);
            }
        }
    }

    // *** 합치는 작업
    // 1. 왼쪽과 오른쪽 배열의 0번째 인덱스의 값들부터 비교하며 더 작은 수를 합치는 배열에 넣기
    // 2. 만약 둘 중 끝까지 도달한 배열이 있다면 비교를 멈추고 남은 배열의 원소들만 쭈르륵 넣기
    static void merge(int[] a, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while(l <= mid && r <= right) {
            if(a[l] <= a[r]) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }else {
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