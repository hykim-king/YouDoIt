/**
 * 파일명: Adminpage.java <br>
 * 설 명:  <br>
 * 작성자: user <br>
 * 생성일: 2026-04-27 <br>
 */
package com.pcwk.menu;

import java.util.Scanner;

import com.pcwk.cmn.MenuDTO;
import com.pcwk.hamburger.Kiosk;
import com.pcwk.menu.dao.MenuDao;
import com.pcwk.menu.domain.MenuVO;

/**
 * 
 */
public class Adminpage {
	
	MenuDao dao;
	MenuVO menuVO01;
	MenuDTO dto;
	static Scanner sc = new Scanner(System.in);
	//String id = sc.nextLine();		
	static String choice = null;
	static int priceChange = 0;


	public Adminpage() {
		dao = new MenuDao();
		menuVO01 = new MenuVO();
		dto = new MenuDTO();		
	}
	
	public void startAdminMode() {
		System.out.println("┌─────────────────────────────────────────┐");
		System.out.println("                [관리자 모드]                    ");
		System.out.println("└─────────────────────────────────────────┘");
		System.out.println(" 1. 가격수정 | 2. 메뉴삭제 | 3. 메뉴추가 | 4. 종료");
		System.out.print("선택 >> ");

		String menu = sc.nextLine(); 

		switch(menu) {
        case "1": 
        	doUpdate(); 
        	break; 
        case "2": 
        	doDelete(); 
        	break;
        case "3": 
        	doSave();   
        	break;
        case "4": 
        	Kiosk.kioskShow();
        	break;
        default: 
        	System.out.println("잘못된 선택입니다.");
        }
		
		writeMenuData();
	}
	
	public void writeMenuData() {
		int menuCount = dao.writeMenuData(MenuDao.MENU_LOG_DATA);
	}
	
	public void doUpdate() {
		System.out.print("변경할 메뉴ID >");
		
		while(true) {           
	        choice = sc.nextLine(); 
	        
	        boolean isCorrect = false;
	        switch (choice){
	            case "01": case "02": case "03": case "04": case "05":
	            case "06": case "07": case "08": case "09":
	                isCorrect = true;
	                break; 
	            default:
	                System.out.println("잘못입력하셨습니다. 다시 입력하세요.");     
	        }
	        
	        if(isCorrect) {
	        	break; 
	        }
	    }
		
		System.out.print("변경할 가격 >");
		priceChange = sc.nextInt();
		menuVO01.setFoodId(choice);
		menuVO01.setPrice(priceChange);
		int flag = dao.doUpdate(menuVO01);	
			
	}	
	
	
	public void doDelete() {
		//menuVO01 = new MenuVO("","추가버거","412");
		int flag = dao.doDelete(menuVO01);
		System.out.println("delete");
	
	}
	
	public void doSave() {
		menuVO01 = new MenuVO("07","빅맥",8000);
		int flag = dao.doSave(menuVO01);
	}

	

}
