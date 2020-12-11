package com.sub.sb6.member;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void memberDelete(MemberVO memberVO) throws Exception{
		memberRepository.deleteById(memberVO.getId());
	}
	
	public void save(MemberVO memberVO) throws Exception{
		memberRepository.save(memberVO);
	}
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception{
		Optional<MemberVO> vo = memberRepository.findById(memberVO.getId());
		
		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		
//		if(vo.isEmpty()) {
//			System.out.println("사용자 정보가 없습니다.");
//		}else {
//			System.out.println("ID가 있는 사용자입니다.");
//		}
		
//		if(vo.isPresent()) {
//			System.out.println("ID가 있는 사용자입니다.");
//			MemberVO memberVOCheck = vo.get();
//			
//			if(memberVO.getPw().equals(memberVOCheck.getPw())) {
//				memberVO = memberVOCheck;
//			}else {
//				memberVO = null;
//			}
//			
//		}else {
//			System.out.println("사용자 정보가 없습니다.");
//			memberVO = null;
//		}
		
		return memberVO;
	}
	

}
