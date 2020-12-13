package Backtracking;

import java.util.*;
import java.io.*;

public class boj_3109_빵집 {
	static int N, M;
	static boolean[][] visited;
	static boolean[] maked;
	static char[][] map;

	static int max = 0;
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		maked = new boolean[N];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = word.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			dfs(i, i, 0);

			// 최적해는 어느정도 정해져 있으므로 맨 위에서부터 채우면 된다. 더이상은 살펴보지 않음 (max==0 조건설정)
		}
		System.out.println(max);
	}

	static void dfs(int h, int x, int y) {
		// h : 출발 높이, x,y, 현재 연결한 가스관
		if (y == M - 1) {
			maked[h] = true;
			max++;
			return;
			// 만약에 내가 가스관 하나를 완벽하게 이었으면 다음 높이에서 더 연결할 수 있는지 봅시다
		} else {
			// 오른대각위,오른,오른대각아래 순으로 dfs 살펴봄, 하나라도 연결 완성되면 가지치기 x
			for (int i = 0; i < 3; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				if (Inrange(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
					visited[nr][nc] = true;
					dfs(h, nr, nc);
//					visited[nr][nc] = false;
					if (maked[h])
						break;
				}
			}
		}

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
