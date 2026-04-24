/**
 * 파일명: Main.java <br>
 * 설 명:  <br>
 * 작성자: user <br>
 * 생성일: 2026-04-24 <br>
 */
package com.pcwk.menu;

import com.pcwk.cmn.MenuDTO;
import com.pcwk.menu.dao.MenuDao;
import com.pcwk.menu.domain.MenuVO;

/**
 * 
 */
public class MenuMain {
	
	MenuDao dao;
	MenuVO menuVO01;
	MenuDTO dto;

	public void Menumain() {
		dao = new MenuDao();
		menuVO01 = new MenuVO("4","버거아니야","0");
		
		dto = new MenuDTO();
		
	}
	
	public void doUpdate() {
		menuVO01 = new MenuVO("4","버거아니야","0");
		int flag = dao.doUpdate(menuVO01);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuDao main = new MenuDao();
		
	//	main.doUpdate();

	}

}
