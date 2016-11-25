package pro.pretest;

import java.io.FileInputStream;
import java.util.*;

/***
 * PreTest
 * 남은 여행 기간동안 우연히 다시 만나야 고백을 받아줌. 
 * 두 사람은 N개의 도시를 M일 동안 여행. 한 도시에서 다른 도시로 이동할 확률은 같다. 
 * 앨리는 1번 도시에서, 노아는 N번 도시에서 하루를 여행하고 남은 M-1일을 여행. 
 * 같은 도시에 머무를 확률이 존재하면 한 도시에 연속해서 머무를 수도 있음.
 * 도시의 개수 N, 여행 일수 M. 도시간 이동확률이 주어졌을때 노아와 앨리가 다시 만날 확률을 계산
 * 단, 여행 중 한번이라도 만나게 되면 그 이후부터는 함께 여행하게 될 것이므로 첫번째 만남 이후 만날 확률은 계산할 필요가 없다
 *
 * 예시.
 * 4개의 도시와 도시간 이동 확률이 주어짐. 앨리는 1번 도시에서 노아는 4번 도시에서 각자 하루를 여행하고 출발. 총 3일동안 여행
 * 1 → 2 : 0.6
 * 1 → 3 : 0.4
 * 2 → 3 : 1.0
 * 3 → 2 : 0.5
 * 3 → 4 : 0.5
 * 4 → 2 : 0.7
 * 4 → 4 : 0.3
 * 
 * 같은 날에 같은 도시를 방문하면 두 사람은 반드시 만난다고 가정
 * 도시를 이동하는 중에 두사람이 만나는 경우는 없다
 * 3<=N<=10개의 도시들을 2<=M<=N<=10 일 동안 여행. 같은 도시를 여러번 방문할 수 있음
 * 같은 도시에 머무를 확률이 존재하면 1개의 도시에 연속해서 머무를 수 있다.
 * 한 도이에서 다른 도시로 이동할 확률의 개수는 3가지 이하이다.
 * 두 사람이 만날수 없는 경우는 입력되지 않는다.
 * 입력
 * 첫줄 테스트 케이스 개수
 * 그 다음 줄에 도시 개수 N, 여행일수 M, 도시간 이동 확률의 개수 K, 3<=N<=K<=30가 주어짐.
 * 그 다음 줄부터 K개 이동확률이 주어짐. 한 도시에서 다른 도시로 이동할 확률의 개수는 3가지 이하
 * 
 * 출력 : 각 줄은 ‘#x’로 시작하고(x는 테스트 케이스 번호) 공백을 하나 둔 다음, 
 * N개의 도시를 M일 동안 여행해서 앨리와 노아가 만날 확률을 구하여라. 답은 소수점 4번째 자리에서 반올림
**/
/*** 설명
 * LinkedList<Integer> deque = new LinkedList<Integer>();
 * deque.addFirst(1);
 * deque.addLast(2);
 * int first = deque.peekFirst();
 * int last = deque.peekLast();
 * int remove = deque.pollFirst(); //queue 의 remove와 같음
 * int pop = deque.pollLast();//statck pop
 * 
 * Dynamic Programming : 원하는 결과를 얻기 위해 먼저 계산한 값을 이용
 * 작은 부분 문제들의 답을 이용해 원래 문제의 해답을 찾아냄. 작은 문제들의 답은 매번 계산하지 않도록 메모리에 저장(Memorization)
 * Double 형을 printf로 출력하려면 %lf
 * */
public class Solution {
	public static int C, D, K;//도시의 개수, 여행일자, 도시간 이동확률의 개수
	public static double[][][] DP;//D일째 C 도시에서 만날 확률
	static ArrayList<Spot>[] sArray;


	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/sample_input_pretest_ori.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int start, end;
		double p, sum;
		
		for (int tc = 1; tc <= T; tc++) {
			C = sc.nextInt();//도시의 개수
			D = sc.nextInt();//여행일자
			K = sc.nextInt();//이동확률의 개수
			
			//[[0.0, 0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.6, 0.4, 0.0], [0.0, 0.0, 0.0, 1.0, 0.0], [0.0, 0.0, 0.5, 0.0, 0.5], [0.0, 0.0, 0.7, 0.0, 0.3]]
			DP = new double[D + 1][C + 1][C + 1];//여행일자에 Eilly, Noah 가 같은 도시에 있을 확률 
			
			sArray = new ArrayList[C + 1];//sArray 초기화
			for (int i = 1; i <= C; i++) {
				sArray[i] = new ArrayList();
			}
			//주어진 확률값들을 sArray로 그래프화
			for (int i = 0; i < K; i++){
					start = sc.nextInt();//int를 double 형으로 
					end = sc.nextInt();
					p = sc.nextDouble();
					sArray[end].add(new Spot(start, end, p));//star
			}
			
			sum = 0.0;
			for (int i = 1; i <= D; i++) {
				for (int j = 1; j <= C; j++) {
					sum += getProbability(i, j, j);
				}
			}

			System.out.println("#" + tc + " " + String.format("%.3f", sum));
		}
		sc.close();

	}

	public static double getProbability(int d, int e, int n) {
		double result;
		if (d == 1) {//1day 를 DP 로 세팅
			if (e == 1 && n == C) {
				return DP[d][e][n] = 1;
			} else {
				return DP[d][e][n] = 0; 
			}
		}

		result = DP[d][e][n];
		if (result == 0) {
			Spot s1, s2;
			for (int i = 0; i < sArray[e].size(); i++) {
				s1 = sArray[e].get(i);
				for (int j = 0; j < sArray[n].size(); j++) {
					s2 = sArray[n].get(j);
					if (s1.s != s2.s) {
						result += getProbability(d-1, s1.s, s2.s) * s1.rate * s2.rate;
						
					}
				}
			}
		}
		
		return DP[d][e][n] = result;
	}


}


class Spot{
	int s, e;
	double rate;
	
	public Spot(int m, int n, double p) {
		this.s = m;
		this.e = n;
		this.rate = p;
	}
}

//[[1, 2], [1, 3], [2, 3], [3, 2], [3, 4], [4, 2], [4, 4]]
//[0.6, 0.4, 1.0, 0.5, 0.5, 0.7, 0.3]

/*
 * (출력)
#1 0.480
#2 0.424
#3 0.382
(sample_input.txt에 대한 정답)

#1 0.116
#2 0.030
#3 0.249
#4 0.337
#5 0.365
#6 0.035
#7 0.082
#8 0.655
#9 0.147
#10 0.102
 * */