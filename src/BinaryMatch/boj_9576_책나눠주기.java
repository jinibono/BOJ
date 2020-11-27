package BinaryMatch;

import java.util.*;
import java.io.*;

public class boj_9576_책나눠주기 {
	static int result = 0;
	static boolean[] visited;
	static int[] parent; // 선택된 사용자의 번호
	static ArrayList list[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			list = new ArrayList[M+1];
			parent = new int[N+1];
			visited = new boolean[N+1]; // 어떤 점에서 다른 점을 방문한 적이 있는가?
			for (int i = 1; i <= M; i++)
				list[i] = new ArrayList<Integer>();
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				for (int num = a; num <= b; num++)
					list[i].add(num);
			}

			for (int i = 1; i <= M; i++) {
				Arrays.fill(visited, false);
				if (dfs(i))
					result++;
			}
			sb.append(result).append('\n');
			result = 0;

		}
		System.out.print(sb.toString());
	}

	static boolean dfs(int num) {

		for (int i = 0; i < list[num].size(); i++) {
			int next = (int) list[num].get(i);
			if (visited[next])
				continue; // num에서 next로 가는 경우의 수를 이미 고려했다면 고려하지 않스빈다.
			visited[next] = true;
			if (parent[next] == 0 || dfs(parent[next])) // 아직 부모가 없으면
			{
				parent[next] = num;
				return true;
			}

		}
		return false;
	}

}
