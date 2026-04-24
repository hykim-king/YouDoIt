package com.pcwk.menu.domain;

import com.pcwk.cmn.MenuDTO;

public class MenuVO extends MenuDTO {
	
	private String foodId; //메뉴ID
	private String food;   //메뉴명
	private String price;  //가격
	
	//생성자
	public MenuVO() {
		
	}
	
	public String toPrint() {
		//회원ID,이름,비밀번호,이메일,가입일,권한
		return String.format("%-3s,%-15s,%-7s", foodId
				, food
				, price);
	}
	
	public String toCsv() {
		//회원ID,이름,비밀번호,이메일,가입일,권한
				return String.format("%-3s,%-10s,%5s", foodId
						, food
						, price);
	}

	public MenuVO(String foodId, String food, String price) {
		super();
		this.foodId = foodId;
		this.food = food;
		this.price = price;
	}

	/**
	 * @return the foodId
	 */
	public String getFoodId() {
		return foodId;
	}

	/**
	 * @param foodId the foodId to set
	 */
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	/**
	 * @return the food
	 */
	public String getFood() {
		return food;
	}

	/**
	 * @param food the food to set
	 */
	public void setFood(String food) {
		this.food = food;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return foodId+" "+food+" "+price;
		//return "MenuVO [foodId=" + foodId + ", food=" + food + ", price=" + price + ", toString()=" + super.toString()
				//+ "]";
	}

}


