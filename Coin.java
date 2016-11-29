package pro.lesson.DP;

import java.io.FileInputStream;
import java.util.*;

/***
 * 2293
 * n가지 종류의 동전. 각각의 동전이 나타내는 가치는 다르다.
 * 이 동전들을 적당히 사용해서 그 가치의 합이 K원이 되도록 하고 싶다. 그 경우의 수를 구하시오
 * (각각의 동전은 몇 개라도 사용가능)
 * 2294는 K원이 되는 최소의 동전개수
 * 입력
 * 첫째줄에 n,K (1<=n<=100, 1<=k<=10000)
 * 다음 줄에 n개의 줄에 각각의 동전의 가치가 주어짐.
 * 출력
 * 첫째줄에 경우의 수를 출력. 경우의 수는 2^31보다 작다.
 * 
 * dp[0]=1 dp[1]=1 dp[2]=2(1*2, 2) dp[3]=2(1*3, 2+1) dp[4]=3(1*4, 2*2,1*2+2)
 * dp[5]=4(1*5, 1*3+2, 1+2*2, 5) dp[6]5(1*6, 1*4+2, 1*2+2*2, 1+5, 2*3)
 * dp[7]=6(1*7, 1*5+2, 1*3+2*2,1*2+5, 1+2*3, 2+5)
 * dp[n]=0(1*n,
 * */

/*** 설명
 * 2293 vs. 2294
 * 2293 : n가지 종류로 K를 만들 수 있는 경우의 수를 구함
 * dp[0] = 1; 
 * for (int i = 1; i <=N; i++){
 * 	for (int j = 1; j <= K; j++) {
 * 		if(coins[i] <= j) {
 * 			dp[j] += dp[j-coins[i]];
 * 		}
 * 	}
 * }
 * 2294 : n가지 종류로 K를 만들때 최소의 동전으로 만들려고 함. 그 최소의 동전개수. 만들수 없을 경우에는 -1출력
 * dp[0] = 0 dp[k] = MAX로세팅해놓고 계산을 다 끝냈을때, dp[K] = MAX라면 만들수 없는 경우이므로, 
 * for (int i = 1; i <=N; i++){
 * 	for (int j = coins[i]; j <= K; j++) {
 * 			dp[j] = Math.min(dp[j-coins[i]] + 1, dp[j]);
 * 	}
 * }
 * */
class Coin {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/testinput_2293.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n, k;
		int[] coins;
		int[] dp;//k원을 만드는 경우의 수
		
		n = sc.nextInt();//동전의 종류
		k = sc.nextInt();//만들고자 하는 값
		coins = new int[n];
		dp = new int[k+1];
		
		for (int i = 0; i < n; i++) {//주어진 동전의 종류를 array로 
			coins[i] = sc.nextInt();
		}
		
		dp[0] = 1;//dp[0]을 0으로 세팅하면 답이 나올수가 없음
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (coins[i] <= j) {//만들려는 금액j보다 동전이 작거나 같아야 함
					dp[j] += dp[j-coins[i]];
				}
			}
		}
		
		System.out.println(dp[k]);
		
	}

}
