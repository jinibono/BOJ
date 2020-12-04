package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_13460_구슬탈출2 {

	static int N, M;
	static char[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] dc = { -1, 0, 1, 0 };
	static int result = 11;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int rx = 0;
		int ry = 0;
		int bx = 0;
		int by = 0;
		int gx = 0;
		int gy = 0;
	
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = word.charAt(j);
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				} else if (map[i][j] == 'B') {
					bx = i;
					by = j;
				} else if (map[i][j] == 'O') {
					gx = i;
					gy = j;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			move(rx, ry, bx, by, i, 1);
		}

		if (result == 11)
			result = -1;
		System.out.println(result);

	}

	static void move(int rx, int ry, int bx, int by, int dir, int cnt) {
		if (cnt > 10 || cnt > result)
			return;

		
		int nrx = 0;
		int nry = 0;
		int nbx = 0;
		int nby = 0;
		int rcnt = 0;
		int bcnt = 0;
		boolean Rpos = true;
		boolean Bpos = true;
		boolean rmoved = false;
		boolean bmoved = false;
		while (true) {

			// Red 이동
			nrx = rx + dr[dir];
			nry = ry + dc[dir];
			// Blue 이동
			nbx = bx + dr[dir];
			nby = by + dc[dir];
			rmoved = false;
			bmoved = false;
			if (map[nrx][nry] != '#' && Rpos) { // 벽이 아닌곳만간다 , 구멍에 빠지면 더이상 못움직인다.
				rx = nrx;
				ry = nry;
				rcnt++;
				rmoved = true;
			}
			if (map[nbx][nby] != '#' && Bpos) {
				bx = nbx;
				by = nby;
				bcnt++;
				bmoved = true;
			}
			if (map[rx][ry] == 'O') {
				Rpos = false;
			}
			
			if (map[bx][by] == 'O') {
				Bpos = false;
			}
			if ((rx == bx) && (ry == by))
				break;

			if ((!rmoved && !bmoved)) {
//				System.out.println(rx + " " + ry + " / " + bx + " " + by + " 방문 return");
				break;
			}
//			if(visited[rx][ry][bx][by][dir]) break; //어 이 위치를 본 기억이 있어 
			
		}

		if (!Rpos && Bpos) {
			result = Math.min(result, cnt);
			return;

		}
		if (!Bpos || !Rpos) {
			// 공이 이미 빠져버렸다면 더 볼피룡가 없는녀석이다
			return;

		}
		if ((rx == bx) && (ry == by)) {
			// 두 녀석이 겹쳐있을때
			if (rcnt > bcnt) {
				rx -= dr[dir];
				ry -= dc[dir];
			} else if (rcnt < bcnt) {
				bx -= dr[dir];
				by -= dc[dir];
			}
//			System.out.println("겹쳐서 보정");
		}
//		System.out.println("이동 후 : " + rx + " " + ry + " / " + bx + " " + by + " 방향 : " + dir + " " + cnt);
		for (int i = 0; i < 4; i++) {
			if (i == dir)
				continue;
			move(rx, ry, bx, by, i, cnt + 1);
		}

	}

}
