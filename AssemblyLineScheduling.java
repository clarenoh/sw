package koitp.day1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 두개의 생산라인, 각 라인에는 n개의 공정이 순서대로 있다. 
 * 중간에 생산라인을 바꿀 수 있다. 
 * 첫번째 생산라인의 i번째 공정에서 소요되는 시간은 S[1,i], 두번째 생산라인의 i번째 공정에서 소요되는 시간은 S[2,i]
 * 첫번째에 진입하는 시간은 e1, 두번째 생산라인은 e2
 * 첫번째에서 생산을 마무리하는 시간은 x1, 두번째는 x2
 * 첫번째에서 i번째 공정을 마치고 두번째로 바꾸는데 걸리는 시간은 t1,i 두번째에서 첫번쨰로 바꾸는데 걸리는 시간은 t2,i
 * 하나의 제품을 만드는데 걸리는 최소 시간을 알고 싶다. 하나의 제품을 만드는데 걸리는 최소 시간을 구하시오.
 * 입력
 * 첫줄에 라인별 공정의 개수 n, 라인별 진입시간과 마무리시간 e1,e2,x1,x2 2<=n<=300,000 1<=e1,e2,x1,x2<=200
 * 두번째 줄에 S11 S12, S1n을 나타내는 자연수가 공백으로 구분 1<=S1i<=200
 * 세번째 줄에 S21,S22,, 
 * 네번째 줄에 t11,t12...t1n-1 을 나타태는 n-1개의 자연수가 공백으로 구분  1<=t1i<=200
 * 다섯 번째 줄에 t21,t22,t2n-1 
 * 출력
 * #테스트케이스수 한 제품을 만드는데 걸리는 최소 시간
 */
/**
 * 물건이 i번 생산라인의 j번째 공정에 오기 직전 상황은 
 * i번 생산라인에서 j-1에 있거나 3-i 라인에서 j-1 라인에 있는 경우 두 가지뿐
 *
 */
public class AssemblyLineScheduling {
	static int[][] S;//i번째 라인에서 j번째 라인을 만드는데 걸리는 시간
	static int[][] Transfer;//i번째 라인에서 j번째 라인을 맞친후 라인을 옮기는데 걸리는 시간
	static int[][] dp;//i번째 (1,2)라인 에 있고, j번째를 맞친 후  최소의 cost 
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sampleInput/AssemblyLine.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		int T =Integer.parseInt(br.readLine());//테스트 케이스 개수
		int n, enter1, enter2, exit1, exit2;//생산라인의 공정의 개수
		for (int tc = 1; tc <= T; tc++) {
			token = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(token.nextToken());
			enter1 = Integer.parseInt(token.nextToken());
			enter2 = Integer.parseInt(token.nextToken());
			exit1 = Integer.parseInt(token.nextToken());
			exit2 = Integer.parseInt(token.nextToken()); 
			
			S = new int[3][n + 1];
			Transfer = new int[3][n];
			dp = new int[3][n + 1];
			

			for (int i = 1; i <= 2; i++) {
				token = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= n; j++) {
					S[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			

			for (int i = 1; i <= 2; i++) {
				token = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j < n; j++) {
					Transfer[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			dp[1][1] = enter1 + S[1][1];//초기값 세팅. 들어가는데 드는 비용 계산
			dp[2][1] = enter2 + S[2][1];
			
			//dp[i][j] = min(dp[i][j-1], dp[3-i][j-1] + Transfer[3-i][j]) + S[i][j]
			for (int j = 2; j <= n; j++) {
				for (int i = 1; i < 3; i++) {
					dp[i][j] = Math.min(dp[i][j - 1], dp[3 - i][j - 1] + Transfer[3 - i][j - 1]) + S[i][j];
				}
			}
		
			System.out.printf("#%d %d\n", tc, Math.min(dp[1][n] + exit1, dp[2][n] + exit2));
		}


	}


}


/*6 2 4 3 2
7 9 3 4 8 4
8 5 6 4 5 7
2 1 1 3 4 
2 1 2 2 1
출력 : 38*/