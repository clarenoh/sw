package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2294
 * n���� ������ ����. ������ ������ ��Ÿ���� ��ġ�� �ٸ���.
 * �� �������� ������ ����ؼ� �� ��ġ�� ���� K���� �ǵ��� �ϰ� �ʹ�. �׷��鼭 ������ ������ �ּҰ� �ǵ����Ϸ��� ��.
 * (������ ������ �� ���� ��밡��)
 * 
 * �Է�
 * ù°�ٿ� n,K (1<=n<=100, 1<=k<=10000)
 * ���� �ٿ� n���� �ٿ� ������ ������ ��ġ�� �־���.
 * ���
 * ù°�ٿ� ����� ������ �ּҰ����� ���. �Ұ����� ��� -1�� ���
 * */
public class CoinExchange {
	static int[] coins;//������ ��ġ ����
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2294.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();//������ ���� n
		int K = sc.nextInt();//������� ��ġ�� ��
		coins = new int[n + 1];
		
		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}
		

	}

}
