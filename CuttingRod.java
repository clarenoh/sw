package koitp.day1;

import java.io.FileInputStream;
import java.util.*;

/**
 * 막대자르기
 * http://www.koitp.org/problem/ROD_CUTTING
 * 길이가 N인 막대기. 막대를 자연수가 되도록 여러 조각으로 자를 수 있다. 각 길이에 대한 값어치가 있을때, 값어치 합이 최대가 되도록 막대기를 자르자.
 * 예를 들어 4인 막대기가 있고 각 길이별 값어치가 아래와 같다.
 * length 1  2  3  4  
 * cost   1  5  8  9
 * 이때 길이가 2인 막대기가 두개가 되도록 잘라야 전체값어치가 최대
 * 첫줄에 막대기 길이 N. 1<=N<=3000
 * 둘째 줄에 N개의 자연수가 공백으로 구분, i번째 주어지는 수는 길이가 i인 막대기의 값어치를 의미
 * 이때 주어지는 수는 1000을 넘지 않는다.
 * 첫 줄에 값어치 합이 최대가 되도록 막대기를 자를 때, 값어치 합을 출력
 * 
 */
/**
 * DP : 점화식, 초기값
 */
public class CuttingRod {
	static int[] dp;//길이가 i인 막대기를 만들때, 최대 값어치
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sampleInput/CuttingRod.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] cost = new int[N + 1];
		dp = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			cost[i] = sc.nextInt();
		}
		
		dp[0] = 0;//막대길이가 0이라면 만들 막대가 없으므로 최대 값어치는 0
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + cost[j]);
			}
		}
		System.out.println(dp[N]);
	}

}
