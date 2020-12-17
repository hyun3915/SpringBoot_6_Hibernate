package com.sub.sb6.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public Page<NoticeVO> noticeList(Pageable pageable) throws Exception{
		return noticeRepository.findByNumGreaterThanAndTitleContainingOrderByNumDesc(0L,"", pageable);
	}
	
	public NoticeVO noticeSelect() {
		return noticeRepository.findById(4L).get();
	}

}
