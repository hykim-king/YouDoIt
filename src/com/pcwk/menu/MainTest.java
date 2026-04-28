/**
 * 파일명: MainTest.java <br>
 * 설 명:  <br>
 * 작성자: user <br>
 * 생성일: 2026-04-27 <br>
 */
package com.pcwk.menu;

import com.pcwk.hamburger.Kiosk;

/**
 * 
 */
public class MainTest extends Adminpage {
	
	//private static final String adminPw = "9999"; 

	// 관리자 메뉴판 (9999 입력 시 진입)
	

	public static void main(String[] args) {
		MainTest main = new MainTest();
		
//		System.out.print("주문하시겠습니까? > ");
//		String input = sc.nextLine(); // 숫자로 받으면 에러 날 수 있으니 문자로 받기

		
		Kiosk.kioskShow();

		System.out.println("프로그램을 종료");
	}
}
