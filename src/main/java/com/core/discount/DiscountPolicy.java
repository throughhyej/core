package com.core.discount;

import com.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
