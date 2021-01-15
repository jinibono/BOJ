package SAMSUNG;

import java.util.*;
import java.io.*;

public class Main {
	static int[][] arr;
	static boolean[][] visited;
	static ArrayList<person> list;
	static int N, M, fuel;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static PriorityQueue<person> pq = new PriorityQueue<>();
	static Queue<person> tempq = new LinkedList<>();

	static class person {
		int x, y, gx, gy, len;

		public person(int x, int y, int gx, int gy, int len) {
			super();
			this.x = x;
			this.y = y;
			this.gx = gx;
			this.gy = gy;
			this.len = len;
		}

		public person() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "person [x=" + x + ", y=" + y + ", gx=" + gx + ", gy=" + gy + ", len=" + len + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean pos = true;
		int tx, ty;
		int templen = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		tx = Integer.parseInt(st.nextToken());
		ty = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		pq = new PriorityQueue<>(new Comparator<person>() {
			@Override
			public int compare(person o1, person o2) {
				if (o1.len == o2.len) {
					if (o1.x == o2.x) {
						return o1.y - o2.y;
					}
					return o1.x - o2.x;
				}
				return o1.len - o2.len;
			}
		});

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c, d;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			pq.add(new person(a, b, c, d, 0));
		}

		while (true) {
			if (!pos || pq.size() == 0)
				break;
			tempq.clear();
			while (!pq.isEmpty()) {
				person q = pq.poll();
				bfs(q, tx, ty, 0);
			}
			pq.addAll(tempq); // 거리가 정렬된 큐
//			tempq.clear();
//			while (!pq.isEmpty()) {
//				person q = pq.poll();
//				tempq.add(q);
//				System.out.println(q);
//			}
//			System.out.println();
//			pq.addAll(tempq);

			person p = pq.poll();

			fuel -= p.len;
			if (fuel < 0) {
				pos = false;
				break;
			}
			templen = bfs(p, p.gx, p.gy, -1);
			tx = p.gx;
			ty = p.gy;
			fuel -= templen;
			if (fuel < 0) {
				pos = false;
				break;
			}
			fuel += 2 * templen;

		}

		if (!pos) {
			System.out.println(-1);
		} else {
			System.out.println(fuel);
		}

	}

	private static int bfs(person p, int tx, int ty, int k) {
		int gx = p.x;
		int gy = p.y;

		Queue<person> queue = new LinkedList<>();
		queue.add(new person(tx, ty, 0, 0, 0));
		visited = new boolean[N + 1][N + 1];
		visited[tx][ty] = true;
		int tlen = 99999999;
		while (!queue.isEmpty()) {
			person q = queue.poll();
			int x = q.x;
			int y = q.y;
			int len = q.len;
			if (len > fuel)
				break;
			if (x == gx && y == gy) {
				tlen = Math.min(tlen, len);
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];

				if (Inrange(nr, nc) && !visited[nr][nc]) {
					queue.add(new person(nr, nc, 0, 0, len + 1));
					visited[nr][nc] = true;
				}
			}
		}
		if (k == -1)
			return tlen;
		p.len = tlen;
		tempq.add(p);
		return 0;
	}

	static boolean Inrange(int x, int y) {
		return x >= 1 && y >= 1 && x <= N && y <= N && arr[x][y] != 1;
	}

}
