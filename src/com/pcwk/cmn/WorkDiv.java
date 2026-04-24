package com.pcwk.cmn;

import java.util.List;

public interface WorkDiv<T> {

	/**
	 * 저장
	 * @param param
	 * @return 1:성공, 0:실패
	 */
	int doSave(T param);
	
	/**
	 * 수정
	 * @param param
	 * @return 1:성공, 0:실패, 2:기존회원 존재
	 */
	int doUpdate(T param);
	
	/**
	 * 삭제
	 * @param param
	 * @return 0:실패, 2:회원없음, 3:성공
	 */
	int doDelete(T param);
	
	/**
	 * 단건조회
	 * @param parem
	 * @return DTO
	 */
	T doSelectOne(T parem);
	
	/**
	 * 목록조회
	 * @param param
	 * @return List<DTO>
	 */
	List<T> doRetrieve(MenuDTO param);


}
