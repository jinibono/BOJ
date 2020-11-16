package MATH;
import java.util.*;
import java.io.*;

public class boj_1016_제곱ㄴㄴ수_에라토테네스의체 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		int result = (int) (max - min + 1);
		int sqrt = ((int) Math.sqrt(max));
		boolean[] checks = new boolean[result];
		for (long i = 2; i <= sqrt; i++) {
			long squared = i * i;
			long start = min % squared == 0 ? min / squared : (min / squared) + 1;
			for (long j = start; j * squared <= max; j++) { // 몫을 1씩 증가시킴( j가 몫 )
				checks[(int) ((j * squared) - min)] = true;
			}
		}

		// 제곱ㄴㄴ수 개수 counting
		int count = 0;
		for (int i = 0; i < result; i++) {
			if (!checks[i])
				count++;
		}
		System.out.println(count);

	}

}
