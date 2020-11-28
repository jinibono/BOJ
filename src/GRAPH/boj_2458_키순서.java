package GRAPH;

import java.util.*;
import java.io.*;

public class boj_2458_키순서 {
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		int[][] dp;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					dp[i][j] = 0;
				else
					dp[i][j] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dp[a][b] = 1;
		}
		int result = 0;
		for (int k = 1; k <= N; k++)
			for (int i = 1; i <= N; i++)
				for (int j = 1; j <= N; j++)
					if (dp[i][j] > dp[i][k] + dp[k][j])
						dp[i][j] = dp[i][k] + dp[k][j];
		for (int i = 1; i <= N; i++) {
			int f = 1;
			for (int j = 1; j <= N; j++) {
				if (dp[i][j] == INF && dp[j][i] == INF) {
					f = 0;
					break;
				}
			}
			if (f == 1)
				result++;
		}
		System.out.println(result);
	}

}
