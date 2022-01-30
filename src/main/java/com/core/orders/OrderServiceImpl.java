package com.core.orders;

import com.core.members.MemberRepository;
import com.core.members.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        int discountPrice = discountPolicy.discount(memberRepository.findById(memberId), itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
