package com.sub.sb6.board.notice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="noticeFiles")
public class NoticeFileVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fnum;
	private String fileName;
	private String oriName;
	
	@ManyToOne
	@JoinColumn(name = "num")
	private NoticeVO noticeVO;
	
	
}
