/**
 * 파일명: MainTest.java <br>
 * 설 명:  <br>
 * 작성자: user <br>
 * 생성일: 2026-04-27 <br>
 */
package com.pcwk.menu;

/**
 * 
 */
public class MainTest extends Adminpage {
	
	private static final String adminPw = "9999"; 

	// 관리자 메뉴판 (9999 입력 시 진입)
	public void startAdminMode() {
		System.out.println("┌─────────────────────────────────────────┐");
		System.out.println("                [관리자 모드]                    ");
		System.out.println("└─────────────────────────────────────────┘");
		System.out.println(" 1. 가격수정 | 2. 메뉴삭제 | 3. 메뉴추가 | 4. 종료");
		System.out.print("선택 >> ");

		String menu = sc.nextLine(); 

		switch(menu) {
        case "1": 
        	doUpdate(); 
        	break; 
        case "2": 
        	doDelete(); 
        	break;
        case "3": 
        	doSave();   
        	break;
        case "4": 
        	return;
        default: 
        	System.out.println("잘못된 선택입니다.");
        }
		
		writeMenuData();
	}

	public static void main(String[] args) {
		MainTest main = new MainTest();

		System.out.print("주문하시겠습니까? > ");
		String input = sc.nextLine(); // 숫자로 받으면 에러 날 수 있으니 문자로 받기

		if (input.equals(adminPw)) {
			main.startAdminMode(); // 관리자 모드 진입!
		} else {			
			System.out.println("일반 주문 모드입니다.");
		}

		System.out.println("프로그램을 종료");
	}
}
