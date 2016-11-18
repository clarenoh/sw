package lesson.bfs;

import java.io.FileInputStream;
import java.util.*;

/***
 * 1261
 * N*M 미로에 같임. 1*1 의 방들로 이루어짐. 미로는 빈방 또는 벽. 빈방은 자유롭게 갈수 있지만, 
 * 벽은 부수지 않으면 이동할 수 없다. 
 * 알고스팟 운영진은 항상 모두 같은 방에 있어야함. 
 * 어떤 방에서 이동할 수 있는 방은 상하좌우로 인접한 빈방.미로의 밖으로는 나갈 수 없음
 * AOJ라는 무기를 이용해 벽을 부수어버릴수 있음. 
 * 벽을 부수면 빈방과 동일한 방으로 변신.
 * 현재 (1,1)에 있는 운영진이 (N,M)으로 이동하려면 벽을 최소 몇개 부수어야 하는지 구하는 프로그램
 * ---> BFS
 * 
 * 입력 : 첫째 줄에 미로의 크기를 나타내틑 M, N 1<=N,M<=100
 * 다음에는 미로의 상태를 나타내는 0(빈방), 1(벽)
 * (1, 1)과 (N, M)은 항상 뚫려있다.
 * 출력 : 첫째줄에 (N,M)으로 이동하기 위해 벽을 최소 몇개 부수어야하는지 출력 
**/
/*** 설명
 * 	int graph[i][j] = char arr[j-1] - '0';
 *  char arrr 의 값을 int araay로 넣을라면 -'0'을 해서 int형으로 넣어준다
 *  
 * Queue que : 먼저 들어간것이 먼저 나옴. LinkedList사용
 * que.offer : 뒤에 값을 추가
 * 1. 바로 갈 수 있는 곳은 바로 간다.
 * 2. 더 이상 갈 수 없으면 벽을 부순다.
 * 3. 벽을 부순 후 갈 수 있는 만큼 이동한다.
 * */


public class AlgoSpot {
	public static int[] dx = {1, -1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_1261.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int tc = 1; tc <= T; tc++){
            int M = sc.nextInt();//Y
            int N = sc.nextInt();//X
            int[][] graph = new int[N][M];//주어진 미로를 그래프로 표기
            int[][] breakNum = new int[N][M];//해당 스팟에 오기 까지 벽을 부순 횟수
            //그래프 초기화
            for (int i = 0; i < N; i++) {
            	String s = sc.next();
            	char[] arr = new char[M];
            	arr = s.toCharArray();
            	for (int j = 0; j < M; j++){
            		graph[i][j] = arr[j] - '0';//arr 가 char 어레이여서 추출한 다음에 -'0'을 해서 int 형으로 만듬
            		breakNum[i][j] = -1;//0으로 되면 부순 횟수는 0이지만 방문은 했었다.
            	}
            }
            
            //queue 를 두개 만든다. 최초는 벽을 부시지 않고 가는거+한번 부시는거, 
            //두번째는 한번 부시는거 + 두번부시는거... 이걸 계속해서 목적지 (N,M)에 도달하면 끝
            Queue<xyPair> que = new LinkedList<xyPair>();
            Queue<xyPair> nextQue = new LinkedList<xyPair>();
            que.add(new xyPair(0,0)); //que에 시작점 세팅
            breakNum[0][0] = 0;//시작점이므로 방문했다 표기하고, 부술 필요 없으므로 0으로 세팅
            while(!que.isEmpty()) {
            	xyPair pair = que.remove();
            	int x = pair.x; //현재점의 x좌표, y좌표
            	int y = pair.y;
            	
            	for (int i = 0; i < 4; i++) {
            		int nx = x + dx[i];
            		int ny = y + dy[i];    
            		if (nx >=0 && ny >= 0 && nx < N && ny < M) {
            			if(breakNum[nx][ny] == -1){//방문을 하지 않았었다면, 이동하는 칸에 벽이 있는지 없는지 확인할 필요가 있음
                			if (graph[nx][ny] == 0) {//다음번 칸이 0이라면 빈방
                				breakNum[nx][ny] = breakNum[x][y];//nx,ny에서 부술 필요가 없기 때문에 횟수는 그 전꺼와 동일
                				que.add(new xyPair(nx, ny));
                			} else {//다음번 칸이 벽이 있는 방이라면
                				breakNum[nx][ny] = breakNum[x][y] + 1;//그전까지 부순 회수에다 한번을 더 부셔야한다
                				nextQue.add(new xyPair(nx,ny));
                			}
            			}

            		}
            	}
            	
            	if(que.isEmpty()){//처음 큐를 다 조사했다면, 1번 부셔야하는 큐를 조사하기 시작
            		que = nextQue;
            		nextQue = new LinkedList<xyPair>();//nextQue 초기화
            	}
            }

            
            System.out.println("#" + tc + ": "  + breakNum[N-1][M-1]);

        }
        sc.close();
        
	}

	
}


//Queue 를 <xyPair>양식으로 받기 위해서 xyPair라는 클래스 생성
class xyPair {
	int x, y;
	xyPair(int a, int b) {
		this.x = a;
		this.y = b;
	}
}


/*
3 3
011
111
110
출력 : 3
4 2
0001
1000
출력 : 0
6 6
001111
010000
001111
110001
011010
100010
출력 : 2
*/

