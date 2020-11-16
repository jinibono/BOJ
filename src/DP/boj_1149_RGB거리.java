package DP;
import java.util.*;
import java.io.*;
public class boj_1149_RGB거리 {
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int d1,d2,d3;
		int pre1,pre2,pre3;
		d1=0;
		d2=0;
		d3=0;
		pre1=0;
		pre2=0;
		pre3=0;
		
		for(int i=0;i<N;i++)
		{
			st=new StringTokenizer(br.readLine());
			pre1=Math.min(d2, d3);
			pre2=Math.min(d1, d3);
			pre3=Math.min(d1, d2);
			d1=pre1+Integer.parseInt(st.nextToken());
			d2=pre2+Integer.parseInt(st.nextToken());
			d3=pre3+Integer.parseInt(st.nextToken());
		}
		System.out.println(Math.min(Math.min(d1, d2),d3));

		

	}

}
