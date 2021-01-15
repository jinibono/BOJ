package BFS;

import java.util.*;
import java.io.*;

public class boj_17244_아맞다우산 {
	static char[][] arr;
	static boolean[][] visited;
	static int W, H;
	static int sx, sy;
	static int ex, ey;
	static int[][] mid = new int[6][2];
	static int result = Integer.MAX_VALUE;
	static int idx = 1;
	static int[][] memo = new int[8][8];

	static class point {
		int x, y, len;

		public point(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}

		public point() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new char[H][W];

		for (int i = 0; i < H; i++) {
			str = br.readLine();
			for (int j = 0; j < W; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'S') {
					sx = i;
					sy = j;
				} else if (arr[i][j] == 'E') {
					ex = i;
					ey = j;
				} else if (arr[i][j] == 'X') {
					mid[idx][0] = i;
					mid[idx++][1] = j;
				}
			}
		}
		idx--;
		int[] temp = new int[idx];
		for (int i = 0; i < idx; i++) {
			temp[i] = i + 1;
		}
//		
//		System.out.println(idx);
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++)
//				System.out.print(arr[i][j]);
//			System.out.println();
//		}
//		System.out.println(sx + " " + sy);
//		System.out.println(ex + " " + ey);
		permutation(temp, 0, idx, idx);
		System.out.println(result);

	}

	static void permutation(int[] arr, int depth, int n, int r) {
		if (depth == r) {
			// 방문순서를 뽑아 놨음
			solve(arr);
			return;
		}

		for (int i = depth; i < n; i++) {
			swap(arr, depth, i);
			permutation(arr, depth + 1, n, r);
			swap(arr, depth, i);
		}
	}

	static void swap(int[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}

	public static void solve(int[] arr) {
		int prex = sx;
		int prey = sy;
		int endx = ex;
		int endy = ey;
		int cnt = 0;
		int currentlen = 0;
		int cdx = 0;
//		System.out.print(Arrays.toString(arr)+" ");
		while (cnt != idx) {
			if (memo[cdx][arr[cnt]] == 0)
				memo[cdx][arr[cnt]] = bfs(prex, prey, mid[arr[cnt]][0], mid[arr[cnt]][1]);
			currentlen += memo[cdx][arr[cnt]];

			prex = mid[arr[cnt]][0];
			prey = mid[arr[cnt]][1];
//			System.out.print(currentlen+" ");
			cdx = arr[cnt];
			cnt++;
		}
		if (memo[cdx][6] == 0)
			memo[cdx][6] = bfs(prex, prey, ex, ey);
		currentlen += memo[cdx][6];
//		System.out.println(currentlen);
		result = Math.min(result, currentlen);
	}

	public static int bfs(int prex, int prey, int ex, int ey) {

		Queue<point> queue = new LinkedList<>();
		queue.add(new point(prex, prey, 0));
		int min = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[H][W];
		while (!queue.isEmpty()) {
			point q = queue.poll();
			int x = q.x;
			int y = q.y;
			int len = q.len;

			if (ex == x && ey == y) {
				min = Math.min(min, len);
			}

			if (visited[x][y])
				continue;
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				if (!inrange(nr, nc))
					continue;
				if (arr[nr][nc] == '#')
					continue;
				queue.add(new point(nr, nc, len + 1));
			}
		}
//		System.out.println(min);
		return min;
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static boolean inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W;
	}

}
