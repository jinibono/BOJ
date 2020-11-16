package DP;
import java.util.*;
import java.io.*;

public class boj_1005_ACM_CRAFT {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int start, finish = 0;
			int[] in = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			int[] cost = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}

			Queue[] queue = new Queue[N + 1];
			for (int i = 0; i <= N; i++) {
				queue[i] = new LinkedList<Integer>();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				finish = Integer.parseInt(st.nextToken());
				queue[start].add(finish);
				in[finish]++;
			}
			Queue q = new LinkedList<Integer>();
			for (int i = 1; i <= N; i++) {
				if (in[i] == 0) {
					q.add(i);
					in[i] = Integer.MAX_VALUE;
				}
			}
			int[] dp = new int[N + 1];

			int num = Integer.parseInt(br.readLine());

			while (!q.isEmpty()) {
				int poll = (int) q.poll();
				in[poll] = Integer.MAX_VALUE;

				while (!queue[poll].isEmpty()) {
					int temp = (int) queue[poll].poll();
					in[temp]--;
					dp[temp] = Math.max(dp[temp], dp[poll] + cost[poll]);
					if (in[temp] == 0)
						q.add(temp);
//				System.out.println("여기");
				}

			}
			sb.append(dp[num] + cost[num]).append('\n');
			
		}
		System.out.println(sb.toString());

	}

}
