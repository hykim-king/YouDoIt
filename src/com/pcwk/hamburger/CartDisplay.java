package com.pcwk.hamburger;

import java.util.List;

public class CartDisplay {

	private CartDisplay() {
	}

	public static void show(Cart cart) {
		List<CartItem> itemList = cart.getItemList();

		System.out.println("  ┌──────────────────────────────────────────────┐");
		System.out.printf("    %-44s%n", "  현재 장바구니 내역");
		

		if (itemList.isEmpty()) {
			System.out.printf("    %-44s %n", "장바구니가 비어 있습니다.");
		} else {
			for (int i = 0; i < itemList.size(); i++) {
				CartItem item = itemList.get(i);
				String line = String.format("[%d] %s(%s)  x %d = %,d원", i+1, item.getMenu().getName(), item.getOrderTypeName(),
						item.getEa(), item.getSubTotal());
				System.out.printf("    %-44s%n", line);
			}
			
			System.out.printf("    %-44s%n", String.format("합계: %,d원", cart.getTotal()));
		}

		System.out.println("  └──────────────────────────────────────────────┘");
	}
}
