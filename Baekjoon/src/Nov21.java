import java.io.*;

public class Nov21 {
    // 1문제 풀이 완료 : 정렬(11651-병합정렬) - 19일날 푼 11650번 문제와 좌표만 바꼈을 뿐 방식은 동일하여 적지 않음
    // 1문제 풀이 실패 : 정렬(1181-병합정렬) 중복 검사 및 합치는 과정에서 막힘
    static String[] sorted;
    static int same=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        String[] words=new String[size];
        for (int i = 0; i < size; i++) {
            words[i]=br.readLine();
        }
        br.close();

        merge_sort(words);

        size-=same;
        for(int i=0;i<size;i++){
            sb.append(words[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void merge_sort(String[] a) {
        sorted = new String[a.length];
        merge_sort(a, 0, a.length - 1);
        sorted = null;
    }

    private static void merge_sort(String[] a, int left, int right) {
        for(int size = 1; size <= right; size += size) {
            for(int l = 0; l <= right - size; l += (2 * size)) {
                int low = l;
                int mid = l + size - 1;
                int high = Math.min(l + (2 * size) - 1, right);
                merge(a, low, mid, high);
            }
        }
    }

    static void merge(String[] a, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while(l <= mid && r <= right) {
            if(a[l].length() < a[r].length()) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }else if(a[l].length() == a[r].length()){
                String result = compare_word(a[l],a[r]);
                if(result.equals("first")){
                    sorted[idx] = a[l];
                    idx++;
                    l++;
                }else if(result.equals("second")){
                    sorted[idx] = a[r];
                    idx++;
                    r++;
                }else{
                    sorted[idx] = a[l];
                    idx++;
                    l++;
                    r++;
                    a[r]="";
                    same++;
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

    static String compare_word(String a, String b){
        int len=a.length();
        for(int i=0;i<len;i++){
            if(a.charAt(i)<b.charAt(i)){
                return "first";
            }else if(a.charAt(i)>b.charAt(i)){
                return "second";
            }
        }
        return "same";
    }
}
