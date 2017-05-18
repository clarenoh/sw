package example01;

import java.io.FileInputStream;
import java.util.*;

/** https://www.acmicpc.net/problem/1915
 * n×m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구해
 * 첫째 줄에 n, m(1 ≤ n, m ≤ 1,000). 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.
 * 
 * */

public class MaxSqure {
	static int[][] dp;//0,0부터 i,j까지 i,j로 끝나는 가장 큰 정사각형의 한 변의 길이
	static char[][] arr;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:/Users/SDS/Desktop/sample.txt"));
		Scanner sc = new Scanner(System.in);
		int N, M;
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new char[N][M];
		dp = new int[N][M];
		
		for (int i = 0 ; i < N; i++) {
			String temp = sc.next();
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}

		int max = 0;
		for (int j = 0 ; j < M; j++) {
			if (arr[0][j] == '1') {
				dp[0][j] = 1;
				max = 1;
			}
		}
		
		for (int i = 0 ; i < N; i++) {
			if (arr[i][0] == '1') {
				dp[i][0] = 1;
				max = 1;
			}
		}
		
		for (int j = 1; j < M; j++) {
			for (int i = 1; i < N; i++) {
				if (arr[i][j] == '1') {
					dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max*max);
		
	}

}


/*
5 5
01001
00110
01111
01111
01110*/
