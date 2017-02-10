package koitp.day1;

import java.io.FileInputStream;
import java.util.*;

/**
 * Matix Chain Multiplication
 * http://www.koitp.org/problem/MATRIX_CHAIN_MULTIPLICATION
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 * M*N A와 N*K 행렬 B의 곱셈 AB를 생각해보자
 * 두 행렬의 곱셈에서 연산 횟수는 MNK. 행렬은 교환법칙은 성립X, 결합법칙은 O. 
 * 어떤 순서로 곱셈을 하는지에 따라 전체 연산 회수가 차이가 난다.
 * A1 10*100  A2 100*5  A3 5*50
 * (A1A2)A3 = 100*5*10 + 5*50*10 = 7500
 * A1(A2A3) = 5*50*100 + 100*50*10 = 75000
 * n개의 행렬 A1, A2, ~ An. Ai의 크기 : ai*ai+1
 * 이때 A1A2..An을 계산하는데 필요한 연산의 최소 횟수를 구하는 프로그램을 작성
 * 
 * 입력
 * 첫줄에 행렬의 개수 n 2<=n<=500
 * 둘째 줄에 행렬의 크기를 나타내는 자연수 n+1개가 공백으로 구분. -- Scanner 사용해라 
 * 출력
 * A1A2A3...An 을 구하기 위해 필요한 연산의 최소 횟수를 출력
 */
/**
 * Memoization : 동일한 계산을 반복해야할 때, 이전에 계산한 값을 메모리에 저장함으로써 동일한 
 * 계산의 반복 수행을 제거하여 프로그램 실행 속도를 개선시키는 기술
 * memo[i][j]를 계산하려면 memo[i][k], memo[k][j]값이 먼저 계산되어 있어야 함
 */
public class MatrixChainMultiplication {
	static int[][] memo;//AiAi+1...Aj까지 계산하기 위해서 필요한 연산의 최소 횟수
	static boolean[][] visited;//Ai...Aj를 계산했는지 여부
	static int max = Integer.MAX_VALUE;
	static int[] array;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sampleInput/MatixChain.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();//행렬의 개수
			memo = new int[N + 1][N + 1];
			visited = new boolean[N + 1][N + 1];//AiAi+1...Aj를 계산했냐여부
			array = new int[N + 2];

			for (int i = 1; i <= N + 1; i++) {
				array[i] = sc.nextInt();
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					memo[i][j] = max;
				}
			}
			
			//A1*A1 은 불가하므로 0리턴
			for (int i = 1; i <= N; i++) {//memo 초기화
				memo[i][i] = 0;
				visited[i][i] = true;
			}
			
			matixChainOrder(1, N);
			
			System.out.printf("#%d %d\n", tc, memo[1][N]);
			
		}
		
		sc.close();

	}

	//memo[i][j]를 계산하려면 memo[i][k], memo[k][j]값이 먼저 계산되어 있어야 함
	//(AiAi+1.. Ak) * (Ak+1..Aj)를 계산한다면
	//dp[i,k] + dp[k+1, j]+ 오른쪽 곱하기 왼쪽한값.
	private static int matixChainOrder(int start, int end) {
		if(visited[start][end]) return memo[start][end];
		
		visited[start][end] = true;//방문한거이므로 true로 바꿈
		
		for (int i = start; i < end; i++) {
			int temp = matixChainOrder(start, i) + //start부터 i까지 계산한 최소의 연산회수
					   matixChainOrder(i + 1, end) + //i+1부터 end까지 계산한 최소의 연산횟수
					   array[i + 1] * array[end + 1] * array[start];
			memo[start][end] = Math.min(memo[start][end], temp);
		}
		
		return memo[start][end];
	}


}
/*
3
10 100 5 50
출력 : 7500
6
30 35 15 5 10 20 25
출력 : 15125
*/