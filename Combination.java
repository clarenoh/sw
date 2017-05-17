package example01;

/**
 * https://www.acmicpc.net/problem/2004
 * n! = n*(n-1)...*2*1
 * 여기서 2*5가 10이 되므로 2와 5중에 작은 개수가 0의 개수가 된다
 * nCm = n!/(n-m)!*m!
 * 이므로 n!의 0의 개수 - (n-m)의 0의 개수 + m!의 0의 개수)
 * 
*/

import java.io.FileInputStream;
import java.util.Scanner;

//n과 m이 주어진다. (5 ≤ n ≤ 100, 5 ≤ m ≤ 100, m ≤ n)
public class Combination {
	public static long[] arrTwo = new long[3];
	public static long[] arrFive = new long[3];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:/Users/SDS/Desktop/sample.txt"));
		Scanner sc = new Scanner(System.in);
		
		long n, m;//입력받는 수
		n = sc.nextLong();
		m = sc.nextLong();
		
		calculate(n, 0);//n!에서 2,5 의 개수 계산해서 0칸에 넣음
		calculate(m, 1);//m!에서 2,5의 개수 계산해서 1칸에 넣음
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
