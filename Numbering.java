package lesson.dfs;

import java.io.FileInputStream;
import java.util.*;
/***
 * 2667
 * 첫째줄에 테스트 케이스. 그 다음줄부터 케이스별로 정사각형의 크기 N,
 * 그 다음줄에는 각각 N개의 자료(0or 1)이 입력
 * 출력 : #testcase번호 총단지수  각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력
 * 1은 집이 있는곳. 0은 집이 없는 곳. 서로 연결된 집들의(좌우, 위아래) 모임을 단지라고 하고
 * 단지에 번호를 붙이려 한다. 
 **/
/*** 설명
 * DFS - Stack을 사용(재귀호출이 이 스택의 역활을 대신함.FILO 먼저 들어간것이 나중에 나온다)
 * 그래프는 G(V,E) vertex(정점)과 edge(간선)으로 이루어짐
 * 사이클 : 처음 정점과 마지막 정점이 같은 경로
 * 스택을 이용해서 갈 수 있는 만큼 최대한 많이 가고 갈 수 없으면 이전 정점으로 돌아감. 
 * graph:모든 점을 인접행렬로 만듬. 
 * visited: 방문여부
 * 모든 점의 방문 여부를 false(또는 0)으로 초기화하고, 모든 점에 대해서 dfs 함수 호출
 * 안봤던 노드이면 주변에 있는 모든 이웃노드를 스택에 넣고, 봤던 노드이면 이미 처리했으므로 continue 로 생략
 * DFS(Graph, Vertex, Edge,visited, 방문순서)
 * --
 *  현재 점을 방문한 것으로 세팅. 만약 방문하지 않았다면 dfs 호출
 * */

public class Numbering {
	static int N;
	static int[] dx = {1, 0, -1, 0};//x가 갈수 있는 값
	static int[] dy = {0, 1, 0, -1};//y가 갈 수 있는 값
	static int[][] graph;// 집들의 그래프
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/sw_advanced/src/testcase/input_2667.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();//테스트케이스
		int[][] visited;//방문여부, 해당 좌표의 값에 단지 번호를 넣음

		
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			graph = new int[N + 1][N + 1];
			visited = new int[N + 1][N + 1];
			
			//그래프값, 방문여부 초기화
			for (int i = 0; i < N; i++) {
				String temp = sc.next();//i에 있는 것을 모두 temp로 
				for (int j = 0; j < N; j++) {
					graph[i][j] = temp.charAt(j) -'0';//String으로 받은것을 CharAt 캐릭터 양식으로 받아들였으므로 -'0'을 해서 int
					visited[i][j] = 0;//방문하지 않음 0으로 초기화
				}
			}
			
			int groupNum = 0;
			//주어진 vertex 에 대해 계산을 하기 시작
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++) {
					if (graph[i][j] == 1 && visited[i][j] == 0) {//현재 점에 집이 있고, 방문한 이력이 없으면
						doDFS(i, j, visited, ++groupNum);//시작점, 방문여부, 현재 단지의 번호를 groupName으
					}
				}
			}
			
			int[] count = new int[groupNum + 1];//최고번호로 count 행렬을 만듬. count[3]: 3group 집의 수가 값이 됨
			//visited[][]는 해당 스팟의 groupNum이 들어가있음
			//이를 추출하기 위해 for문 돌림
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++){
					if (visited[i][j] != 0) {
						int temp = visited[i][j];
						count[temp]++;
					}
				}
			}
			
			Arrays.sort(count);
			System.out.printf("#%d %d\n", t, groupNum);
			for (int i = 1; i <=groupNum; i++) {
				System.out.print(count[i] + " ");
			}
			
			System.out.println();
		}
		sc.close();

	}

	private static void doDFS(int x, int y, int[][] visited, int num) {
		if (visited[x][y] != 0) {//방문한 이력이 있다면 재귀호출을 하지 말고 그냥 return
			return;
		}
		
		//해당 vertex 에서 방문했으므로, visited에 그룹번호를 insert
		visited[x][y] = num;

		//현재 점에서 위아래 좌우로 갈때 계속 가도 되는지 여부를 계산
		for (int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//새로 이동한 점이 0,0 보다는 크거나 같고, N,N 보다는 작을때
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (graph[nx][ny] == 1 && visited[nx][ny] == 0) {//이동점에 집이 있고그 점을 방문하지 않았다면
					doDFS(nx, ny, visited, num);
				}
			}

		}
		
		
	}

}
