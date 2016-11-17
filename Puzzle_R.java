package review;

import java.io.FileInputStream;
import java.util.*;

/***
 * 1525
 * 3*3 표에 오른쪽 아래 가장 끝칸은 비어 있는 칸으로 아래와 같이 만들려고 한다
 * 1 2 3
 * 4 5 6
 * 7 8 
 * 어떤 수와 인접해있는 네 개의 칸 중에 하나가 비어있으면 수를 그 칸으로 이동시킬수 있다.
 * 표 바깥으로 나가는 건 X 
 * 입력 : 세줄에 걸쳐서 표에 채워져 있는 아홉개의 숫자가 주어짐. 빈칸은 0
 * 첫째 줄에 테스트 케이스 개수. 그 다음줄 부터 3*3 행렬의 숫자가 주어짐
 * 최소의 이동 횟수를 출력한다. 이동이 불가능한 경우 -1을 출력한다.
 * 이와 같이 최소 이동 횟수를 구하는 프로그램을 작성하시오.---> BFS
 * 
**/
public class Puzzle_R {
	static int N = 3;
	public static int[] dx = {1, -1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_1525.txt"));
		Scanner sc = new Scanner(System.in);
		char move = '9';
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			//필요한 인자들 생성
			Queue<Integer> que = new LinkedList<Integer>();
			int x, y, nx, ny;//원래점, 이동할 점
			int idxNine, idxNew;//9의 idx, 9가 이동하는 좌표의 새로운 idx
			HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();//만들어진 수열과 최초부터 거기까지의 거리
			
			//원래 주어진 puzzle을 숫자로 만듬 -- 숫자로 만들고 난 후에 String 으로 해서 0(9)의 idx를 알아냄
			int puzzNum = 0;
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					int temp = sc.nextInt();
					if (temp == 0) temp = 9;
					puzzNum = puzzNum * 10 + temp; //기존에 주어질 graph를 숫자로 만듬
				}
			}
			hm.put(puzzNum, 0);
			que.add(puzzNum);
		
			
			while(!que.isEmpty()) {
				int q = que.remove();

				String temp = Integer.toString(q);
				idxNine = temp.indexOf('9');
				x = idxNine / 3;
				y = idxNine % 3;

				for (int i = 0;i < N; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (nx >= 0 && ny >= 00 && nx < N && ny < N) {
							String puzzStr = Integer.toString(q);
							idxNew = nx*3 + ny;	
							StringBuilder sb = new StringBuilder();
							sb.append(puzzStr);
							char transfered = sb.charAt(idxNew);
							sb.setCharAt(idxNine, transfered);
							sb.setCharAt(idxNew, move);
							
							int puzzNumNew = Integer.parseInt(sb.toString());

							if(!hm.containsKey(puzzNumNew)) {
								que.add(puzzNumNew);
								hm.put(puzzNumNew, hm.get(q)+ 1);
							}
						}
				}

			}
			if (!hm.containsKey(123456789)) {
				System.out.println("-1");
			} else {
				System.out.printf("#%d %d", tc, hm.get(123456789));
				System.out.println();
			}
			
			
		}
		sc.close();
	}

}
