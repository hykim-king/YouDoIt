package com.pcwk.menu.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.cmn.MenuDTO;
import com.pcwk.cmn.PLogger;
import com.pcwk.cmn.WorkDiv;
import com.pcwk.menu.domain.MenuVO;

public class MenuDao implements WorkDiv<MenuVO>, PLogger {

	// 메뉴 파일
	public static final String MENU_LOG_DATA = "C:\\Users\\user\\git\\YouDoIt\\data\\menu.csv";

	// 메뉴 정보
	private ArrayList<MenuVO> menus = new ArrayList<MenuVO>();

	// 생정자
	public MenuDao() {
		
		getMenuDataRead(MenuDao.MENU_LOG_DATA);

	}

	/**
	 *메뉴 읽기 
	 */	
	public int getMenuDataRead(String path) {

		int count = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			int i = 1;
			String line = "";

			while ((line = br.readLine()) != null) {
				if (i == 1) {
					i++;
					log.debug("{}", line);
					continue;
				}
				count++;
				String[] strArray = line.split(",");
				if(strArray.length == 3) {
					MenuVO menuLog = new MenuVO(strArray[0],strArray[1],strArray[2]);
					
					
					log.debug("{}", menuLog.toCsv());
					this.menus.add(menuLog); ///////
				
				}
				
			}

		} catch (FileNotFoundException e) {
			log.debug("FileNotFoundException: {}", e);
		} catch (IOException e) {
			log.debug("IOException: {}", e);
		} catch (Exception e) {
			log.debug("Exception: {}", e);
		}
		return count;
	}
	
	public boolean isExistsMenu(MenuVO param) {
		boolean flag = false;
		
		for(MenuVO vo: menus)
			if(vo.getFoodId().equals(param.getFoodId())) {
				flag = true;
				break;
			}		
		return flag;
	}

	@Override
	public int doSave(MenuVO param) {
		int flag = 0;
		//기존 회원ID가 존재하면 : 1:성공, 0:실패, 2:기존회원 존재
		
		if(isExistsMenu(param)==true) {
			flag = 2;
			return flag;
		}	
		
		flag = this.menus.add(param)==true?1:0;
		log.debug("등록여부: {}", flag);
		
		return flag;
	
	}

	@Override
	public int doUpdate(MenuVO param) {
		int flag = 0;
		//기존 데이터 존재 확인
		//존재하면: 삭제, 변경
		if(isExistsMenu(param)==true) {
			flag = 2;
			return flag;
		}
		
		flag = this.menus.add(param)==true?1:0;
		log.debug("등록여부: {}",flag);
		
		return flag;
	
	}

	@Override
	public int doDelete(MenuVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MenuVO doSelectOne(MenuVO parem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuVO> doRetrieve(MenuDTO param) {
		// TODO Auto-generated method stub
		return null;
	}	

}
