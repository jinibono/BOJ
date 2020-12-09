package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_20056_마법사상어와파이어볼 {
	static int N, M, K;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class ball {
		int x, y, m, s, d;

		public ball(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		public ball() {
			super();
			// TODO Auto-generated constructor stub
		}

		public void move() {

			this.x = (this.x + dr[d] * this.s) % N;
			this.y = (this.y + dc[d] * this.s) % N;
			//만약에 -7 N=5인경우
			if(this.x<0)
			{
				this.x+=(((this.x*-1)/N)+1)*N;
			}
			if(this.y<0)
			{
				this.y+=(((this.y*-1)/N)+1)*N;
			}


		}

		@Override
		public String toString() {
			return "ball [x=" + x + ", y=" + y + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<ball> list = new ArrayList<>();
		ArrayList<ball> copylist = new ArrayList<>();
		int r, c, m, s, d;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			list.add(new ball(r, c, m, s, d));
		}
		int ac = 0;
		int bc = 0;
		int cx = 0;
		int cy = 0;
		int cd = -1;
		int msum = 0;
		int ssum = 0;
		int size = 0;
		while (K > 0) {
			copylist.clear();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).move();

			}

			Collections.sort(list, new Comparator<ball>() {

				@Override
				public int compare(ball o1, ball o2) {
					if (o1.x == o2.x)
						return o1.y - o2.y;
					return o1.x - o2.x;
				}
			});

			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i).x == list.get(i + 1).x && list.get(i).y == list.get(i + 1).y) {
					// 다음 애랑 같네?! 2번 조건 만족!
					size = 0;
					ac = 0;
					bc = 0; // 홀수 짝수 갯수를 세기 위해 각각 0으로 다시 초기화
					msum = 0; // 질량의 합을 0으로 초기화
					ssum = 0; // ...
					cx = list.get(i).x;
					cy = list.get(i).y; // 기준점을 잡아둔다! 특히나 2번조건을 만족해서 현재 상태는 삭제하기 때문에 미리 저장해둠
					for (int j = i; j < list.size(); j++) {
						ball ball = list.get(j);
						if (ball.x == cx && ball.y == cy) {
							// 좌표가 같으니까 넣을거야

							msum += ball.m; // 질량 계산해주고~
							ssum += ball.s;
							if (ball.d % 2 == 0)
								bc++;
							else
								ac++; // 짝 홀 갯수 세줄거
							size++; // ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수) size도 세자 ㅎㅎ
							list.remove(j--);

						} else
							break;
					}
					i--;

					// 자 ! 2번조건에 필요한 정보 ac,bc size를 다 구했다 깔깔
					m = msum / 5;
					if (m == 0)
						continue; // 2.4번조건
					s = ssum / size;
					// 기준 방향을 잡을것이다
					if ((ac > 0 && bc == 0) || (ac == 0 && bc > 0)) {
						// 모두 홀수이거나 모두 짝수이면
						cd = 0; // 기준방향 0
					} else {
						cd = 1;
					}

					for (int idx = 0; idx < 4; idx++) {
						copylist.add(new ball(cx, cy, m, s, cd + (idx * 2)));
					}

				}

			}
			list.addAll(copylist);
			// 이동 후 정렬이 다 되어있음

			K--;
		}
		// K번 연산완료
		long sum = 0l;
		for (int i = 0; i < list.size(); i++)
			sum += list.get(i).m;

		System.out.println(sum);

	}

}
