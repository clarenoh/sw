package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2748
 * �Ǻ���ġ ���� 0�� 1�� ����. 0��° �Ǻ���ġ �� : 0, 1��° �Ǻ���ġ �� : 1, 
 * �� ���� 2��°���ʹ� �ٷ� �� �� �Ǻ���ġ ���� ��. Fn = Fn-1 + Fn-2(n>=2)
 * n�� 17�϶� �Ǻ���ġ ���� �Ẹ��, 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597
 * n�� �־�������, n��° �Ǻ���ġ ���� ���ϴ� ���α׷��� �ۼ�
 * ù°�ٿ�n�� �־��� (n<=90�� �ڿ���)
 * n��° �Ǻ���ġ ���� ���- 90�϶� �Ǻ���ġ�� Long Ÿ���̱� ������ ������ long ���� ����
 * */

public class Fibonacci_Long {
	static long[] memo = new long[91];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2748.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		memo[0] = 0;
		memo[1] = 1;
		
		long answer = fibonacci(n);
		System.out.println("Dynamic Programming - Long : " + answer);
	}

	private static long fibonacci(int n) {
		if (n == 0 || n == 1) {
			return n;
		} else if (memo[n] != 0) {
			return memo[n];
		} else {
			return memo[n] = fibonacci(n-1) + fibonacci(n-2);
		}
	}

}
