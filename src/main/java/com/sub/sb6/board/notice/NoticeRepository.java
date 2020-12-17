package com.sub.sb6.board.notice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long>{
	
	//상속받은 메서드
	//long count()	: 데이터의 총 갯수 select count() from notice
	//from 뒤에 테이블명이 아니라 Entity의 이름을 명시하고 별칭 꼭 작성
	//파라미터값을 연결, 쿼리문 내에서 :변수명 변수명은 매개변수명과 일치시켜야함
	@Query("select N from NoticeVO N where N.num=:n ")
	public NoticeVO getSelect(Long n);
	
	//SELECT 특정 컬럼만 조회
	@Query("select N.title, N.contents from NoticeVO N where N.num=:n ")
	public String [] getSelect2(Long n);
	
	@Modifying
	@Transactional
	@Query("update NoticeVO N set title=?1 where N.num=?2")
	public void setUpdate1(String title, long n);
	
	//각 DB마다 고유의 쿼리
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update notice set title=:title where num=:n")
	public void setUpdate2(@Param("title")String title, @Param("n")long num);
	
	//select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long n);
	
	//select * from notice where num between 100 and 105
	public List<NoticeVO> findByNumBetween(long n, long m);
	
	//select * from notice where title like '%?%' order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);
	
	public Page<NoticeVO> findByNumGreaterThanAndTitleContainingOrderByNumDesc(Long num, String title,Pageable pageable);
}
