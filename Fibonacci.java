package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2747
 * 피보나치 수는 0과 1로 시작. 0번째 피보나치 수 : 0, 1번째 피보나치 수 : 1, 
 * 그 다음 2번째부터는 바로 앞 두 피보나치 수의 합. Fn = Fn-1 + Fn-2(n>=2)
 * n이 17일때 피보나치 수을 써보면, 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597
 * n이 주어졌을때, n번째 피보나치 수를 구하는 프로그램을 작성
 * 첫째줄에n이 주어짐 (n<=45인 자연수)
 * n번째 피보나치 수를 출력 (n이 10일때 피보나치 수는 55)
 * */
/*** 설명
 * memoization 프로그램을 실행할 때 이전에 계산한 값을 메모리에 저장해서 매번 다시 계산하지 않도록 하여 전체적인 실행속도를 빠르게 하는 기술. 동적계획법의 핵심
 * DP
 * 최적값을 구하는 문제. 해당 문제에 여러 해가 있을수 있지만, the 최적해를 구하는 것이 아니라, an 최적해를 구하는것
 * 최장경로 문제는 DP 로 구할수 없음
 * DP에는 부분문제들 사이에 의존적 관계가 존재. 부분 문제들이 연관이 없으면 적용 불가. 모든 부분문제를 한번만 계산하고 결과를 저장하고 재사용
 * 1. 문제를 부분문제로 나눈다.
 * 2. 부분문제의 최적해 값에 기반하여 문제의 최적해 값을 정의
 * 3. 가장 작은 부분 문제부터 해를 구한 뒤 테이블에 저장. 테이블에 저장되어 있는 부분 문제의 해를 이용하여 점차적으로 상위 부분 문제의 최적해를 구함
 * Memo 를 위한 배열을 할당 : 모두 0으로 초기화
 * memo[0] = 0. memo[1] = 1
 * fibo(n)
 *   if n > 2 and memo[n] == 0
 *   		memo[n] ← fibo(n-1) + fibo(n-2)
 *   return memo[n]
 * 
 * 비둘기집의 원리 : n+1개의 물건은 n개의 상자에 넣을 때 적어도 한 상자에는 두 개 이상의 물건이 들어있음.
 * 분할정복 : 연관없는 부분 문제로 분할. 부분문제를 재귀적으로 해결. 부분문제의 해를 결합(combine) .
 * 분할정복은 같은 부분문제가 나타날경우 다시 계산
 * 피보나치 수열
 * */

public class Fibonacci {
	static int[] memo = new int[46];//DP 를 위한 Memoization array. //45보다 작거나 같은 자연수
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2747.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();//구해야하는 n번째
		memo[0] = 0;
		memo[1] = 1;
		
		System.out.println("Dynamic Programming : " + fibonacci(n));
		
		int number = fibonacciByRecursive(n);
		System.out.println("Recursive : " + number);
		


	}
	
	//다이나믹 방법을 이요한 풀이
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
