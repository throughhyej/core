package com.core.order;

import com.core.discount.DiscountPolicy;
import com.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // SOLID의 DIP 위반
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // SOLID의 DIP 준수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        int discountPrice = discountPolicy.discount(memberRepository.findById(memberId), itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
