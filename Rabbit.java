package pro.lesson.DP;

/***
 * 토끼수 구하기
 * 첫 달에는 새로 태어난 토끼 한 쌍만이 존재
 * 두 달 이상이 된 토끼는 번식 가능. 번식 가능한 토끼 한쌍은 매달 새끼 한 쌍을 낳는다.
 * 토끼는 죽지않는다. 이런 조건을 가질때 n번째 달의 토끼수는 ?
 * 
 * 1st-1쌍
 * 2nd-1쌍
 * 3rd-2쌍
 * 4th-3쌍
 * 5th-5쌍
 * .... 
 * 피보나치 수열(n-2와 n-1을 더하면 n의 값이 나옴) : 0과 1로 시작하고 이전 두 수 합을 다음 항으로 하는 수열
 * F0 = 0,F1 = 1일때
 * Fi = Fi-1 + Fi-2 for i>=2
 * 중복 호출이 어마어마하게 존재
**/
/*** 설명
 * 비둘기집의 원리 : n+1개의 물건은 n개의 상자에 넣을 때 적어도 한 상자에는 두 개 이상의 물건이 들어있음.
 * */

public class Rabbit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
