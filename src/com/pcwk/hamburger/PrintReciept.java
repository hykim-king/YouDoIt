package com.pcwk.hamburger;

import java.util.List;

public class PrintReciept {

	private PrintReciept() {
	}

	// diningType : 매장식사 / 포장
	// paymentType : 카드 / 현금
	public static void print(Cart cart, String diningType, String paymentType) {
		List<CartItem> itemList = cart.getItemList();

		System.out.println("  ════════════════════════════════════════════════");
		System.out.println("                   [ 영  수  증 ]");
		System.out.println("  ────────────────────────────────────────────────");
		System.out.printf("  주문 유형 : %s%n", diningType);
		System.out.printf("  결제 방법 : %s%n", paymentType);
		System.out.println("  ────────────────────────────────────────────────");

		for (int i = 0; i < itemList.size(); i++) {
			CartItem item = itemList.get(i);
			// 메뉴명(세트/단품) * 수량 
			System.out.printf("  %-16s (%-3s)  x%2d  %,8d원%n", item.getMenu().getName(), item.getOrderTypeName(),
					item.getEa(), item.getSubTotal());
		}

		System.out.println("  ────────────────────────────────────────────────");
		System.out.printf("  합  계 :                        %,8d원%n", cart.getTotal());
		System.out.println("  ════════════════════════════════════════════════");
		System.out.println("        결제가 완료되었습니다. 감사합니다!");
		System.out.println("  ════════════════════════════════════════════════");
	}
}
