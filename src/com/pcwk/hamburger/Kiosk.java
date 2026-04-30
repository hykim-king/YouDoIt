package com.pcwk.hamburger;

import java.util.List;
import java.util.Scanner;

import com.pcwk.menu.Adminpage;
import com.pcwk.menu.dao.MenuDao;
import com.pcwk.menu.domain.MenuVO;

public class Kiosk {
	private final MenuService menuService;
    private final StartWindow startWindow;
    private final MenuWindow menuWindow;
    private final PaymentWindow paymentWindow;
    private final Cart cart;
    private final Adminpage admin;
	
    private static final String adminPw = "9999";

	public Kiosk(MenuService menuService, StartWindow startWindow, MenuWindow menuWindow, PaymentWindow paymentWindow,
			Cart cart, Adminpage admin) {
		super();
		this.menuService = menuService;
		this.startWindow = startWindow;
		this.menuWindow = menuWindow;
		this.paymentWindow = paymentWindow;
		this.cart = cart;
		this.admin = admin;
	}

	public  void run() {
		outer:
		while (true) {

			List<MenuVO> menuList = menuService.getAllMenus();
			if (menuList.isEmpty()) {
				System.out.println("메뉴를 불러올 수 없습니다.");
				return;
			}
			// 시작 화면 출력 — Y: 시작, 9999: 관리자 모드
			startWindow.show();
			while (true) {
				String input = startWindow.waitForStart();
				if (!input.equals("9999")) {
					break;
				}
				if (adminPw.equals(input)) {
					admin.startAdminMode();
					//화면 밀어내기 
					for (int i = 0; i < 25; i++) {
						System.out.println();
						}
					startWindow.show();
				}
			}
			// 매장, 포장 선택
			String diningType = menuWindow.getDiningType();

			// 메인 주문 루프
			while (true) {
				menuWindow.show(menuList);
				String choice = menuWindow.getMenuChoice(menuList);

				switch (choice) {
				
				case "D":
				    if (cart.isEmpty()) {
				        System.out.println("  장바구니가 비어 있습니다.");
				    } else {
				        CartDisplay.show(cart);
				        CartItem target = menuWindow.getRemoveChoice(cart.getItemList());
				        if (target != null) {
				            cart.removeItem(target.getMenu().getFoodId(), target.isSet());
				            System.out.println("  [" + target.getMenu().getName() + "] 이 장바구니에서 삭제되었습니다.");
				            menuWindow.waitForEnter();
				        }
				    }
				    break;
				
				case "C":
					CartDisplay.show(cart);
					menuWindow.waitForEnter();
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
						menuWindow.waitForEnterReturn();
						//화면 밀어내기 
						for (int i = 0; i < 25 ; i++) {
							System.out.println();
						}
						continue outer;
					}
					break;

				default:
					MenuVO selected = menuService.findById(choice);
					System.out.printf("선택된 메뉴는 [%s] 입니다.%n", selected.toString());
					if (selected != null) {
						boolean isSet = menuWindow.getSetOrSingleChoice();
						int qty = menuWindow.getQuantity();
						cart.addItem(selected, qty, isSet);

						String typeName = isSet ? "세트" : "단품";
						System.out.println(
								"  [" + selected.getName() + "(" + typeName + ")] " + qty + "개가 장바구니에 추가되었습니다.");
						menuWindow.waitForEnter();
					}
					break;
				}
			}
		}
	}
}
