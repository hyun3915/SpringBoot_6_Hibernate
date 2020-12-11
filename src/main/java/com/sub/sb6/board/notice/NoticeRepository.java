package com.sub.sb6.board.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long>{
	
	//상속받은 메서드
	//long count()	: 데이터의 총 갯수 select count() from notice
	
	//select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long n);
	
	//select * from notice where num between 100 and 105
	public List<NoticeVO> findByNumBetween(long n, long m);
	
	//select * from notice where title like '%?%' order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);

}
