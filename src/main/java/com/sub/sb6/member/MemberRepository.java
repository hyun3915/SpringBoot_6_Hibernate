package com.sub.sb6.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO, String>{
	
	//select * from member where id=? and pw=?
	public MemberVO findByIdAndPw(String id, String pw);

}
