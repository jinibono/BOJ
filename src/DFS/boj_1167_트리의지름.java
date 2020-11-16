package DFS;
import java.util.*;
import java.io.*;

public class boj_1167_트리의지름 {
	static int N;
	static boolean[] checked;

	static ArrayList[] list;
	static int[][] costmap;
	static boolean[] visited;
	static int start;
	static int res = 0;
	static int leaf = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			while (true) {
				int next = Integer.parseInt(st.nextToken());
				if (next == -1)
					break;
				int cost = Integer.parseInt(st.nextToken());
				list[start].add(next);
				list[start].add(cost);
				list[next].add(start);
				list[next].add(cost);

			}

		}
		dfs(1, 0, new boolean[N + 1]);

		dfs(leaf, 0, new boolean[N + 1]);

		System.out.println(res);

	}

	static void dfs(int cur, int cost, boolean[] visited) {
		visited[cur]=true;
		if (cost > res) {
			res = cost;
			leaf = cur;
		}

		for (int i = 0; i < list[cur].size(); i = i + 2) {
			int next = (int) list[cur].get(i);
			if (!visited[next]) {

				visited[next] = true;
				dfs(next, cost + (int) list[cur].get(i + 1), visited);

			}
		}

	}

}
