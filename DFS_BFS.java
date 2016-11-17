package lesson.dfs;

import java.io.FileInputStream;
import java.util.*;


/***
 * 1260
 * 그래프를 DFS로 탐색한 결과, BFS로 탐색한 결과를 출력하는 프로그램 작성
 * 방문할 수 있는 정점이 여러개인 경우에는 정점 번호가 작은것을 먼저방문하고
 * 더 이상 방문할수 있는 점이 없는 경우 종료
 * 첫째줄에 정점의 개수 N(1≤N≤1,000)
 * 간선의 개수 M(1≤M≤10,000), 탐색을 시작할 정점의 번호 V
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다
 * 입력으로 주어지는 간선은 양방향
 * 출력 : 첫번째 줄에 DFS, 그 다음줄에는 BFS를 수행한 결과를 보여줌
 **/
/*** 설명
 * 너비우선탐색(BFS) 바로 빝 자손들을 전부 훓고 난 다음에 자손을 탐색
 * 한 vertex에서 다른 vertex로 가는 최소경로를 구할 수 있음
 * 출발점을 기준으로 연결된 모든 vertex를 큐에 넣고 큐에 있는 것을 하나씩 뽑아가면서 
 * 큐가 empty 가 될때까지 진행
 * vertex의 상태 : 방문하기 전, 방문해서 자손을 훓는 중, 방문완료(자손들도 탐색 완료)
 * 출발점에서 해당 vertex까지의 도달거리, 현재 vertex 의 선조 vertex
 * 
 * ArrayList<Integer> arr = new ArrayList<Integer>(); //arr의 원소가 integer임
 * Arrays.sort(arr);//정렬
 * 
 * ArrayList<Integer>[] array = new ArrayList[N];
 * for (int i = 0; i <= N; i++) {
 *		array[i] = new ArrayList<Integer>();
 * }
 * 
 * for (int i = 0; i <= N; i++) {
 * 		Collections.sort(array[i]); //array[i]의 값들을 정렬
 * }
 **/
public class DFS_BFS {
	static int[] visited;//방문여부
	static int N;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_1260.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();//정점의 개수
		int M = sc.nextInt();//edge 개수
		int V = sc.nextInt();//시작점
		visited = new int[N + 1];
		ArrayList<Integer>[] movePath = new ArrayList[N + 1];//방문할 수 있는 이동경로
		
		for (int i = 0; i <= N; i++) {
			movePath[i] = new ArrayList<Integer>();
		}

		//movePath 초기화
		for (int i = 1; i <= M; i++){
			int x = sc.nextInt();//시작점
			int y = sc.nextInt();//도착점
			movePath[x].add(y);
			movePath[y].add(x);
		}
		
		for (int i = 1; i <=N; i++) {
			Collections.sort(movePath[i]);
		}
		
		initializeArray(visited);
		System.out.print("DFS result : ");
		doDFS(V, visited, movePath);//DFS로 계산 시작점, 방문여부, 방문할 수 있는 경로들
		
		initializeArray(visited);
		System.out.println();
		System.out.print("BFS result : ");
		doBFS(V, visited, movePath);

	}
	


	private static void initializeArray(int[] arr) {
		for (int i = 0; i < N + 1; i++) {
			arr[i] = 0;
		}
	}

	private static void doDFS(int x, int[] visited, ArrayList<Integer>[] movePath) {
		if (visited[x] != 0){
			return;
		}
		
		visited[x]++;
		System.out.print(x + " ");
		
		for (int y : movePath[x]) {//x가 갈수있는 경로들의 집합에 대해, 그 값들로 for문
			if (visited[y] == 0) {
				doDFS(y, visited, movePath);
			}
			
		}	
	}
	

	private static void doBFS(int x, int[] visited,  ArrayList<Integer>[] movePath) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(x);
		visited[x]++;
		
		while(!que.isEmpty()) {
			int result = que.remove();
			System.out.print(result +" ");
			for (int y : movePath[result]){
				if (visited[y] == 0){
					que.add(y);
					visited[y]++;//방문여부 체크
				}
			}
		}
	}


} 

/*
 * 입력
4 5 1
1 2
1 3
1 4
2 4
3 4
 * 출력
1 2 4 3
1 2 3 4
 * */
