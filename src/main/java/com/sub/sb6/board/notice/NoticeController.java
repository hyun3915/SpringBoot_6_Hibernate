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

import com.sub.sb6.util.Pager;

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
	public ModelAndView noticeList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		
//		Pageable pageable = PageRequest.of(0, 10);
//		Pageable pageable = PageRequest.of(curPage, perPage, 정렬방향, 정렬할 컬럼명)
		Pageable pageable = PageRequest.of(pager.getPage(), pager.getSize());
		Page<NoticeVO> page = noticeService.noticeList(pageable);
		
		pager.makePage(page);
		mv.setViewName("board/boardList");
		mv.addObject("page", page);
		mv.addObject("pager", pager);
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public String noticeSelect(Model model) {
		NoticeVO noticeVO = noticeService.noticeSelect();
		model.addAttribute("vo", noticeVO);
		
		return "board/boardSelect";
	}

}
