package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2293
 * n���� ������ ����. ������ ������ ��Ÿ���� ��ġ�� �ٸ���.
 * �� �������� ������ ����ؼ� �� ��ġ�� ���� K���� �ǵ��� �ϰ� �ʹ�. �� ����� ���� ���Ͻÿ�
 * (������ ������ �� ���� ��밡��)
 * 2294�� K���� �Ǵ� �ּ��� ��������
 * �Է�
 * ù°�ٿ� n,K (1<=n<=100, 1<=k<=10000)
 * ���� �ٿ� n���� �ٿ� ������ ������ ��ġ�� �־���.
 * ���
 * ù°�ٿ� ����� ���� ���. ����� ���� 2^31���� �۴�.
 * 
 * dp[0]=1 dp[1]=1 dp[2]=2(1*2, 2) dp[3]=2(1*3, 2+1) dp[4]=3(1*4, 2*2,1*2+2)
 * dp[5]=4(1*5, 1*3+2, 1+2*2, 5) dp[6]5(1*6, 1*4+2, 1*2+2*2, 1+5, 2*3)
 * dp[7]=6(1*7, 1*5+2, 1*3+2*2,1*2+5, 1+2*3, 2+5)
 * dp[n]=0(1*n,
 * */

/*** ����
 * 2293 vs. 2294
 * 2293 : n���� ������ K�� ���� �� �ִ� ����� ���� ����
 * dp[0] = 1; 
 * for (int i = 1; i <=N; i++){
 * 	for (int j = 1; j <= K; j++) {
 * 		if(coins[i] <= j) {
 * 			dp[j] += dp[j-coins[i]];
 * 		}
 * 	}
 * }
 * 2294 : n���� ������ K�� ���鶧 �ּ��� �������� ������� ��. �� �ּ��� ��������. ����� ���� ��쿡�� -1���
 * dp[0] = 0 dp[k] = MAX�μ����س��� ����� �� ��������, dp[K] = MAX��� ����� ���� ����̹Ƿ�, 
 * for (int i = 1; i <=N; i++){
 * 	for (int j = coins[i]; j <= K; j++) {
 * 			dp[j] = Math.min(dp[j-coins[i]] + 1, dp[j]);
 * 	}
 * }
 * */
class Coin {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2293.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n, k;
		int[] coins;
		int[] dp;//k���� ����� ����� ��
		
		n = sc.nextInt();//������ ����
		k = sc.nextInt();//������� �ϴ� ��
		coins = new int[n];
		dp = new int[k+1];
		
		for (int i = 0; i < n; i++) {//�־��� ������ ������ array�� 
			coins[i] = sc.nextInt();
		}
		
		dp[0] = 1;//dp[0]�� 0���� �����ϸ� ���� ���ü��� ����
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (coins[i] <= j) {//������� �ݾ�j���� ������ �۰ų� ���ƾ� ��
					dp[j] += dp[j-coins[i]];
				}
			}
		}
		
		System.out.println(dp[k]);
		
	}

}
