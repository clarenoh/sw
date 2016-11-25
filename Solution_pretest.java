package pro.pretest;

import java.io.FileInputStream;
import java.util.*;

/***
 * PreTest
 * ���� ���� �Ⱓ���� �쿬�� �ٽ� ������ ����� �޾���. 
 * �� ����� N���� ���ø� M�� ���� ����. �� ���ÿ��� �ٸ� ���÷� �̵��� Ȯ���� ����. 
 * �ٸ��� 1�� ���ÿ���, ��ƴ� N�� ���ÿ��� �Ϸ縦 �����ϰ� ���� M-1���� ����. 
 * ���� ���ÿ� �ӹ��� Ȯ���� �����ϸ� �� ���ÿ� �����ؼ� �ӹ��� ���� ����.
 * ������ ���� N, ���� �ϼ� M. ���ð� �̵�Ȯ���� �־������� ��ƿ� �ٸ��� �ٽ� ���� Ȯ���� ���
 * ��, ���� �� �ѹ��̶� ������ �Ǹ� �� ���ĺ��ʹ� �Բ� �����ϰ� �� ���̹Ƿ� ù��° ���� ���� ���� Ȯ���� ����� �ʿ䰡 ����
 *
 * ����.
 * 4���� ���ÿ� ���ð� �̵� Ȯ���� �־���. �ٸ��� 1�� ���ÿ��� ��ƴ� 4�� ���ÿ��� ���� �Ϸ縦 �����ϰ� ���. �� 3�ϵ��� ����
 * 1 �� 2 : 0.6
 * 1 �� 3 : 0.4
 * 2 �� 3 : 1.0
 * 3 �� 2 : 0.5
 * 3 �� 4 : 0.5
 * 4 �� 2 : 0.7
 * 4 �� 4 : 0.3
 * 
 * ���� ���� ���� ���ø� �湮�ϸ� �� ����� �ݵ�� �����ٰ� ����
 * ���ø� �̵��ϴ� �߿� �λ���� ������ ���� ����
 * 3<=N<=10���� ���õ��� 2<=M<=N<=10 �� ���� ����. ���� ���ø� ������ �湮�� �� ����
 * ���� ���ÿ� �ӹ��� Ȯ���� �����ϸ� 1���� ���ÿ� �����ؼ� �ӹ��� �� �ִ�.
 * �� ���̿��� �ٸ� ���÷� �̵��� Ȯ���� ������ 3���� �����̴�.
 * �� ����� ������ ���� ���� �Էµ��� �ʴ´�.
 * �Է�
 * ù�� �׽�Ʈ ���̽� ����
 * �� ���� �ٿ� ���� ���� N, �����ϼ� M, ���ð� �̵� Ȯ���� ���� K, 3<=N<=K<=30�� �־���.
 * �� ���� �ٺ��� K�� �̵�Ȯ���� �־���. �� ���ÿ��� �ٸ� ���÷� �̵��� Ȯ���� ������ 3���� ����
 * 
 * ��� : �� ���� ��#x���� �����ϰ�(x�� �׽�Ʈ ���̽� ��ȣ) ������ �ϳ� �� ����, 
 * N���� ���ø� M�� ���� �����ؼ� �ٸ��� ��ư� ���� Ȯ���� ���Ͽ���. ���� �Ҽ��� 4��° �ڸ����� �ݿø�
**/
/*** ����
 * LinkedList<Integer> deque = new LinkedList<Integer>();
 * deque.addFirst(1);
 * deque.addLast(2);
 * int first = deque.peekFirst();
 * int last = deque.peekLast();
 * int remove = deque.pollFirst(); //queue �� remove�� ����
 * int pop = deque.pollLast();//statck pop
 * 
 * Dynamic Programming : ���ϴ� ����� ��� ���� ���� ����� ���� �̿�
 * ���� �κ� �������� ���� �̿��� ���� ������ �ش��� ã�Ƴ�. ���� �������� ���� �Ź� ������� �ʵ��� �޸𸮿� ����(Memorization)
 * Double ���� printf�� ����Ϸ��� %lf
 * */
public class Solution {
	public static int C, D, K;//������ ����, ��������, ���ð� �̵�Ȯ���� ����
	public static double[][][] DP;//D��° C ���ÿ��� ���� Ȯ��
	static ArrayList<Spot>[] sArray;


	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/sample_input_pretest_ori.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int start, end;
		double p, sum;
		
		for (int tc = 1; tc <= T; tc++) {
			C = sc.nextInt();//������ ����
			D = sc.nextInt();//��������
			K = sc.nextInt();//�̵�Ȯ���� ����
			
			//[[0.0, 0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.6, 0.4, 0.0], [0.0, 0.0, 0.0, 1.0, 0.0], [0.0, 0.0, 0.5, 0.0, 0.5], [0.0, 0.0, 0.7, 0.0, 0.3]]
			DP = new double[D + 1][C + 1][C + 1];//�������ڿ� Eilly, Noah �� ���� ���ÿ� ���� Ȯ�� 
			
			sArray = new ArrayList[C + 1];//sArray �ʱ�ȭ
			for (int i = 1; i <= C; i++) {
				sArray[i] = new ArrayList();
			}
			//�־��� Ȯ�������� sArray�� �׷���ȭ
			for (int i = 0; i < K; i++){
					start = sc.nextInt();//int�� double ������ 
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
		if (d == 1) {//1day �� DP �� ����
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
 * (���)
#1 0.480
#2 0.424
#3 0.382
(sample_input.txt�� ���� ����)

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