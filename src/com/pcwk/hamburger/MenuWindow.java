package com.pcwk.hamburger;

import java.util.List;
import java.util.Scanner;

import com.pcwk.cmn.PLogger;
import com.pcwk.menu.domain.MenuVO;


public class MenuWindow implements PLogger {

	Scanner sc = new Scanner(System.in);

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
		System.out.println("  ※ 세트 선택 시 +2,000원   [C] 장바구니   [P] 결제");
	}

	// 사용자 입력
	public String getMenuChoice(List<MenuVO> menuList) {
		while (true) {
			System.out.print("번호 입력 (1~5 또는 C/P) > ");
			String input = sc.nextLine().trim();
			String upper = input.toUpperCase();

			if (upper.equals("C") || upper.equals("P")) {
				return upper;
			}

			try {
				int num = Integer.parseInt(input);
				if (num >= 1 && num <= menuList.size()) {
					return menuList.get(num - 1).getFoodId();
				}
			} catch (NumberFormatException e) {
				log.debug("NumberFormatException {}",e);
			}

			System.out.println("1~" + menuList.size() + " 또는 C/P를 입력하세요.");
		}
	}

	// 단품 : false, 세트 : true 선택
	public boolean getSetOrSingleChoice() {
		while (true) {
			System.out.print("  단품(1) / 세트(2, +2,000원) > ");
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
}
