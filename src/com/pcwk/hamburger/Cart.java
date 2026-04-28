package com.pcwk.hamburger;

import java.util.ArrayList;
import java.util.List;

import com.pcwk.menu.domain.MenuVO;

public class Cart {

	private List<CartItem> itemList = new ArrayList<CartItem>();

	// 동일 메뉴 + 동일 주문유형(세트/단품)이면 수량 증가, 아니면 신규 추가
	public void addItem(MenuVO menu, int ea, boolean isSet) {
		for (int i = 0; i < itemList.size(); i++) {
			CartItem item = itemList.get(i);
			if (item.getMenu().getFoodId().equals(menu.getFoodId()) && item.isSet() == isSet) {
				item.setEa(item.getEa() + ea);
				return;
			}
		}
		itemList.add(new CartItem(menu, ea, isSet));
	}

	public void removeItem(String foodId) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getMenu().getFoodId().equals(foodId)) {
				itemList.remove(i);
				break;
			}
		}
	}

	public int getTotal() {
		int total = 0;
		for (int i = 0; i < itemList.size(); i++) {
			total += itemList.get(i).getSubTotal();
		}
		return total;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void clearCart() {
		itemList.clear();
	}

	public boolean isEmpty() {
		return itemList.isEmpty();
	}
}
