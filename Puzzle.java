package lesson.bfs;

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
/*** 설명
 * HashMap : key와 value 를 쌍으로 입력받음
 * 0을 이동했을때, 새로 생성된 3*3 행렬을 array로 저장해놓을 수가 없음.
 * ---> 순열로 생각해서 푼다.. 0이 있으면 초기값과 헷갈리므로, 9로 변환
 * int now 에 대해 String temp = Integer.toString(now);
 * int nine = temp.indexOf('9');
 * 
 * int next = Integer.parseInt(sb.toString());
 * String s.toCharArray() -- String 을 Char array 로 리턴
 * ArrayList arr = new ArrayList(); -- 이렇게 하면 arr에 collection 을더할 수 있음
 * StringBuilder 는 동기화를 제공하지 않음
 * StringBuilder sb = new StringBuilder(초기문자열. 비워도 상관없음);
 * sb.setCharAt(integer, char); index 가 i인 곳에 char로 세팅해라
 * hashMap hm.containsKey(int);
 * 
 * */
public class Puzzle {

	public static final int[] dx = {1, -1, 0, 0};//한 좌표를 움직일수 있는 x방향값들
	public static final int[] dy = {0, 0, 1, -1};//한 좌표를 움직일수 있는 y방향값들
	static int N = 3;
	
	public static void main(String[] args) throws Exception{
		//int start = (int) System.currentTimeMillis();
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_1525.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();//테스트 케이스 개수
		for (int tc = 1; tc <= T; tc++) {
			//빈칸을 옮겨서 얻은 수열들의 집합. <수열을 숫자로 저장, 그 수열까지 오는 이동횟수>
			HashMap<Integer, Integer> hmVisited = new HashMap<Integer, Integer>();
			Queue<Integer> que = new LinkedList<Integer>();
			int nowNum = 0;//현재 숫자

			//주어진 3*3을 순열로 세팅
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int temp = sc.nextInt();
					if (temp == 0) temp = 9;//만약 0이라면 9로 치환
					nowNum = nowNum * 10 + temp; //이전의 수에다 10을 곱해서 현재나온 수를 더하면 지금까지 수의 행렬을 구할수 있음
				}
			}
			
			hmVisited.put(nowNum, 0);//최초 graph는 이동횟수 0
			que.add(nowNum);//q에다가 add
			
			while(!que.isEmpty()) {
				nowNum = que.remove();
				int nx = 0;//x,y 가 좌우 양옆으로 이동한 점의 좌표
				int ny = 0;
				
				String sNow = Integer.toString(nowNum);//숫자를 문자로 치환해서 9의 인덱스를 찾는다.
				int idxStart = sNow.indexOf('9');//9가 위치하고 있는 index
				//int nine = arr.
				//int 를 String 으로 만들어서 9의 index를 알아내고 그걸 다시 int 로
				//int nine = Integer.toString(nowSerise).indexOf('9');
				
				//순열으로 나열했을때 nine 의 위치를 x,y 좌표로 표시 -> 이동점 nx,ny 를 구함 -> nx,ny 에 대한 newlyindex 를 구함
				int x = idxStart / 3; //만약 9의 indexof 가 5라면 (x,y) = (1,2)
				int y = idxStart % 3; //idx 가 주어지면 x,y idx/3, idx%3
									 //x, y 가 주어지면 x*3 + y가 idx

				for (int i = 0; i < 4; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
						StringBuilder sb = new StringBuilder(sNow);
						char newChar = sb.charAt(nx*3 + ny);//new에 있는 문자
						char nowChar = sb.charAt(x*3 + y);//현재 이동을 시작하는 점
						sb.setCharAt(nx*3 + ny, nowChar);
						sb.setCharAt(x*3 + y, newChar);
						
						int next = Integer.parseInt(sb.toString());
						
						if(!hmVisited.containsKey(next)) {
							que.add(next);
							hmVisited.put(next,  hmVisited.get(nowNum) + 1);
						}

					}
				}
			}
			if (hmVisited.containsKey(123456789)) {
				System.out.printf("#%d %d\n", tc,hmVisited.get(123456789));
			}else {
				System.out.println("-1");
			}



		}
		sc.close();
		//int end = (int) System.currentTimeMillis();
		//System.out.println("time : " + Integer.toString(end-start));	
	}

	
}

/*
입력
0 1 3
4 2 5
7 8 6
출력 : 4
1 0 3
4 2 5
7 8 6
출력 : 3
 입력
1 2 3
4 0 5
7 8 6
출력 2

#1 3
#2 2
#3 4
*/