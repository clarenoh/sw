package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2294
 * n가지 종류의 동전. 각각의 동전이 나타내는 가치는 다르다.
 * 이 동전들을 적당히 사용해서 그 가치의 합이 K원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록하려고 함.
 * (각각의 동전은 몇 개라도 사용가능)
 * 
 * 입력
 * 첫째줄에 n,K (1<=n<=100, 1<=k<=10000)
 * 다음 줄에 n개의 줄에 각각의 동전의 가치가 주어짐.
 * 출력
 * 첫째줄에 사용한 동전의 최소개수를 출력. 불가능한 경우 -1를 출력
 * */
public class CoinExchange {
	static int[] coins;//동전의 가치 수열
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2294.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();//동전의 종류 n
		int K = sc.nextInt();//만들려는 가치의 합
		coins = new int[n + 1];
		
		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}
		

	}

}
