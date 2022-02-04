package com.core.singleton;

public class SingletonService {

    // 구동 시, 객체 생성: static 키워드로 1개만 사용
    public static final SingletonService instance = new SingletonService();

    // 이 메서드 만으로 생성된 인스턴스 하나를 사용
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        // private 키워드로 생성자 처리
        // 외부에서 new 키워드로 객체 생성 불가
    }

    public void logic() {
        System.out.println("싱글톤 테스트");
    }

}
