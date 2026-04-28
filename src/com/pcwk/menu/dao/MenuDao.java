package com.pcwk.menu.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	 * 신우
	 * @param path
	 */
//	public MenuDao(String path) {
//		readMenuData(path);
//	}
	
	public int writeMenuData(String path) {
		int count = 0;
		String divStr = ",";//데이터 구분자
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			//hearder생성 1회
			String header = "메뉴ID,메뉴,가격";
			bw.write(header);
			bw.newLine();
			log.debug("header: {}", header);
			for(MenuVO vo:menus) {
				count++;
				log.debug("{}, {}", count, vo.toCsv());
				bw.write(vo.toCsv());
				bw.newLine();				
			}			
			
		} catch (IOException e) {
			log.debug("IOException: {}", e);			
		}
		
		return count;
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
					MenuVO menuLog = new MenuVO(strArray[0],strArray[1],Integer.parseInt(strArray[2]));
										
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
		System.out.println("doUpdate");
		
		Iterator<MenuVO> iter = menus.iterator();
		
		while(iter.hasNext()) {
			MenuVO vo = iter.next();
			if(vo.getFoodId().equals(param.getFoodId())) {
				vo.setPrice(param.getPrice());
				flag = 1;
				return flag;
			} 
		}		
		
		log.debug("등록여부: {}",flag);
		
		return flag;
	
	}

	@Override
	public int doDelete(MenuVO param) {
		int flag = 0;
		
		if(isExistsMenu(param) == false) {
			flag = 2;			
			return flag;
		}
		
		//삭제
		Iterator<MenuVO> iter = menus.iterator();
		while(iter.hasNext()) {
			MenuVO vo = iter.next();
			
			if(vo.getFoodId().equals(param.getFoodId())) {
				//삭제
				System.out.println("왔니");
				iter.remove();
				flag = 1;
				break;
			}		
		
		}		
		return flag;
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

	/**
	 * 신우 메서드 겹침
	 */
//	public int writeMenuData(String path) {
//		int count = 0;
//
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
//			bw.write("메뉴ID,메뉴,가격");
//			bw.newLine();
//			for (int i = 0; i < menus.size(); i++) {
//				bw.write(menus.get(i).toCsv());
//				bw.newLine();
//				count++;
//			}
//
//		} catch (IOException e) {
//
//			System.out.println("[오류] 파일 쓰기 실패: " + e.getMessage());
//		}
//		return count;
//	}
	
	public List<MenuVO> getMenuList() {
		return menus;
	}
	
	public MenuVO findById(String foodId) {
		for (int i = 0; i < menus.size(); i++) {
			if (menus.get(i).getFoodId().equals(foodId)) {
				return menus.get(i);
			}
		}
		return null;
	}
	
	public boolean updatePrice(String foodId, int newPrice) {
		for (int i = 0; i < menus.size(); i++) {
			if (menus.get(i).getFoodId().equals(foodId)) {
				menus.get(i).setPrice(newPrice);
				return true;
			}
		}
		return false;
	}

		
	
}
