package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_17142_연구소3 {
	static int N;
	static int M;
	static char[][] crr;
	static int[][] arr;
	static boolean[][] visited;
	static StringTokenizer st;
	static ArrayList<virus> list;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int count = 0;
	static int result = Integer.MAX_VALUE;

	static class virus {
		int x, y, time;

		public virus(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

		public virus() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "virus [x=" + x + ", y=" + y + ", time=" + time + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		crr = new char[N][N];
		count = N * N;
		list = new ArrayList<>();
		int num = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					crr[i][j] = '-';
					count--;
				}
				if (num == 2) {
					list.add(new virus(i, j, 0));
					crr[i][j] = '*';
					count--;
				}
			}
		}
//		System.out.println(count);
		int[] temp = new int[list.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = i + 1;
		}
		comb(temp, new int[M], 0, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);

	}

	static void comb(int[] arr, int[] sel, int n, int k) {
		if (k == sel.length) {
//			System.out.println(Arrays.toString(sel));
			ArrayList<virus> newlist = new ArrayList<>();
			for (int i = 0; i < sel.length; i++) {
				newlist.add(list.get(sel[i] - 1));
			}
			bfs(newlist);
			return;
		}
		if (n == arr.length)
			return;
		sel[k] = arr[n];
		comb(arr, sel, n + 1, k + 1);
		comb(arr, sel, n + 1, k);
	}

	public static void bfs(ArrayList<virus> newlist) {
		Queue<virus> queue = new LinkedList<>();
		for (int i = 0; i < newlist.size(); i++) {
			queue.add(newlist.get(i));
		}
		visited = new boolean[N][N];
		arr = new int[N][N];
		int vc = 0;
		int max = 0;
		while (!queue.isEmpty()) {
			virus q = queue.poll();
//			System.out.println(q);
			int x = q.x;
			int y = q.y;
			int time = q.time;
			if (arr[x][y] == 0) {

				if (visited[x][y])
					continue;
//				System.out.println("숙주");
				arr[x][y] = time;

				if (time > 0) {
					if (!(crr[x][y] == '*'))
						vc++;
				}
				visited[x][y] = true;
			} else {
				if (time >= arr[x][y])
					continue;
				else {
					arr[x][y] = time;
				}
			}
			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				if (Inrange(nr, nc))
					queue.add(new virus(nr, nc, time + 1));
			}

		}
//		System.out.println(vc);
		if (vc == count) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (crr[i][j] == '*')
						continue;
					max = Math.max(max, arr[i][j]);
//				if (crr[i][j] == '-')
//					System.out.print(crr[i][j] + " ");
//				else if (crr[i][j] == '*')
//					System.out.print(crr[i][j] + " ");
//				else
//					System.out.print(arr[i][j] + " ");
				}
//			System.out.println();
			}
//		System.out.println("MAX : "+max);
			result = Math.min(result, max);
		}

	}

	public static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N && crr[x][y] != '-';
	}

}
