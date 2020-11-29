package LCA;

import java.util.*;
import java.io.*;

public class boj_11437_LCA {
	static int N;
	static ArrayList[] list;
	static int[] depth, parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		depth = new int[N + 1];
		parent = new int[N + 1];
		dfs(1, 1);
		StringBuffer sb = new StringBuffer();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append((solve(a, b))).append('\n'); // 공통 부모 찾기
		}
		System.out.print(sb.toString());

	}

	public static void dfs(int node, int cnt) {// node: 방문 노드, cnt: 현재 깊이
		depth[node] = cnt;

		for (int i = 0; i < list[node].size(); i++) {
			int child = (int) list[node].get(i);
			if (depth[child] == 0) { // 깊이 계산이 안 된 곳은 자식 노드이므로
				dfs(child, cnt + 1);
				parent[child] = node;
			}
		}

	}

	public static int solve(int a, int b) {
		// 같은 층으로 만들기
		while (depth[a] > depth[b]) { // a가 더 밑에 있다면
			a = parent[a];
		}
		while (depth[a] < depth[b]) { // b가 더 밑에 있다면
			b = parent[b];
		}

		// 같은 층인데 같지 않다면(부모가 다르다면)
		while (a != b) { // 같은 부모를 찾을 때 까지 반복
			a = parent[a];
			b = parent[b];
		}

		return a;
	}
}
