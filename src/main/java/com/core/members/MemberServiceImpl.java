package com.core.members;

public class MemberServiceImpl implements MemberService {

    // 인터페이스와 구현체 모두에 의존 > SOLID의 DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
