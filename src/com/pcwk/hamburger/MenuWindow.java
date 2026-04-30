package com.pcwk.hamburger;

import java.util.List;
import java.util.Scanner;

import com.pcwk.cmn.PLogger;
import com.pcwk.menu.domain.MenuVO;


public class MenuWindow implements PLogger {

	private Scanner sc;

	public MenuWindow(Scanner sc) {
		this.sc = sc;
	}

	public void show(List<MenuVO> menuList) {

		
		System.out.println("       ============================================================");
		System.out.println("                         🍔  BURGER KIOSK  🍔                      ");
		System.out.println("            ██████╗ ██╗   ██╗██████╗  ██████╗ ███████╗██████╗     ");
		System.out.println("            ██╔══██╗██║   ██║██╔══██╗██╔════╝ ██╔════╝██╔══██╗    ");
		System.out.println("            ██████╔╝██║   ██║██████╔╝██║  ███╗█████╗  ██████╔╝    ");
		System.out.println("            ██╔══██╗██║   ██║██╔══██╗██║   ██║██╔══╝  ██╔══██╗   ");
		System.out.println("            ██████╔╝╚██████╔╝██║  ██║╚██████╔╝███████╗██║  ██║   ");
		System.out.println("            ╚═════╝  ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝      ");
		System.out.println("  ┌────────────────────────────────────────────────────────────────────┐");
		System.out.println("  │┌──────────────────────────────────────────────────────────────────┐│");		
		// 메뉴 출력 
		for (MenuVO menu : menuList) {
			System.out.printf("           [%s] %15s %,15d원%n",
					menu.getFoodId(),   
					menu.getName(),     
					menu.getPrice());   
		}
		System.out.println("  │└──────────────────────────────────────────────────────────────────┘│");
		System.out.println("  └────────────────────────────────────────────────────────────────────┘");
		System.out.println("  ※ 세트 선택 시 +2,000원   [C] 장바구니   [D] 장바구니 삭제   [P] 결제");
	}

	
	// 사용자 입력
	public String getMenuChoice(List<MenuVO> menuList) {
		while (true) {
			System.out.print("메뉴 번호 입력 또는 (C/P/D) > ");
			String input = sc.nextLine().trim();
			String upper = input.toUpperCase();

			if (upper.equals("C") || upper.equals("P") || upper.equals("D")) {
				return upper;
			}

			try {
				int num = Integer.parseInt(input);
				if (num >= 1 && num <= menuList.size()) {
					return menuList.get(num - 1).getFoodId();
				}
			} catch (NumberFormatException e) {
				//log.debug("NumberFormatException {}",e);
			}

			System.out.println("1~" + menuList.size() + " 또는 C/P를 입력하세요.");
		}
	}

	// 단품 : false, 세트 : true 선택
	public boolean getSetOrSingleChoice() {
		while (true) {
			System.out.print("  단품(1) / 세트(2) +2,000원) > ");
			String input = sc.nextLine().trim();
			if (input.equals("1")) {
				return false;
			}
			if (input.equals("2")) {
				return true;
			}
			System.out.println("1 또는 2를 입력하세요.");
		}
	}

	public int getQuantity() {
		while (true) {
			System.out.print("수량 입력 > ");
			String input = sc.nextLine().trim();
			try {
				int qty = Integer.parseInt(input);
				if (qty > 0) {
					return qty;
				}
			} catch (NumberFormatException e) {
			
			}
			System.out.println("1 이상의 숫자를 입력하세요.");
		}
	}

	// 매장식사/포장
	public String getDiningType() {
		System.out.println("주문 방식을 선택해 주세요.");
		while (true) {
			System.out.print("매장식사(1) / 포장(2) > ");
			String input = sc.nextLine().trim();
			if (input.equals("1")) {
				return "매장식사";
			}
			if (input.equals("2")) {
				return "포장";
			}
			System.out.println("1 또는 2를 입력하세요.");
		}
	}
	
	// 삭제 항목 
	public CartItem getRemoveChoice(List<CartItem> itemList) {
	    System.out.print("  삭제할 항목 번호를 입력하세요: ");
	    String input = sc.nextLine().trim();
	    try {
	        int index = Integer.parseInt(input) - 1;
	        if (index >= 0 && index < itemList.size()) {
	            return itemList.get(index);
	        }
	    } catch (NumberFormatException e) {}
	    System.out.println("  잘못된 입력입니다.");
	    return null;
	}
	
	public void waitForEnter() {
		System.out.println("[돌아가기 ENTER]");
	    sc.nextLine();
	}
	public void waitForEnterReturn() {
		System.out.println("[종료하기 ENTER]");
	    sc.nextLine();
	}
}
