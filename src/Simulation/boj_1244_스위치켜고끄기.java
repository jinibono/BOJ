package Simulation;

import java.util.*;
import java.io.*;

public class boj_1244_스위치켜고끄기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		int[] arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		int gender;
		int idx;
		int maxleft;
		int maxright;
		int k = 1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			idx = Integer.parseInt(st.nextToken());
			if (gender == 1) {
				k = 1;
				while (idx * k <= N) {
					arr[idx * k] ^= 1;
					k++;
				}
			} else {
				maxleft = idx;
				maxright = idx;
				arr[idx] ^= 1;
				while (true) {
					maxleft--;
					maxright++;
					if (maxleft < 1 || maxright > N)
						break;
					if (arr[maxleft] == arr[maxright]) {
						arr[maxleft] ^= 1;
						arr[maxright] ^= 1;
					}
					else
						break;

				}

			}
		}
		for(int i=1;i<=N;i++)
		{
			if((i-1)%20==0&&(i-1)!=0)
			{
				sb.append('\n');
				
			}
			sb.append(arr[i]);
			sb.append(" ");
			
		}
		System.out.print(sb.toString());
		

	}

}
