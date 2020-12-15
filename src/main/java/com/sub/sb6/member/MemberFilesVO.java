package com.sub.sb6.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="memberFiles")
public class MemberFilesVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long num;
	
	@Column
	private String fileName;
	
	private String oriName;
	
	@OneToOne
	@JoinColumn(name = "id", nullable = true)
	private MemberVO memberVO;
}
