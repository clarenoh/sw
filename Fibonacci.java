package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2747
 * �Ǻ���ġ ���� 0�� 1�� ����. 0��° �Ǻ���ġ �� : 0, 1��° �Ǻ���ġ �� : 1, 
 * �� ���� 2��°���ʹ� �ٷ� �� �� �Ǻ���ġ ���� ��. Fn = Fn-1 + Fn-2(n>=2)
 * n�� 17�϶� �Ǻ���ġ ���� �Ẹ��, 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597
 * n�� �־�������, n��° �Ǻ���ġ ���� ���ϴ� ���α׷��� �ۼ�
 * ù°�ٿ�n�� �־��� (n<=45�� �ڿ���)
 * n��° �Ǻ���ġ ���� ��� (n�� 10�϶� �Ǻ���ġ ���� 55)
 * */
/*** ����
 * memoization ���α׷��� ������ �� ������ ����� ���� �޸𸮿� �����ؼ� �Ź� �ٽ� ������� �ʵ��� �Ͽ� ��ü���� ����ӵ��� ������ �ϴ� ���. ������ȹ���� �ٽ�
 * DP
 * �������� ���ϴ� ����. �ش� ������ ���� �ذ� ������ ������, the �����ظ� ���ϴ� ���� �ƴ϶�, an �����ظ� ���ϴ°�
 * ������ ������ DP �� ���Ҽ� ����
 * DP���� �κй����� ���̿� ������ ���谡 ����. �κ� �������� ������ ������ ���� �Ұ�. ��� �κй����� �ѹ��� ����ϰ� ����� �����ϰ� ����
 * 1. ������ �κй����� ������.
 * 2. �κй����� ������ ���� ����Ͽ� ������ ������ ���� ����
 * 3. ���� ���� �κ� �������� �ظ� ���� �� ���̺� ����. ���̺� ����Ǿ� �ִ� �κ� ������ �ظ� �̿��Ͽ� ���������� ���� �κ� ������ �����ظ� ����
 * Memo �� ���� �迭�� �Ҵ� : ��� 0���� �ʱ�ȭ
 * memo[0] = 0. memo[1] = 1
 * fibo(n)
 *   if n > 2 and memo[n] == 0
 *   		memo[n] �� fibo(n-1) + fibo(n-2)
 *   return memo[n]
 * 
 * ��ѱ����� ���� : n+1���� ������ n���� ���ڿ� ���� �� ��� �� ���ڿ��� �� �� �̻��� ������ �������.
 * �������� : �������� �κ� ������ ����. �κй����� ��������� �ذ�. �κй����� �ظ� ����(combine) .
 * ���������� ���� �κй����� ��Ÿ����� �ٽ� ���
 * �Ǻ���ġ ����
 * */

public class Fibonacci {
	static int[] memo = new int[46];//DP �� ���� Memoization array. //45���� �۰ų� ���� �ڿ���
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2747.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();//���ؾ��ϴ� n��°
		memo[0] = 0;
		memo[1] = 1;
		
		System.out.println("Dynamic Programming : " + fibonacci(n));
		
		int number = fibonacciByRecursive(n);
		System.out.println("Recursive : " + number);
		


	}
	
	//���̳��� ����� �̿��� Ǯ��
	private static int fibonacci(int n) {
		if (n == 0 || n == 1) {
			return n;
		} else if (memo[n] != 0) {
			return memo[n];
		} else {
			return memo[n] = fibonacci(n-1) + fibonacci(n-2);
		}
	}
	
	private static int fibonacciByRecursive(int n) {
		if(n == 0 || n == 1) {
			return n;
		} else {
			return fibonacciByRecursive(n-1) + fibonacciByRecursive(n-2); 
		}
	}


}
