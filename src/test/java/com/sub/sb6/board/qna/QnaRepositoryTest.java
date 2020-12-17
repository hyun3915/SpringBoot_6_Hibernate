package com.sub.sb6.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
		listTest();
	}
	
	
	
	private void listTest() {
		//일정한 갯수, 정렬방식
		//select * from qna order by num desc limit 0, 10;
		//Pageable
		//PageRequest.of(페이지번호, 페이지당 보여줄 갯수);
		//페이지 번호는 0부터 시작
		Pageable pageable = PageRequest.of(0, 10);
		Page<QnaVO> page = qnaRepository.findAll(pageable);
		
		System.out.println("Size :"+ page.getSize()); //perpage의 갯수(페이지당 보여줄 갯수)
		System.out.println("VO : "+page.getContent().size()); //VO의 갯수
		System.out.println("Total Element :"+ page.getTotalElements()); //테이블의 모든 row갯수, count()와 같음
		System.out.println("Total Pages : "+page.getTotalPages()); //전체 페이지 갯수
		System.out.println("Next : "+page.hasNext()); //다음페이지가 있으면 true, 없으면 false
		System.out.println("Precious : "+page.hasPrevious()); //이전페이지가 있으면 true, 없으면 false
		System.out.println("Num : "+page.getNumber()); //number 시작번호
		System.out.println("NoE : "+page.getNumberOfElements());
		System.out.println("HContent : "+page.hasContent()); //List<VO> 있으면 true, 없으면 false
		System.out.println("First : "+page.isFirst()); //현재 페이지가 처음
		System.out.println("Last : "+page.isLast()); //현재 페이지가 마지막
		
		List<QnaVO> ar = page.getContent();
		
		for(QnaVO qnaVO: ar) {
			System.out.println(qnaVO.getNum());
			System.out.println(qnaVO.getTitle());
			System.out.println("--------------------------");
		}
		
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
	
	private void saveTest2() {
		List<QnaVO> ar = new ArrayList<>();
		for(int i=0; i<100;i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("title"+i);
			qnaVO.setWriter("writer"+i);
			qnaVO.setContents("contents"+1);
			ar.add(qnaVO);
		}
		
		qnaRepository.saveAll(ar);
		System.out.println("finish");
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
