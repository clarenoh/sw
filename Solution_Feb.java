package pretest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 2월 사전테스트
 * N일동안 개인 트레이닝. 운동을 할 때마다 생기는 근육통을 최소로하며 운동을 하고 싶다.
 * 근육통, 피로도, 운동량을 수치화하여 생각할떄, 어제까지 운동하여 쌓여있는 피로도가 6, 오늘 9만큼의 운동을 한다면
 * 오늘 느끼는 근육통은 6*9=54이고 피로도는 6+9 = 15
 * 피로도를 0으로 만드는 만능주사를 M개 구입해 M번 사용하기로 함.
 * 항상 그날 운동이 끝난 뒤 사용하며, 사용할 시 누적되어 있는 피로도가 0으로
 * 최초 피로도, 근육통 수치는 0. 만능 주사를 언제 사용하여야 근육통이 최소로 될지 계산
 * if (index == last)
    return 0;
if (남은 양자화 step == 0)
    return INFINITY; // fail case
숫자열에서 그룹화 할 범위를 늘려가면서 나머지 부분을 재귀호출한다.
출처: http://suwonbongdal.tistory.com/3
 * (예시) 운동 8일, 만능주사 2회, 8일동안 운동량이 6, 9, 7, 11, 2, 16, 4, 10
 * 3일과 6일 저녁에 만능 주사를 맞으면 근육통을 최소인 429로 만들 수 있다.
 * 제한조건 : 만능주사는 항상 당일 운동이 끝난 저녁에 맞는다
 * 주사는 N일동안 M번을 남김없이 모두 사용해야함
 * 입력
 * 최초 테스트케이스 개수 T,
 * 그 다음줄부터 T개의 테스트 케이스. 각 케이스마다 운동일수 (1 ≤ N ≤ 100),만능주사(1 ≤ M ≤ min(N, 10))
 * 다음 줄에는 매일 하는 운동량 Ai(1 ≤ Ai ≤ 1,000)이 N개
 * 
 * 출력
 * #x 출력 공백을 하나. 근육통의 최소값을 출력한뒤 공백 만능주사를 맞은 날짜를 오름차순으로 출력.
 * 근육통을 최소로 하는 방법이 여러가지이면, 최대한 주사를 일찍 맞는 경우(사전순)를 출력
 */
/**
 * 피로도 - 어제한 운동량 + 오늘 운동량 -- 운동량의 합
 * 근육통 - 피로도 * 운동량 SIMILAR 운동량 * 운동량
 * 시작일과 남은 주사수의 부분문제로 해결하는 2차원 동적계획법 문제이다.
 *  DP 구할 때 최소가 되는 주사 투입일을 시작일과 남은 주사수에 맞춰 2차원 배열로 저장한 후 해당 정보를 이용 투입일을 출력한다.
 */
public class Solution_Feb {
	static int[] fatigue;//i일 아침에 피로도
	static int[] pain;//i일 저녁의 근육통의 합
	static int[][] dp;//i일에 투입하고 남은 주사수가 j개일때 근육통의 합 
	static int M;
	static int answer;//근육통의 최소치 값
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("D:/5.SW/project/sampleInput/FebTest1.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T, N;
		int[] training;//운동량
		
		T = sc.nextInt();//테스트 케이스 수
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();//운동일수
			M = sc.nextInt();//만능주사의 개수
			training = new int[N + 1];
			fatigue = new int[N + 1];
			pain = new int[N + 1];
			dp = new int[N + 1][M + 1];

			for (int i = 1; i <= N; i++) {
				training[i] = sc.nextInt();
			}
			
			fatigue[1] = 0;
			pain[1] = 0;
			for (int i = 1; i < N; i++) {
				fatigue[i] = fatigue[i - 1] + training[i];
				pain[i] = pain[i - 1] +  fatigue[i] * training[i + 1];
			}
			
			for (int row[]:dp) {			//dp초기화
				Arrays.fill(row,  -1);
			}
			
		    for (int i = 1; i <= N; i++) {
		    	dp[i][M] = pain[i];//주사를 사용하지 않았다면, 원래 pain 과 같다
		    }
		    answer = pain[N - 1];//초기값은 아무 주사도 맞지 않은 값
		    
		    calculateMinPain(M);

			System.out.println(dp[N - 1][M]);
		}
		
	}


	private static void calculateMinPain(int nth) {
		if (nth == M) return;//주사를 다 맞았으므로 
		fatigue[nth + 1] = 0;
		dp[nth + 1] = dp[nth];
		for (int n = 1; n + nth <= M; n++) {//2day에 주사를 맞았다면, 
			
		}
		
	}


/*
	public static int quantize(int from, int part) {
		if (part == 0) return MAX;
		if (from == N) return 0;
		
		if (dp[from][part] != -1) {
			return dp[from][part] ;
		}
		
		int temp = MAX;
		for (int size = 1; size + from <= N;++size) {
			temp = Math.min(temp, minError(from, from + size - 1) + quantize(from + size, part -1));
		}
		System.out.println(temp);
		return dp[from][part] = temp;
	}


	private static int minError(int low, int high) {
	  int minSum = fatigue[high] - (low == 0?0 : fatigue[low - 1]);
	  int sqSum = pain[high] - (low == 0?0 : pain[low - 1]);
	  int m = (int)(0.5 + (double) minSum / (high - low + 1));
	  
	  int ans = sqSum - 2 * m * minSum + m * m * (high - low + 1);
	  return ans;
	}*/
}
