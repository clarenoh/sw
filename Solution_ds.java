package pro.pretest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution_ds {
    public static int N, M, K;    // city, day, road
    public static double[][][] DP;
    public static double[][] mx;
    public static ArrayList<Edge>[] es;

    public static void main(String[] args) throws Exception{
    	System.setIn(new FileInputStream("D:/5.SW/project/sw_pro/src/testCase/test_input_pretest.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int s, e;
        double p;
        double sum;
        for (int t=0; t<T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            DP = new double[M+1][N+1][N+1];

            es = new ArrayList[N+1];
            for (int n=1; n<=N; n++) {
                es[n] = new ArrayList();
            }
            for (int k=0; k<K; k++) {
                s = sc.nextInt();
                e = sc.nextInt();
                p = sc.nextDouble();
                es[e].add(new Edge(s,e,p));
            }
            sum = 0;
            for (int m=1; m<=M; m++) {
                for (int i=1; i<=N; i++) {
                    sum += cal(m, i, i);
                }
            }

            System.out.printf("#"+(t+1)+" %.3f\n",sum);
        }
    }

    public static double cal(int day, int f, int s) {
        double ret;
        if (day <= 1) {
            if (f == 1 && s == N) {
                return DP[day][f][s] = 1;
            } else {
                return DP[day][f][s] = 0;
            }
        }
        ret = DP[day][f][s];
        if (ret == 0) {
            Edge e1, e2;
            for (int i=0; i<es[f].size(); i++) {
                e1 = es[f].get(i);
                for (int j=0; j<es[s].size(); j++) {
                    e2 = es[s].get(j);
                    if (e1.s != e2.s) {
                        ret += (cal(day-1, e1.s, e2.s) * e1.p * e2.p);
                    }
                }
            }
        }
        return DP[day][f][s] = ret;
    }

    public static void print(double[][] mx) {
        System.out.print("\n");
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                System.out.print(mx[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void print(double[][][] mx3) {
        System.out.print("\n");
        for (int i = 0; i < mx3.length; i++) {
            print(mx3[i]);
            System.out.print("\n");
        }
    }

}

class Edge {
    int s;
    int e;
    double p;

    public Edge(int s, int e, double p) {
        this.s = s;
        this.e = e;
        this.p = p;
    }
}
