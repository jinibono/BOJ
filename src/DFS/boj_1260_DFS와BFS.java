package DFS;

import java.util.*;
import java.io.*;

public class boj_1260_DFS와BFS {
	static ArrayList[] list;
	static boolean[] visited;
	static StringBuffer dfsBuffer = new StringBuffer();
	static StringBuffer bfsBuffer = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[s].add(e);
			list[e].add(s);
		}
		for (int i = 1; i <= N; i++)
			Collections.sort(list[i], new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o1 - o2;
				}
			});
		visited = new boolean[N + 1];
		visited[start] = true;
		dfs(start);
		System.out.println(dfsBuffer.toString());
		visited = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			Integer num = queue.poll();
			bfsBuffer.append(num + " ");
			for (int i = 0; i < list[num].size(); i++) {
				int next = (int) list[num].get(i);
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		System.out.print(bfsBuffer.toString());

	}

	static void dfs(int num) {
		dfsBuffer.append(num + " ");
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
