package DP;

import java.util.*;
import java.io.*;

public class boj_1520_내리막길 {
	static int N, M;
	static int[][] arr;
	static int[][] dp;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(0, 0));

	}

	static int dfs(int x, int y) {
		if (x == N - 1 && y == M - 1)
			return 1;

		if (dp[x][y] == -1) {
			dp[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				if (Inrange(nr, nc) && arr[nr][nc] < arr[x][y])
					dp[x][y] += dfs(nr, nc);

			}
		}
		return dp[x][y];

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
