package com.sub.sb6.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberFilesRepository memberFilesRepository;
	
	private MemberVO memberVO;
	private MemberFilesVO memberFilesVO;
	
	@BeforeEach
	public void makeMember() throws Exception{
		int num=7;
		String data = "test"+num;
		
		this.memberVO = new MemberVO();
		this.memberVO.setId(data);
		this.memberVO.setPw(data);
		this.memberVO.setAge(num*10);
		this.memberVO.setName(data);
		this.memberVO.setEmail(data+"@email.com");
		
		this.memberFilesVO = new MemberFilesVO();
		this.memberFilesVO.setFileName("fileName"+num);
		this.memberFilesVO.setOriName("oriName"+num);
	}
	
	@Test
	void selectFileTest() throws Exception{
		MemberFilesVO memberFilesVO = memberFilesRepository.findById(4L).get();
		System.out.println(memberFilesVO.getFileName());
		System.out.println(memberFilesVO.getOriName());
		System.out.println(memberFilesVO.getMemberVO().getId());
		System.out.println(memberFilesVO.getMemberVO().getEmail());
	}
	
	//@Test
	void selectTest() throws Exception{
		MemberVO memberVO= memberRepository.findById("test1").get();
		System.out.println(memberVO.getId());
		System.out.println(memberVO.getEmail());
		System.out.println(memberVO.getMemberFilesVO().getFileName());
		System.out.println(memberVO.getMemberFilesVO().getOriName());
	}
	
	//@Test
	void saveTest() throws Exception{
		saveTest3();
	}
	
	private void saveTest3() throws Exception{
		memberVO.setMemberFilesVO(memberFilesVO);
		memberFilesVO.setMemberVO(memberVO);
		memberFilesRepository.save(memberFilesVO);
	}
	
	private void saveTest2() throws Exception{
		
		//id가 null이 들어가는 버그가 발생
		memberVO.setMemberFilesVO(memberFilesVO);
		
		//서로 값을 넣어주어야함
		memberFilesVO.setMemberVO(memberVO);
		memberRepository.save(memberVO);
	}
	
	private void saveTest1() throws Exception{
		
		//insert 내장 메서드인 save() 사용
		
		memberVO = memberRepository.save(memberVO);
		memberFilesRepository.save(memberFilesVO);
		
		assertNotNull(memberVO);
		
	}

}
