package review;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 가장 먼 두 도시
 * N 개의 도시, 임의의 두 도시 사이에는 항상 도로가 있다. 도로는 1번부터 N번까지 넘버링
 * a에서 b로 가는 길이와 b에서 a로 가는 길이는 다를 수 있음.
 * 직접 연결된 도로를 통하는 것보다 다른 도시들을 거쳐가는 것이 빠를 수도 있음.
 * 도시의 도로 정보가 주어졌을때, 거리가 가장 먼 두 도시 사이의 거리를 구하는 프로그램을 작성
 * 입력
 * 첫줄에 도시의 수 1 ≤ N ≤ 300
 * 다음 N개의 줄에 도로의 정보를 나타내는 음이 아닌 정수 N개가 공백으로 구분되어 주어짐.
 * i+1번째 줄에서 j번째로 주어지는 수는 i번 도시에서 j번 도시로 가는 도로의 길이를 의미
 * 이 수들은 1,000,000 보다 크지 않으며, i+1 번째 줄에서 i번쨰 수는 항상 0
 */
/**
 * 경유지 → Floyd Warshall
 * : 동적계획법적 접근. 그래프 상의 모든 두 정점을 있는 경로의 최소 비용을 구함
 * 경유지를 기록하면 최소비용 경로 구할 수 있음. 
 * 제한 조건 없음. 음수 가능. 복잡도 큼 O(V^3)
 * 두 정점을 잇는 최소 비용 경로는 경유지 없이 다이렉트로 가거나, 경유지를 거치거나 둘 중 하나
 * 경유지를 거친다면, 그것을 이루는 부분 경로 역시 최소 경로여야함.
 * 원래는 K경유지를 거쳐서 i에서 j로 가는 비용테이블을 int[k][i][j]로 선언, int[0][i][j]는 직항으로 가는 방법
 * Floyd Marshall 정리에 의해(경유지를 거쳐가는 각각의 cost가 최소여야 전체 cost도 최소다) --int[i][j]로 단순화
 * → 기존에 주어진 그래프 자체가 i에서 j로 거리의 좌표
 * → 주어진 좌표 자체가 초기값, dp에 초기값을 넣는다
 * BufferedReader br = new BufferedReader(new InputFileStream(System.in));
 * StringToknizer token = new StringToknizer(br.readLine(), " ");
 */
public class FARTHEST_CITY {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/1. SW/pjt/sample/sampleInput_71.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;//공백으로 구분되어 있다고 하므로 StringTokenizer 사용
		int[][] dp;//i지점에서 출발해서 j지점까지 갈 때의 최소 경로
		
		int N = Integer.parseInt(br.readLine());//도시의 수
		dp = new int [N + 1][N + 1];
		
		//주어진 좌표를 그래프로 & 직항으로 갈 수 있을 때의 거리
		for (int i = 1; i <= N; i++) {
			token = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				dp[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		//dp 돌리기 시작, 경유지가 맨 밖
		for (int k = 1; k <= N; k++) {//경유지
			for (int i = 1; i <= N; i++) {//출발지
				for (int j = 1; j <= N; j++) {//도착지
					if (dp[i][j] > dp[i][k] + dp[k][j]) {//기존 값보다 경유해서 가는 값이 작다면 dp를 갱신
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}
		
		int max = 0;
		for (int i = 1; i <= N; i++) {//가장 거리가 먼거를 찾음
			for (int j = 1; j <= N; j++) {
				if (max < dp[i][j]) {
					max = dp[i][j];
				}
			}
		}
		
		System.out.println(max);
	}

}

//sample : 15
//4
//0 5 3 1
//2 0 8 6
//8 5 0 4
//4 9 8 0
//ans : 9
