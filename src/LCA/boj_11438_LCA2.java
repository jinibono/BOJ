package LCA;

import java.util.*;
import java.io.*;

public class boj_11438_LCA2 {
	static int N;
	static ArrayList[] list;
	static int[] depth, parent;
	static int[][] dp;
	static int K = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		depth = new int[N + 1];
		parent = new int[N + 1];
		int tmp = 1;
		while (tmp <= N) { // 최대 depth 알아내기.
			tmp <<= 1;
			K++;
		}
		dp = new int[N + 1][K];
		dfs(1, 1);
		for (int i = 1; i < K; i++) { // DP를 이용해 각 노드별 2^K 번 째 조상 노드를 저장한다.
			for (int j = 1; j <= N; j++) {
				dp[j][i] = dp[dp[j][i - 1]][i - 1];
			}
		}

		StringBuffer sb = new StringBuffer();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append((LCA(a, b))).append('\n'); // 공통 부모 찾기
		}
		System.out.print(sb.toString());

	}

	public static void dfs(int node, int cnt) {// node: 방문 노드, cnt: 현재 깊이
		depth[node] = cnt;

		for (int i = 0; i < list[node].size(); i++) {
			int child = (int) list[node].get(i);
			if (depth[child] == 0) { // 깊이 계산이 안 된 곳은 자식 노드이므로
				dfs(child, cnt + 1);
				dp[child][0] = node;
			}
		}

	}

	private static int LCA(int a, int b) {
		if (depth[a] < depth[b]) { // 깊이가 낮은 쪽을 기준으로 맞춘다.
			int temp = a;
			a = b;
			b = temp;
		}

		// 더 깊은 a를 2승씩 점프하며 두 노드의 depth를 맞춘 후, 맞춘 depth의 조상 노드로 대체한다.
		for (int i = K - 1; i >= 0; i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				a = dp[a][i]; // a를 2^i 번 째 조상 노드로 대체한다.
			}
		}

		// depth 맞춘 후 대체한 조상 노드가 b와 같다면. 즉, a의 조상노드가 b라면 종료한다.
		if (a == b)
			return a;

		// 이제 두 노드는 같은 depth를 가지기 때문에
		// 같이 2승씩 점프시키며 공통부모 바로 아래까지 올린다.
		for (int i = K - 1; i >= 0; i--) {
			if (dp[a][i] != dp[b][i]) {
				a = dp[a][i];
				b = dp[b][i];
			}
		}

		return dp[a][0];
	}
}
