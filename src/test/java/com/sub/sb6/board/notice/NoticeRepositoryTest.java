package com.sub.sb6.board.notice;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sub.sb6.board.qna.QnaVO;

@SpringBootTest
class NoticeRepositoryTest {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	
	@Autowired
	private NoticeService NoticeService;
	
	@Test
	void test() throws Exception{
		jpqlTest4();
		jpqlTest1();
	}
	
	private void jpqlTest1() {
		NoticeVO noticeVO = noticeRepository.getSelect(100L);
		System.out.println(noticeVO.getTitle());
		System.out.println(noticeVO.getContents());
	}
	
	private void jpqlTest2() {
		String [] obj = noticeRepository.getSelect2(99L);
		for(Object o: obj) {
			System.out.println(o);
		}
	}
	
	private void jpqlTest3() {
		noticeRepository.setUpdate1("update title", 100L);
		System.out.println("finish");
	}
	
	private void jpqlTest4() {
		noticeRepository.setUpdate2("Mod Title", 100L);
		System.out.println("finish");
	}
	
	private void saveTest2() {
		List<NoticeVO> ar = new ArrayList<>();
		for(int i=0; i<100;i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO.setContents("contents"+1);
			ar.add(noticeVO);
		}
		
		noticeRepository.saveAll(ar);
		System.out.println("finish");
	}
	
	private void selectTest1() {
		//글번호로 notice, noticeFiles 조회
		//글제목, 작성자, filename, oriname
//		Optional<NoticeVO> opt = noticeRepository.findById(1L);
//		NoticeVO noticeVO = opt.get();
		
		NoticeVO noticeVO = NoticeService.noticeSelect();
		
		System.out.println(noticeVO.getTitle());
		System.out.println(noticeVO.getContents());
		
		for(NoticeFileVO noticeFileVO: noticeVO.getNoticeFileVOs()) {
			System.out.println("-------------------------------------");
			System.out.println(noticeFileVO.getFileName());
			System.out.println(noticeFileVO.getOriName());
		}
		System.out.println("---------finish--------");
	}
	
	private void saveTest1() {
		//insert notice, noticeFile
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("title1");
		noticeVO.setContents("contents1");
		noticeVO.setWriter("writer1");
		
		List<NoticeFileVO> ar = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("fileName1.jpg");
		noticeFileVO.setOriName("oriName1.jpg");
		noticeFileVO.setNoticeVO(noticeVO);
		
		ar.add(noticeFileVO);
		
		NoticeFileVO noticeFileVO2 = new NoticeFileVO();
		noticeFileVO2.setFileName("fileName2.jpg");
		noticeFileVO2.setOriName("oriName2.jpg");
		noticeFileVO2.setNoticeVO(noticeVO);
		
		ar.add(noticeFileVO2);
		
		noticeVO.setNoticeFileVOs(ar);
		
		noticeRepository.save(noticeVO);
		
		System.out.println("Finish");
 	}
	
}
