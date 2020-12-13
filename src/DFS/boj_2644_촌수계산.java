package DFS;

import java.util.*;
import java.io.*;

public class boj_2644_촌수계산 {
	static int start;
	static int end;
	static boolean[] visited;
	static ArrayList[] list;
	static int result = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		dfs(start, 0);
		System.out.println(result);

	}

	static void dfs(int num, int cnt) {
		
		visited[num] = true;
		if (num == end) {
			result = cnt;
			return;
		}
		for (int i = 0; i < list[num].size(); i++) {
			int next = (int) list[num].get(i);
			if (!visited[next])
				dfs(next, cnt+1);
		}
	}

}
