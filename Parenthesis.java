package lesson.structure;

import java.io.FileInputStream;
import java.util.*;

/***
 * 9012
 * 괄호 문자열 : 두개의 괄호 기호인 (, )으로만 구성되어 있는 문자열.
 * 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호문자열 VPS라고 부른다.
 * 만약 x가 vps라면, 이것을 하나의 괄호에 넣은 새로운 문자열 (x)도 VPS가 된다.
 * 두 VPS x,y을 접합시킨 새로운 문자열 xy도 VPS가 된다.
 * 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 yes or 로 나타냄
 * T개의 테스트 데이터. 
 * 각 테스트 데이터의 첫째줄에는 괄호 문자열이 한줄에 주어짐. 괄호문자열의 길이는 2이상 50이하
 * 올바른 문자열이면 YES, 아니면 NO 를 출력
 * 
**/
/*** 설명
 * 
 * */
public class Parenthesis {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("E:/01.SW_Advanced/project/advanced_ar/src/testcase/input_9015.txt"));
		Scanner sc = new Scanner(System.in);

		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++){
			String s = sc.next();
			int count = 0;//괄호의 개수가 valid인지 판단하는 변수 0보다 크거나 작으면 not valid
			int sNum = s.length();//문자열의 길이
			
			for (int i = 0; i < sNum; i++){
				if (s.charAt(i) == ')') {
					count += 1;
				} else {
					count -= 1;
				}
			}
			
			System.out.printf("#%d %s", tc, ": ");
			if (count == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		
		sc.close();
	
	}
}

/*
NO
NO
YES
NO
YES
NO
*/