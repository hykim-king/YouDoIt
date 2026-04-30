/**
 * 파일명: Adminpage.java <br>
 * 설 명:  <br>
 * 작성자: user <br>
 * 생성일: 2026-04-27 <br>
 */
package com.pcwk.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pcwk.menu.dao.MenuDao;
import com.pcwk.menu.domain.MenuVO;

public class Adminpage {
	
	private MenuDao dao;
	private  Scanner sc;
	private String choice = null;

	public Adminpage(Scanner sc, MenuDao dao) {
		this.sc = sc;
		this.dao = dao;
	}
		
	public void startAdminMode() {
		dao.logMenus();
		while (true) {
			System.out.println("┌─────────────────────────────────────────┐");
			System.out.println("                [관리자 모드]                    ");
			System.out.println("└─────────────────────────────────────────┘");
			System.out.println(" 1. 가격수정 | 2. 메뉴삭제 | 3. 메뉴추가 | 4. 종료");
			System.out.print("선택 >> ");

			String menu = sc.nextLine();

			switch (menu) {
			case "1":
				doUpdate();
				writeMenuData();
				break;
			case "2":
				doDelete();
				writeMenuData();
				break;
			case "3":
				doSave();
				writeMenuData();
				break;
			case "4":
				return; 
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}
	
	public void writeMenuData() {
		int menuCount = dao.writeMenuData(MenuDao.MENU_LOG_DATA);
		System.out.println("총 " + menuCount + "개의 메뉴가 있습니다. ");
	}
	
	public void doUpdate() {
	    MenuVO param = new MenuVO(); 
		int priceChange;
		
		System.out.print("변경할 메뉴ID >");
		choice = sc.nextLine().trim(); 
	//	while(true) {           
	        
		
	        
//	        boolean isCorrect = false;
	        
	        
	        // 현재는 case: 를 하드 코딩해서, 추가하는 메뉴 아이디가 10이 되는 경우 선택할 수 없음
//	        switch (choice){
//	            case "01": case "02": case "03": case "04": case "05":
//	            case "06": case "07": case "08": case "09":
//	                isCorrect = true;
//	                break; 
//	            default:
//	                System.out.println("잘못입력하셨습니다. 다시 입력하세요.");     
//	        }  
//	        if(isCorrect) {
//	        	break; 
//	        }
//	    }
		System.out.print("변경할 가격 >");
		while (true) {
			String priceInput = sc.nextLine().trim();
			
			try {
				priceChange = Integer.parseInt(priceInput);
				
				break;
				
			} catch (NumberFormatException e) {
				
				System.out.println("숫자만 입력하세요.");
				System.out.print("변경할 가격 >");
			}
		}
		
		param.setFoodId(choice);
		param.setPrice(priceChange);
		int flag = dao.doUpdate(param);
		
		if (flag == 1) {
			System.out.println("수정 완료");
		} else {
			System.out.println("해당 메뉴ID가 존재하지 않습니다.");
		}
	}	
	
	
	public void doDelete() {
		System.out.print("삭제할 메뉴ID >");
		String foodId = sc.nextLine().trim();

		MenuVO param = new MenuVO();
		param.setFoodId(foodId);

		int flag = dao.doDelete(param);
		if (flag == 1) {
			System.out.println("삭제 완료");
		} else if (flag == 2) {
			System.out.println("해당 메뉴ID가 존재하지 않습니다.");
		} else {
			System.out.println("삭제 실패");
		}
	
	}
	
	public void doSave() {
		System.out.print("추가할 메뉴ID >");
		String foodId = sc.nextLine().trim();

		System.out.print("추가할 메뉴명 >");
		String foodName = sc.nextLine().trim();

		System.out.print("추가할 가격 >");
		int price = 0;
		while (true) {
			String priceInput = sc.nextLine().trim();
			try {
				price = Integer.parseInt(priceInput);
				break;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
				System.out.print("추가할 가격 >");
			}
		}

		MenuVO param = new MenuVO(foodId, foodName, price);
		int flag = dao.doSave(param);
		if (flag == 1) {
			System.out.println("추가 완료");
		} else if (flag == 2) {
			System.out.println("이미 존재하는 메뉴ID입니다.");
		} else {
			System.out.println("추가 실패");
		}
	}

	

}
