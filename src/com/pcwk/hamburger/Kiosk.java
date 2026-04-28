package com.pcwk.hamburger;

import java.util.List;
import java.util.Scanner;

import com.pcwk.menu.Adminpage;
import com.pcwk.menu.dao.MenuDao;
import com.pcwk.menu.domain.MenuVO;


public class Kiosk {

	private static final String adminPw = "9999";
	
	public static void kioskShow() {

		Scanner sc = new Scanner(System.in);

		MenuService menuService = new MenuService(new MenuDao());
		StartWindow startWindow = new StartWindow();
		MenuWindow menuWindow = new MenuWindow();
		PaymentWindow paymentWindow = new PaymentWindow();
		Cart cart = new Cart();
		Adminpage admin = new Adminpage();

		// 시작 화면 출력
		startWindow.show();

		String start = sc.nextLine().trim().toUpperCase();
		while (!start.equals("Y")) {
			if(start.equals(adminPw)) {
			admin.startAdminMode(); // 관리자 모드 진입
			startWindow.show();
			}
			start = sc.nextLine().trim().toUpperCase();
		}

		// 매장식사 / 포Y장 선택
		System.out.println("주문 방식을 선택해 주세요.");
		String diningType = menuWindow.getDiningType();

		// 메뉴 파일 로드
		List<MenuVO> menuList = menuService.getAllMenus();
		if (menuList.isEmpty()) {
			System.out.println("메뉴를 불러올 수 없습니다.");
			return;
		}
		// 메인 주문 루프
		while (true) {
		    menuWindow.show(menuList);
		    String choice = menuWindow.getMenuChoice(menuList);

		    switch (choice) {
		        case "C":
		            CartDisplay.show(cart);
		            break;

		        case "P":
		            if (cart.isEmpty()) {
		                System.out.println("  장바구니가 비어 있습니다.");
		            } else {
		                CartDisplay.show(cart);
		                System.out.println("  결제 방법을 선택해 주세요.");
		                String paymentType = paymentWindow.getPaymentType();
		                PrintReciept.print(cart, diningType, paymentType);
		                cart.clearCart();
		                System.out.println("  이용해 주셔서 감사합니다!");
		                return; 
		            }
		            break;

		        default:
		            MenuVO selected = menuService.findById(choice);
		            if (selected != null) {
		                boolean isSet = menuWindow.getSetOrSingleChoice();
		                int qty = menuWindow.getQuantity();
		                cart.addItem(selected, qty, isSet);

		                String typeName = isSet ? "세트" : "단품";
		                System.out.println("  [" + selected.getName() + "(" + typeName + ")] " + qty + "개가 장바구니에 추가되었습니다.");
		            }
		            break;
		    }
		}
	}
	
}
