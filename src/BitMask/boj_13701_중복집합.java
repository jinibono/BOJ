package BitMask;

import java.util.*;
import java.io.*;

public class boj_13701_중복집합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BitSet b = new BitSet();
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			int m = Integer.parseInt(st.nextToken());
			if (b.get(m))
				continue;
			b.set(m);
			sb.append(m + " ");
		}
		System.out.print(sb.toString());

	}

}
