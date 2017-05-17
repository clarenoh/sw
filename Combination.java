package example01;

/**
 * https://www.acmicpc.net/problem/2004
 * n! = n*(n-1)...*2*1
 * ���⼭ 2*5�� 10�� �ǹǷ� 2�� 5�߿� ���� ������ 0�� ������ �ȴ�
 * nCm = n!/(n-m)!*m!
 * �̹Ƿ� n!�� 0�� ���� - (n-m)�� 0�� ���� + m!�� 0�� ����)
 * 
*/

import java.io.FileInputStream;
import java.util.Scanner;

//n�� m�� �־�����. (5 �� n �� 100, 5 �� m �� 100, m �� n)
public class Combination {
	public static long[] arrTwo = new long[3];
	public static long[] arrFive = new long[3];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:/Users/SDS/Desktop/sample.txt"));
		Scanner sc = new Scanner(System.in);
		
		long n, m;//�Է¹޴� ��
		n = sc.nextLong();
		m = sc.nextLong();
		
		calculate(n, 0);//n!���� 2,5 �� ���� ����ؼ� 0ĭ�� ����
		calculate(m, 1);//m!���� 2,5�� ���� ����ؼ� 1ĭ�� ����
		calculate(n - m, 2);
		
		long a, b, c = 0;
	/*	a = Math.min(arrTwo[0], arrFive[0]);
		b = Math.min(arrTwo[1], arrFive[1]);
		c = Math.min(arrTwo[2], arrFive[2]);*/
		a = Math.min(arrTwo[0] - arrTwo[1] - arrTwo[2], arrFive[0] - arrFive[1] - arrFive[2]);
		
		System.out.println(a);
		
	}

	private static void calculate(long num, int flag) {
		long two = 0;
		long five = 0;

		
		for (long i = 2; i <= num; i *= 2) {
			two += num / i;
		}
		
		for (long i = 5; i <= num; i *= 5) {
			five += num / i;
		}
		
		arrTwo[flag] = two;
		arrFive[flag] = five;
		
	}

}
