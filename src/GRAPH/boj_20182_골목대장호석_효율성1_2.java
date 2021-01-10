package GRAPH;

import java.io.*;
import java.util.*;

public class boj_20182_골목대장호석_효율성1_2 {
	static int N, M;
	static int start, goal;
	static long money;
	static int ans;
	static ArrayList<Edge> adj[];
	static long dist[];

	static class Edge {
		int index;
		long weight;

		public Edge(int index, long weight) {
			super();
			this.index = index;
			this.weight = weight;
		}

		public Edge() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		money = Long.parseLong(st.nextToken());
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, cost));
			adj[v].add(new Edge(u, cost));
		}
		ans = Integer.MAX_VALUE;
		int left = 1;
		int right = (int) 1e9;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (djik(mid)) {
				ans = Math.min(ans, mid);
				right = mid - 1;

			} else {
				left = mid + 1;
			}
		}
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}

	private static boolean djik(int mid) {
		dist = new long[N + 1];
		for (int i = 1; i <= N; i++)
			dist[i] = Long.MAX_VALUE;
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return Long.compare(o1.weight, o2.weight);
			}
		});
		dist[start] = 0l;
		pq.add(new Edge(start, dist[start]));
		while (!pq.isEmpty()) {
			long peek = pq.peek().weight;
			Edge p = pq.poll();
			if (peek != dist[p.index])
				continue;
			for (Edge y : adj[p.index]) {
				if (y.weight > mid)
					continue;
				if (dist[y.index] > dist[p.index] + y.weight) {
					dist[y.index] = dist[p.index] + y.weight;
					pq.add(new Edge(y.index, dist[y.index]));
				}
			}
		}

		return dist[goal] <= money;

	}

}
