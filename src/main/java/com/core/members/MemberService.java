package com.core.members;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
