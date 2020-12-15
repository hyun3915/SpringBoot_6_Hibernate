package com.sub.sb6.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaRepositoryTest {
	
	@Autowired
	private QnaRepository qnaRepository;

	@Test
	void test() {
		//save(insert) test
		//ref : 자기자신의 글번호(UPDATE)
		//step : 0
		//depth : 0
		this.saveTest();
	}
	
	private void saveTest() {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setTitle("qt1");
		qnaVO.setWriter("qw1");
		qnaVO.setContents("qc1");
		
		QnaFileVO qnaFileVO = new QnaFileVO();
		qnaFileVO.setFileName("fName1");
		qnaFileVO.setOriName("oName1");
		qnaFileVO.setQnaVO(qnaVO);
		
		List<QnaFileVO> ar = new ArrayList<>();
		ar.add(qnaFileVO);
		
		qnaVO.setQnaFileVOs(ar);
		
		qnaVO = qnaRepository.save(qnaVO); //insert
		
		qnaVO.setRef(qnaVO.getNum());
		
		qnaRepository.save(qnaVO); //refupdate
	}

}
