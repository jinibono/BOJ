package Simulation;
import java.util.*;
import java.io.*;
public class boj_1052_물병 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt =0;
		String str;
		int count=0;
		while(true)
		{
			count=0;
			str = Integer.toBinaryString(N);
			for(int i=0;i<str.length();i++)
			{
				count+=str.charAt(i)-'0';
			}
			if(count<=K)
				break;
			cnt++;
			N++;
		}
		System.out.println(cnt);
		
	}

}
