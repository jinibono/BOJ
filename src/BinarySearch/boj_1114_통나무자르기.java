package BinarySearch;
import java.util.*;
import java.io.*;

public class boj_1114_통나무자르기 {
	static int K;
	static int C;
	static int[] len;
	static int[] cut;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		if (K < C)
			C = K;
		cut = new int[K + 1];
		len = new int[K + 1];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++)
			cut[i] = Integer.parseInt(st.nextToken());
		cut[K] = (int) L;
		Arrays.sort(cut);
		for (int i = 1; i <= K; i++)
			len[i] = cut[i] - cut[i - 1];
		len[0] = cut[0];
		int left = 1;
		int right = L;
		long ans1 = L;
		long ans2 = L;
		boolean pos = false;

		while (left <= right) {
			int mid = (left + right) / 2;
			int c = check(mid);
			if (c >= 0) {
				ans1 = mid;
				ans2 = c;
				right = mid - 1;
			} else
				left = mid + 1;
		}
		System.out.print(ans1 + " " + ans2);

	}

	static int check(int size) {
		int i, j;
		int use = C;
		int tmp = 0;
		for (i = K; i >= 0; i--) // 맨 오른쪽부터 왼쪽으로 오면서
		{
			if (len[i] > size)
				return -1; // 더 자를수 없는 토막이 사이즈보다 크면 -1
			tmp += len[i];
			if (tmp > size) // 나무토막이 사이즈보다 크면
			{
				use--; // 이번 토막 직전에 자르고,
				tmp = len[i]; // tmp값 변경.
			}
			if (use == 0) // 더이상 자를 수 없으면
			{
				if (cut[i] > size)
					return -1; // 남은 토막의 길이와 사이즈비교.
				else
					return cut[i];
			}
		}
		return cut[0]; // 더 자를수 있으면 가장 왼쪽 자름.
	}

}
