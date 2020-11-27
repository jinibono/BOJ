package BinaryMatch;
import java.util.*;
import java.io.*;

public class boj_11375_열혈강호 {
	static int result = 0;
	static boolean[] visited;
	static int[] parent; // 선택된 사용자의 번호
	static ArrayList list[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		parent = new int[M+1];
		visited = new boolean[M+1]; // 어떤 점에서 다른 점을 방문한 적이 있는가?
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			for (int c = 0; c < S; c++) {
				int temp = Integer.parseInt(st.nextToken());
				list[i].add(temp);
			}

		}
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			if (dfs(i))
				result++;
		}
		System.out.println(result);

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
