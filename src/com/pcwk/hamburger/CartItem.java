package com.pcwk.hamburger;

import com.pcwk.menu.domain.MenuVO;

public class CartItem {

	private static final int SET_PRICE = 2000; // 세트 추가 금액

	private MenuVO menu;
	private int ea;
	private boolean isSet; // true: 세트
							// false: 단품

	public CartItem(MenuVO menu, int ea, boolean isSet) {
		this.menu = menu;
		this.ea = ea;
		this.isSet = isSet;
	}

	public MenuVO getMenu() {
		return menu;
	}
	
	

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

	public boolean isSet() {
		return isSet;
	}

	public String getOrderTypeName() {
		if (isSet) {
			return "세트";
		}
		return "단품";
	}

	// 세트면 2,000원 추가
	public int getUnitPrice() {
		if (isSet) {
			return menu.getPrice() + SET_PRICE;
		}
		return menu.getPrice();
	}

	public int getSubTotal() {
		return getUnitPrice() * ea;
	}
}
