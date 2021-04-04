package RecurSive;

import java.util.*;
import java.io.*;

public class boj_2630_색종이만들기 {
	static int N;
	static StringTokenizer st;
	static int[][] arr;
	static boolean[][] checked;
	static int blueresult = 0;
	static int whiteresult = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		checked = new boolean[N][N];
		int temp = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int size = N;
		while (size != 0) {
			for (int i = 0; i < N; i += size) {
				for (int j = 0; j < N; j += size) {
					int zerocount = 0;
					int onecount = 0;
					for (int x = i; x < i + size; x++) {
						for (int y = j; y < j + size; y++) {
							if (checked[x][y])
								break;
							if (arr[x][y] == 1)
								onecount++;
							else
								zerocount++;
						}
					}
					if (zerocount == size * size) {
						whiteresult++;
						for (int a = i; a < i + size; a++) {
							for (int b = j; b < j + size; b++) {
								checked[a][b] = true;
							}
						}
					}
					if (onecount == size * size) {
						blueresult++;
						for (int a = i; a < i + size; a++) {
							for (int b = j; b < j + size; b++) {
								checked[a][b] = true;
							}
						}
					}

				}
			}
//			System.out.println(whiteresult+" "+blueresult);
			size /= 2;
		}

		System.out.println(whiteresult);
		System.out.println(blueresult);

	}

}
