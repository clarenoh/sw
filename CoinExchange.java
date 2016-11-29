package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2294
 * n���� ������ ����. ������ ������ ��Ÿ���� ��ġ�� �ٸ���.
 * �� �������� ������ ����ؼ� �� ��ġ�� ���� K���� �ǵ��� �ϰ� �ʹ�. �׷��鼭 ������ ������ �ּҰ� �ǵ����Ϸ��� ��.
 * (������ ������ �� ���� ��밡��)
 * �־��� ������ ������ �����Ͽ� ��ǥ�ݾ��� ������� �Ҷ�, �ּ��� ���� ������ ���ϴ� ����
 * �Է�
 * ù°�ٿ� n,K (1<=n<=100, 1<=k<=10000)
 * ���� �ٿ� n���� �ٿ� ������ ������ ��ġ�� �־���.
 * ���
 * ù°�ٿ� ����� ������ �ּҰ����� ���. �Ұ����� ��� -1�� ���
 * 
 * */
/*** ����
 * ����ȭ���� : ���� i�� �������� j���� �ٴ� �������� �Ž��� ���� ������, ��ü �Ž������� ������ ������ ���� ����
 * 1���� ���Ե� n���� �������� W���� ������� �Ҷ�, W���� ����� �ּ��� ���� ������ 
 * ���� �� ���� ����� �� �� �ּҰ�
 * - W-1���� ����� �ּ� ���� ���� + 1���� �߰��Ͽ� W���� ����� ���
 * - W-(Ư�������� ��)���� ����� �ּ� �������� + �� Ư�� ������ �ϳ� �߰��Ͽ� W���� ����� ���
 * 
 * DP[j] : j �ݾ��� ����� ���� �ּ� ������
 * dp[1] = 1 , dp[2] = 2, dp[3] = 3, dp[4] = 4,
 * dp[5] = 1 5�� ¥�� ������ �ֱ� ������ 5���� ���鶧�� 5��¥�� ���� �Ѱ��� ����ϴ� ���� ����
 * dp[6] = 2, dp[7] = 3, dp[8] = 4, dp[9] = 5, dp[10] = 2
 * ---> dp ���� �ƽ������� ������ �Ŀ�, ���� dp[j]���� dp[j-coins[i]] + 1 ���� ���ؼ� ���� ���� ����
 * */
public class CoinExchange {
	static int MAX = 10001;
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2294.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();//������ ���� n
		int K = sc.nextInt();//������� ��ġ�� ��
		int[] coins = new int[N];//���� ����
		int[] dp = new int[K + 1];//j �ݾ��� ����� �ִ� �ּ� ������.
		
		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}
		
		//�񱳸� ���� ���ʰ��� �ƽ������� ����
		for (int i = 1; i <= K; i++) {
			dp[i] = MAX;
		}
		
		int temp;
		dp[0] = 0;
		for (int i = 0;i < N; i++) {
			for (int j = coins[i]; j <= K; j++) {
				temp = j - coins[i];
				dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
			}
		}
		
		if(dp[K] == MAX) {
			System.out.println("-1");
		} else {
			System.out.println(dp[K]);
		}

		
		sc.close();
		long end = System.currentTimeMillis();
		System.out.println("##  �ҿ�ð� : " + ( end - start )/1000.0f +"��"); 
	}

}
/*
 * �Է�
 * 3 15
 * 1
 * 5
 * 12
 * ���
 * 3
 * */
