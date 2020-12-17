package com.sub.sb6.util;

import org.springframework.data.domain.Page;

import com.sub.sb6.board.BoardVO;
import com.sub.sb6.board.notice.NoticeVO;

import lombok.Data;

@Data
public class Pager {
	
	private Integer page;
	private Integer size;
	
	private String kind;
	private String search;
	
	private int startNum;
	private int lastNum;
	
	private boolean previous;
	private boolean isNext;
	
	
	
	public Integer getPage() {
		if(this.page == null) {
			this.page=0;
		}
		return page;
	}
	
	public void setPage(Integer page) {
		if(page==null) {
			this.page=0;
		}
		this.page = page;
	}
	
	public Integer getSize() {
		if(this.size==null || this.size==0) {
			this.size=10;
		}
		return size;
	}
	
	public void setSize(Integer size) {
		if(this.size==null || this.size==0) {
			this.size=10;
		}
		this.size = size;
	}
	
	public void makePage(Page<NoticeVO> page) { //Page
		//1. totalCount로 totalPage계산
		
		//2. totalPage로 totalBlock계산
		int totalblock = page.getTotalPages()/5; //perBlock=5
		if(page.getTotalPages()%5 !=0) {
			totalblock++;
		}
		
		//3. 현재 페이지 번호로 현재 블럭번호 계산
		int curBlock = (page.getNumber()+1)/5;
		if((page.getNumber()+1)%5 !=0) {
			curBlock++;
		}
		
		//4. 현재 블럭 번호로 startNum, lastNum 구하기
		startNum = (curBlock-1)*5+1;
		lastNum = curBlock*5;
		
		//5. 현재 블럭이 마지막 블럭과 같다면 lastNum 조정
		if(curBlock == totalblock) {
			lastNum=page.getTotalPages();
			isNext=false;
		}else {
			isNext=true;
		}
		
		//6. 이전 블럭의 유무
		if(curBlock>1) {
			previous=true;
		}
		
	}
	

}
