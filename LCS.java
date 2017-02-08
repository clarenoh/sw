package koitp.review;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * LCS (최장 공통 부분 수열, Longest Common Subsequence)
 * http://www.koitp.org/problem/LCS
 * 두 수열이 있을 때, 두 수열의 공통 부분 수열 중 가장 긴 공콩 부분수열의 길이를 의미
 * 예를 들어 1,4,2,5, 와 1,2,3,4,5의 LCS는 1,2,5
 * ABCBDAB 와 BDCABA 의 LCS 는 BCBA
 * 두 문자열이 주어졌을때, 두 문자열의 LCS를 구하는 프로그램을 작성하시오
 * 첫 줄에 첫번재 문자열, 둘째 줄에 두 번째 문자열. 주어지는 문자열은 대문자로만 구성, 길이가 1000을 넘지 않는다.
 * 출력 : 첫 줄에 주어진 두 문자열의 LCS 를 출력. 답이 여러가지 인 경우 아무거나 하나 출력
 */
public class LCS {
	static String A, B;//입력받은 두 문자
	static int[][] dp;//A의 i문자열,B의 j문자열까지의 공통되는 문자열의 개수
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sampleInput/LCS.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int a, b;
		
		for (int tc = 1; tc <= T; tc++) {
			A = br.readLine();
			B = br.readLine();
			a = A.length();
			b = B.length();
			
			dp = new int[a + 1][b + 1];//dp 초기화
			
			for (int i = 1; i <= a; i++) {
				dp[i][0] = 0;//B의 문자가 0일때는 공통되는 문자열이 없다
			}
			for (int j = 1; j <= b; j++) {
				dp[0][j] = 0;//A의 문자가 0일때는 공통되는 문자열이 없다
			}
			
			for (int i = 1; i <= a; i++) {
				char temp = A.charAt(i - 1);
				for (int j = 1; j <= b; j++) {//out of array 에러를 막기 위해
					if (temp == B.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
			//System.out.printf("#%d %d\n", tc, dp[a][b]);
			
			int max = dp[a][b];//max인 값
			int startIdx = b;
			StringBuilder sb = new StringBuilder();
			
			for (int j = startIdx; j > 0; j--) {//j를 먼저 돌리고 i를 돌린다
				for (int i = a; i > 0; i--) {
					if (dp[i][j] == max && dp[i - 1][j] == max - 1 
							&& dp[i][j - 1] == max - 1 && dp[i - 1][j - 1] == max - 1) {
						sb.append(A.charAt(i - 1));
						max--;
						startIdx = j;//지금까지 계산한 값으로 startIdx를 세팅
						break;//같은 거가 있으면 더 이상 로직을 돌릴 필요가 없다
					}
				}
			}
			
			System.out.printf("#%d %s\n", tc, sb.reverse());

		}

	}

}
