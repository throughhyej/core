package com.core.orders;

import com.core.members.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
