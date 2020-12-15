package com.sub.sb6.board.qna;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sub.sb6.board.BoardVO;

import lombok.Data;

@Data
@Entity
@Table(name = "qna")
public class QnaVO extends BoardVO{
	
	private long ref;
	private long step;
	private long depth;
	
	@OneToMany(mappedBy = "qnaVO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<QnaFileVO> qnaFileVOs;

}
