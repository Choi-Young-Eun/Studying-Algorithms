package In2023.November_11;

import java.util.*;

public class Nov29 {
    static boolean[] visit;
    static int[] bridge;
    static ArrayList<int[]> [] connection;

    public static void main(String[] args){
        //1문제 풀이 완료 : 탐색(1167-트리의 지름)
    }

    public void q_1167(){
         /* 아래 두 코드는 차이가 있음
        1. 백준에서 NullPointException 발생
        for(int i=1;i<=N;i++){
            connection[i] = new ArrayList<>();
            int j = sc.nextInt();
            while(true){
                int n = sc.nextInt();
                if(n == -1)
                    break;
                int m = sc.nextInt();
                connection[j].add(new int[]{n,m});
            }
        }
        2. 잘 작동
        for(int i=1;i<=N;i++){
            int j = sc.nextInt();
            connection[j] = new ArrayList<>();
            while(true){
                int n = sc.nextInt();
                if(n == -1)
                    break;
                int m = sc.nextInt();
                connection[j].add(new int[]{n,m});
            }
        }
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        visit = new boolean[N+1];
        bridge = new int[N+1];
        connection = new ArrayList[N+1];
        connection[0] = new ArrayList<>();
        for(int i=1;i<=N;i++){
            int j = sc.nextInt();
            connection[j] = new ArrayList<>();
            while(true){
                int n = sc.nextInt();
                if(n == -1) break;
                int m = sc.nextInt();
                connection[j].add(new int[]{n,m});
            }
        }

        bfs_1167(1);
        int max = 0;
        int max_index = 0;
        for(int i=1; i<=N; i++){
            if(bridge[i]>max){
                max=bridge[i];
                max_index=i;
            }
        }
        visit = new boolean[N+1];
        bridge = new int[N+1];
        bfs_1167(max_index);
        System.out.println(Arrays.stream(bridge).max().getAsInt());
    }
    private static void bfs_1167(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        visit[n] = true;

        while(!queue.isEmpty()){
            int pre = queue.remove();
            for(int[] crn : connection[pre]){
                if(visit[crn[0]])
                    continue;
                queue.add(crn[0]);
                visit[crn[0]] = true;
                bridge[crn[0]] = bridge[pre]+crn[1];
            }
        }
    }
}
