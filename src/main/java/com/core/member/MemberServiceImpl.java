package com.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 인터페이스와 구현체 모두에 의존 > SOLID의 DIP 위반
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // SOLID의 DIP 준수
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /* ConfigurationTest.java 단위 테스트용 로직 */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
