package MATH;
import java.util.*;
import java.io.*;

public class boj__1153_네개의소수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] flag = new boolean[N + 1];

		ArrayList<Integer> list = new ArrayList<Integer>();
		int two = 2;
		int tmp = 2;
		while (tmp * two <= N) {
			flag[tmp * two] = true;
			tmp++;
		}
		for (int num = 2; num <= N; num++) {
			if (flag[num])
				continue;
			list.add(num);
			tmp = 2;
			while (tmp * num <= N) {
				flag[tmp * num] = true;
				tmp++;
			}

		}
		int left, right;
		int l1 = 0;
		int l2 = 0;
		int r1 = 0;
		int r2 = 0;
		
		// left+right = N
		// left도 두 소수로 만들수 있고
		// right도 두 소수로 만들수 있어야함
		boolean pos = false;
		boolean lpos = false;
		boolean rpos = false;
		int result[] = new int[4];
		if (N < 8) {
			System.out.println(-1);
			return;
		}
		result[0] = 2;
		result[1] = 2;
		if (N % 2 == 1) {
			N -= 5;
			result[1] = 3;
		} else {
			N -= 4;
		}
		left = N / 2;
		right = N - left;
		while (left > 2 && right < N && !pos) {

			if (flag[left] == false && flag[right] == false) {
				result[2] = left;
				result[3] = right;
				break;
			}

			left--;
			right++;

		}
		for(int i=0;i<4;i++)
			System.out.print(result[i]+" ");

	}

}
