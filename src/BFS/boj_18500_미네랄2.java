package BFS;

import java.util.*;
import java.io.*;

public class boj_18500_미네랄2 {
	static int R, C;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static boolean[][] visited;
	static int[][] map;
	static ArrayList list[];

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		visited = new boolean[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			String read = br.readLine();
			for (int j = 1; j <= C; j++) {
				if (read.charAt(j - 1) == 'x') {
					map[i][j] = (int) 1e9;
				}
			}
		}

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int H;
		int sx, sy;
		int nx, ny;
		int idx = 0;
		int len;
		int drop;
		for (int a = 0; a < N; a++) {
			H = Integer.parseInt(st.nextToken());
			sx = R - H + 1;
			if (a % 2 == 0)
				sy = 1;
			else
				sy = C;
//			System.out.println(sx + " " + sy + "출발");
			while (Inrange(sx, sy)) {
				if (map[sx][sy] != 0) {
					map[sx][sy] = 0; // 미네랄을 만나면 뿌셔부러
//					System.out.println(sx + " " + sy + "뿌심");
					break;
				}
				sy = sy + dc[(a % 2) + 2];
//				System.out.println(sx + " " + sy);
			}
			// 뿌셔뿌셔했음
			visited = new boolean[R + 1][C + 1];
			list = new ArrayList[10000];
			idx = 1;
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					if (map[i][j] == 0 || visited[i][j])
						continue;
					bfs(i, j, idx++);
				}
			}
//			System.out.println("여까진왔지");

			for (int num = 1; num < idx; num++) {
				len = 1000;
				for (int i = 0; i < list[num].size(); i++) {
					point temp = (point) list[num].get(i);
					sx = temp.x;
					sy = temp.y;
					sx++;
					drop = 0;
					while (true) {

						if (!Inrange(sx, sy) || (map[sx][sy] != num && map[sx][sy] != 0)) {
							len = Math.min(len, drop);
							break;
						}
						if (map[sx][sy] == num)
							break;
						sx++;
						drop++;

					}
				}
//				System.out.println(num+" "+len+"만큼 떨어지자");
				if (len == 0 || len == 1000)
					continue;
				// drop 할 높이가 len으로 남아있다면
				for (int i = 0; i < list[num].size(); i++) {
					point temp = (point) list[num].get(i);
					sx = temp.x;
					sy = temp.y;
					map[sx][sy] = 0;

				}
				for (int i = 0; i < list[num].size(); i++) {
					point temp = (point) list[num].get(i);
					sx = temp.x;
					sy = temp.y;
					map[sx + len][sy] = num;
				}
				break;
			}

		}
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++)
				if (map[i][j] == 0)
					System.out.print('.');
				else
					System.out.print('x');
			System.out.println();
		}
	}

	static boolean Inrange(int x, int y) {
		return x > 0 && y > 0 && x <= R && y <= C;
	}

	private static void bfs(int i, int j, int k) {
		Queue<point> queue = new LinkedList<>();

		list[k] = new ArrayList<>();
		queue.add(new point(i, j));
		list[k].add(new point(i, j));
		visited[i][j] = true;
		point poll;

		int x, y, nr, nc;
		while (!queue.isEmpty()) {
			poll = queue.poll();
			x = poll.x;
			y = poll.y;
			map[x][y] = k;
			for (int d = 0; d < 4; d++) {
				nr = x + dr[d];
				nc = y + dc[d];
				if (Inrange(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
					visited[nr][nc] = true;
					queue.add(new point(nr, nc));
					list[k].add(new point(nr, nc));
				}
			}
		}

	}

}
