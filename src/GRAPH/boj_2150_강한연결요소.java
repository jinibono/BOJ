package GRAPH;

import java.util.*;
import java.io.*;

public class boj_2150_강한연결요소 {
	static boolean[] visited;
	static ArrayList<Integer> list[];
	static ArrayList<Integer> rlist[];
	static ArrayList<Integer> temp = new ArrayList<Integer>();
	static Stack<Integer> stack = new Stack<Integer>();
	static int cnt = 0;

	public static <T> void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V, E;
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new ArrayList[V + 1];
		rlist = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		
		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<Integer>();
			rlist[i] = new ArrayList<Integer>();
			
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			rlist[b].add(a);
		}
		for (int i = 1; i <= V; i++) {
			if (!visited[i])
				dfs(i);
		}

		visited = new boolean[V + 1];

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		while (!stack.isEmpty()) {
			Integer pop = stack.pop();
			if (!visited[pop]) {
				temp.clear();
				rdfs(pop);
				cnt++;
				Collections.sort(temp);
				pq.add(temp.get(0));
				list[temp.get(0)].clear();
				list[temp.get(0)].addAll(temp);
				

			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append('\n');
		while (!pq.isEmpty()) {
			Integer poll = pq.poll();
			for (int i = 0; i < list[poll].size(); i++)
				sb.append(list[poll].get(i) + " ");
			sb.append(-1).append('\n');
		}
		System.out.print(sb.toString());
	}

	static void dfs(int num) {
		visited[num] = true;
		for (int next : list[num]) {
			{
				if (!visited[next])
					dfs(next);
			}
		}
//		System.out.println(num + "추가");
		stack.add(num);
	}

	static void rdfs(int num) {
		visited[num] = true;
		for (int next : rlist[num]) {
			if (!visited[next])
				rdfs(next);
		}
		temp.add(num);

	}

}
