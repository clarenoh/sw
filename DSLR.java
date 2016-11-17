package lesson.bfs;

import java.io.FileInputStream;
import java.util.*;

/***
 * 9019
 * 네개의 명령어 D,S,L,R 을 이용하는 간단한 계산기
 * 이 계산기에는 레지스터가 하나. 0~100,000 미만의 십진수를 저장
 * 각명령어는 이 레지스터에 저장된 n을 다음과 같이 변환
 * n = ((d1 × 10 + d2) × 10 + d3) × 10 + d4
 * D : n을 두배로. 만약 결과값이 9999보다 큰 경우에는 10000 으로 나눈 나머지
 * 2n mod 10000 을 레지스터에 저장
 * S : n-1(만약 n이 0이라면 9999가 대신 저장) 
 * L : n의 각 자릿수를 왼편으로 회전시켜 저장.d2,d3, d4, d1
 * R : n의 각 자릿수를 오른편으로 회전시켜 저장. d4, d1, d2, d3
 * L, R은 n이 십진자릿수라는 것을 가정하고 연산. 
 * 서로다른 두 정수A,B에 대하여 A를 B로 바꾸는 최소한의 명령어를 생성. 
 * 입력 : A = 1234, B= 3412
 * T개의 테스트 케이스. 
 * 각 테스트케이스로는 두개의 정수가 공백으로 분리. 0<=A,B<=10000, A!=B
 * 출력 : LL RR
 * A에서 B로 변환하기 위해 필요한 최소한의 명령어를 출력 ----> BFS(Queue)
 * n의 자릿수로 0 이 포함된 경우에 주의해야 한다. 
 * 예를 들어서 1000 에 L 을 적용하면 0001 이 되므로 결과는 1 이 된다. 그러나 R 을 적용하면 0100 이 되므로 결과는 100 이 된다.
 **/
public class DSLR {
	static int MAX = 10001;
	
	public static void main(String[] args) throws Exception{

		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_9019.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();//테스트케이스
		int A, B;//시작값, 변환한 결과가 되야하는 값
		int[] visited; //해당 점을 방문했는지 여부
		int[] distance;//해당 인덱스까지 올때 계산한 거리
		char[] how;//해당index까지 연산을 어떻게 해서 갔는지를 저장
		int[] from;//해당 index 에 오기 전에 어디에서 왔는지 

		
		for (int tc = 1; tc <= T; tc++) {
			A = sc.nextInt();
			B = sc.nextInt();
			Queue<Integer> q = new LinkedList<Integer>();//
			visited = new int[MAX];
			how = new char[MAX];
			from = new int[MAX];
			distance = new int[MAX];
			
			q.add(A);//시작점 세팅
			visited[A]++;//방문여부
			distance[A] = 0;//시작점이므로 거기는 0
			
			int next;
			while(!q.isEmpty()) {//queue 에 아무것도 남지 않을때까지 계싼
				int now = q.remove();
				//D를 했을 경우
				next = (2*now)%10000;
				if (visited[next] == 0){
					q.add(next);
					visited[next]++;
					from[next] = now;
					how[next] ='D';
					distance[next] = distance[now] + 1;
				}
				//S를 했을 경우
				next = now - 1;
				if (next == 0) {
					next = 9999;
				}
				if (next >=0 && visited[next] == 0 ){
					q.add(next);
					visited[next]++;
					from[next] = now;
					how[next] ='S';
					distance[next] = distance[now] + 1;
				}
				//L를 했을 경우
				next = (now%1000)*10 + now/1000;
				if (visited[next] == 0){
					q.add(next);
					visited[next]++;
					from[next] = now;
					how[next] ='L';
					distance[next] = distance[now] + 1;
				}
				//R를 했을 경우
				next = (now%10)*1000+ now/10;
				if (visited[next] == 0){
					q.add(next);
					visited[next]++;
					from[next] = now;
					how[next] ='R';
					distance[next] = distance[now] + 1;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			while(A != B) {
				sb.append(how[B]);
				B = from[B];
			}
			System.out.println(sb);
		}
	}

}
