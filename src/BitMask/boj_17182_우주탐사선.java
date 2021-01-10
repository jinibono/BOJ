package BitMask;

import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;

public class boj_17182_우주탐사선 {
	static int[][] map;
	static int[][] dp;
	static int INF = Integer.MAX_VALUE - 10000000;
	static int goal;
	static int ans = INF;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N][1 << N];
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]); // 플로이드 와샬 알고리즘으로 최소 거리 구함
				}
			}
		}
		boolean[] visited = new boolean[N];
		visited[goal] = true;
		dfs(visited, goal, 0, 0);
		System.out.println(ans);

	}

	public static void dfs(boolean[] visited, int temp, int sum, int depth) {
		if (depth == N - 1) {
			ans = Math.min(ans, sum); // 최단 거리 구하기
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(visited, i, sum + map[temp][i], depth + 1); // 안지나간 정거장 지나가기
				visited[i] = false;
			}
		}
	}

}
