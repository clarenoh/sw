package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2748
 * 피보나치 수는 0과 1로 시작. 0번째 피보나치 수 : 0, 1번째 피보나치 수 : 1, 
 * 그 다음 2번째부터는 바로 앞 두 피보나치 수의 합. Fn = Fn-1 + Fn-2(n>=2)
 * n이 17일때 피보나치 수을 써보면, 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597
 * n이 주어졌을때, n번째 피보나치 수를 구하는 프로그램을 작성
 * 첫째줄에n이 주어짐 (n<=90인 자연수)
 * n번째 피보나치 수를 출력- 90일때 피보나치는 Long 타입이기 때문에 모든것을 long 으로 선언
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
