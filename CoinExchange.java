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
 * 
 * */
/*** 설명
 * 최적화원리 : 만약 i번 동전으로 j원을 줄대 최적으로 거슬러 주지 않으면, 전체 거스름돈의 갯수로 최적이 되지 않음
 * 1원이 포함된 n가지 동전으로 W원을 만들려고 할때, W원을 만드는 최소의 동전 개수는 
 * 다음 두 가지 경우의 수 중 최소값
 * - W-1원을 만드는 최소 동전 개수 + 1원을 추가하여 W원을 만드는 경우
 * - W-(특정동전의 값)원을 만드는 최소 동전개수 + 그 특정 동전을 하나 추가하여 W원을 만드는 경우
 * 
 * DP[j] : j 금액을 만들기 위한 최소 동전수
 * dp[1] = 1 , dp[2] = 2, dp[3] = 3, dp[4] = 4,
 * dp[5] = 1 5원 짜리 동전이 있기 때문에 5원을 만들때는 5원짜리 동전 한개를 사용하는 것이 최적
 * dp[6] = 2, dp[7] = 3, dp[8] = 4, dp[9] = 5, dp[10] = 2
 * ---> dp 값을 맥스값으로 세팅한 후에, 지금 dp[j]값과 dp[j-coins[i]] + 1 값을 비교해서 작은 것을 선택
 * */
public class CoinExchange {
	static int MAX = 10001;
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2294.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();//동전의 종류 n
		int K = sc.nextInt();//만들려는 가치의 합
		int[] coins = new int[N];//동전 종류
		int[] dp = new int[K + 1];//j 금액을 만들수 있는 최소 동전수.
		
		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}
		
		//비교를 위해 최초값을 맥스값으로 세팅
		for (int i = 1; i <= K; i++) {
			dp[i] = MAX;
		}
		
		int temp;
		dp[0] = 0;
		for (int i = 0;i < N; i++) {
			for (int j = coins[i]; j <= K; j++) {
				int coin = coins[i];
				dp[j] = Math.min(dp[j], dp[j-coin] + 1);
			}
		}
		
		if(dp[K] == MAX) {
			System.out.println("-1");
		} else {
			System.out.println(dp[K]);
		}

		
		sc.close();
		long end = System.currentTimeMillis();
		System.out.println("##  소요시간 : " + ( end - start )/1000.0f +"초"); 
	}

}
/*
 * 입력
 * 3 15
 * 1
 * 5
 * 12
 * 출력
 * 3
 * */
