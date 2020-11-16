package DP;
import java.io.*;
import java.util.*;
public class boj_1003_피보나치함수 {
	static long cnt0 = 0;
	static long cnt1 = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		long[] memo = new long[41];
		memo[0]=0l;
		memo[1]=1l;
		memo[2]=1l;
		for(int i=3;i<41;i++)
		{
			memo[i]=memo[i-1]+memo[i-2];
		}
		for(int tc=1;tc<=T;tc++)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0) {
			sb.append(1+" "+0).append('\n');
			continue;
			}
			cnt0=memo[N-1];cnt1=memo[N];
			
			
			sb.append(cnt0+" "+cnt1).append('\n');
		}
		System.out.print(sb.toString());
											
	}
	static int fibo(int x)
	{
		if(x==0)
		{
			cnt0++;
			return 0;
		}
		else if(x==1)
		{
			cnt1++;
			return 1;
		}
		else
			return fibo(x-1)+fibo(x-2); 
	}

}
