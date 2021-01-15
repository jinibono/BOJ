package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_17472_다리만들기2 {
	static int W, H;
	static int bridge = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] arr;
	static boolean[][] visited;
	static int[] parent;
	static int cnt = 0;
	static boolean[][] connected;

	static class edge {
		int x, y, len;

		public edge(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}

		@Override
		public String toString() {
			return "edge [x=" + x + ", y=" + y + ", len=" + len + "]";
		}

	}

	static class point {
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<point> list = new ArrayList<>();
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visited = new boolean[H][W];
		PriorityQueue<edge> pq = new PriorityQueue<>(new Comparator<edge>() {

			@Override
			public int compare(edge o1, edge o2) {
				// TODO Auto-generated method stub
				return o1.len - o2.len;
			}
		});
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		int idx = 1;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] == 0 || visited[i][j])
					continue;
				list.add(new point(i, j));
				bfs(i, j, idx++);
			}

		}
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		parent = new int[idx + 1];
		connected = new boolean[idx + 1][idx + 1];
		for (int i = 1; i <= idx; i++)
			parent[i] = i;
		idx--;
//		System.out.println(idx);
		int sx, sy;
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] != 0)// 좌표 찾자!
				{
					for (int k = 0; k < 4; k++) {
						count = 0;
						while (true) {
							count++;
							sx = i + count * dr[k];
							sy = j + count * dc[k];
							if (!inrange(sx, sy) || arr[sx][sy] == arr[i][j])// 나랑 똑같은 놈이면 멈춰!
								break; // 그러면 0 인애들만 계속지나가다가
							if (arr[sx][sy] != 0) // 다른놈을 만나면 멈춰!
							{
								if (count > 2)
									pq.add(new edge(arr[i][j], arr[sx][sy], count - 1));
								break;
							}

						}
					}
				}
			}
		} // pq설정완료
		edge poll;
//		System.out.println(idx);
		int x, y, len;
		boolean maked = false;
		int result = -1;
		if (pq.size() == 0) {
			System.out.println(result);
			return;
		}
		ArrayList<edge> mst = new ArrayList<>();
		while (mst.size() < (idx - 1)) { // V개의 정점을 연결하기 위한 최소간선 갯수는 V-1개인데 아직 그게 안됐으면 계속 하기
			edge edge = pq.poll();
//            System.out.println("111111");
			if (edge == null) {
				System.out.println("-1");
				return;
			}
			if (union_find(edge.x) != union_find(edge.y)) {
//                System.out.println("22222");
				mst.add(edge);
				merge(edge.x, edge.y);
			}
		}
		for (int i = 0; i < mst.size(); i++) {
			cnt += mst.get(i).len;
		}

		System.out.println(cnt);

	}

	static int union_find(int u) {
		if (u == parent[u]) {
			return u;
		}
		return parent[u] = union_find(parent[u]); // 압축

	}

	static void merge(int u, int v) {
		int x = union_find(u);
		int y = union_find(v);
		if (x == y)
			return;

		parent[x] = y;
	}

	private static void bfs(int i, int j, int k) {
		Queue<point> queue = new LinkedList<>();
		queue.add(new point(i, j));
		visited[i][j] = true;
		point poll;

		int x, y, nr, nc;
		while (!queue.isEmpty()) {
			poll = queue.poll();
			x = poll.x;
			y = poll.y;
			arr[x][y] = k;
			for (int d = 0; d < 4; d++) {
				nr = x + dr[d];
				nc = y + dc[d];
				if (inrange(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 1) {
					visited[nr][nc] = true;
					queue.add(new point(nr, nc));
				}
			}
		}
	}

	static boolean inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W;
	}

}