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
		replyTest();
	}
	
	private void replyTest() {
		//부모글 번호, 답글 제목, 답글 작성자, 답글 내용
		QnaVO qnaVO = new QnaVO();
		qnaVO.setNum(3L);
		qnaVO.setTitle("reply Title");
		qnaVO.setWriter("reply Writer");
		qnaVO.setContents("reply Contents");
		
		//0. 부모글 번호로 부모글을 SELECT
		QnaVO parent = qnaRepository.findById(qnaVO.getNum()).get();
		System.out.println(parent.getTitle());
		System.out.println(parent.getRef());
		System.out.println(parent.getStep());
		System.out.println(parent.getDepth());
		
		//1. 부모의 ref와 같고, step이 부모의 step보다 큰 것들은 step+1(SELECT, 값을 변경 후 save)
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(parent.getRef(), parent.getStep());
		
		for(QnaVO qnVO: ar) {
			System.out.println(qnVO.getTitle());
			System.out.println(qnVO.getRef());
			System.out.println(qnVO.getStep());
			qnVO.setStep(qnVO.getStep()+1);
		}
		
		qnaRepository.saveAll(ar);
		
		//2. 답글을 insert
		//ref는 부모의 ref, step은 부모의 step+1, depth는 부모의 depth+1
		qnaVO.setNum(0);
		qnaVO.setRef(parent.getRef());
		qnaVO.setStep(parent.getStep()+1);
		qnaVO.setDepth(parent.getDepth()+1);
		
		qnaRepository.save(qnaVO); //부모글이 업데이트 진행
		
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
		
		qnaRepository.save(qnaVO); //pk가지고 SELEC, 결과가 없으면 INSERT, 없으면 refupdate
	}

}
