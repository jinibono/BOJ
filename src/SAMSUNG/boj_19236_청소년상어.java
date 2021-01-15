package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_19236_청소년상어 {
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[4][4];
		int[][] drmap = new int[4][4];
		StringTokenizer st;
		int shark = 100;
		int shdir = 0;
		int shsum = 0;
		int a;
		int b;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				drmap[i][j] = b - 1;
			}
		}

		process(map, drmap, 0, 0, drmap[0][0], map[0][0]);
		System.out.println(max);

	}

	static void process(int map[][], int drmap[][], int sx, int sy, int sdir, int sum) {
		// 상어가 이동한 직후
//		sum += map[sx][sy]; // 상어가 먹은 수 누적
		sdir = drmap[sx][sy]; // 먹은놈의 방향 획득
		map[sx][sy] = 100; // 상어라고 표기
		max = Math.max(max, sum);
//		
		boolean numfind = false;
		// 물고기이동
		for (int num = 1; num <= 16; num++) {
			numfind = false;
			for (int x = 0; x < 4; x++) {
				if (numfind)
					break;
				for (int y = 0; y < 4; y++) {
					if (numfind)
						break;
					if (map[x][y] == num) {
						numfind = true;
						int tdir = drmap[x][y];
						int nx, ny;
						while (true) {
							nx = x + dr[tdir];
							ny = y + dc[tdir];
							if (Inrange(nx, ny) && map[nx][ny] != 100) {
								// 다음 위치가 경계안이고, 상어가 없는곳이면 이동
								// swap
								if (map[nx][ny] == 0) // 빈공간이면 그냥이동
								{
									map[nx][ny] = map[x][y];
									drmap[nx][ny] = tdir;
									map[x][y] = 0;
								} else {
									int tn, td;
									tn = map[x][y];
									td = tdir;

									map[x][y] = map[nx][ny];
									drmap[x][y] = drmap[nx][ny];

									map[nx][ny] = tn;
									drmap[nx][ny] = td;
								}
								break;
							}
							tdir = (tdir + 1) % 8;
						}

					}
				}
			}

		}

		// 물고기가 다 이동했으니 상어 위치를 빈자리로 표기
		map[sx][sy] = 0;
		int nmap[][] = new int[4][4];
		int dmap[][] = new int[4][4];

		// 상어이동 1,2,3
		for (int i = 1; i <= 3; i++) {
			int nx = sx + dr[sdir] * i;
			int ny = sy + dc[sdir] * i;
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					nmap[x][y] = map[x][y];
					dmap[x][y] = drmap[x][y];
				}
			}
			if (Inrange(nx, ny) && nmap[nx][ny] != 0) {
				// 경계내에서만 이동, 빈공간으로는 이동x
				process(nmap, dmap, nx, ny, dmap[nx][ny], sum + nmap[nx][ny]);
			}
		}
		return;

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < 4 && y < 4;
	}

}
