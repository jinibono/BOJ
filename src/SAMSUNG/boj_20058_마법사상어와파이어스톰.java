package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_20058_마법사상어와파이어스톰 {

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

	static int[] dr = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] dc = { -1, 0, 1, 0 };
	static int[][] map;
	static int[][] count;
	static int[][] copymap;

	static int N;
	static int Q;

	static int sum = 0;
	static int result = 0;

	static int cnt = 0;
	static int nr = 0;
	static int nc = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		N = (int) Math.pow(2, N);
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++)
			process(Integer.parseInt(st.nextToken()));

		resultcalc();
		System.out.println(sum);
		System.out.println(result);

	}

	static void process(int len) {
		// rotation 작업
		len = (int) Math.pow(2, len);
		copymap = new int[N][N];
		for (int i = 0; i < N; i = i + len) {
			for (int j = 0; j < N; j = j + len) {
				rotate(i, j, len);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = copymap[i][j];
		}

		

		// 회전 작업 완료
		calc();

	}

	static void rotate(int x, int y, int len) {

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				// 0 3 = 0 0
				// 0 0 = 3 0
				// 3 0 = 3 3
				copymap[i+x][j+y] = map[len + -j - 1+x][i+y];
			}
		}
	}

	static void calc() {
		count = new int[N][N];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[x][y] == 0)
					continue;
				for (int i = 0; i < 4; i++) {
					nr = x + dr[i];
					nc = y + dc[i];
					if (Inrange(nr, nc) && map[nr][nc] > 0)
						count[x][y]++;
				}
			}
		}
		minus();
	}

	static void minus() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[x][y] == 0)
					continue;
				if (count[x][y] <= 2) {
					map[x][y] = Math.max(0, map[x][y] - 1);
				}
			}
		}

	}

	static void resultcalc() {
		Queue<point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
				if (visited[i][j] || map[i][j] == 0)
					continue;

				queue.add(new point(i, j));
				visited[i][j] = true;
				cnt = 0;
				while (!queue.isEmpty()) {
					point poll = queue.poll();
					int x = poll.x;
					int y = poll.y;
					cnt++;
					for (int c = 0; c < 4; c++) {
						int nr = x + dr[c];
						int nc = y + dc[c];
						if (Inrange(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
							queue.add(new point(nr, nc));
							visited[nr][nc] = true;
						}
					}
				}
				result = Math.max(result, cnt);

			}
		}

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}
