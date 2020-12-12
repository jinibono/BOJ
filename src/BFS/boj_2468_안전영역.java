package BFS;

import java.io.*;
import java.util.*;

public class boj_2468_안전영역 {

	static int std; // 기준
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int max = 1;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static class point {
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public point() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				std = Math.max(std, map[i][j] = Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= std; i++)
			max = Math.max(max, process(i));
		System.out.println(max);
	}

	static int process(int height) {
		visited = new boolean[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] > height) {
					visited[i][j] = true;
					bfs(i, j, height);
					cnt++;
				}
			}
		}
//		System.out.println(height+" "+cnt);
		return cnt;
	}

	private static void bfs(int a, int b, int height) {
		Queue<point> queue = new LinkedList<>();
		queue.add(new point(a, b));
		while (!queue.isEmpty()) {
			point poll = queue.poll();
			int x = poll.x;
			int y = poll.y;
			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				if (Inrange(nr, nc) && !visited[nr][nc] && map[nr][nc] > height) {
					visited[nr][nc] = true;
					queue.add(new point(nr, nc));
				}
			}

		}
	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}
