package lesson.structure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/***
 * 1406
 * 한줄로 된 간단한 에디터 구현. 영어 소문자만을 기록할수 있음. 최대 600,000 글자까지 입력가능
 * 커서 : 문장의 맨앞(첫 번째 문자의 왼쪽), 문장의 맨뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의곳
 * (모든 연속된 두 문자 사이)에 위치 할 수 있다. 
 * 즉 길이가 L 인 문자열이 편집기에 입력되어 있다면 커서가 위치할 수 있는 곳은 L+1
 * L : 커서를 왼쪽으로 한칸 옮기. 문장의 맨 앞이면 무시됨
 * D : 오른쪽으로 한 칸 옮김. 문장의 맨뒤이면 무시됨
 * B : 커서 왼쪽에 있는 문자를 삭제함(커서가 문장의 맨 앞이면 무시됨)
 *    삭제로 인해 커서는 한칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
 * P$ $라는 문자를 커서 오른쪽에 추가함. 커서는 $의 오른쪽에 위치함
 * 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성.
 * 초기 커서는 문장의 맨 뒤에 위치하고 있음.
 * 입력 : 첫째줄에는 편집기에 입력되어 있는 문자열이 주어짐. 영어소문자로만 이루어져 있음. <100,000
 * 둘째 줄에는 입력할 명령어의 개수 1<=N<=500,000. 
 * 셋째줄부터 N개의 줄에 걸쳐 입력할 명령어가 순서대로 주어짐. 
 * 출력 : 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 출력
 * 
**/
/*** 설명
 * 커서를 기준으로 왼쪽 스택, 오른쪽 스택으로 나눠서 생각
 * Stack : push pop 스택은 먼저 들어간것이 나중에 나옴. 한 방향에서만 출입
 * Queue : add, remove 먼저 들어간것이 먼저 나옴 양 사이드에서 출입
 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 * br.readLine();한줄을 읽어드림
 * String "" Char ''
 * */
public class Editor {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_1406.txt"));
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> left = new Stack<Character>();//커서를 기준으로 왼쪽에 있는 문자열
		Stack<Character> right = new Stack<Character>();//커서를 기준으로 오른쪽에 있는 문자열
		
		String s = br.readLine();//에디터에 입력된 초기 문자
		int T = Integer.parseInt(br.readLine());//명령어의 개수
		
		for (int i = 0; i < s.length(); i++){//초기 문자열은 모두 커서 왼쪽에. left에 push
			char temp = s.charAt(i);
			left.push(temp);
		}

		for (int tc = 1; tc <= T; tc++){
			String[] line = br.readLine().split(" ");
			
			char what = line[0].charAt(0);//order 가 p$일수 있기 때문에
			char temp;
			switch(what) {
				case 'L' ://String "" Char ''
					if (left.isEmpty()) {
						break;
					}else {
						temp = left.pop();
						right.push(temp);
					}
					break;
				case 'D' :
					if (right.isEmpty()){
						break;						
					}else {
						temp = right.pop();
						left.push(temp);
					}
					break;
				case 'B' :
					if (left.isEmpty()){
						break;
					}else {
						int size = left.size();
						left.remove(size-1);//idx가 0부터 시작
					}
					break;
				case 'P' :
					String a = line[1];
					char insert = a.charAt(0);
					left.push(insert);
					break;
				default : 
					break;
			}
		}
		
		while(!left.empty()) {
			right.push(left.pop());
		}
		StringBuilder ansSB = new StringBuilder();
		while(!right.empty()) {
			ansSB.append(right.pop());
		}
		System.out.println(ansSB);
	}
}

/*
abcdyx
*/