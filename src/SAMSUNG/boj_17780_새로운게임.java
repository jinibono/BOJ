package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_17780_새로운게임 {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int N;
	static int[][] arr;

	static class point {
		int idx;
		int x, y;
		int dir;

		public point(int idx, int x, int y, int dir) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		public point() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "point [idx=" + idx + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int x, y, dir;
		arr = new int[N][N];
		ArrayList[][] map = new ArrayList[N][N];
		ArrayList<point> plist = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new ArrayList<point>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			plist.add(new point(i, x - 1, y - 1, dir - 1));
			map[x - 1][y - 1].add(new point(i, x - 1, y - 1, dir - 1));
		}
		point temp;
		point poll;
		int nx, ny;
		int ans = -1;
		for (int turn = 1; turn <= 1000; turn++) {
//			System.out.println(turn+"회차");
			for (int idx = 0; idx < K; idx++) {
				temp = plist.get(idx);
				x = temp.x;
				y = temp.y;
//				System.out.println(temp);
				poll = (point) map[x][y].get(0);

				if (poll.idx != idx)
					continue; // 만약에 바닥의 놈이 아니면 통과
				nx = x + dr[temp.dir];
				ny = y + dc[temp.dir];

				// 밖을 넘어가는경우
				if (!Inrange(nx, ny) || arr[nx][ny] == 2) {
					temp.dir = changedir(temp.dir); // 일단 못가니까 방향을 바꿈
					nx = x + dr[temp.dir];
					ny = y + dc[temp.dir];
					if (!Inrange(nx, ny))
						continue;
					if (arr[nx][ny] == 2)
						continue; // 바꿨는데도 파랑색이면 종료

				}

				// 1 흰색인경우
				if (arr[nx][ny] == 0) {

					for (int k = 0; k < map[x][y].size(); k++) {
						poll = (point) map[x][y].get(k);
						temp = plist.get(poll.idx);
						temp.x = nx;
						temp.y = ny;
						map[nx][ny].add(temp);
					}
					map[x][y].clear();
					if (map[nx][ny].size() >= 4) {
						ans = turn;
//						System.out.println(nx + " " + ny);
						break;
					}
					continue;
				}
				// 2 빨갱이
				if (arr[nx][ny] == 1) {

					for (int k = map[x][y].size() - 1; k >= 0; k--) {
						poll = (point) map[x][y].get(k);
						map[nx][ny].add(map[x][y].get(k));
						temp = plist.get(poll.idx);
						temp.x = nx;
						temp.y = ny;
					}

					map[x][y].clear();
					if (map[nx][ny].size() >= 4) {
						ans = turn;

						break;
					}
					continue;
				}

			}

			if (ans != -1)
				break;
		}
		System.out.println(ans);

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static int changedir(int dir) {
		if (dir == 0)
			return 1;
		if (dir == 1)
			return 0;
		if (dir == 2)
			return 3;
		if (dir == 3)
			return 2;
		return -1;
	}

}
