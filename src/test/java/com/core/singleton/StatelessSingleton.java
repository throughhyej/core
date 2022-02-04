package com.core.singleton;

public class StatelessSingleton {
    /* 싱글톤이 적용된 객체를 다룰 때는, stateless 상태 즉 무상태로 설계되어야 한다.
     * 값을 변경할 수 있는 필드가 존재하면 안 된다. 가급적 읽기 기능만 제공해야 한다.
     *
     * 다음은 StatefulSingleton.class를 stateless 즉 무상태로 수정한 클래스다.
     **/

    public int order(String name, int price) {
        System.out.println("## name : " + name + ", price : " + price);
        return price;
    }
}
