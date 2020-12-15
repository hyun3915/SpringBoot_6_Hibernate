package com.sub.sb6.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public NoticeVO noticeSelect() {
		return noticeRepository.findById(4L).get();
	}

}
