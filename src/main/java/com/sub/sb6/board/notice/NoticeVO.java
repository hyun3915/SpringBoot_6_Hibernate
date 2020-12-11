package com.sub.sb6.board.notice;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
//연결 테이블의 테이블 이름 설정
@Entity
@Table(name = "notice")
public class NoticeVO {
	
	@Id //primary key
	private long num;
	
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String contents;
	@Column
	private Date regDate;
	@Column
	private long hit;

}
