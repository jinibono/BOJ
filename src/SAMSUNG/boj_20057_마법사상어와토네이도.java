package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_20057_마법사상어와토네이도 {
	static int[] dr = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] dc = { -1, 0, 1, 0 };
	static int[][] map;
	static int N;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int sx = N / 2;
		int sy = N / 2;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] ratiomap = { { 0, 1, 0 }, { 0, 7, 2 }, { 0, 10, 0 }, { 5, 0, 0 } };

		result = 0;
		int dir = 0;
		int cnt = 2;// 이동할 거리를 연산하기 위함
		int nx = 0;
		int ny = 0;
		int ax = 0;
		int ay = 0; // a의 위치
		int side[] = new int[2];// 상대적으로 왼쪽 오른쪽 방향이 어느 dir인가?
		int tx = 0;
		int ty = 0;
		int sidex = 0;
		int sidey = 0;
		int tc = 0;
		while (!(sx == 0 && sy == 0)) {
			// sx,sy 에서 nx,ny 까지 1칸씩 이동할겁니다.

			int cx = 0;
			int cy = 0;
			for (int len = 1; len <= cnt / 2; len++) {
				cx = sx + dr[dir];
				cy = sy + dc[dir];
				int Ydust = map[cx][cy];
				int temp = 0;
				// 토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동한다.
				// => sx,sy 에서 cx,cy로 이동하면 ax,ay칸으로 모두 이동할것이다
				ax = cx + dr[dir];
				ay = cy + dc[dir]; // 모래는 ax,ay로 이동할겁니다.

				// 비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼이고, 계산에서 소수점 아래는 버린다.
				if (dir == 0 || dir == 2) {
					// dir 1,3 조사
					side[0] = 1;
					side[1] = 3;
				} else {
					// dir 0,2 조사
					side[0] = 0;
					side[1] = 2;
				}
				for (int k = 0; k < 2; k++) { // 좌우 칸을 탐색할것입니다.
					for (int i = 0; i < 4; i++) { // 전방 4칸을 탐색할것입니다
						if (k == 1 && i == 3)
							break;
						tx = sx + i * dr[dir];
						ty = sy + i * dc[dir];
						for (int j = 0; j < 3; j++) { // 좌우 3칸씩 탐색
							if (ratiomap[i][j] == 0)
								continue;
							sidex = tx + j * dr[side[k]];
							sidey = ty + j * dc[side[k]];
							int sand = (ratiomap[i][j] * map[cx][cy]) / 100;

							Ydust -= sand;
							if (!Inrange(sidex, sidey)) {
								result += sand;
								continue; // 범위를 넘어가면 result 증가
							}
							// 비율만큼 Y의 모래가 줍니다.
							map[sidex][sidey] += sand;
						}
					}
				}

				if (Inrange(ax, ay)) {
					map[ax][ay] += Ydust;
				} else
					result += Ydust;

				// α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다
				map[cx][cy] = 0;
				sx = cx;
				sy = cy;
				if (sx == 0 && sy == 0)
					break;
			}

			cnt++;
			dir = (dir + 1) % 4;

//			print(map);

		}

		System.out.println(result);

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static void print(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(result);
		System.out.println();

	}

}
