package DFS;
import java.util.*;
import java.io.*;
public class boj_1012_유기농배추 {
	
	static int[][] arr;
	static boolean[][] visited;
	static int N,M;
	static int virus = 0;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int tempx,tempy;
	static int count = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++)
		{
			count = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			virus = Integer.parseInt(st.nextToken());
			arr = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0;i<virus;i++)
			{
				st= new StringTokenizer(br.readLine());
				tempx = Integer.parseInt(st.nextToken());
				tempy = Integer.parseInt(st.nextToken());
				arr[tempx][tempy] = 1;
			}
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
				{
					if(arr[i][j]==1)
					{
						dfs(i,j);
						count++;
					}
				}
			}
			sb.append(count).append('\n');
			
		}
		System.out.print(sb.toString());
		
		

	}
	public static void dfs(int x, int y) {
		if(!inrange(x, y))
			return;
		if(visited[x][y])
			return;
		visited[x][y] = true;
		if(arr[x][y]!=1)
			return;
		arr[x][y] = 0;
		virus--;
		dfs(x+1,y);
		dfs(x-1,y);
		dfs(x,y+1);
		dfs(x,y-1);
		
	}
	static boolean inrange(int x, int y)
	{
		return x>=0&&y>=0&&x<N&&y<M;
	}

}

