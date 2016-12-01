package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 11050
 * 자연수 N과 정수 K가 주어졌을때 이항계수 (N,K)를 구하는 프로그램을 작성하시오
 * 
 * 입력
 * 첫째줄에 N, K가 주어짐(1<=N<=10, 0<=K<=N)
 * 출력 
 * (N,K)를 출력
 * */
/*** 설명
 * 이항계수 : n개의 서로 다른 물건 중에서 순서 없이 K개를 뽑는 조합의 가짓수. 
 *  (n                             (n-1    (n-1
 *   k) = n!/k!(n-k)! = if 0<k<n,   k-1) +   k)
 *                      if k = 0 or k =n, 1
 *                      
 *  by memoization bino(n, k) 
 *  	if k == 0 || n == k
 *  		return 1
 *  	else 
 *  		return bino(n-1,k-1) + bino(n-1,k)
 *  
 * */
public class Binomial {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_11050.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		for (int i = 0; i <= N; i++) {
			int temp = Math.min(i, K);//N이 2인데 K가 4까지 할 필요 없음
			for (int j = 0; j <=temp; j++) {
				if (j == i ||j == 0) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
	}

}


/* 입력 5 2
 * 출력 10
 * */
