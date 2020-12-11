package DFS;

import java.util.*;
import java.io.*;

public class boj_11724_연결요소의개수 {
	static ArrayList[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		visited = new boolean[N + 1];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] ) {
				visited[i] = true;
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	static void dfs(int num) {
		for (int i = 0; i < list[num].size(); i++) {
			int next = (int) list[num].get(i);
			if (!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
		return;
	}

}
