package com.sub.sb6.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("noticeSelect")
	public String noticeSelect(Model model) {
		NoticeVO noticeVO = noticeService.noticeSelect();
		model.addAttribute("vo", noticeVO);
		
		return "board/boardSelect";
	}

}
