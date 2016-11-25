package edu.mum.farmer.app.service;

import java.util.List;

import edu.mum.farmer.app.domain.Authority;
import edu.mum.farmer.app.domain.Member;

public interface MemberService {
	public Authority getAuthorityForUser(String name);

	public void deleteMember(Long id);

	public List<Member> getAll();

	public Member getUser(Long id);

	public void save(Member member);

	public List<Member> findByUserName(String username);
}
