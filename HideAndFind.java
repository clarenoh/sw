package lesson.bfs;

import java.io.FileInputStream;
import java.util.*;

/***
 * 1697
 * 수빈이는 동생과 숨바꼭질. 수진이는 현재점 0<=N<=100,000에 있고
 * 동생은 0<=K<=100,000 
 * 수빈이는 걷거나 순간이동가능. 
 * 걷는다면 -1, +1 순간이동은 1초후에 2*X(현재위치)
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초후인지 구하라
 * 첫번째줄 수빈의 위치 N, 동생의 위치 K
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력 
 **/
/*** 설명
 * 가장 빠른 시간--- BFS(Queue) 탐색
 * 한 vertex에서 다른 vertex로 가는 최소경로를 구할 수 있음
 * 한 번 방문한 곳은 다시 방문하지 않는 것이 좋기 때문에, 따로 배열에 체크하면서 방문
 **/

public class HideAndFind {
	static int MAX = 100000;

	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_1697.txt"));
		Scanner sc = new Scanner(System.in);
		int N, K;
		Queue<Integer> q = new LinkedList<Integer>();
		int[] visited = new int[MAX + 1];//방문여부체크
		int[] distance = new int[MAX + 1];//동생에게 가는 최단거리
		
		N = sc.nextInt();//수빈의 위치
		K = sc.nextInt();//동생의 위치
		
		//수빈이 현재위치 세팅
		q.add(N);
		visited[N]++;
		distance[N] = 0;
		
		while(!q.isEmpty() && distance[K]== 0) {
			int vertex = q.remove();
			//현재 점에서 -1로 이동을 했더니, 그 점이 이동가능한 경로안에 포함되고 방문이력이 X
			if (vertex-1 >= 0 && vertex-1 <= MAX && visited[vertex-1] == 0 ) {
				q.add(vertex-1);//해당 점을 방문
				visited[vertex-1]++;
				distance[vertex-1] = distance[vertex] + 1;
			}
			
			//현재 점에서 +1로 이동을 했더니, 그 점이 이동가능한 경로안에 포함되고 방문이력이 X
			if (vertex+1 >= 0 && vertex+1 <= MAX && visited[vertex+1] == 0 ) {
				q.add(vertex+1);//해당 점을 방문
				visited[vertex+1]++;
				distance[vertex+1] = distance[vertex] + 1;
			}
			
			//현재 점에서 순간이동을 했더니, 그 점이 이동가능한 경로안에 포함되고 방문이력이 X
			if (vertex*2 >= 0 && vertex*2 <= MAX && visited[vertex*2] == 0 ) {
				q.add(vertex*2);//해당 점을 방문
				visited[vertex*2]++;
				distance[vertex*2] = distance[vertex] + 1;
			}
		}
		
		System.out.println(distance[K]);
		sc.close();
	}

}
