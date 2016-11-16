package lesson.dfs;

import java.io.FileInputStream;
import java.util.*;
/**
 * 2331 반복수열
 * D[1] = A
 * D[n] = D[n-1]의 각 자리의 숫자를 P번 곱한 수들의 합
 * 예를 들어 A=57, P=2 일때 수열 D = {57, 74(5*5+2*2), 65, 61, 37, 58, 89, 145, 42,20, 4, 16, 37..}
 * 그뒤에서는 앞에 나온 수들이 반복된다. 이런 반복 수열에서 반복되는 부분을 
 * 제외했을때 수열에 남게되는 수들의 개수를 구하는 프로그램을 작성하시오
 * A(1≤A≤9999), P(1≤P≤5)
 **/
/**
 * 설명
 * Math.pow(n, p) --n의 p승
 */
public class Series {
	static int[] graph;//수의 배열
	static int[] visited;//해당 수를 방문했는지 여부
	static int MAX = 1000000; //가장 큰 자리수는 9999의 각 자리의 5승의 합 : 295245
	static int P;
	static int ans;//반복이 시작되는 seq

	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_2331.txt"));
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		P = sc.nextInt();
		int sequence = 1;
		graph = new int[MAX];
		visited = new int[MAX];
		graph[sequence] = A;
		
		//int next = getNextNum(A, P);
		doDFS(A, graph, visited, sequence);
		


		for (int i = 1; i < ans; i++) {//중복이 시작되는 전까지 출력
			System.out.print(graph[i] + " ");
		}
		sc.close();
	}

	private static void doDFS(int pre, int[] graph, int[] visited, int seq) {
		if (visited[pre] != 0) {//같은 숫자가 나오기 시작	
			return;
		}

		visited[pre] = seq;//방문한 숫자에 해당하는 array 에 몇번째 숫자인지 seq 를 넣음
		int next = getNextNum(pre, P);

		if (visited[next] == 0) {
			seq++;
			graph[seq] = next;
			doDFS(next, graph, visited, seq);
		} else {
			ans = visited[next];
		}
		
		
	}

	private static int getNextNum(int a, int p) {
		int result = 0;
		
		while(a > 0){
			int temp = a % 10;//맨 오른쪽 자리부터 계산하기 시작
			result += Math.pow(temp, p);
			a /= 10;//계산한 숫자는 소수점으로 만들어서 위에서 a%10을 하면 없어지게 됨
		}
		return result;
	}

}

/**
 * 입력
 * 88 4
 * 출력
 * 88 8192	10674	3954	7523	3123	179	8963	12034	354	962	7873	8979	19619	14420	529	7202	2433	434	593	7267	6114	1554	1507	3027	2498	10929	13139
 * 입력 
 * 57 2
 * 출력
 * 57 74 65 61 
 * 222 3
 * output-222 24 72 351 
 * 7777 4
 * output-7777 9604 8113 
 * 9496 5
 * ouput- 
 */
