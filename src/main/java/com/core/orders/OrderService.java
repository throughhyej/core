package com.core.orders;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
