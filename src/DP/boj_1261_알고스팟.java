package DP;

import java.util.*;
import java.io.*;

public class boj_1261_알고스팟 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int W, H;
	static int[][] arr;
	static int[][] dp;
	static int[][] visited;

	static class point {
		int x, y, cnt;

		public point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt; // 벽을 부순 횟수
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		dp = new int[H][W];
		visited = new int[H][W];
		String temp;
		for (int i = 0; i < H; i++) {
			temp = br.readLine();
			for (int j = 0; j < W; j++) {
				arr[i][j] = temp.charAt(j) - '0';
				dp[i][j] = 999999;
				visited[i][j] = 999999;
			}
		}
		Queue<point> queue = new LinkedList<>();
		queue.add(new point(0, 0, 0));
		visited[0][0] = 0;
		point q;
		int x, y, cnt;
		int nr, nc;
		while (!queue.isEmpty()) {
			q = queue.poll();
			x = q.x;
			y = q.y;
			cnt = q.cnt;
//			System.out.println(q);
			dp[x][y] = Math.min(dp[x][y], arr[x][y] + cnt);
			for (int i = 0; i < 4; i++) {
				nr = x + dr[i];
				nc = y + dc[i];
				if (!inrange(nr, nc))
					continue;
				dp[nr][nc] = Math.min(dp[nr][nc], arr[nr][nc] + dp[x][y]);
				// 방문한적이 없는 곳이라면 dp 값으로 갱신
				if (visited[nr][nc] > dp[nr][nc]) {
					visited[nr][nc] = dp[nr][nc];
					queue.add((new point(nr, nc, dp[x][y])));
				}

			}

		}
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(dp[H - 1][W - 1]);

	}

	static boolean inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W;
	}

}
