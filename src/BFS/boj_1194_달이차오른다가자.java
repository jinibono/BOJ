package BFS;

import java.util.*;
import java.io.*;

public class boj_1194_달이차오른다가자 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class point {
		int x, y, len;
		String key;

		public point(int x, int y, int len, String key) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
			this.key = key;
		}

		public point() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + ", len=" + len + ", key=" + key + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		char[][] arr;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		boolean[][][] visited = new boolean[N][M][64];
		String str;
		int result = Integer.MAX_VALUE;
		Queue<point> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == '0') {
					queue.add(new point(i, j, 0, ""));
					visited[i][j][0] = true;
				}
			}
		}
		while (!queue.isEmpty())

		{
			point poll = queue.poll();
			int x = poll.x;
			int y = poll.y;
			int len = poll.len;
			String key = poll.key;
			int getkey = cal(key);
//			System.out.println(poll.toString() + " " + getkey);
			if (arr[x][y] == '1') {
				result = Math.min(result, len);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if ((arr[nr][nc] == '1' || arr[nr][nc] == '0' || arr[nr][nc] == '.') && !visited[nr][nc][getkey]) {
						visited[nr][nc][getkey] = true;
						queue.add(new point(nr, nc, len + 1, key));
					}
					if (arr[nr][nc] >= 'a' && arr[nr][nc] <= 'f') {
						// 열쇠가 있는곳일때
						// 내가 만약에 그 열쇠를 가지고 있지 않을때만 방문
						if (!visited[nr][nc][getkey]) {
							visited[nr][nc][getkey] = true;
							queue.add(new point(nr, nc, len + 1, key + arr[nr][nc]));
						}
					}

					if (!visited[nr][nc][getkey] && arr[nr][nc] >= 'A' && arr[nr][nc] <= 'F') {
						if (key.contains((arr[nr][nc] + "").toLowerCase()))// 열쇠가 있다면
						{
							visited[nr][nc][getkey] = true;
							queue.add(new point(nr, nc, len + 1, key));
						}
					}
				}

			}

		}
		if (result != Integer.MAX_VALUE)
			System.out.println(result);
		else
			System.out.println(-1);
	}

	static int cal(String str) {
		int sum = 0;
		if (str.contains("a"))
			sum += Math.pow(2, 0);
		if (str.contains("b"))
			sum += Math.pow(2, 1);
		if (str.contains("c"))
			sum += Math.pow(2, 2);
		if (str.contains("d"))
			sum += Math.pow(2, 3);
		if (str.contains("e"))
			sum += Math.pow(2, 4);
		if (str.contains("f"))
			sum += Math.pow(2, 5);

		return sum;
	}

}
