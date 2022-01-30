package com.core.members;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
