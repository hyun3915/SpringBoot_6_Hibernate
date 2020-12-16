package com.sub.sb6.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noticeList")
	public ModelAndView noticeList(Pageable pageable) throws Exception{
		ModelAndView mv = new ModelAndView();
		
//		Pageable pageable = PageRequest.of(0, 10);
		
//		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "num");
		Page<NoticeVO> page = noticeService.noticeList(pageable);
		mv.setViewName("board/boardList");
		mv.addObject("page", page);
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public String noticeSelect(Model model) {
		NoticeVO noticeVO = noticeService.noticeSelect();
		model.addAttribute("vo", noticeVO);
		
		return "board/boardSelect";
	}

}
