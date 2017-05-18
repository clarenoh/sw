package example01;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// 100보다 작거나 같은 자연수이고, K는 1,000,000,000보다 작거
//https://www.acmicpc.net/problem/1256

public class Dictionary {
	static int MAX = 1000000001;
	static int[][] combi = new int[201][201];

	static Queue<Integer> que;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:/Users/SDS/Desktop/sample.txt"));
		Scanner sc = new Scanner(System.in);
		int N, M, K;
		int L;
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		que = new LinkedList<Integer>();
		L = N + M;
		combi[0][0] = 1;
		
		for (int i = 1; i <= L; i++) {
			combi[i][0] = 1;
			for (int j = 1; j <= i ; j++) {
				combi[i][j] = Math.min(MAX, combi[i - 1][j] + combi[i - 1][j - 1]);
			}
		}

		if ( K > combi[L][M]) {
			System.out.println("-1");
			return;//이걸 안하면 런타임 에러남
		}	
		for (int i = 1; i <= L; i++) {
			if (combi[L - i][M] >= K) continue;
			que.add(i);
			K -= combi[L - i][M];
			M--;
		}
		
		for (int i = 1; i <= L; i++) {
			if (!que.isEmpty() && que.peek() == i) {
				System.out.printf("%s","z");
				que.poll();
			}else {
				System.out.printf("a");
			}
		}


	}
}
