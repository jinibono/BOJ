package DP;

import java.util.*;
import java.io.*;

public class boj_17090_미로탈출하기 {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int N, M;
	static int[][] map;
	static int[][] dp;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		long sum = 0l;
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (int j = 0; j < M; j++) {
				int value = -1;
				switch (word.charAt(j)) {
				case 'U':
					value = 0;
					break;

				case 'R':
					value = 1;
					break;
				case 'D':
					value = 2;
					break;
				case 'L':
					value = 3;
					break;
				}
				map[i][j] = value;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dp[i][j] == 0 && !visited[i][j])
					dp[i][j] = dfs(i, j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
//				System.out.print(dp[i][j] + " ");
				sum+=dp[i][j];
			}
//			System.out.println();
		}
		System.out.println(sum);

	}

	static int dfs(int x, int y) {
		visited[x][y] = true;

		int dir = map[x][y];
		int nx = x + dr[dir];
		int ny = y + dc[dir];

		if (Inrange(nx, ny) && !visited[nx][ny])
			return dp[x][y] = dfs(nx, ny);
		else if (!Inrange(nx, ny) || dp[nx][ny] == 1)
			return dp[x][y] = 1;
		return dp[x][y];

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
