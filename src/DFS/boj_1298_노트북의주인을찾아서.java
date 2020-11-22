package DFS;

import java.util.*;
import java.io.*;

public class boj_1298_노트북의주인을찾아서 {

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList list[] = new ArrayList[N + 1];
		visited = new boolean[N + 1][N + 1]; // 어떤 점에서 다른 점을 방문한 적이 있는가?
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		

	}

}
