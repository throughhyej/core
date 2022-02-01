package com.core.order;

import com.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
