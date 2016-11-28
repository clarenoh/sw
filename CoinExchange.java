package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2294
 * n가지 종류의 동전. 각각의 동전이 나타내는 가치는 다르다.
 * 이 동전들을 적당히 사용해서 그 가치의 합이 K원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록하려고 함.
 * (각각의 동전은 몇 개라도 사용가능)
 * 주어진 동전의 종류를 조합하여 목표금액을 만들고자 할때, 최소의 동전 개수를 구하는 문제
 * 입력
 * 첫째줄에 n,K (1<=n<=100, 1<=k<=10000)
 * 다음 줄에 n개의 줄에 각각의 동전의 가치가 주어짐.
 * 출력
 * 첫째줄에 사용한 동전의 최소개수를 출력. 불가능한 경우 -1를 출력
 * 최적화원리 : 만약 i번 동전으로 j원을 줄대 최적으로 거슬러 주지 ㅇ낳으면, 전체 거스름돈의 갯수로 최적이 되지 않음
 * 1원이 포함된 n가지 동전으로 W원을 만들려고 할때, W원을 만드는 최소의 동전 개수는 
 * 다음 두 가지 경우의 수 중 최소값
 * - W-1원을 만드는 최소 동전 개수 + 1원을 추가하여 W원을 만드는 경우
 * - W-(특정동전의 값)원을 만드는 최소 동전개수 + 그 특정 동전을 하나 추가하여 W원을 만드는 경우
 * 
 *
 * */
public class CoinExchange {
	static int[] coins;//동전 종류
	static int[] dp;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/sw_pro/testCase/testinput_2748.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();//동전의 종류 n
		int K = sc.nextInt();//만들려는 가치의 합
		coins = new int[N + 1];
		dp = new int[K + 1];//coins[i]번째로 j 원을 거슬러 줄때 최적값
		
		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}
		
		for (int i = 0;i < N; i++) {
			for (int j = coins[i]; j <= K; j++) {
				dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
			}
		}
		
		if(dp[K] == 0) {
			System.out.println("-1");
		} else {
			System.out.println(dp[K]);
		}

		
		

	}

}
