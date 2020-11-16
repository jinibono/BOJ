package DFS;
import java.util.*;
import java.io.*;

public class boj_1103_게임 {
	static int N, M;
	static char[][] arr;
	static int max = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] dp;
	static boolean loop = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		dp = new int[N][M];
		String word;
		for (int i = 0; i < N; i++) {
			word = (br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = word.charAt(j);
			}
		}
		dfs(0, 0, new boolean[N][M], 1);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		if(loop)
			System.out.println(-1);
		else
		System.out.println(max);
	

	}

	static void dfs(int x, int y, boolean[][] visited, int cnt) {
//		if (!visited[x][y]) {
//			visited[x][y] = true;
//		} else {
//			System.out.println("문제 : "+x+" "+y);
//			loop = true;
//			return;
//		}
		dp[x][y] = cnt;
		max = Math.max(max, cnt);
		for (int i = 0; i < 4 && !loop; i++) {
			int nx = x + (arr[x][y] - '0') * dr[i];
			int ny = y + (arr[x][y] - '0') * dc[i];
			if (Inrange(nx, ny) && arr[nx][ny] != 'H' && cnt + 1 > dp[nx][ny] && !loop) {
				// 범위 안의 영역이고 구멍이 아니면 간다
				// 방문한적이 없어야한다
				// 그리고 dp값보다 작거나 같은 길은 다시 방문 x
				if(!visited[nx][ny])
				{
					visited[nx][ny] = true;
				dfs(nx, ny, visited, cnt + 1);
				visited[nx][ny]=false;
				}
				else
				{
					loop=true;
					return;
				}
			}
		}

	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
