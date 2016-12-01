package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 11050
 * �ڿ��� N�� ���� K�� �־������� ���װ�� (N,K)�� ���ϴ� ���α׷��� �ۼ��Ͻÿ�
 * 
 * �Է�
 * ù°�ٿ� N, K�� �־���(1<=N<=10, 0<=K<=N)
 * ��� 
 * (N,K)�� ���
 * */
/*** ����
 * ���װ�� : n���� ���� �ٸ� ���� �߿��� ���� ���� K���� �̴� ������ ������. 
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
			int temp = Math.min(i, K);//N�� 2�ε� K�� 4���� �� �ʿ� ����
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


/* �Է� 5 2
 * ��� 10
 * */
