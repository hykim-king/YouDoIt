package com.pcwk.hamburger;

import java.util.List;

import com.pcwk.menu.dao.MenuDao;
import com.pcwk.menu.domain.MenuVO;


public class MenuService {

	private final MenuDao menuDao;

	public MenuService(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public List<MenuVO> getAllMenus() {
		List<MenuVO> list = menuDao.getMenuList();
		// System.out.printf("전체 메뉴 조회: %d건", list.size());
		return list;
	}

	public MenuVO findById(String foodId) {
		MenuVO result = menuDao.findById(foodId);
		if (result == null) {
			System.out.printf("메뉴 없음: foodId= %s", foodId);
		}
		return result;
	}

	public void saveMenuData() {
		menuDao.writeMenuData(MenuDao.MENU_LOG_DATA); //MENU_LOG_DATA
		System.out.println("메뉴 데이터 저장 완료");
	}
}
