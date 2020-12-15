package com.sub.sb6.board.notice;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.sub.sb6.board.BoardVO;

import lombok.Data;

@Data
//연결 테이블의 테이블 이름 설정

//Entity Manager가 관리할 수 있도록 해당 VO를 등록
//Entity의 이름이 중복X
//Entity(name="Entity Name")
//name속성 생략하면 클래스명 자체가 이름으로 적용(NoticeVO)
@Entity

//VO와 DB의 어느 table과 연결정보
//name="연결할 테이블명"
@Table(name = "notice")
public class NoticeVO extends BoardVO{
	
	@OneToMany(mappedBy = "noticeVO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NoticeFileVO> noticeFileVOs;
	
	//fetch
	//LAZY	: SELECT Join을 하지 않고, 해당 리스트를 필요로 할 때 다시 조회
	//EAGER	: SELECT Join까지 진행
	
	
//	@Id //primary key
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long num;
//	
//	@Column
//	private String title;
//	@Column
//	private String writer;
//	@Lob
//	private String contents;
//	
//	@Column(updatable = false)
//	@CreationTimestamp
//	private Date regDate;
//	@Column
//	private long hit;

}
